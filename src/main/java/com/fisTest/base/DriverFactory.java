package com.fisTest.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class DriverFactory extends Setup {

    public static WebDriver driver;
    private static final ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return dr.get();
    }

    public static void setDriver(WebDriver driver) {
        dr.set(driver);
    }

    public static void removeDriver(){
        dr.remove();
    }

    public static void initDriver(){
        switch (properties.getProperty("BROWSER").toUpperCase()) {
            case "CHROME" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                Reporter.log("Chrome browser launched");
            }
            case "FIREFOX" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                Reporter.log("Firefox browser launched");
            }
            default -> Assert.fail("Browser is not defined");
        }
        setDriver(driver);
        driver.manage().window().maximize();
    }

    public static void quitDriver(){
        if(Objects.nonNull(getDriver())){
            getDriver().quit();
            removeDriver();
        }
    }

    public WebElement waitTillElementIsPresent(WebElement element){
        return waitTillElementIsPresent(element, 10);
    }

    public WebElement waitTillElementIsPresent(WebElement element, int numberOfSeconds){
        return (new WebDriverWait(getDriver(), Duration.ofSeconds(numberOfSeconds)).until(ExpectedConditions.visibilityOf(element)));
    }
}
