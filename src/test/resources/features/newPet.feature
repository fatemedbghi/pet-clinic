Feature: New Pet

  Scenario: A pet is added to an owner
    Given A new owner with Id
    When Owner adds new pet to his pet list
    Then The new pet has its owner set
