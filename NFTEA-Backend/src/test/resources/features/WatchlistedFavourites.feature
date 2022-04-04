Feature: Watchlist Favorites
  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1111 | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2222 | lol67890 | user2@gmail.com |
    Given the following listings exist in the system
      | title | price | nftLink | owner           |
      | nft1  | 230   | fj83skl | user1@gmail.com |
      | nft2  | 1232  | jsai3s9 | user2@gmail.com |

  Scenario: Creating a favourite listing
    When a user "user1@gmail.com" creates a favourite listing "fj83skl"
    Then the listing "fj83skl" will be favoured in the user "user1@gmail.com"

  Scenario: Delete a favourite listing
    When deleting a favourite listing "fj83skl"
    Then the listing of user "user1@gmail.com" will not be favoured

  Scenario: Delete a favourite listing from a user
    When a user "user1@gmail.com" deletes a favourite listing "fj83skl"
    Then the listing "fj83skl" will not be favoured in the user "user1@gmail.com"
