package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.HomePage;
import com.qa.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test(groups = {"smoke", "regression"},
          description = "Verify registration form is displayed")
    public void tc004_registerFormDisplayed() {
        HomePage home = new HomePage(driver);
        RegisterPage register = home.clickRegister();
        Assert.assertTrue(register.isFormDisplayed(),
            "Registration form should be visible");
    }

    @Test(groups = {"regression"},
          description = "Verify user can fill registration form fields")
    public void tc005_fillRegistrationForm() {
        HomePage home = new HomePage(driver);
        RegisterPage register = home.clickRegister();

        register.enterFirstName("Arunaditya")
                .enterLastName("Bagchi")
                .enterEmail("test.qa@example.com")
                .enterPhone("9876543210")
                .selectMale();

        Assert.assertEquals(driver.getCurrentUrl().contains("Register"), true,
            "Should remain on register page after filling form");
    }
}
