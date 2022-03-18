Feature: Edit Listing
  As a registered and logged-in User
  I would like to edit my listing
  So my listing is updated with all the correct information

  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1234 | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2234 | lol67890 | user2@gmail.com |
    Given the following listings exist in the system
      | title | price | nftLink | owner           |
      | nft1  | 230   | fj83skl | user1@gmail.com |
      | nft2  | 1232  | jsai3s9 | user2@gmail.com |

  Scenario: Edit listing title successfully
    Given the user is logged in to an account with username "User1234" and password "lol12345" and email "user1@gmail.com" to edit listing
    When the user tries to edit listing "nft1"'s title to "nft382"
    Then the listing's name shall be "nft382"
    Then no error message shall be raised for listings

  Scenario: Edit listing title unsuccessfully - listing does not exist
    Given the user is logged in to an account with username "User1234" and password "lol12345" and email "user1@gmail.com" to edit listing
    When the user tries to edit listing "nft45"'s title to "nft382"
    Then an error message "listing not found" shall be raised for listings

  Scenario: Edit listing title unsuccessfully - title field is empty
    Given the user is logged in to an account with username "User1234" and password "lol12345" and email "user1@gmail.com" to edit listing
    When the user tries to edit listing "nft1"'s title to ""
    Then the listing's name shall be "nft1"
    Then an error message "title cannot be empty" shall be raised for listings

  Scenario: Edit listing price successfully
    Given the user is logged in to an account with username "User1234" and password "lol12345" and email "user1@gmail.com" to edit listing
    When the user tries to edit listing "nft1"'s price to 38429
    Then the listing's price shall be 38429
    Then no error message shall be raised for listings

  Scenario: Edit listing price unsuccessfully - listing does not exist
    Given the user is logged in to an account with username "User1234" and password "lol12345" and email "user1@gmail.com" to edit listing
    When the user tries to edit listing "nft432"'s price to 38429
    Then an error message "listing not found" shall be raised for listings

  Scenario: Edit listing price unsuccessfully - negative price
    Given the user is logged in to an account with username "User1234" and password "lol12345" and email "user1@gmail.com" to edit listing
    When the user tries to edit listing "nft1"'s price to -232
    Then the listing's price shall be 230
    Then an error message "price cannot be negative" shall be raised for listings

  Scenario: Edit listing NFT link successfully
    Given the user is logged in to an account with username "User1234" and password "lol12345" and email "user1@gmail.com" to edit listing
    When the user tries to edit listing "nft1"'s NFT link to "sfam3us0"
    Then the listing's NFT link shall be "sfam3us0"
    Then no error message shall be raised for listings

  Scenario: Edit listing NFT link unsuccessfully - listing does not exist
    Given the user is logged in to an account with username "User1234" and password "lol12345" and email "user1@gmail.com" to edit listing
    When the user tries to edit listing "nft483"'s NFT link to "sfam3us0"
    Then an error message "listing not found" shall be raised for listings

  Scenario: Edit listing NFT link unsuccessfully - NFT link is empty
    Given the user is logged in to an account with username "User1234" and password "lol12345" and email "user1@gmail.com" to edit listing
    When the user tries to edit listing "nft1"'s NFT link to ""
    Then the listing's NFT link shall be "fj83skl"
    Then an error message "NFT link cannot be empty" shall be raised for listings