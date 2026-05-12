package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class RegisterPage extends BasePage {

    // Text inputs — use ng-model since no id/placeholder on most fields
    private final By firstNameField    = By.xpath("//input[@placeholder='First Name']");
    private final By lastNameField     = By.xpath("//input[@placeholder='Last Name']");
    private final By emailField        = By.xpath("//input[@ng-model='EmailAdress']");
    private final By phoneField        = By.xpath("//input[@ng-model='Phone']");

    // Gender — two radio buttons by name, select by index
    private final By genderRadios      = By.name("radiooptions");

    // Hobbies — checkboxes by id
    private final By hobby1            = By.id("checkbox1");
    private final By hobby2            = By.id("checkbox2");
    private final By hobby3            = By.id("checkbox3");

    // Password fields
    private final By passwordField     = By.id("firstpassword");
    private final By confirmPassField  = By.id("secondpassword");

    // Dropdowns
    private final By skillsDropdown    = By.id("Skills");
    private final By countryDropdown   = By.id("countries");
    private final By yearDropdown      = By.id("yearbox");
    private final By dayDropdown       = By.id("daybox");

    // Form container — to verify page loaded
    private final By formContainer     = By.xpath("//form | //div[contains(@class,'container')]");

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

    // index 0 = Male, index 1 = Female
    public RegisterPage selectGender(int index) {
        List<WebElement> radios = driver.findElements(genderRadios);
        if (index < radios.size()) {
            radios.get(index).click();
        }
        return this;
    }

    public RegisterPage selectMale()   { return selectGender(0); }
    public RegisterPage selectFemale() { return selectGender(1); }

    public RegisterPage selectHobby1() { click(hobby1); return this; }
    public RegisterPage selectHobby2() { click(hobby2); return this; }
    public RegisterPage selectHobby3() { click(hobby3); return this; }

    public RegisterPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    public RegisterPage enterConfirmPassword(String password) {
        type(confirmPassField, password);
        return this;
    }

    public RegisterPage selectSkill(String visibleText) {
        new Select(driver.findElement(skillsDropdown)).selectByVisibleText(visibleText);
        return this;
    }

    public RegisterPage selectCountry(String visibleText) {
        new Select(driver.findElement(countryDropdown)).selectByVisibleText(visibleText);
        return this;
    }

    public boolean isFormDisplayed() {
        return isDisplayed(formContainer);
    }
}