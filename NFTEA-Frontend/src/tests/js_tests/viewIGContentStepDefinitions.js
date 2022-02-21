const { Given, Then, When, After, And } = require('@cucumber/cucumber');
const assert = require('assert');

let error = "";
let cookie = "";

After(async function () {
    error = "";
})

Given('the account is linked to an Instagram account', function () {
    cookie.concat("shortIGToken=notNull" + "; path=/");
});

When('the user attempts to view his Instagram content', function () {
    if(cookie != "") {
    } else {
        error = "No Instagram account found";
    }
});

Then("the user's Instagram content shall be displayed", function () {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});

Given('the account is not linked to an Instagram account', function () {
    cookie = "";
});


Then('an error message shall be raised {string}', function (errorMsg) {
    assert(errorMsg, error);
});

Then('no Instagram content shall be displayed', function () {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});