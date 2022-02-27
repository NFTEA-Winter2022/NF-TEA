const assert = require('assert');
const Axios = require("axios");
const { Given, When, Then } = require('@cucumber/cucumber');
const { abi, bytecode } = require('../../../../NFTEA-Blockchain/build/contracts/NFTEA.json')
const ganache = require('ganache-cli');
const blockchain = require("@/api/blockchain"); // Mockup of eth network
const web3 = new (require('web3'))(ganache.provider());

let axios = Axios.create({
    baseURL: `http://localhost:8081/`
})

let contract;
let userAccounts;
let media1;
let media2;

Given('the user has two post on their Instagram account', async function () {

    media1 = {
        id: "10923847",
        publisherId: "1109238470",
        media_type: "image",
        media_url: "https://instagram.fymy1-1.fna.fbcdn.net/v/t51.2885…3shzb4ZlB1oNPId6W535JQ&oe=62204617&_nc_sid=7b02f1",
        permalink: "https://www.instagram.com/p/CabSOfpNXrj/",
        thumbnail_url: "none",
        timestamp: "2020-07-10 15:00:00.000",
        username: "Tornade99",
        caption: "We all need sleep, running on caffeine rn",

    }
    media2 = {
        id: "10923848",
        publisherId: "1109238471",
        media_type: "image",
        media_url: "https://instagram.fymy1-1.fna.fbcdn.net/v/t51.2885…uwmvvQ-2YiZeEJzUWsIH6Q&oe=62230809&_nc_sid=83d603",
        permalink: "https://www.instagram.com/p/Cad93Gao3aq/",
        thumbnail_url: "none",
        timestamp: "2020-07-10 16:00:00.000",
        username: "Tornade99",
        caption: "Help us",
    }
});

Given('that the user has two NFTS linked to their account', async function () {
    await contract.methods.mintNFTea(media1.id, media1.publisherId, media1.media_type, media1.media_url, media1.permalink,
        media1.thumbnail_url, media1.timestamp, media1.username, media1.caption, "None")
        .send({from: userAccounts[0].toString(), gas: "6721975"})

    await contract.methods.mintNFTea(media2.id, media2.publisherId, media2.media_type, media2.media_url, media2.permalink,
        media2.thumbnail_url, media2.timestamp, media2.username, media2.caption, "None")
        .send({from: userAccounts[0].toString(), gas: "6721975"})
});

Given('that the user has a Spring collection with one NFT inside', async function () {
    await contract.methods.changeCollection("10923847", "Spring");
});

When('the user categorizes an NFT as \'Spring\' and clicks add to collection', async function() {
    await contract.methods.changeCollection("10923848", "Spring");
});

Then('the NFT shall be linked to the collection', async function () {
    var NFT = await contract.methods.getNFT("10923848")
    assert(NFT[10], "Spring")
});

Then('the collection shall have two NFTs linked', async function () {
    var array = await contract.methods.getNFTsByCollection("Spring");
    assert(array.length, 2);
});