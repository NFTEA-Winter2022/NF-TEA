Feature: Delete Account
  As a user
  I would like to be able to delete my NF-TEA Marketplace account
  So that my user data is removed and the account closed

  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1    | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2    | lol67890 | user2@gmail.com |
      | Ehsan     | Ahmed    | CodeX    | Dan12345 | codex@gmail.com |
    Given user "User1" is currently logged in

    Scenario: Delete Account Normally (Normal Flow)
      When the user attempts to delete the account with the correct password "lol12345"
      Then the account shall be deleted

    Scenario: Delete Account Wrong Password (Error Flow)
      When the user attempts to delete the account with the wrong password "lol12345777"
      Then the account shall not be deleted