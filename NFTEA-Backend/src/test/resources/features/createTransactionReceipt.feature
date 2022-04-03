Feature: Create transaction Receipt
  As a registered and logged-in User
  I would like to receive a transaction receipt
  when I buy an NFT

  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1111 | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2222 | lol67890 | user2@gmail.com |
    Given the following listings exist in the system
      | title | price | nftLink | owner           |
      | nft1  | 230   | fj83skl | user1@gmail.com |
      | nft2  | 1232  | jsai3s9 | user2@gmail.com |

  Scenario: Create Transaction Receipt successfully
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com" for transaction
    When the user tries to buy the nft listed under "nft2"
    Then a transaction receipt for the listing "nft2" shall be generated
    Then the listing "nft2" shall no longer exist
    Then no error message shall be raised for transaction

  Scenario: Create Transaction Receipt unsuccessfully - listing does not exist
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com" for transaction
    When the user tries to buy the nft listed under "nft4"
    Then no new transaction receipt shall be generated
    Then an error message "Listing not found" shall be raised for transaction