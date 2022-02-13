Feature: Change Password
  As a registered and logged-in User
  I would like to change my password
  So that my account has my new desired password

  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1    | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2    | lol67890 | user2@gmail.com |
      | Ehsan     | Ahmed    | CodeX    | Dan12345 | codex@gmail.com |

  Scenario Outline: Change Password Successfully (Normal Flow)
    Given the user is logged in to an account with username "<username>" and password "<password>" and email "<email>"
    When the user updates his account with "<newPassword>" and confirms password "<confirmPassword>"
    Then the account shall have password "<newPassword>"

    Examples:
      | username | password | newPassword | confirmPassword | email           |
      | User1    | lol12345 | lol67890    | lol67890        | user1@gmail.com |
      | User2    | lol67890 | Dan12345    | Dan12345        | user2@gmail.com |
      | CodeX    | Dan12345 | UrGirl123   | UrGirl123       | codex@gmail.com |


  Scenario Outline: Change Password No Match
    Given the user is logged in to an account with username "<username>" and password "<password>" and email "<email>"
    When the user updates his account with "<newPassword>" and confirms password "<confirmPassword>"
    But the new password "<newPassword>" and confirm password "<confirmPassword>" do not match
    Then the account shall have password "<password>"
    And an error message "<error>" shall be raised

    Examples:
      | username | password | newPassword | confirmPassword | email           | error                     |
      | User1    | lol12345 | lol67890    |                 | user1@gmail.com | Passwords cannot be empty |
      | User2    | lol67890 |             | Dan12345        | user2@gmail.com | Passwords cannot be empty |
      | CodeX    | Dan12345 | UrGirl123   | UrGirl4Ever     | codex@gmail.com | Passwords do not match    |