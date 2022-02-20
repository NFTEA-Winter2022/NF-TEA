Feature: Link Instagram account(s)
  As a registered and logged-in user,
  I would like to be able to link one or more Instagram accounts to my NF-TEA account
  So that I can create and sell NFTs

  Background:
    Given user is registered and logged-in

  Scenario: Link Instagram Account to my NF-TEA account (Normal Flow)
    When the user links his instagram account to his NF-TEA account
    And the user's instagram account has "<credentials>"
    Then the instagram account is connected to user's NF-TEA account

  Scenario: Link to Another Instagram Account to my NF-TEA Account (Alternate Flow)
    When the user links new instagram account to his NF-TEA account
    And the user already has an instagram account connected to his NF-TEA account
    And the user's new instagram account has "<credentials>"
    Then the new instagram account is connected to user's NF-TEA account

  Scenario: Cannot Link an Invalid Instagram Account (Error Flow)
    When the user links his instagram account to his NF-TEA account
    And the user's instagram account does not have "<credentials>"
    Then the instagram account is not connected to user's NF-TEA account