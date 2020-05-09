Feature: Go To EvaluatieFiche

  It should be possible to go to the entry page starting from the home page.

  Scenario:
    Given I am on the home page
    When I enter "user" in the username field
    And I enter "user" in the password field
    And I press on the Entry button
    Then I should be on the EvaluatieFiche page