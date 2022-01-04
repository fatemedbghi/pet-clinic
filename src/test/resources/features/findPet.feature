Feature: Find Pet

  Scenario: A pet is found by its Id
    Given A pet with Id
    When findPet with existing Id is called
    Then Pet is found with Id
