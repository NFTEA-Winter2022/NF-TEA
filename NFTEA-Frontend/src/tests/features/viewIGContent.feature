Feature: View Instagram Content
  As a user, I would like to view my Instagram content.

  Background:
    Given user is registered and logged-in

  Scenario: Successfully view Instagram content
    Given the account is linked to an Instagram account
    When the user attempts to view his Instagram content
    Then the user's Instagram content shall be displayed

  Scenario: Unsuccessfully view Instagram content when no account linked
    Given the account is not linked to an Instagram account
    When the user attempts to view his Instagram content
    Then an error message shall be raised "No Instagram account found"

