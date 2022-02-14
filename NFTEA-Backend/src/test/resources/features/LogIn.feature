Feature: Login as customer or admin
  As a registered user, I would like to be able to login to my NT-TEA Marketplace account

  Background:
    Given the following users exist in the system:
      | email  | password    | attempts |
      | Email1 | apple123    | 1        |
      | Email2 | grape123    | 1        |
      | Email3 | grape123    | 2        |
    And the current date and time "2022-02-13 18:00:00"


  Scenario: Log in successfully
    Given the registered user is not logged in with "Email1"
    And "Email1" has "1" attempts
    When the registered user tries to log in with email "Email1", password "apple"
    Then the registered user should be successfully logged in
    And "Email1" should have "0" attempts

  Scenario: Log in while already logged in
    Given the registered user is logged in with "Email1"
    And "Email1" has "1" attempts
    When the registered user tries to log in with email "Email1", password "apple"
    Then the registered user should be successfully logged in
    And "Email1" should have "0" attempts

  Scenario: Log in with an email that does not exist
    When the registered user tries to log in with email "Email4", password "apple"
    Then the registered user should not be logged in
    And an error message "Incorrect email/password" shall be raised

  Scenario: Log in with incorrect password
    Given "Email1" has "1" attempts
    When the registered user tries to log in with email "Email1" and password "grape"
    Then the registered user should not be logged in
    And an error message "Incorrect email/password" shall be raised
    And "Email1" should have "2" attempts
    And "Email1"'s most recent attempt should be at "2022-02-13 18:00:00"

  Scenario: Log in with incorrect password after second attempt
    Given "Email3" has "2" attempts
    When the registered user tries to log in with email "Email3", password "apple"
    Then the registered user should not be logged in
    And an error message "Wrong Password, account is locked out" shall be raised
    And "Email3" should have "3" attempts
    And "Email3"'s most recent attempt should be at "2022-02-13 18:00:00"

  Scenario: Log in with incorrect password after third attempt while account is locked
    Given "Email3" has "3" attempts
    And the last attempt "2022-02-13 17:46:36"
    When the registered user tries to log in with email "Email3", password "apple" and attempts "3"
    Then the registered user should not be logged in
    And an error message "Too many attempts, please try again later" shall be raised
    And "Email3" should have "3" attempts

  Scenario: Log in with correct password after third attempt while account is locked
    Given "Email3" has "3" attempts
    And the last attempt "2022-02-13 17:46:36"
    When the registered user tries to log in with email "Email3", password "grape" and attempts "3"
    Then the registered user should not be logged in
    And an error message "Too many attempts, please try again later" shall be raised
    And "Email3" should have "3" attempts

  Scenario: Log in with incorrect password after third attempt after cool down elapsed
    Given "Email3" has "3" attempts
    And the last attempt "2022-02-13 17:30:36"
    When the registered user tries to log in with email "Email3", password "apple" and attempts "3"
    Then the registered user should not be logged in
    And an error message "Wrong Password, account is locked out" shall be raised
    And "Email3" should have "4" attempts
    And "Email3"'s most recent attempt should be at "2022-02-13 18:00:00"

  Scenario: Log in with correct password after third attempt after cool down elapsed
    Given "Email3" has "3" attempts
    And the last attempt "2022-02-13 17:30:36"
    When the registered user tries to log in with email "Email3", password "grape" and attempts "3"
    Then the registered user should be successfully logged in
    And "Email3" should have "0" attempts