// contracts/MyNFT.sol
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC721/ERC721.sol";
//import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Royalty.sol";
import "@openzeppelin/contracts/utils/Counters.sol";

contract NFTea is ERC721 { // ERC721Royalty to be added in US036
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
    }

    mapping (uint256 => Media) public contents;

    constructor() ERC721("NF-TEA", "NFTEA") {}

    function mintNFTea (
        uint256 id,
        uint256 publisherId,
        address publisherAddress,
        string memory  mediaType,
        string memory  URL,
        string memory  permaLink,
        string memory  thumbnailURL,
        string memory  timestamp,
        string memory  publisherUsername,
        string memory  caption
    )
    public
    returns (uint256)
    {
        require(contents[id].publisherAddress == address(0x0), "An NFT with that content Id has already been minted.");

        contents[id] = Media(
            id,
            publisherId,
            publisherAddress,
            mediaType,
            URL,
            permaLink,
            thumbnailURL,
            timestamp,
            publisherUsername,
            caption
        );
        _mint(publisherAddress, id);

        return id;
    }
}
