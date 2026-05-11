package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Tugas2Test {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void testCase1_NoSuchElementFix() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        driver.findElement(By.id("add_btn")).click();
        WebElement row2Input = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='row2']/input")));
        row2Input.sendKeys("Pizza");
        Assertions.assertTrue(row2Input.isDisplayed());
    }

    @Test
    void testCase5_TimeoutFix() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        driver.findElement(By.id("add_btn")).click();
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement row2Input = shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        Assertions.assertTrue(row2Input.isDisplayed());
    }

    @Test
    void testCase2_ElementNotInteractableFix() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        driver.findElement(By.id("add_btn")).click();
        WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        row2Input.sendKeys("Pasta");
        Assertions.assertEquals("Pasta", row2Input.getAttribute("value"));
    }

    @Test
    void testCase3_InvalidElementStateFix() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        driver.findElement(By.id("edit_btn")).click();
        WebElement row1Input = driver.findElement(By.xpath("//div[@id='row1']/input"));
        row1Input.clear();
        row1Input.sendKeys("Burger");
        Assertions.assertEquals("Burger", row1Input.getAttribute("value"));
    }

    @Test
    void testCase4_StaleElementReferenceFix() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        WebElement instructions = driver.findElement(By.id("instructions"));
        Assertions.assertTrue(instructions.isDisplayed());

        driver.findElement(By.id("add_btn")).click();
        boolean isGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions")));
        Assertions.assertTrue(isGone);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}