Feature: Filter NFTs
  As a registered and logged-in user,
  I would like to be able to filter my searched items
  so that I can easily browse through them."

  Background:
    Given user is registered and logged-in
    Given the user has access to four marketplace listings

  Scenario: Sort by ascending price (Normal Flow)
    When the user sorts the listings by ascending price
    Then the available marketplace listings are sorted by ascending price

  Scenario: Sort by descending price (Normal Flow)
    When the user sorts the listings by descending price
    Then the available marketplace listings are sorted by descending price