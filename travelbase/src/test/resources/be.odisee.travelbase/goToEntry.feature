Feature: Go To Entry

It should be possible to go to the entry page starting from the home page.

Scenario:
Given I am on the home page
When I enter "user" in the username field
And I enter "user" in the password field
And I press on the Entry button
  Then I should be on the Entry page




  Scenario: Add one EvaluatieFiche
    Given I am on the Entry page where I can add a new Entry
    When I select EF_BXL_Atomium in the BXL_Atomium field
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
