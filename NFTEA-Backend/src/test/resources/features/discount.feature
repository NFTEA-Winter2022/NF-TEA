Feature: Create discount on NFT
  As a registered and logged-in User
  I would like to put discounts on my listings
  when I list an NFT

  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1111 | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2222 | lol67890 | user2@gmail.com |

    Given the following listings exist in the system
      | title | price | nftLink | owner           |
      | nft1  | 230   | fj83skl | user1@gmail.com |
      | nft2  | 1232  | jsai3s9 | user2@gmail.com |

    Scenario: Put Discount Succefully
      Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com" for the purpose of putting a discount
      When the user tries to put a discount of "25" percent on the listing "nft1"
      Then the discounted price will be "172.5"
