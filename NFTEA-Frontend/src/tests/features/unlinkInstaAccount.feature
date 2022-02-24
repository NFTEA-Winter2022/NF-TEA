Feature: Unlinking Instagram Account

  Scenario: Successfully unlinking Instagram Account
    Given a NF-Tea's account with a linked Instagram Account
    When the user unlink their Instagram Account from their NF-Tea's account
    And the Instagram Account is successfully unlinked from their account
    Then their Instagram account is not link to their NF-Tea's Account
    And a message will appear "Successfully unlinked"

#  Scenario: Unsuccessfully unlinking Instagram Account
#    Given a NF-Tea's account with a linked Instagram Account
#    When the user unsuccessfully unlink their Instagram Account from their NF-Tea's account
#    And the Instagram Account is unsuccessfully unlinked from their account
#    Then their Instagram account is linked to their NF-Tea's Account
#    And a message will appear "Please try again"