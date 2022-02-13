Feature: ChangeUsernameFeature
  As a registered and logged-in User
  I would like to change my username
  So that my account has my new desired username

  Background:
    Given the following user accounts exist in the system
      | username | password |
      | User1    | lol      |
      | User2    | lol2     |
      | CodeX    | Dan      |

  Scenario Outline: Change Username Successfully (Normal Flow)
    Given the user is logged in to an account with username "<username>"
    When the user tries to update account with a new username "<newUsername>"
    Then the account shall have username "<newUsername>"

    Examples:
      | username | newUsername |
      | User1    | CodeXMaster |
      | User2    | User2       |
      | CodeX    | CodeY       |

  Scenario Outline: Change Username Failed (Error Flow)
    Given the user is logged in to an account with username "<username>"
    When I change "<username>" to "<newUsername>"
    Then the account shall have username "<username>"
    And an error message "<error>" shall be raised

    Examples:
      | username        | newUsername | error                      |
      | User1           | User2       | Username not available     |
      | User2           | CodeX       | Username not available     |
      | CodeX           | User1       | Username not available     |

  Scenario Outline: Change Username with empty string
    Given the user is logged in to an account with username "<username>"
    When I change "<username>" to "<newUsername>"
    Then the account shall have username "<username>"
    And an error message "<error>" shall be raised

    Examples:
      | username        | newUsername | error                      |
      | User1           |             | Username cannot be empty   |
      | User2           |             | Username cannot be empty   |
      | CodeX           |             | Username cannot be empty   |