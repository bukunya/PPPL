package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestPage {
    WebDriver driver;
    LoginPage loginPage;
    Inventory inventory;

    @BeforeEach
    void init() {
        driver = new FirefoxDriver();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        inventory = new Inventory(driver);
    }

    @Test
    void validTest() {
        loginPage.login("standard_user", "secret_sauce");

        Assertions.assertEquals("Products", inventory.getHeaderText());
        Assertions.assertTrue(inventory.isCartVisible());
    }

    @Test
    void lockedOutUserTest() {
        loginPage.login("locked_out_user", "secret_sauce");

        String error = inventory.getErrorMessage();
        Assertions.assertTrue(error.contains("locked out"));
    }

    @AfterEach
    void quit() {
        if (driver != null) driver.quit();
    }
}