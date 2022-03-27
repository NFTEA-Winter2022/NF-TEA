Feature: Backend Notifications

  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1    | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2    | lol67890 | user2@gmail.com |
      | Ehsan     | Ahmed    | CodeX    | Dan12345 | codex@gmail.com |


  Scenario: Notification exist when Listing has been made