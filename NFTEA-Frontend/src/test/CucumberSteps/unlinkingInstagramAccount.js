const defineSupportCode = require('cucumber');
const assert = require('assert');

defineSupportCode(function({ Given, Then, When }) {
    Given('I start with {int}', function (int, callback) {
        // Write code here that turns the phrase above into concrete actions
        callback(null, 'pending');
    });
    When('I add {int}', function (int, callback) {
        // Write code here that turns the phrase above into concrete actions
        callback(null, 'pending');
    });
    Then('I end up with {int}', function (int, callback) {
        // Write code here that turns the phrase above into concrete actions
        callback(null, 'pending');
    });
});