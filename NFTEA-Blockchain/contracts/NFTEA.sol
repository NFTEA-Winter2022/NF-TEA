// contracts/MyNFT.sol
// SPDX-License-Identifier: MIT
//pragma experimental ABIEncoderV2;
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Royalty.sol";
import "@openzeppelin/contracts/utils/Counters.sol";

contract NFTea is ERC721Royalty {
    struct Media {
        uint256 id;
        uint256 publisherId;
        address publisherAddress;
        string  mediaType;
        string  URL;
        string  permaLink;
        string  thumbnailURL;
        string  timestamp;
        string  publisherUsername;
        string  caption;
        string  CollectionName;
    }

    using Counters for Counters.Counter;
    Counters.Counter private _contentCount;
    mapping (uint256 => uint256) public nftToMedia; // IG_ID -> Map_Counter -> Media_Struct
    mapping (uint256 => Media) public contents;
    mapping (address => uint256) public contentCountByUser;
    address payable private _productOwner;
    uint256 commission = 2;
    mapping (address => mapping(uint256 => uint256)) offers; // User_Address -> NFT_ID -> WEI

    constructor(address productOwner) ERC721("NF-TEA", "NFTEA") {
        // Set Product Owner address for marketplace markup
        _productOwner = payable(productOwner);
    }

    function mintNFTea (
        uint256 id,
        uint256 publisherId,
        string memory  mediaType,
        string memory  URL,
        string memory  permaLink,
        string memory  thumbnailURL,
        string memory  timestamp,
        string memory  publisherUsername,
        string memory  caption,
        string memory  CollectionName
    )
    public
    {
        require(nftToMedia[id] == 0, "An NFT with that content Id has already been minted.");

        _contentCount.increment();
        nftToMedia[id] = _contentCount.current();
        contentCountByUser[msg.sender] = contentCountByUser[msg.sender] + 1;

        contents[_contentCount.current()] = Media(
            id,
            publisherId,
            msg.sender,
            mediaType,
            URL,
            permaLink,
            thumbnailURL,
            timestamp,
            publisherUsername,
            caption,
            CollectionName
        );

        _mint(msg.sender, id);

        // Set default 1% royalty for all tokens
        _setTokenRoyalty(id, msg.sender, 0.01*10000); // Denominator is 10 000 by default in the interface
    }

    // Buyer initiates the transaction
    function buy (uint256 tokenId, uint256 price) external payable {
        _payFees(tokenId, price, payable(msg.sender), false);

        contentCountByUser[msg.sender] ++;
        contentCountByUser[ownerOf(tokenId)] --;

        _transfer(ownerOf(tokenId), msg.sender, tokenId);
    }

    function proposeTradeOffer(uint256 tokenId) external payable {
        offers[msg.sender][tokenId] += msg.value;
    }

    function withdrawOffer(uint256 tokenId) external {
        uint256 offer = offers[msg.sender][tokenId];
        offers[msg.sender][tokenId] = 0;

        (bool withdrew, ) = payable(msg.sender).call{value: offer}("");
        require(withdrew, "Failed to withdraw ETH.");
    }

    // Seller initiates the transaction
    function acceptTradeOffer (uint256 tokenId, address payable buyer) external {
        require(ownerOf(tokenId) == msg.sender, "Seller does not own the NFT.");

        _payFees(tokenId, offers[buyer][tokenId], buyer, true);
        offers[msg.sender][tokenId] = 0;

        contentCountByUser[buyer] ++;
        contentCountByUser[msg.sender] --;

        _transfer(msg.sender, buyer, tokenId);
    }

    function _payFees(uint256 tokenId, uint256 price, address payable buyer, bool bid) private {
        require(_exists(tokenId), "NFT with tokenID does not exist.");
        require(buyer != ownerOf(tokenId), "User already owns the NFT.");

        // Determine Royalty & Commission
        (address receiver, uint256 royalty) = this.royaltyInfo(tokenId, price);
        uint256 commissionValue = price * commission / 100;

        // Bidding fees can't overflow bid (seller pays for transaction)
        if(bid) {
            price -= commissionValue + royalty;
        } else {
            // Check Funds (buyer pays for transaction)
            require(msg.value >= price + commissionValue + royalty, "Insufficient Funds.");
        }

        // Pay Platform Fees
        (bool successPlatform, ) = _productOwner.call{value: commissionValue}("");
        require(successPlatform, "Failed to send Ether to platform.");

        // Pay Owner
        (bool success, ) = payable(ownerOf(tokenId)).call{value: price}("");
        require(success, "Failed to send Ether to seller.");

        // Pay Royalties Fees
        if(ownerOf(tokenId) != receiver) {
            (bool successRoyalty, ) = payable(receiver).call{value: royalty}("");
            require(successRoyalty, "Failed to send Ether to royalty delegate.");
        }
    }

    function getMediaByUser () public view returns (Media[] memory) {
        require(contentCountByUser[msg.sender] != 0, "The user owns no NFTs.");

        Media[] memory memoryArray = new Media[](contentCountByUser[msg.sender]);
        uint256 j = 0;

        for(uint256 i = 1; i <= _contentCount.current() && j < contentCountByUser[msg.sender]; i++) {
            if(ownerOf(contents[i].id) == msg.sender) {
                memoryArray[j] = contents[i];
                j++;
            }
        }

        return memoryArray;
    }

    function getMediaByCollection (string memory CollectionName) public view returns (Media[] memory) {
        require(contentCountByUser[msg.sender] != 0, "The user owns no NFTs.");

        Media[] memory memoryArray = new Media[](contentCountByUser[msg.sender]);
        uint256 j = 0;

        for(uint256 i = 1; i <= _contentCount.current() && j < contentCountByUser[msg.sender]; i++) {
            if(ownerOf(contents[i].id) == msg.sender &&
                keccak256(abi.encodePacked((contents[i].CollectionName))) == keccak256(abi.encodePacked((CollectionName)))) {
                memoryArray[j] = contents[i];
                j++;
            }
        }

        return memoryArray;
    }

    function getCollectionByUser () public view returns (string[] memory) {
        require(contentCountByUser[msg.sender] != 0, "The user owns no NFTs.");

        string[] memory memoryArray = new string[](contentCountByUser[msg.sender]);
        uint256 j = 0;

        for(uint256 i = 1; i <= _contentCount.current() && j < contentCountByUser[msg.sender]; i++) {
            if(ownerOf(contents[i].id) == msg.sender) {
                memoryArray[j] = contents[i].CollectionName;
                j++;
            }
        }

        return memoryArray;
    }

    function changeCollection (uint256 _nftId, string memory _newCollectionName) public {
        require(contentCountByUser[msg.sender] != 0, "The user owns no NFTs.");

        contents[nftToMedia[_nftId]].CollectionName = _newCollectionName;
    }

    function deleteCollection (string memory _collectionName) public {
        require(contentCountByUser[msg.sender] != 0, "The user owns no NFTs.");

        uint256 j = 0;

        for(uint256 i = 1; i <= _contentCount.current() && j < contentCountByUser[msg.sender]; i++) {
            if(ownerOf(contents[i].id) == msg.sender
                && keccak256(abi.encodePacked((contents[i].CollectionName))) == keccak256(abi.encodePacked((_collectionName)))) {
                contents[i].CollectionName = "";
                j++;
            }
        }
    }
}
