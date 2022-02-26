Feature: Create NFT based on Instagram
  As a registered and logged-in user with at least one Instagram account linked,
  I would like to create NFTs based on my Instagram content
  So that I can list them on the marketplace.

  Background:
    Given user is registered and logged-in
    Given the account is linked to an Instagram account
    Given the user has a post on their Instagram account
    Given user account is registered on the blockchain
    Given A NFT contract has been deployed

  Scenario: Create NFTs based on my Instagram content (Normal Flow)
    When the user creates an NFT from one of their photos of their Instagram account
    Then The NFT's publisher is the user

