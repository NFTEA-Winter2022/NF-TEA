const { Given, Then, When, After, Status } = require('@cucumber/cucumber');
const assert = require('assert');
const { Builder, By, Capabilities, Key } = require('selenium-webdriver');
// const { expect } = require('chai');

let error = "";
let userAccount = {};

// driver setup
const capabilities = Capabilities.chrome();
capabilities.set('chromeOptions', {"w3c": false});
const driver = new Builder().withCapabilities(capabilities).build();

After(async function (scenario) {
    // if(scenario.result.status === Status.FAILED) {
    //     var world = this;
    //     return WebDriver.takeScreenshot().then(function(screenShot, error) {
    //         if(!error) {
    //             world.attach(screenShot, "image/png");
    //         }
    //     })
    // }
    error = "";
    userAccount = {};
    // await driver.quit();
})

Given('the following user accounts exist in the system', function (dataTable) {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});

Given('{string} is logged into the system', async function (email) {
    await driver.get('https://www.google.com');
    // let userEmailField = await driver.wait(until.elementLocated(By.id('userEmail')),10000);
    // await userEmailField.sendKeys(userAccount.email);
  
    // let userPasswordField = await driver.wait(until.elementLocated(By.id('userPassword')),10000);
    // await userEmailField.sendKeys(userAccount.password);

    // let loginBtn = await driver.wait(until.elementLocated(By.id('loginBtn')),10000);
    // await loginBtn.click();

});

Given('{string} is linked to an Instagram account', function (email) {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});

When('{string} attempts to view his Instagram content', function (email) {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});

Then('{string}\'s Instagram content shall be displayed', function (email) {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});

Given('{string} is not linked to an Instagram account', function (email) {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});


Then('an error message shall be raised {string}', function (errorMsg) {
    assert(errorMsg, error);
});

Then('no Instagram content shall be displayed', function () {
    // Write code here that turns the phrase above into concrete actions
    return 'pending';
});