Feature: Login as customer or admin
  As a registered user, I would like to be able to login to my NT-TEA Marketplace account

  Background:
    Given the following users exist in the system:
      | email  | password | attempts |
      | Email1 | apple    | 1        |
      | Email2 | grape    | 1        |
      | Email3 | grape    | 2        |
    Given the current date and time "2022-02-13T19:00:00.884097400"


  Scenario: Log in successfully
    When the registered user tries to log in with email "Email1", password "apple" and attempts "1"
    Then the registered user should be successfully logged in

  Scenario: Log in with an email that does not exist
    When the registered user tries to log in with email "Email4", password "apple" and attempts "1"
    Then the registered user should not be logged in
    Then an error message "Incorrect email/password" shall be raised

  Scenario: Log in with incorrect password
    When the registered user tries to log in with email "Email1" and password "grape"
    Then the registered user should not be logged in
    Then an error message "Incorrect email/password" shall be raised

  Scenario: Log in with incorrect password after third attempt
    When the registered user tries to log in with email "Email3", password "apple" and attempts "2"
    Then the registered user should not be logged in
    Then an error message "Wrong Password, account is locked out" shall be raised

  Scenario: Log in with incorrect password after third attempt
    Given the last attempt "2022-02-13T19:06:36.884097400"
    When the registered user tries to log in with email "Email3", password "grape" and attempts "3"
    Then the registered user should not be logged in
    Then an error message "Too many attempts, please try again later" shall be raised