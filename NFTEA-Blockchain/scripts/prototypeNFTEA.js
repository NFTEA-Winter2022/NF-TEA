// Initial testing of the NFTEA contract
// Demonstrates how it may be acquired, how tokens can be minted and how its data can be read
// This is how test scripts will interact with the smart contract

module.exports = async function main (callback) {
    try {
        // Get available accounts on the test local blockchain
        const accounts = await web3.eth.getAccounts();

        // Get contract instance
        const NFTEA = artifacts.require('NFTEA');
        const nft = await NFTEA.deployed();

        // Mint a new NFTea token
        await nft.mintNFTea(1, 10, "a", "b", "c", "d", "e", "f", "g");

        // Read the minted NFTea's data from the blockchain
        const singleNFT = await nft.contents(1)

        // Read all NFTs owned by a user
        const allNFTs = await nft.getMediaByUser();

        callback(0);
    } catch (error) {
        console.error(error);
        callback(1);
    }
};