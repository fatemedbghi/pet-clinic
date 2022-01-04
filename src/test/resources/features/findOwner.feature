Feature: Find Owner

  Scenario: An owner is found by its Id
    Given An owner with Id
    When findOwner with existing Id is called
    Then Owner is found with Id
