Feature: Accept Trade Offer
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
    And the following tradeOffers exist in the system:
      | senderID  | receiverID | listingID | price  |
      | Email1    | Email2     | linkA     | 100    |

  Scenario: User accepts trade offer
    Given a tradeOffer is OnGoing
    When a tradeOffer tries to get accepted
    Then the tradeOffer is Accepted

  Scenario: User accepts a declined trade offer
    Given a tradeOffer is declined
    When a tradeOffer tries to get accepted
    Then an error message shall be raised "TradeOffer already declined"

  Scenario: User accepts an already accepted trade offer
    Given a tradeOffer is accepted
    When a tradeOffer tries to get accepted
    Then an error message shall be raised "TradeOffer already accepted"