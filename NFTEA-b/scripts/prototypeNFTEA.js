// Initial testing of the NFTEA contract
// Demonstrates how it may be acquired, how tokens can be minted and how its data can be read

module.exports = async function main (callback) {
    try {
        // Get available accounts
        const accounts = await web3.eth.getAccounts();
        console.log(accounts)

        // Get contract instance
        const NFTEA = artifacts.require('NFTEA');
        const nft = await NFTEA.deployed();

        // Mint a new NFTea token
        await nft.mintNFTea(1, 10, accounts[0], "a", "b", "c", "d", "e", "f", "g");

        // Read the minted NFTea's data from the blockchain
        const contents = await nft.contents(1)

        callback(0);
    } catch (error) {
        console.error(error);
        callback(1);
    }
};