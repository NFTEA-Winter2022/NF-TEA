Feature: Create Listing
  As a registered and logged-in User with at least one NFT linked to my account
  I would like to create a listing on the marketplace
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

  Scenario: Create Listing Successfully
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com"
    When the user tries to create a listing with title "nft3" and price "500" and nftLink "fr54er4" and email "user1@gmail.com"
    Then the listing shall be created successfully
    And no error message shall be raised for listings

  Scenario: Create Listing Missing Information
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com"
    When the user tries to create a listing with title "nft4" and price "500" and email "user1@gmail.com"
    Then no listing shall be created
    And the error message "Title and/or NFTLink cannot be empty" shall be raised

  Scenario: Create Listing Missing User
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com"
    When the user tries to create a listing with title "nft4" and price "500" and nftLink "abcdef"
    Then no listing shall be created
    And the error message "Missing user" shall be raised