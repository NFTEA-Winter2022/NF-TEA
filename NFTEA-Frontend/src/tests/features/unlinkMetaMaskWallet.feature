Feature: Unlink MetaMask
  As a registered and logged-in user,
  I would like to unlink my previously-linked MetaMask wallet from my NF-TEA account,
  so that I can safely leave the website

  Background:
    Given user is registered and logged-in

  Scenario: Unlink my MetaMask Wallet (Normal Flow)
    When the user disconnects from their metamask account
    Then a cookie web3 is null

  Scenario: Cannot unlink my MetaMask
    When the user tries to disconnect from metamask
    Then there is a web3 cookie that exists
