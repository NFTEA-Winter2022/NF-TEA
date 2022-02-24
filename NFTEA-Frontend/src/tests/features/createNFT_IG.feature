Feature: Create NFT based on Instagram
  As a registered and logged-in user with at least one Instagram account linked,
  I would like to create NFTs based on my Instagram content
  So that I can list them on the marketplace.

  Background:
    Given user is registered and logged-in
    And user has an Instagram account linked to his NFT account

  Scenario: Create NFTs based on my Instagram content (Normal Flow)
    When the user creates an NFT from one of their photos of their Instagram account
    Then a cookie web3 is not null

  Scenario: Cannot create NFTs due to no Instagram content (Error Flow)
    When the user creates an NFT from one of their photos of their Instagram account
    But there is no content in the Instagram account
    Then a cookie web3 is null