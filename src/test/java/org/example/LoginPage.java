package org.example;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        inputText(Locators.USERNAME_FIELD, username);
        inputText(Locators.PASSWORD_FIELD, password);
        clickElement(Locators.LOGIN_BUTTON);
    }
}