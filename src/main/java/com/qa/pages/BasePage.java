package com.qa.pages;

import com.qa.utils.WaitHelper;
import org.openqa.selenium.By;
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

    protected void click(By locator) {
        wait.waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement el = wait.waitForVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(By locator) {
        return wait.waitForVisible(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try { return driver.findElement(locator).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
