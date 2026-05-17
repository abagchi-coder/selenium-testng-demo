package com.qa.pages;

import com.qa.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;
    protected WaitHelper wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }

    // Retry up to 3 times on StaleElementReferenceException
    protected void click(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.waitForClickable(locator).click();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("StaleElement on click, retry " + attempts);
            }
        }
    }

    protected void type(By locator, String text) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement el = wait.waitForVisible(locator);
                el.clear();
                el.sendKeys(text);
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("StaleElement on type, retry " + attempts);
            }
        }
    }

    protected String getText(By locator) {
        return wait.waitForVisible(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return wait.waitForVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
