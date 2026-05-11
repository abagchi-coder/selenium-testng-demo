package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private final By firstNameField  = By.xpath("//input[@placeholder='First Name']");
    private final By lastNameField   = By.xpath("//input[@placeholder='Last Name']");
    private final By emailField      = By.xpath("//input[@type='email']");
    private final By phoneField      = By.xpath("//input[@placeholder='Mob']");
    private final By maleRadio       = By.xpath("//input[@value='Male']");
    private final By submitButton    = By.xpath("//input[@type='submit']");
    private final By formContainer   = By.className("form-group");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage enterFirstName(String name) {
        type(firstNameField, name);
        return this;
    }

    public RegisterPage enterLastName(String name) {
        type(lastNameField, name);
        return this;
    }

    public RegisterPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    public RegisterPage enterPhone(String phone) {
        type(phoneField, phone);
        return this;
    }

    public RegisterPage selectMale() {
        click(maleRadio);
        return this;
    }

    public RegisterPage clickSubmit() {
        click(submitButton);
        return this;
    }

    public boolean isFormDisplayed() {
        return isDisplayed(formContainer);
    }
}
