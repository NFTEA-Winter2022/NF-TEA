Feature:
  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1234 | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2234 | lol67890 | user2@gmail.com |
    Given the following listings exist in the system
      | title | price | nftLink | owner           |
      | nft1  | 230   | fj83skl | user1@gmail.com |
      | nft2  | 1232  | jsai3s9 | user2@gmail.com |

  Scenario: Seeing all the listings
    When an admin checks all the listings
    Then a list of listings should show