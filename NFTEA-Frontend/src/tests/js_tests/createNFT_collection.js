const assert = require('assert');
const { Given, When, Then } = require('@cucumber/cucumber');
const { abi, bytecode } = require('../../../../NFTEA-Blockchain/build/contracts/NFTEA.json')
const ganache = require('ganache-cli'); // Mockup of eth network
const web3 = new (require('web3'))(ganache.provider());

let contract;

Given("Said NFT contract has been deployed", async function () {
    let userAccounts =  await web3.eth.getAccounts();
    const deployable = new web3.eth.Contract(abi)
        .deploy({
            data: bytecode,
            arguments: [userAccounts[9]]
        })

    contract = await deployable.send({
        from: (await web3.eth.getAccounts())[0],
        gas: await deployable.estimateGas()
    })
});

Given('that the user has one NFT linked to their account', async function () {
    let userAccounts =  await web3.eth.getAccounts();

    let media1 = {
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

    await contract.methods.mintNFTea(media1.id, media1.publisherId, media1.media_type, media1.media_url, media1.permalink,
        media1.thumbnail_url, media1.timestamp, media1.username, media1.caption, "Winter")
        .send({from: userAccounts[0].toString(), gas: "6721975"})

});

Given("that the user has no collection with the name 'Spring' exists", async function () {
    let userAccounts =  await web3.eth.getAccounts();
    let exists = false;
    try {
        var collectionNameArray = await contract.getCollectionByUser().call({from: userAccounts[0].toString()});
        collectionNameArray.forEach((name) => {
            if (name === 'Spring') {
                exists = true;
            }
        })
        assert.equal(exists, false);
    } catch(e) {
        console.log(e);
    }
});

When("said user categorizes an NFT as 'Spring' and clicks add to collection", async function () {
    let userAccounts =  await web3.eth.getAccounts();
    await contract.methods.changeCollection("10923847", "Spring").send({from: userAccounts[0].toString()});
});

Then("the NFT shall be linked to the created collection", async function () {
    let userAccounts = await web3.eth.getAccounts();

    const nftID = await contract.methods.nftToMedia("10923847").call({from: userAccounts[0].toString()});
    const nft = await contract.methods.contents(nftID).call({from: userAccounts[0].toString()});

    assert.equal(nft.CollectionName, "Spring")
})



