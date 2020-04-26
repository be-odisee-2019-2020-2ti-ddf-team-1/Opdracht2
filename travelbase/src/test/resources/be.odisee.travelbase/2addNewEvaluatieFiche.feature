Feature: add new EvaluatieFiche

  Scenario: Add one EvaluatieFiche
    Given I am on the EvaluatieFiche page
    When I select BXL_Atomium in the activiteit field
    And I select the date of today in the Date field
    And I enter "dit is feedback" in the Feedback field
    And I enter "dit is een oordeel" in the Oordeel field
    And I enter "dit is een beoordeling" in the Beoordeling field
    And I press on the Submit button
    Then I should see the following on the screen
      | 24-04-2020             |
      | BXL_Atomium            |
      | EF_BXL_Atomium         |
      | dit is feedback        |
      | dit is een oordeel     |
      | dit is een beoordeling |