Feature: Edit options on Collection of NFTs

  Background:
    Given there is an online user:
      | firstName | lastName | username | email  | password    |
      | JohnA     | DoeA     | JohnnyA  | Email1 | apple123    |
    And some NFTs:
      | listingID  | title      | price  | nftLink | ownerEmail |
      | 04         | NFTtitleA  | 1      | linkA   | Email1     |
      | 05         | NFTtitleB  | 2      | linkB   | Email1     |
    And a collection of NFTs:
      | numberID  | title    | listOfNFTs |
      | 01        | titleA   | 01,02,03   |


  Scenario: Adding a NFT to the collection
    When a user wants to add a NFT "04" to the collection "01"
    Then the NFT "04" shall be added to the collection "01"

  Scenario: Removing a NFT to the collection
    When a user wants to remove a NFT "01" to the collection "01"
    Then the NFT "01" will not exist in the collection "01"

  Scenario: Changing the name of the collection
    When a user changes the name "titleB" of the collection "01"
    Then the collection's name "01" will be changed "titleB"
