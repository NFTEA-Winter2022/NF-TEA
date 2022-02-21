Feature: Link MetaMask
  As a registered and logged-in user,
  I would like to be able to link my MetaMask wallet to my NF-TEA account
  So that I can interact with the Ethereum blockchain

  Background:
    Given user is registered and logged-in

  Scenario: Link to MetaMask Wallet (Normal Flow)
    When the user connects to their metamask account
    Then a cookie web3 is not null

  Scenario: Cannot Link to MetaMask Wallet due to Invalid Credentials (Error Flow)
    When the user connects to their metamask account with invalid credentials
    Then there is no web3 cookie that exists
