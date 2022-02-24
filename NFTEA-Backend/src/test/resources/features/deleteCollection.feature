Feature: Delete collections of NFT
  Background:
    Given a collection of NFT:
      | collectionID | title | listOfNFT    |
      |      01      | Food  | {01, 02, 03} |
      |      02      | Dog   | {04, 05}     |
      |      03      | Cat   | {06}         |

  Scenario: Delete collection successfully
    When a user deletes a collection "01"
    Then the collection gets deleted
    And a message shall be raised ""
    # Need to add message