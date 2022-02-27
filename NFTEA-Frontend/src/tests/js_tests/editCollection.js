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
let newCollectionName = "Winter"


When('the user changes the collection of one of its NFTs', async function () {
    await contract.methods.changeCollection(media[0].id, newCollectionName)
        .send({from: userAccounts[0].toString(), gas: "6721975"})
});

Then('exactly one of the user\'s NFTs should have its collection changed', async function() {
    const firstNFT = await contract.methods.contents("10923847");
    const secondNFT = await contract.methods.contents("10923848");

    assert.notEqual(firstNFT.CollectionName, media[0].collectionName);
    assert.equal(secondNFT.CollectionName, media[1].collectionName);
});