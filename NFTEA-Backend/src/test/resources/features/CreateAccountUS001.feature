Feature: Create A Customer account
  As a user, I would like to be able to create a NF-TEA Marketplace account

  Background:

    Scenario: Create account successfully
      Given I write my personal information correctly
      When I create an account
      Then My account should exist in the system

    Scenario: Create account unsuccessfully
      Given I write my personal information incorrectly
      When I create a bad account
      Then My account should not exist in the system