Feature: View Listings
  As a registered and logged-in User
  I would like to view listings
  To see which NFT's are for sale


  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1111 | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2222 | lol67890 | user2@gmail.com |

    Given the following listings exist in the system
      | title | price | nftLink | owner           |
      | nft1  | 230   | fj83skl | user1@gmail.com |
      | nft2  | 1232  | jsai3s9 | user1@gmail.com |

  Scenario: View listing successfully
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com" to view listings
    When the user tries to to view listings
    Then the total listings shall be 2
    Then the listings provided shall contain "nft1"
    Then the listings provided shall contain "nft2"
    Then no error message shall be raised for listings

  Scenario: View listing successfully - filter by title
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com" to view listings
    When the user tries to to view listings by title "nft1"
    Then the total listings shall be 1
    Then no error message shall be raised for listings

  Scenario: View listing successfully - filter by price
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com" to view listings
    When the user tries to to view listings by price 230
    Then the total listings shall be 1
    Then the listings provided shall contain "nft1"
    Then no error message shall be raised for listings

  Scenario: View listing successfully - filter by NFT link
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com" to view listings
    When the user tries to to view listings by NFT link "fj83skl"
    Then the total listings shall be 1
    Then the listings provided shall contain "nft1"
    Then no error message shall be raised for listings