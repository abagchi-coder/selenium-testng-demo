package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test(groups = {"smoke", "regression"},
            description = "Verify home page title is not empty")
    public void tc001_homePageTitleNotEmpty() {
        HomePage home = new HomePage(driver);
        String title = home.getPageTitle();
        Assert.assertFalse(title.isEmpty(), "Page title should not be empty");
        System.out.println("Page title: " + title);
    }

    @Test(groups = {"smoke", "regression"},
            description = "Verify navigation container with Sign In and Skip Sign In buttons is visible")
    public void tc002_navContainerIsVisible() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isNavDisplayed(),
                "Navigation container (div#abcd) should be visible");
    }

    @Test(groups = {"regression"},
            description = "Verify clicking Skip Sign In navigates to registration page")
    public void tc003_registerLinkNavigates() {
        HomePage home = new HomePage(driver);
        home.clickRegister();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("Register"),
                "URL should contain 'Register' after clicking Skip Sign In"
        );
    }
}