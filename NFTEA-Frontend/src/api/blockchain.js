/* eslint-disable */
import FacebookAPI from "./facebook"
import NFTDetails from "./NFTDetails.json"
const Web3 = require('web3')

const web3 = new Web3('http://127.0.0.1:8545')
// For Prod (using the public testnet)
// const web3 = new Web3(new Web3.providers.HttpProvider(
//          'https://eth-ropsten.alchemyapi.io/v2/QObSDMqhnbSGbBQPM0MYX8v6FWBFoQxV'
//      ));
const NFTContract = new web3.eth.Contract(NFTDetails.abi, NFTDetails.address);
const transactionFees = 0.96; // Rougly estimating 5% fees

export default {
    async mintNFT(media) {
        let userAddress = FacebookAPI.getCookie("address");
        let userInstagramId = JSON.parse(FacebookAPI.getCookie("shortIGToken")).user_id;

        try {
            await NFTContract.methods.mintNFTea (
                media.id.toString(),
                userInstagramId.toString(),
                media.media_type,
                media.media_url,
                media.permalink,
                media.thumbnail_url || "None",
                media.timestamp,
                media.username,
                media.caption || "None",
                media.CollectionName || "None"
            ).send({from: userAddress.toString(), gas: "6721975"})
        } catch (e) {
            console.log(e);
            return e;
        }
    },
    async getNFT(mediaId) {
        let userAddress = FacebookAPI.getCookie("address");

        try {
            const tokenId = await NFTContract.methods.nftToMedia(mediaId.toString()).call({from: userAddress.toString()});
            return await NFTContract.methods.contents(tokenId.toString()).call({from: userAddress.toString()});
        } catch (e) {
            console.log(e);
        }
    },
    async getNFTs() {
        let userAddress = FacebookAPI.getCookie("address");

        try {
            return await NFTContract.methods.getMediaByUser().call({from: userAddress.toString()});
        } catch (e) {
            console.log(e);
        }
    },
    async getNFTsByCollection(CollectionName) {
        let userAddress = FacebookAPI.getCookie("address");
        console.log(CollectionName);
        try {
            return await NFTContract.methods.getMediaByCollection(CollectionName).call({from: userAddress.toString()});
        } catch (e) {
            console.log(e);
        }
    },
    async getCollectionNamesForUser() {
        let userAddress = FacebookAPI.getCookie("address");

        try {
            return await NFTContract.methods.getCollectionByUser().call({from: userAddress.toString()});
        } catch (e) {
            console.log(e);
        }
    },
    async deleteCollection(collectionName) {
        let userAddress = FacebookAPI.getCookie("address");

        try {
            return await NFTContract.methods.deleteCollection(collectionName).send({from: userAddress.toString(), gas: "6721975"})
        } catch (e) {
            console.log(e);
        }
    },
    async changeCollection(nftId, collectionName) {
        let userAddress = FacebookAPI.getCookie("address");

        try {
            return await NFTContract.methods.changeCollection(nftId, collectionName).send({from: userAddress.toString(), gas: "6721975"})
        } catch (e) {
            console.log(e);
        }
    },
    async buyNFT(nftID, price) {
        let userAddress = FacebookAPI.getCookie("address");
        let bid = transactionFees * price;

        await NFTContract.methods.buy(nftID, web3.utils.toWei(bid.toString(), "ether"))
            .send({from: userAddress.toString(), value:web3.utils.toWei(price.toString(), "ether"), gas: "6721975"})
    },
    async offerTrade(nftID, price) {
        let userAddress = FacebookAPI.getCookie("address");

        try {
            await NFTContract.methods.proposeTradeOffer(nftID)
                .send({from: userAddress.toString(), value:web3.utils.toWei(price.toString(), "ether"), gas: "6721975"})
        } catch(e) {
            console.log(e);
        }
    },
    async acceptTrade(nftID, buyerAddress) {
        let userAddress = FacebookAPI.getCookie("address");

        await NFTContract.methods.acceptTradeOffer(nftID, buyerAddress.toString())
            .send({from: userAddress.toString(), gas: "6721975"})

    }
}
