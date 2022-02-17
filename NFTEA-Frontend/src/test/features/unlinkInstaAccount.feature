Feature: Unlinking Instagram Account
  Scenario: Successfully unlinking Instagram
    Given a NF-Tea's account "Email1" with a linked Instagram Account
    When the user unlink their Instagram Account from their NF-Tea's account
    Then their Instagram account is not link to their NF-Tea's Account
