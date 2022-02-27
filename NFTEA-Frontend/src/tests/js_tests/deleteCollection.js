const assert = require('assert');
const Axios = require("axios");
const { Given, When, Then } = require('@cucumber/cucumber');
const { abi, bytecode } = require('../../../../NFTEA-Blockchain/build/contracts/NFTEA.json')
const ganache = require('ganache-cli'); // Mockup of eth network
const web3 = new (require('web3'))(ganache.provider());

let axios = Axios.create({
    baseURL: `http://localhost:8081/`
})

let error = "";
let cookie = "";
let contract;
let userAccounts;
let media;


Given('the user has two posts on their Instagram account', async function () {
    media = [
        {
            id: "10923847",
            publisherId: "1109238470",
            media_type: "image",
            media_url: "https://instagram.fymy1-1.fna.fbcdn.net/v/t51.2885…3shzb4ZlB1oNPId6W535JQ&oe=62204617&_nc_sid=7b02f1",
            permalink: "https://www.instagram.com/p/CabSOfpNXrj/",
            thumbnail_url: "none",
            timestamp: "2020-07-10 15:00:00.000",
            username: "Tornade99",
            caption: "We all need sleep, running on caffeine rn",
            collectionName: "Summer"
        },
        {
            id: "10923848",
            publisherId: "1109238470",
            media_type: "image",
            media_url: "https://instagram.fymy1-1.fna.fbcdn.net/v/t51.2885…3shzb4ZlB1oNPId6W535JQ&oe=62204617&_nc_sid=7b02f1",
            permalink: "https://www.instagram.com/p/CabSOfpNXrj/",
            thumbnail_url: "none",
            timestamp: "2020-07-10 15:00:00.000",
            username: "Tornade99",
            caption: "We all need sleep, running on caffeine rn",
            collectionName: "Summer"
        },
    ]

});

Given("A NFT contract has been deployed", async function () {
    const deployable = new web3.eth.Contract(abi)
        .deploy({
            data: bytecode,
            arguments: []
        })

    contract = await deployable.send({
        from: (await web3.eth.getAccounts())[0],
        gas: await deployable.estimateGas()
    })
});

Given("user account is registered on the blockchain", async function () {
    userAccounts = await web3.eth.getAccounts();
});


Given('two nfts with the same collection belong to the user', async function () {

    await contract.methods.mintNFTea(media[0].id, media[0].publisherId, media[0].media_type, media[0].media_url, media[0].permalink,
        media[0].thumbnail_url, media[0].timestamp, media[0].username, media[0].caption, media[0].collectionName)
        .send({from: userAccounts[0].toString(), gas: "6721975"})

    await contract.methods.mintNFTea(media[1].id, media[1].publisherId, media[1].media_type, media[1].media_url, media[1].permalink,
        media[1].thumbnail_url, media[1].timestamp, media[1].username, media[1].caption, media[1].collectionName)
        .send({from: userAccounts[0].toString(), gas: "6721975"})

    return true;
});

When('the user deletes its collection', async function () {
    await contract.methods.deleteCollection(media[0].collectionName)
        .send({from: userAccounts[0].toString(), gas: "6721975"})
});

Then('the user keeps his two nfts', async function() {
    const firstNFT = await contract.methods.contents("10923847");
    const secondNFT = await contract.methods.contents("10923848");

    assert.equal(userAccounts[0].toString(), firstNFT.publisherAddress);
    assert.equal(userAccounts[0].toString(), secondNFT.publisherAddress);
});

Then('the user\'s nfts do not belong to any collection', async function() {
    await contract.methods.deleteCollection(media[0].collectionName).send({from: userAccounts[0].toString()});
    const collections = await contract.methods.getCollectionByUser().call({from: userAccounts[0].toString()});

    assert.notEqual(collections.includes(media[0].collectionName), true);
});