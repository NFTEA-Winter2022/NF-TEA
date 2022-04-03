Feature: Create Trade Offer
  As a registered and logged-in user
  I would like to be able to submit a trade offer to another marketplace user
  So I can sell my NFT

  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1111 | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2222 | lol67890 | user2@gmail.com |
    Given the following listings exist in the system
      | title | price | nftLink | owner           |
      | nft1  | 230   | fj83skl | user1@gmail.com |
      | nft2  | 1232  | jsai3s9 | user2@gmail.com |

  Scenario: Create Trade Offer Successfully
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com"
    When the user with id "123" tries to create a trade off to user "456" with a listing id of "789" with a price "10" from address "0x00"
    Then the trade off shall be successfully created