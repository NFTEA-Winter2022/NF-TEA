Feature: Link Instagram account(s)
  As a registered and logged-in user,
  I would like to be able to link one or more Instagram accounts to my NF-TEA account
  So that I can create and sell NFTs

  Background:
    Given user is registered and logged-in

  Scenario: Link Instagram Account to my NF-TEA account (Normal Flow)
    When the user links his instagram account to his NF-TEA account through the API
    Then the browser should have a non-null shortIGToken cookie

  Scenario: Cannot Link an Invalid Instagram Account (Error Flow)
    When the user links his instagram account to his NF-TEA account with invalid credentials
    Then the browser should not have a shortIGToken cookie
