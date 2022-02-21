Feature: View Instagram Content
  As a user, I would like to view my Instagram content.

  Background:
    Given the following user accounts exist in the system
    |     email     | username | password |
    | joe@mcgill.ca | joe2022  | pssword1 |

  Scenario: Successfully view Instagram content
    Given "joe@mcgill.ca" is logged into the system
    Given "joe@mcgill.ca" is linked to an Instagram account
    When "joe@mcgill.ca" attempts to view his Instagram content
    Then "joe@mcgill.ca"'s Instagram content shall be displayed

  Scenario: Unsuccessfully view Instagram content when no account linked
    Given "joe@mcgill.ca" is logged into the system
    Given "joe@mcgill.ca" is not linked to an Instagram account
    When "joe@mcgill.ca" attempts to view his Instagram content
    Then an error message shall be raised "No Instagram account found"
    Then no Instagram content shall be displayed

