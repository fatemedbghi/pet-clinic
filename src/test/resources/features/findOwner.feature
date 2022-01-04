Feature: Find Owner

  Scenario:
    Given An owner with Id
    When findOwner with existing Id is called
    Then Owner is found with Id
