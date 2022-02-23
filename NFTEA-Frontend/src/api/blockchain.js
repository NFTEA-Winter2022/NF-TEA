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

export default {
    async mintNFT() {
        let insta = await FacebookAPI.getInstagramContent();
        let media = insta.data[0];
        console.log("Insta: " + JSON.stringify(insta.data));

        console.log("Creating NFTS")

        let userAddress = FacebookAPI.getCookie("address");
        let userInstagramId = JSON.parse(FacebookAPI.getCookie("shortIGToken")).user_id;

        try {
            const result = await NFTContract.methods.mintNFTea (
                media.id.toString(),
                userInstagramId.toString(),
                userAddress.toString(),
                media.media_type,
                media.media_url,
                media.permalink,
                media.thumbnail_url || "None",
                media.timestamp,
                media.username,
                media.caption || "None"
            ).send({from: userAddress.toString(), gas: "6721975"})
            console.log(result);
            await this.getNFTs();
            return result;
        } catch (e) {
            console.log(e);
            return e;
        }
    },
    async getNFT(mediaId) {
        let userAddress = FacebookAPI.getCookie("address");

        try {
            return await NFTContract.methods.contents(mediaId.toString()).call({from: userAddress.toString()});
        } catch (e) {
            console.log(e);
        }
    },
    async getNFTs() {
        let userAddress = FacebookAPI.getCookie("address");
        try {
            console.log('querying NFT')
            const nfts = await NFTContract.methods.getMediaByUser().call({from: userAddress.toString()});
            const tt = await NFTContract.methods.contents("17908267844453410").call({from: userAddress.toString()});
            console.log(nfts);
            console.log(tt);
        } catch (e) {
            console.log(e);
        }
    }
}