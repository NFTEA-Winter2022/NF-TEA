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


Given('user is registered and logged-in', async function () {

    try {
        // Create a user and set loggedIn boolean to true
        await axios.post('user-account/',
            null, {
                params: {
                    firstname: "Harsh",
                    lastname: "Patel",
                    username: "HPatel",
                    email: "hpatel@gmail.com",
                    password: "123456789."
                }
            }
        )

        await axios.get("/home/login", {
            params: {
                email: "hpatel@gmail.com",
                password: "123456789."
            }
        })

    } catch(e) {
        console.log(e);
    }
});


Given('the user has a post on their Instagram account', async function () {

    media = {
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

});

Given("a NFT contract has been deployed", async function () {
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

Given("a user account is registered on the blockchain", async function () {
    userAccounts = await web3.eth.getAccounts();
});


When('the user creates an NFT from one of their photos of their Instagram account', async function () {
    let userAccounts = await web3.eth.getAccounts();

    await contract.methods.mintNFTea(media.id, media.publisherId, media.media_type, media.media_url, media.permalink,
        media.thumbnail_url, media.timestamp, media.username, media.caption, "Winter")
        .send({from: userAccounts[0].toString(), gas: "6721975"})
});

Then('The NFT\'s publisher is the user', async function() {
    let userAccounts = await web3.eth.getAccounts();

    const nftID = await contract.methods.nftToMedia(media.id).call({from: userAccounts[0].toString()});
    const nft = await contract.methods.contents(nftID).call({from: userAccounts[0].toString()});

    assert.equal(media.publisherId, nft.publisherId);
});