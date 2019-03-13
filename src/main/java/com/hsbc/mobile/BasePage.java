package com.hsbc.mobile;

import com.hsbc.mobile.data.TestConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Mark Ma
 */
public abstract class BasePage {

    private Logger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    protected void toClick(WebElement webElement) {
        driverWait(webElement);
        webElement.click();
    }

    protected void toInput(WebElement webElement, String content) {
        driverWait(webElement);
        webElement.clear();
        webElement.sendKeys(content);
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        driverWait(webElement);
        return webElement.isDisplayed();
    }

    protected void scrollToSpecifiedElement(WebElement webElement) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", webElement);
        } catch (WebDriverException e) {
            logger.info("Scroll to element failed.");
        }
    }

    protected void scrollToSpecifiedElement(WebElement webElement, boolean isScrollElementToTopOfPage) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(" + isScrollElementToTopOfPage + ")", webElement);
        } catch (WebDriverException e) {
            //This error may happens.
            logger.info("Scroll to element failed.");
        }
    }

    public void clickElementByJS(WebElement webElement) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", webElement);
        } catch (WebDriverException e) {
            logger.info("Click element failed via JS.");
        }
    }

    private BasePage driverWait(WebElement webElement) {
        return driverWait(webElement, TestConstants.DEFAULT_WAIT_TIMEOUT);
    }

    private BasePage driverWait(WebElement webElement, int secs) {
        try {
            //This method should check visibility instead of enabled
            new WebDriverWait(driver, secs).until((ExpectedCondition<Object>) driver -> webElement.isDisplayed());
            return this;
        } catch (Exception e) {
            return this;
        }
    }
}
