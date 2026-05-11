package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test(groups = {"smoke", "regression"},
          description = "Verify home page loads and title is not empty")
    public void tc001_homePageTitleNotEmpty() {
        HomePage home = new HomePage(driver);
        String title = home.getPageTitle();
        Assert.assertFalse(title.isEmpty(), "Page title should not be empty");
    }

    @Test(groups = {"smoke", "regression"},
          description = "Verify navbar is visible on home page")
    public void tc002_navBarIsVisible() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isNavBarDisplayed(), "Navbar should be visible");
    }

    @Test(groups = {"regression"},
          description = "Verify clicking Register navigates to register page")
    public void tc003_registerLinkNavigates() {
        HomePage home = new HomePage(driver);
        home.clickRegister();
        Assert.assertTrue(
            driver.getCurrentUrl().contains("Register"),
            "URL should contain 'Register'"
        );
    }
}
