package com.fisTest.pageObjects;

import com.fisTest.base.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

public class EbayPage {
    public WebDriver driver;
    public static DriverFactory driverFactory = new DriverFactory();

    @FindBy(xpath = ".//input[@id='gh-ac']")
    private WebElement searchBox;
    @FindBy(xpath = ".//input[@id='gh-btn']")
    private WebElement searchButton;
    @FindBy(xpath = "(.//li[contains(@id, 'item')]//div[@class='s-item__image-section'])[1]")
    private WebElement firstSearchResult;
    @FindBy(css = "[data-testid='x-atc-action']")
    private WebElement addToCartButton;
    @FindBy(id="gh-cart-n")
    private WebElement cartValue;


    public EbayPage (WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public void searchAndValidateCart(){
        try{
            int cartCount=0;
            driverFactory.waitTillElementIsPresent(searchBox);
            searchBox.sendKeys("books");

            driverFactory.waitTillElementIsPresent(searchButton);
            searchButton.click();
            Reporter.log("Searched for books!");

            driverFactory.waitTillElementIsPresent(firstSearchResult);
            firstSearchResult.click();
            Reporter.log("Selected first search result");

            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            //to get cart value before adding
            try{
                driverFactory.waitTillElementIsPresent(cartValue);
                cartCount = Integer.parseInt(cartValue.getText());
            }catch (Exception ignored){            }

            //adding to cart
            driverFactory.waitTillElementIsPresent(addToCartButton);
            addToCartButton.click();
            Reporter.log("Added to cart!!");

            //Check if cart is updated
            driverFactory.waitTillElementIsPresent(cartValue);
            Assert.assertTrue("Cart Value is not updated",cartCount<Integer.parseInt(cartValue.getText()));
            Reporter.log("Current cart value: "+cartValue.getText());

            Reporter.log("All Validations complete!");

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
