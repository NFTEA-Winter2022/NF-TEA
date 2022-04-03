Feature: View Transaction Receipt
  As a registered and logged-in User
  I would like to view my transaction receipt
  To see my history of NFT bought or sold

  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1111 | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2222 | lol67890 | user2@gmail.com |

    Given the following transactions exist in the system
      | buyerEmail      | sellerEmail     | title | price | nftLink | discountedPrice |
      | user1@gmail.com | user2@gmail.com | nft1  | 32    | aejlk38 | 32              |
      | user2@gmail.com | user1@gmail.com | nft2  | 48    | fjik38  | 40              |
      | user1@gmail.com | user2@gmail.com | nft3  | 1000  | ksauk38 | 999             |

  Scenario: View Transaction Receipt as Buyer
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com" for transaction
    When the user tries to view transaction receipt for nfts he bought
    Then the list of transaction shall have length 2
    Then the list shall contain an entry for the listing "nft1"
    Then the list shall contain an entry for the listing "nft3"
    Then no error message shall be raised for transaction

  Scenario: View Transaction Receipt as Seller
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com" for transaction
    When the user tries to view transaction receipt for nfts he sold
    Then the list of transaction shall have length 1
    Then the list shall contain an entry for the listing "nft2"
    Then no error message shall be raised for transaction