Feature: As a user, I want to view listings that corresponds to my inputs parameters

  Background:
    Given the following listings exist in the system:
      | listingID | title    | price    | nftLink | owner     |
      | 1         | titleA   | 100      | linkA   | JohnnyA   |
      | 2         | titleB   | 100      | linkB   | JohnnyB   |
      | 3         | titleB   | 100      | linkC   | JohnnyC   |
    And the following users exist in the system:
      | firstName | lastName | username | email  | password    | attempts |
      | JohnA     | DoeA     | JohnnyA  | Email1 | apple123    | 1        |
      | JohnB     | DoeB     | JohnnyB  | Email2 | grape123    | 1        |
      | JohnC     | DoeC     | JohnnyC  | Email3 | grape123    | 2        |

  Scenario: Search listing with valid listing ID
    When the user searches a listing with listing ID ""
    Then the listing "" should be return

  Scenario: Search listing with invalid listing ID
    When the user searches a listing with listing ID ""
    Then an error message shall be raised ""

  Scenario: Search listing with title
    When the user searches a listing with title ""
    Then a list of listings should be return

  Scenario: Search listing with no title
    When the user searches a listing with title ""
    Then an error message shall be raised ""

  Scenario: Search listing with valid Owner
    When the user searches a listing with Owner ""
    Then a list of listings should be return

  Scenario: Search listing with invalid Owner
    When the user searches a listing with NFT link ""
    Then an error message shall be raised ""

  Scenario: Search listing containing title
    When the user searches a listing containing the title ""
    Then a list of listings should be return
