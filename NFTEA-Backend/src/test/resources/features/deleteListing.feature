Feature: Delete Listing
  As a registered and logged-in User
  I would like to delete my listing
  Because I no longer want to sell my NFT

  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1111 | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2222 | lol67890 | user2@gmail.com |
    Given the following listings exist in the system
      | title | price | nftLink | owner           |
      | nft1  | 230   | fj83skl | user1@gmail.com |
      | nft2  | 1232  | jsai3s9 | user2@gmail.com |

  Scenario: Delete listing successfully
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com" to delete listing
    When the user tries to delete the listing "nft1"
    Then the listing shall be deleted successfully
    Then no error message shall be raised for listings

  Scenario: Delete listing unsuccessfully - listing does not exist
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com" to delete listing
    When the user tries to delete the listing "nft4"
    Then an error message "listing not found" shall be raised for listings
