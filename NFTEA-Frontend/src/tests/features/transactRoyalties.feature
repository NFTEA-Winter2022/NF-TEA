Feature: Create NFT collection
  As the marketplace owner,
  I would like to receive 2% of each transaction amount on the business wallet
  so that I can turn a profit.

  Background:
    Given NFT smart Contract has been deployed
    Given first user has created an NFT
    Given a second user purchases the NFT from the first user

  Scenario: Buyer Initiated Transactions (Normal Flow)
    When a third user purchases the NFT
    Then the first user wallet should collect 1% of the fees.