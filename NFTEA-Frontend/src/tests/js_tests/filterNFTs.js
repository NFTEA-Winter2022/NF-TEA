const assert = require('assert');
const { Given, When, Then } = require('@cucumber/cucumber');

let listings;

Given('the user has access to four marketplace listings', async function () {
    listings = [
        {
            listingID : 1,
            title: "t1",
            price : 5,
            nftLink : "https://google.com"
        },
        {
            listingID : 2,
            title : "t2",
            price : 9,
            nftLink : "https://google.com"
        },
        {
            listingID : 3,
            title : "t3",
            price :  4,
            nftLink : "https://google.com"
        },
        {
            listingID : 4,
            title : "t4",
            price : 15,
            nftLink : "https://google.com"
        },
    ]
});

When('the user sorts the listings by ascending price', function() {
    listings.sort((a,b) => a.price >= b.price ? 1 : -1);
});

When('the user sorts the listings by descending price', function() {
    listings.sort((a,b) => a.price <= b.price ? 1 : -1);
});

Then('the available marketplace listings are sorted by ascending price', function () {
    let ascendingListings = [
        {
            listingID : 3,
            title : "t3",
            price : 4,
            nftLink : "https://google.com"
        },
        {
            listingID : 1,
            title : "t1",
            price : 5,
            nftLink : "https://google.com"
        },
        {
            listingID : 2,
            title : "t2",
            price : 9,
            nftLink : "https://google.com"
        },
        {
            listingID : 4,
            title : "t4",
            price : 15,
            nftLink : "https://google.com"
        }
    ]

    for(let i = 0; i < listings.length; i++) {
        assert.equal(listings[i].title, ascendingListings[i].title)
    }
});

Then('the available marketplace listings are sorted by descending price', function () {
    let descendingListings = [
        {
            listingID : 4,
            title : "t4",
            price : 15,
            nftLink : "https://google.com"
        },
        {
            listingID : 2,
            title : "t2",
            price : 9,
            nftLink : "https://google.com"
        },
        {
            listingID : 1,
            title : "t1",
            price : 5,
            nftLink : "https://google.com"
        },
        {
            listingID : 3,
            title : "t3",
            price : 4,
            nftLink : "https://google.com"
        }
    ]

    for(let i = 0; i < listings.length; i++) {
        assert.equal(listings[i].title, descendingListings[i].title)
    }
});