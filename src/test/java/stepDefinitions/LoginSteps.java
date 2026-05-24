package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;

    @Before
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the saucedemo login page")
    public void iAmOnTheSaucedemoLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeRedirectedToTheInventoryPage() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl(), "URL does not match");
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedErrorMessage) {
        WebElement errorMessageContainer = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String actualErrorMessage = errorMessageContainer.getText();
        Assertions.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Error message is incorrect");
    }
}
