Feature: Change Password
  As a registered and logged-in User
  I would like to change my password
  So that my account has my new desired password

  Background:
    Given the following user accounts exist in the system
      | username | password |
      | User1    | lol      |
      | User2    | lol2     |
      | CodeX    | Dan      |

  Scenario Outline: Change Username Successfully (Normal Flow)
    Given the user is logged in to an account with username "<username>" and password "<password>"
    When I change "<password>" to "<newPassword>"
    Then the account shall have password "<newPassword>"

    Examples:
      | username | password | newPassword |
      | User1    | lol      | lol         |
      | User2    | lol2     | Code        |
      | CodeX    | Dan      | UrGirl      |

  Scenario Outline: Change Username with empty string
    Given the user is logged in to an account with username "<username>" and password "<password>"
    When I change "<password>" to "<newPassword>"
    Then the account shall have password "<password>"
    And an error message "<error>" shall be raised

    Examples:
      | username | password | newPassword | error                    |
      | User1    | lol      |             | Password cannot be empty |
      | User2    | lol2     |             | Password cannot be empty |
      | CodeX    | Dan      |             | Password cannot be empty |