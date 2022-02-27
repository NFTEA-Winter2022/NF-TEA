Feature: Add NFT to a collection
  As a registered and logged-in user with at least two NFTs linked to my account
  and a Spring collection with one NFT inside, I would like to add the second NFT
  to the Spring collection
  So that I can group NFTs together

  Background:
    Given user is registered and logged-in
    Given the account is linked to an Instagram account
    Given said user has two posts on their Instagram account
    Given user account is registered on the blockchain
    Given The NFT contract has been deployed
    Given that the user has two NFTS linked to their account
    Given that the user has a Spring collection with one NFT inside


  Scenario: Adds NFT to Collection (Normal Flow)
    When the user categorizes an NFT as 'Spring' and clicks add to collection
    Then the NFT shall be linked to the collection
    Then the collection shall have two NFTs linked
