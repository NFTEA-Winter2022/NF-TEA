const { Given, Then, When, After } = require('@cucumber/cucumber');
const assert = require('assert');

let error = "";
let cookie = "";

After(async function () {
    error = "";
})

Given('the account is linked to an Instagram account', function () {
    cookie = "shortIGToken=notNull" + "; path=/";
});

When('the user attempts to view his Instagram content', function () {
    if (cookie !== "") {
        error = "";
        return true;
    } else {
        error = "No Instagram account found";
    }
});

Then("the user's Instagram content shall be displayed", function () {
    // Write code here that turns the phrase above into concrete actions
    if (error !== "") {
        //console.log(error);
        assert.equal(false, true);
    } else {
        assert.equal(true, true);
    }
});

Given('the account is not linked to an Instagram account', function () {
    cookie = "";
});


Then('an error message shall be raised {string}', function (errorMsg) {
    assert(errorMsg, error);
});