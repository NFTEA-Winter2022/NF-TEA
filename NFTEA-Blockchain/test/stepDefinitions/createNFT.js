const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');

const { abi, bytecode } = require('../../build/contracts/NFTEA.json')
const ganache = require('ganache-cli'); // Mockup of eth network
const web3 = new (require('web3'))(ganache.provider());

let contract;
let userAccounts;

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

Given("An user account is registered on the blockchain", async function () {
    userAccounts = await web3.eth.getAccounts();
});

When('A new NFT is created with id 1 by the user', async function() {
    await contract.methods.mintNFTea(1, 10, "a", "b", "c", "d", "e", "f", "g")
        .send({from: userAccounts[0].toString(), gas: "6721975"})

    return true;
});

Then('The smart contract holds an NFT with id 1', async function() {
    const singleNFT = await contract.methods.contents(1);
    assert(1, singleNFT.id);
});

Then('The NFT\'s publisher is the user', async function() {
    const singleNFT = await contract.methods.contents(1);
    assert(userAccounts[0].toString(), singleNFT.publisherAddress);
});

