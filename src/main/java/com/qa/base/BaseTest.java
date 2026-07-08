package com.qa.base;

import com.qa.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getBrowser();
        boolean headless = ConfigReader.isHeadless();

        switch (browser.toLowerCase()) {
            case "firefox" -> driver = createFirefoxDriver(headless);
            default -> driver = createChromeDriver(headless);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(ConfigReader.getBaseUrl());

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        waitForAngular();
    }

    private ChromeDriver createChromeDriver(boolean headless) {
        if (!isDriverConfigured("webdriver.chrome.driver")) {
            WebDriverManager.chromedriver().setup();
        }

        ChromeOptions options = new ChromeOptions();
        applyBinary(options, "webdriver.chrome.binary");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
        }

        return new ChromeDriver(options);
    }

    private FirefoxDriver createFirefoxDriver(boolean headless) {
        if (!isDriverConfigured("webdriver.gecko.driver")) {
            WebDriverManager.firefoxdriver().setup();
        }

        FirefoxOptions options = new FirefoxOptions();
        applyBinary(options, "webdriver.firefox.bin");

        if (headless) {
            options.addArguments("--headless");
        }

        return new FirefoxDriver(options);
    }

    private static boolean isDriverConfigured(String property) {
        String value = System.getProperty(property);
        return value != null && !value.isBlank();
    }

    private static void applyBinary(Object options, String property) {
        String binary = System.getProperty(property);
        if (binary == null || binary.isBlank()) {
            return;
        }

        if (options instanceof ChromeOptions chromeOptions) {
            chromeOptions.setBinary(binary);
        } else if (options instanceof FirefoxOptions firefoxOptions) {
            firefoxOptions.setBinary(binary);
        }
    }

    private void waitForAngular() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            wait.until(d -> js.executeScript("return document.readyState").equals("complete"));

            Boolean angularExists = (Boolean) js.executeScript(
                    "return typeof angular !== 'undefined'");

            if (Boolean.TRUE.equals(angularExists)) {
                wait.until(d -> {
                    try {
                        return (Boolean) js.executeScript(
                                "return angular.element(document.body).injector() !== undefined");
                    } catch (Exception e) {
                        return false;
                    }
                });
            }
            Thread.sleep(500);

        } catch (Exception e) {
            System.out.println("Angular wait skipped: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
