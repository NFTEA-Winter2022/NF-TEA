Feature: Create NFT based on Instagram
  As a registered and logged-in user with at least one NFT collection linked to my account,
  I would like to edit what NFTs are included in the collection
  So that I can re-organize my NFTs.

  Background:
    Given user is registered and logged-in
    Given the account is linked to an Instagram account
    Given the user has two posts on their Instagram account
    Given user account is registered on the blockchain
    Given A NFT contract has been deployed
    Given two nfts with the same collection belong to the user

  Scenario: NFT collection change attempt (Normal Flow)
    When the user changes the collection of one of its NFTs
    Then the user keeps his two nfts
    And exactly one of the user's NFTs should have its collection changed

