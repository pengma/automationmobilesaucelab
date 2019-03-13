package com.hsbc.mobile.base;

import com.hsbc.mobile.data.TestConstants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.sql.Timestamp;

/**
 * Simple TestNG test which demonstrates being instantiated via a DataProvider in order to supply multiple browser combinations.
 */
public class TestDataAndDriverProvider {

    private String buildTag = new Timestamp(System.currentTimeMillis()).toString();

    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    protected WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     * @return the Sauce Job id for the current thread
     */
    public String getSessionId() {
        return sessionId.get();
    }

    protected void createDriver(String browser, String version, String os, String methodName)
            throws MalformedURLException, UnexpectedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // set desired capabilities to launch appropriate browser on Sauce
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", methodName);

        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }

        // Launch remote browser and set it as the current thread
        webDriver.set(new RemoteWebDriver(new URL(TestConstants.SAUCE_TEST_URL), capabilities));

        // set current sessionId
        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        webDriver.get().quit();
    }

    protected void annotate(String text) {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
    }
}
