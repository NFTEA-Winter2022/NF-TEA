Feature: ChangeUsernameFeature
  As a registered and logged-in User
  I would like to change my username
  So that my account has my new desired username

  Background:
    Given the following user accounts exist in the system
      | firstname | lastname | username | password | email           |
      | Dan       | Hosi     | User1    | lol12345 | user1@gmail.com |
      | Harsh     | Patel    | User2    | lol67890 | user2@gmail.com |
      | Ehsan     | Ahmed    | CodeX    | Dan12345 | codex@gmail.com |

  Scenario Outline: Change Username Successfully (Normal Flow)
    Given the user is logged in to an account with username "<username>" and password "<password>" and email "<email>"
    When the user updates his account with a new username "<newUsername>"
    Then the account shall have username "<newUsername>"

    Examples:
      | username | newUsername | password | email           |
      | User1    | CodeXMaster | lol12345 | user1@gmail.com |
      | User2    | Dragon      | lol67890 | user2@gmail.com |
      | CodeX    | CodeY       | Dan12345 | codex@gmail.com |

  Scenario Outline: Change Username Failed (Error Flow)
    Given the user is logged in to an account with username "<username>" and password "<password>" and email "<email>"
    When the user updates his account with a new username "<newUsername>"
    But the new username "<newUsername>" is invalid
    Then the account shall have username "<username>"
    And an error message "<error>" shall be raised

    Examples:
      | username | newUsername | password | email           | error                    |
      | User1    | CodeX       | lol12345 | user1@gmail.com | Username not available   |
      | User2    | User1       | lol67890 | user2@gmail.com | Username not available   |
      | CodeX    |             | Dan12345 | codex@gmail.com | Username cannot be empty |