const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
let cookie = "";

// Given('user is registered and logged-in', function() {
//     cookie = "id=1" + "; path=/";
//     return true;
// });

When('the user connects to their metamask account', function() {
    cookie.concat("web3=notNull" + "; path=/");
    return true;
});

When('the user connects to their metamask account with invalid credentials', function() {
   return true
});

Then('a cookie web3 is not null', function() {
    let splits = cookie.split(';');
    splits.forEach(element => function() {
        let id = element.split('=');
        if (id[0] === 'web3') return assert.equal(false, true);
    });
});

Then('there is no web3 cookie that exists', function() {
    let splits = cookie.split(';');
    splits.forEach(element => function() {
        let id = element.split('=');
        if (id[0] === 'web3') return assert.equal(false, true);
    });
});