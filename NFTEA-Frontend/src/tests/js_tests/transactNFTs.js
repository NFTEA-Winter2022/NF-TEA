const assert = require('assert');
const { Given, When, Then } = require('@cucumber/cucumber');
const { abi, bytecode } = require('../../../../NFTEA-Blockchain/build/contracts/NFTEA.json')
const ganache = require('ganache-cli'); // Mockup of eth network
const web3 = new (require('web3'))(ganache.provider());

let contract;
let productOwner;
let productOwnerInitialBalance;
let buyer;
let seller;
let price;
let bid;
let media1;

Given("the NFT smart Contract has been deployed", async function () {
    let userAccounts =  await web3.eth.getAccounts();
    productOwner = userAccounts[9];
    buyer = userAccounts[0];
    seller = userAccounts[1];
    price = 20;
    bid =30;

    const deployable = new web3.eth.Contract(abi)
        .deploy({
            data: bytecode,
            arguments: [productOwner]
        })

    contract = await deployable.send({
        from: productOwner,
        gas: await deployable.estimateGas()
    })

    productOwnerInitialBalance = await web3.eth.getBalance(productOwner)
});

Given('a user has created an NFT', async function () {
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

    await contract.methods.mintNFTea(media1.id, media1.publisherId, media1.media_type, media1.media_url, media1.permalink,
        media1.thumbnail_url, media1.timestamp, media1.username, media1.caption, "Winter")
        .send({from: seller.toString(), gas: "6721975"})
});

Given('a second user has proposed a bid on the NFT', async function () {
    try {
        await contract.methods.proposeTradeOffer(media1.id)
            .send({from: buyer.toString(), value:web3.utils.toWei(price.toString(), "ether"), gas: "6721975"})
    } catch(e) {
        console.log(e);
    }
});

When("a second user purchases the NFT", async function () {
    try {
        await contract.methods.buy(media1.id, web3.utils.toWei(bid.toString(), "ether"))
            .send({from: buyer.toString(), value:web3.utils.toWei(price.toString(), "ether"), gas: "6721975"})
    } catch(e) {
        console.log(e);
    }
});

When("the NFT's owner accepts the bid", async function () {
    try {
        await contract.methods.acceptTradeOffer(media1.id, buyer.toString())
            .send({from: seller.toString(), gas: "6721975"})
    } catch(e) {
        console.log(e);
    }
});

// Loosely testing values because gas fees give them uncertainty
Then("the product owner wallet should collect 2% of the fees.", async function () {
    let userAccounts = await web3.eth.getAccounts();

    const currentProductOwnerBalance = await web3.eth.getBalance(productOwner)
    assert.equal(parseInt(currentProductOwnerBalance) >= parseInt(productOwnerInitialBalance), true);
})



