Feature: Delete an NFT collection
  As a registered and logged-in user with at least one NFT collection linked to my account,
  I would like to delete a collection from my account, keeping the NFTs
  So that I can re-organize my NFTs.

  Background:
    Given user is registered and logged-in
    Given the account is linked to an Instagram account
    Given the user has two posts on their Instagram account
    Given user account is registered on the blockchain
    Given A NFT contract has been deployed
    Given two nfts with the same collection belong to the user

  Scenario: NFT collection deletion attempt (Normal Flow)
    When the user deletes its collection
    Then the user keeps his two nfts
    And the user's nfts do not belong to any collection

