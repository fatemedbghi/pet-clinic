Feature: Save Pet

  Scenario: Save an existing pet
    Given An owner and a pet with Id
    When Owner adds an existing pet to his pet list
    Then The pet has its owner set
