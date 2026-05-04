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

        WebElement swaglabs = driver.findElement(By.className("login_logo"));
        String swaglabsstring = swaglabs.getText();

        swaglabs.isDisplayed();
        Assertions.assertEquals("Swag Labs", swaglabsstring);

//        X-PATH
        WebElement userName = driver.findElement(By.xpath("//input[@id='user-name']"));
        userName.sendKeys("standard_user");
        WebElement password = driver.findElement(By.xpath("(//input[@id='password'])[1]"));
        password.sendKeys("secret_sauce");
        WebElement submit = driver.findElement(By.xpath("(//input[@id='login-button'])[1]"));
        submit.submit();

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl(), "URL should match inventory page");

//        CLASSNAME
        WebElement header = driver.findElement(By.className("title"));
        Assertions.assertEquals("Products", header.getText());
        boolean isCartVisible = driver.findElement(By.className("shopping_cart_link")).isDisplayed();
        Assertions.assertTrue(isCartVisible);
    }

    @Test
    void lockedOutuser(){
        driver = new FirefoxDriver();
        driver.get("https://www.saucedemo.com/");

//        NAME
        WebElement userName = driver.findElement(By.name("user-name"));
        userName.sendKeys("locked_out_user");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("secret_sauce");
        WebElement submit = driver.findElement(By.className("submit-button"));
        submit.submit();

//        TAGNAME
        WebElement errorElement = driver.findElement(By.tagName("button"));
        WebElement errorContainer = errorElement.findElement(By.xpath("./parent::h3"));

        String errorMessage = errorContainer.getText();
        Assertions.assertTrue(errorMessage.contains("Sorry, this user has been locked out"));
    }

    @AfterEach
    void quit() {
        if (driver != null) driver.quit();
    }
}
