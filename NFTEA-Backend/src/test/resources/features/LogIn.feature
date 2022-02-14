Feature: Login as customer or admin
  As a registered user, I would like to be able to login to my NT-TEA Marketplace account

  Background:
    Given the following users exist in the system:
      | firstName | lastName | username | email  | password    | attempts |
      | JohnA     | DoeA     | JohnnyA  | Email1 | apple123    | 1        |
      | JohnB     | DoeB     | JohnnyB  | Email2 | grape123    | 1        |
      | JohnC     | DoeC     | JohnnyC  | Email3 | grape123    | 2        |
    And the current date and time "2022-02-13 18:00:00"


  Scenario: Log in successfully
    Given the registered user is not logged in with "Email1"
    And "Email1" has "1" attempts
    When the registered user tries to log in with email "Email1", password "apple123"
    Then the registered user should be successfully logged in
    And "Email1" should have "0" attempts

  Scenario: Log in while already logged in
    Given the registered user is logged in with "Email1"
    And "Email1" has "1" attempts
    When the registered user tries to log in with email "Email1", password "apple123"
    Then the registered user should be successfully logged in
    And "Email1" should have "0" attempts

  Scenario: Log in with an email that does not exist
    When the registered user tries to log in with email "Email4", password "apple123"
    Then the registered user should not be logged in
    And an error message shall be raised "Incorrect email/password"

  Scenario: Log in with incorrect password
    Given "Email1" has this amount of attempts "1"
    When the registered user tries to log in with email "Email1" and password "grape123"
    Then the registered user should not be logged in with "Email1"
    And an error message shall be raised "Incorrect email/password"
#    And "Email1" should have "2" attempts
    And "Email1"'s most recent attempt should be at "2022-02-13 18:00:00"

  Scenario: Log in with incorrect password after second attempt
    Given "Email3" has "2" attempts
    When the registered user tries to log in with email "Email3", password "apple123"
    Then the registered user should not be logged in
    And an error message shall be raised "Wrong Password, account is locked out"
    And "Email3" should have "3" attempts
    And "Email3"'s most recent attempt should be at "2022-02-13 18:00:00"

  Scenario: Log in with incorrect password after third attempt while account is locked
    Given "Email3" has "3" attempts and the last attempt "2022-02-13 17:46:36"
    When the registered user tries to log in with email "Email3" and password "apple123"
    Then the registered user should not be logged in
    And an error message shall be raised "Too many attempts, please try again later"
    And "Email3" should have "3" attempts

  Scenario: Log in with incorrect password after third attempt after cool down elapsed
    Given "Email3" has "3" attempts and the last attempt "2022-02-13 17:30:36"
    When the registered user tries to log in with email "Email3" and password "apple123"
    Then the registered user should not be logged in
    And an error message shall be raised "Wrong Password, account is locked out"
    And "Email3" should have "4" attempts
    And "Email3"'s most recent attempt should be at "2022-02-13 18:00:00"

  Scenario: Log in with correct password after third attempt after cool down elapsed
    Given "Email3" has "3" attempts and the last attempt "2022-02-13 17:30:36"
    When the registered user tries to log in with email "Email3" and password "grape123"
    Then the registered user should be successfully logged in
    And "Email3" should have "0" attempts