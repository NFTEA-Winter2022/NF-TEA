Feature: Edit options on Collection of NFTs

  Background:
    Given some NFTs:
      | listingID  | title      | price  | nftLink |
      | 04         | NFTtitleA  | 1      | linkA   |
      | 05         | NFTtitleB  | 2      | linkB   |
    And a collection of NFTs:
      | numberID  | title    | listOfNFTs |
      | 01        | titleA   | 01,02,03   |
    # TODO: Change List of NFTs


  Scenario: Adding a NFT to the collection
    When a user wants to add a NFT "04" to the collection "01"
    Then the NFT "04" shall be added to the collection "01"

  Scenario: Removing a NFT to the collection
    When a user wants to remove a NFT "01" to the collection "01"
    Then the NFT "01" will not exits in the collection "01"
