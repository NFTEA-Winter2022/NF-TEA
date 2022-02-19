Feature: Unlinking Instagram Account

  Scenario: Successfully unlinking Instagram Account
    Given a NF-Tea's account "Email1" with a linked Instagram Account
    When the user unlink their Instagram Account from their NF-Tea's account
    When the Instagram Account is successfully unlinked from their account
    Then a message shall be raised "Successfully unlinked"
    Then their Instagram account is not link to their NF-Tea's Account

  Scenario: Unsuccessfully unlinking Instagram Account
    Given a NF-Tea's account "Email1" with a linked Instagram Account
    When the user unlink their Instagram Account from their NF-Tea's account
    When the Instagram Account is unsuccessfully unlinked from their account
    Then an error shall be raised "Please try again"
    Then their Instagram account is linked to their NF-Tea's Account