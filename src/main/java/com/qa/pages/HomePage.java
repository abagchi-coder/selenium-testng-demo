package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By registerLink  = By.linkText("Register");
    private final By pageHeading   = By.tagName("h1");
    private final By navBar        = By.id("navbarNav");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isNavBarDisplayed() {
        return isDisplayed(navBar);
    }

    public String getHeadingText() {
        return getText(pageHeading);
    }

    public RegisterPage clickRegister() {
        click(registerLink);
        return new RegisterPage(driver);
    }
}
