package com.fisTest.tests;

import com.fisTest.base.DriverFactory;
import com.fisTest.base.Setup;
import com.fisTest.pageObjects.EbayPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EbayUITest extends Setup {

    public WebDriver driver;

    @Test
    public void ebayCartValidate(){
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(properties.getProperty("EBAY_URL"));
        EbayPage ebayPage = new EbayPage(driver);
        ebayPage.searchAndValidateCart();
    }
}
