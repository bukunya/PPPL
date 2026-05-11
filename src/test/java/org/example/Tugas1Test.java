package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

public class Tugas1Test {
    WebDriver driver;
    Actions actions;

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    void testHover() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement firstProfile = driver.findElement(By.xpath("(//div[@class='figure'])[1]"));
        actions.moveToElement(firstProfile).perform();

        WebElement nameLabel = driver.findElement(By.xpath("(//div[@class='figcaption']/h5)[1]"));
        Assertions.assertEquals("name: user1", nameLabel.getText());
    }

    @Test
    void testKeyPresses() {
        driver.get("https://the-internet.herokuapp.com/key_presses");
        WebElement inputField = driver.findElement(By.id("target"));
        inputField.sendKeys(Keys.SHIFT);

        WebElement resultText = driver.findElement(By.id("result"));
        Assertions.assertEquals("You entered: SHIFT", resultText.getText());
    }

    @Test
    void testDragAndDrop() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        String script = "function simulateDragDrop(s, t) {" +
                "  const dt = new DataTransfer();" +
                "  s.dispatchEvent(new DragEvent('dragstart', { dataTransfer: dt }));" +
                "  t.dispatchEvent(new DragEvent('drop', { dataTransfer: dt }));" +
                "  s.dispatchEvent(new DragEvent('dragend', { dataTransfer: dt }));" +
                "} " +
                "simulateDragDrop(arguments[0], arguments[1]);";

        ((JavascriptExecutor) driver).executeScript(script, source, target);
        Assertions.assertEquals("B", source.findElement(By.tagName("header")).getText());
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}