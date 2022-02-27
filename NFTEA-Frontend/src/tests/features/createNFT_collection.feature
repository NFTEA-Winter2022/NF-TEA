Feature: Create NFT collection
  As a registered and logged-in user with at least one NFT linked to my account,
  I would like to create a collection of NFTs
  So that I can view them in groups.

  Background:
    Given user is registered and logged-in
    Given the account is linked to an Instagram account
    Given the user has a post on their Instagram account
    Given user account is registered on the blockchain
    Given Said NFT contract has been deployed
    Given that the user has one NFT linked to their account
    Given that the user has no collection with the name 'Spring' exists

  Scenario: Create NFT collection (Normal Flow)
    When said user categorizes an NFT as 'Spring' and clicks add to collection
    Then the NFT shall be linked to the created collection

