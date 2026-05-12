package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    // FIXED: actual nav container is div#abcd, not navbarNav
    private final By navContainer   = By.id("abcd");
    private final By signInButton   = By.id("btn1");
    private final By registerButton = By.id("btn2");  // "Skip Sign In"
    private final By emailInput     = By.id("email");
    private final By logo           = By.id("logo");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isNavDisplayed() {
        return isDisplayed(navContainer);
    }

    public boolean isLogoDisplayed() {
        return isDisplayed(logo);
    }

    public boolean isEmailInputDisplayed() {
        return isDisplayed(emailInput);
    }

    public RegisterPage clickRegister() {
        click(registerButton);
        return new RegisterPage(driver);
    }

    public void clickSignIn() {
        click(signInButton);
    }
}