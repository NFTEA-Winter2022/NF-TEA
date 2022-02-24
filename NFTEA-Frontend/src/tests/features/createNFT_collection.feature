Feature: Create NFT collection
  As a registered and logged-in user with at least one NFT linked to my account,
  I would like to create a collection of NFTs
  So that I can view them in groups.

  Background:
    Given user is registered and logged-in

  Scenario: Create NFT collection (Normal Flow)
    When the user creates a collection of NFTs
    Then the system links a collection of NFTs of size zero to the user's NFT account