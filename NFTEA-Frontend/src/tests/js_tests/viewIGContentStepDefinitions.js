const { Given, Then, When, After, And } = require('@cucumber/cucumber');
const assert = require('assert');

let error = "";

After(async function () {
    error = "";
})

Given('The account is linked to an Instagram account', function () {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});

When('The user attempts to view his Instagram content', function () {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});

Then("The user's Instagram content shall be displayed", function () {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});

Given('The account is not linked to an Instagram account', function () {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});


Then('an error message shall be raised {string}', function (errorMsg) {
    assert(errorMsg, error);
});

And('no Instagram content shall be displayed', function () {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});