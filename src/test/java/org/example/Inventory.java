package org.example;

import org.openqa.selenium.WebDriver;

public class Inventory extends BasePage {
    public Inventory(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        return getElementText(Locators.INVENTORY_TITLE);
    }

    public boolean isCartVisible() {
        return isElementDisplayed(Locators.SHOPPING_CART);
    }

    public String getErrorMessage() {
        return getElementText(Locators.ERROR_MESSAGE);
    }
}