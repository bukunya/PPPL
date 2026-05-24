Feature: Saucedemo Login

  @positive @login
  Scenario: Successful login with valid credentials
    Given I am on the saucedemo login page
    When I enter username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be redirected to the inventory page

  @negative @login
  Scenario: Failed login with locked out user
    Given I am on the saucedemo login page
    When I enter username "locked_out_user" and password "secret_sauce"
    And I click the login button
    Then I should see an error message "Epic sadface: Sorry, this user has been locked out."
