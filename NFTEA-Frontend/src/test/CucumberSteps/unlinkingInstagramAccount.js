const defineSupportCode = require('cucumber');
const assert = require('assert');
let message = "";


defineSupportCode.Given("a NF-Tea's account {string} with a linked Instagram Account", async function (arg1) {

    return 'pending';
});

defineSupportCode.When("the user unlink their Instagram Account from their NF-Tea's account", async function () {

    return 'pending';
});

defineSupportCode.When("the Instagram Account is successfully unlinked from their account", async function () {
    if (!document.cookie){
        continue;
    }
    else{
        error = "";
    }
    return 'pending';
});

defineSupportCode.Then("a message shall be raised {string}", async function (arg1) {
    assert.equal(arg1,message);
});

defineSupportCode.Then("their Instagram account is not link to their NF-Tea's Account", async function () {
    assert.equal(null, document.cookie);
});

defineSupportCode.Then("their Instagram account is linked to their NF-Tea's Account", async function () {

    assert.notEqual(null, document.cookie);

});

defineSupportCode.Then("an error shall be raised {string}", async function (arg1) {
    assert.equal(arg1, message);
});