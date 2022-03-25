Feature: View Listings
  As a registered and logged-in User
  I would like to search other users
  To see see what listings they have

  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1111 | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2222 | lol67890 | user2@gmail.com |

  Scenario: Search user by numberID successfully
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com"
    When the user search for the user "User2222" with the numberID
    Then the user account returned shall have the username "User2222"
    Then no error message shall be raised for search user account

  Scenario: Search user by numberID unsuccessfully - does not exist
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com"
    When the user search for the user with the numberID 38293
    Then no user account shall be returned
    Then an error message "User does not exist" shall be raised for search user account

  Scenario: Search user by numberID unsuccessfully - does not exist
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com"
    When the user search for the user with a null numberID
    Then no user account shall be returned
    Then an error message "User Id required to search user." shall be raised for search user account

  Scenario: Search user by username successfully
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com"
    When the user search for the user "User2222"
    Then the user account returned shall have the username "User2222"
    Then no error message shall be raised for search user account

  Scenario: Search user by username unsuccessfully - does not exist
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com"
    When the user search for the user "superuser428"
    Then no user account shall be returned
    Then an error message "User does not exist" shall be raised for search user account

  Scenario: Search user by username unsuccessfully - empty string
    Given the user is logged in to an account with username "User1111" and password "lol12345" and email "user1@gmail.com"
    When the user search for the user ""
    Then no user account shall be returned
    Then an error message "Invalid username" shall be raised for search user account