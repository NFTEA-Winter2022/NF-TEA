const assert = require('assert');
const { Given, When, Then } = require('@cucumber/cucumber');
const { abi, bytecode } = require('../../../../NFTEA-Blockchain/build/contracts/NFTEA.json')
const ganache = require('ganache-cli'); // Mockup of eth network
const web3 = new (require('web3'))(ganache.provider());

let contract;

Given("that the user has no collection with the name 'Spring' exists", async function () {
    let exists = false;
    try {
        var collectionNameArray = await blockchain.getCollectionNamesForUser()
        collectionNameArray.forEach((name) => {
            if (name === 'Spring') {
                exists = true;
            }
        })
        assert(exists, false);
    } catch(e) {
        console.log(e);
    }
});

When("the user categorizes an NFT as 'Spring' and clicks add to collection", async function () {
    await contract.methods.changeCollection("10923847", "Spring");
});

Then("the NFT shall be linked to the created collection", async function () {
    let exists = false;

    var nft = await contract.methods.getNFTsByCollection("Spring")

    var collectionNameArray = await contract.methods.getCollectionNamesForUser()
    collectionNameArray.forEach((name) => {
        if (name === 'Spring') {
            exists = true;
        }
    })
    assert(exists, true);
    assert(nft[0], "10923847");
});




