package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainTest {
    WebDriver driver;

    @Test
    void validTest(){
        driver = new FirefoxDriver();
        driver.get("https://www.saucedemo.com/");
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        WebElement submit = driver.findElement(By.id("login-button"));
        submit.submit();

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl(), "URL should match inventory page");

        WebElement header = driver.findElement(By.className("title"));
        Assertions.assertEquals("Products", header.getText());

        boolean isCartVisible = driver.findElement(By.className("shopping_cart_link")).isDisplayed();
        Assertions.assertTrue(isCartVisible);
    }

    @Test
    void lockedOutuser(){
        driver = new FirefoxDriver();
        driver.get("https://www.saucedemo.com/");
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("locked_out_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        WebElement submit = driver.findElement(By.id("login-button"));
        submit.submit();

        WebElement errorElement = driver.findElement(By.cssSelector("[data-test='error']"));
        String errorMessage = errorElement.getText();

        Assertions.assertTrue(errorMessage.contains("Sorry, this user has been locked out"));
    }

    @AfterEach
    void quit() {
        if (driver != null) driver.quit();
    }
}
