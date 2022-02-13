
Feature: Logout  as customer or admin
  As a registered user, I would like to be able to logout of my NT-TEA Marketplace account

  Background:

  Scenario: Logout  successfully:
    Given there is an online user
    When the online registered user tries to logout
    Then the registered user should be successfully logged out

