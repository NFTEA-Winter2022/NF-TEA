Feature: Link MetaMask
  As a registered and logged-in user,
  I would like to be able to link my MetaMask wallet to my NF-TEA account
  So that I can interact with the Ethereum blockchain

  Background:
    Given user is registered and logged-in

  Scenario: Link to MetaMask Wallet (Normal Flow)
    When the user connects to their metamask account
    And the user's metamask account has "<credentials>"
    Then the account is joined
    And the cookie has "<value>"

  Scenario: Cannot Link to MetaMask Wallet due to Invalid Credentials (Error Flow)
    When the user connects to their metamask account
    But the user's metamask account does not "<credentials>"
    Then the account is not joined
    And the system states that the user has invalid credentials