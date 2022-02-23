const { When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
let cookie = "";

When('the user disconnects from their metamask account', function() {
    cookie = "id=1" + "; path=/";
    return true;
});

Then('a cookie web3 is null', function() {
    let splits = cookie.split(';');
    splits.forEach(element => function() {
        let id = element.split('=');
        if (id[0] === 'web3') return assert.equal(false, true);
    });
});