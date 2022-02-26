Feature: Create NFT

  Scenario: Successfully Create NFT
    Given A NFT contract has been deployed
    And An user account is registered on the blockchain
    When A new NFT is created with id 1 by the user
    Then The smart contract holds an NFT with id 1
    And The NFT's publisher is the user
