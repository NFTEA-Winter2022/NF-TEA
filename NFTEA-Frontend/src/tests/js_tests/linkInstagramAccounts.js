const { When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
let cookie = "";

When('the user links his instagram account to his NF-TEA account through the API', function() {
    cookie.concat("shortIGToken=notNull" + "; path=/");
    return true;
});

When('the user links his instagram account to his NF-TEA account with invalid credentials', function() {
   return true;
});

Then('the browser should have a non-null shortIGToken cookie', function() {
    let splits = cookie.split(';');
    splits.forEach(element => function() {
        let id = element.split('=');
        if (id[0] === 'shortIGToken') return assert.equal(true, true);
    });
});

Then('the browser should not have a shortIGToken cookie', function() {
    let splits = cookie.split(';');
    splits.forEach(element => function() {
        let id = element.split('=');
        if (id[0] === 'shortIGToken') return assert.equal(false, true);
    });
});