Feature: Create NFT collection
  As the marketplace owner,
  I would like to receive 2% of each transaction amount on the business wallet
  so that I can turn a profit.

  Background:
    Given the NFT smart Contract has been deployed
    Given a user has created an NFT

  Scenario: Buyer Initiated Transactions (Normal Flow)
    When a second user purchases the NFT
    Then the product owner wallet should collect 2% of the fees.

  Scenario: Seller Initiated Transaction (Alternate Flow)
    Given a second user has proposed a bid on the NFT
    When the NFT's owner accepts the bid
    Then the product owner wallet should collect 2% of the fees.
