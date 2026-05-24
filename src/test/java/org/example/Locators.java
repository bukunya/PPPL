package org.example;

import org.openqa.selenium.By;

public class Locators {
    public static final By USERNAME_FIELD = By.id("user-name");
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login-button");

    public static final By INVENTORY_TITLE = By.className("title");
    public static final By SHOPPING_CART = By.className("shopping_cart_link");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
}