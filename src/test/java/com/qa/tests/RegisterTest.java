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
            description = "Verify user can fill all registration form fields")
    public void tc005_fillRegistrationForm() {
        HomePage home = new HomePage(driver);
        RegisterPage register = home.clickRegister();

        register.enterFirstName("Arunaditya")
                .enterLastName("Bagchi")
                .enterEmail("test.qa@example.com")
                .enterPhone("9876543210")
                .selectMale()
                .selectHobby1()
                .enterPassword("Test@1234")
                .enterConfirmPassword("Test@1234");

        // Verify we're still on the register page
        Assert.assertTrue(
                driver.getCurrentUrl().contains("Register"),
                "Should remain on Register page after filling form"
        );
    }
}