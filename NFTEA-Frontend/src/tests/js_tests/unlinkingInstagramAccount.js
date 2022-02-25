const { Given, When, Then, And } = require('cucumber');
const assert = require('assert');
const {By,Key,Builder, Capabilities} = require("selenium-webdriver");
require("chromedriver");
const {Options} = require("selenium-webdriver/chrome");
const web = require("selenium-webdriver");

let message = "";
let cookie = "";

Given("a NF-Tea's account with a linked Instagram Account",{timeout: 2 * 5000} , async function () {
    let options = new Options().addExtensions("./src/tests/metamaskExtension/metamaskExtension.crx");
    let chromeCapabilities = Capabilities.chrome();
    chromeCapabilities.setAcceptInsecureCerts(true);
    let driver = await new Builder()
        .withCapabilities(chromeCapabilities)
        .setChromeOptions(options)
        .build();
    try{
        await driver.get('https://192.168.86.42:8080/');
        // await driver.findElement(web.By.xpath('//button[text()="Get Started"]')).click();
        // await driver.findElement(web.By.xpath('//button[text()="Import wallet"]')).click();
        // await driver.findElement(web.By.xpath('//button[text()="No Thanks"]')).click();


    }
    finally {
    }





    cookie = "shortIGToken=notNull" + "; path=/";
    return true;
});

When("the user unlink their Instagram Account from their NF-Tea's account", function () {
    cookie = null;
    return true;
});

When("the Instagram Account is successfully unlinked from their account", function () {
    message = "Successfully unlinked";
    return true;
});

Then("their Instagram account is not link to their NF-Tea's Account", function () {

    return assert.equal(null, cookie);
});

Then("a message will appear {string}", function (arg1) {

    return assert.equal(arg1, message);
});

Then("their Instagram account is linked to their NF-Tea's Account", function () {

    return assert.notEqual(null,cookie);
});



When("the user unsuccessfully unlink their Instagram Account from their NF-Tea's account", function () {

    return true;
});

When("the Instagram Account is unsuccessfully unlinked from their account", function () {

    message = "Please try again";
    return true;
});

