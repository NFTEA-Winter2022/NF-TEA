Feature: Login as customer or admin
  As a registered user, I would like to be able to login to my NT-TEA Marketplace account

  Background:
    Given the following users exist in the system:
      | email  | password |
      | Email1 | apple    |
      | Email2 | grape    |

  Scenario: Log in successfully
    When the registered user tries to log in with email "Email1" and password "apple"
    Then the registered user should be successfully logged in

  Scenario: Log in with an email that does not exist
    When the registered user tries to log in with email "Email3" and password "apple"
    Then the registered user should not be logged in
    Then an error message "Email/password not found" shall be raised

  Scenario: Log in with incorrect password
    When the registered user tries to log in with username "Email1" and password "grape"
    Then the registered user should not be logged in
    Then an error message "Email/password not found" shall be raisedD