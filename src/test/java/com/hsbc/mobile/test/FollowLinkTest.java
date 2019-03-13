package com.hsbc.mobile.test;

import com.hsbc.mobile.base.TestDataAndDriverProvider;
import com.hsbc.mobile.pages.SauceGithubPage;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

public class FollowLinkTest extends TestDataAndDriverProvider {

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

    @DataProvider(name = "platformAndBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{
                new Object[]{"MicrosoftEdge", "17.17134", "Windows 10"},
                new Object[]{"firefox", "latest", "Windows 10"},
                new Object[]{"internet explorer", "11", "Windows 8.1"},
                new Object[]{"safari", "12.0", "macOS 10.14"},
                new Object[]{"chrome", "72", "macOS 10.13"},
                new Object[]{"firefox", "latest-1", "Windows 7"},
        };
    }

}