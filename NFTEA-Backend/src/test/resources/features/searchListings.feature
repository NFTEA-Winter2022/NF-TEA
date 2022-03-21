Feature: As a user, I want to view listings that corresponds to my inputs parameters

  Background:
    Given the following users exist:
      | firstName | lastName | username | email  | password    | attempts |
      | JohnA     | DoeA     | JohnnyA  | Email1 | apple123    | 0        |
      | JohnB     | DoeB     | JohnnyB  | Email2 | grape123    | 0        |
      | JohnC     | DoeC     | JohnnyC  | Email3 | grape123    | 0        |
    And the following listings exist in the system:
      | listingID | title    | price    | nftLink | owner    |
      | 1         | titleA   | 100      | linkA   | Email1   |
      | 2         | titleB   | 200      | linkB   | Email2   |
      | 3         | titleB   | 300      | linkC   | Email3   |
      | 4         | titleD   | 400      | linkD   | Email3   |

  Scenario: Search listing with title
    When the user searches a listing with title "titleA"
    Then a list of listings should be return

  Scenario: Search listing with valid Owner
    When the user searches a listing with Owner "Email3"
    Then a list of listings with the same owner should be return

  Scenario: Search listing containing title
    When the user searches a listing containing the title "title"
    Then a list of listings with similar titles should be return

  Scenario: Search listing with NFT links
    When the user searches a listing with NFT link "linkA"
    Then a listing should be return with NFT link "linkA"