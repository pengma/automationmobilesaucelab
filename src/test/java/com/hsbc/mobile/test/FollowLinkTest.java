package com.hsbc.mobile.test;

import com.hsbc.mobile.base.TestDataAndDriverProvider;
import com.hsbc.mobile.pages.SauceGithubPage;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

public class FollowLinkTest extends TestDataAndDriverProvider {

    /**
     * Runs a simple test verifying link can be followed.
     *
     * @throws InvalidElementStateException
     */
    @Test(dataProvider = "platformAndBrowsers")
    public void verifyLinkTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        //create webDriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting sauceLab on github page...");
        SauceGithubPage page = SauceGithubPage.visitPage(driver);

        this.annotate("Clicking on link...");
        page.followLink();

        this.annotate("Asserting that we are on a new page...");
        Assert.assertFalse(page.isOnPage());
    }

}