package com.hsbc.mobile;

import com.hsbc.mobile.data.TestConstants;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static com.hsbc.mobile.data.TestConstants.DEFAULT_WAIT_TIMEOUT;
import static com.hsbc.mobile.data.TestConstants.HSBC_BASE_URL;

/**
 * Created by Mark Ma
 */
@Listeners(SauceOnDemandTestListener.class)
public class TestBase implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private ThreadLocal<String> sessionId = new ThreadLocal<>();
    private final SauceOnDemandAuthentication authentication =
            new SauceOnDemandAuthentication(TestConstants.SAUCE_USERNAME, TestConstants.SAUCE_ACCESS_KEY);
    private boolean local = false;

    @BeforeMethod(alwaysRun = true)
    public void setupTest(Method method) throws MalformedURLException {
        createDriver(method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void setResultAndClearTest(ITestResult result) {
        ((JavascriptExecutor)driver.get())
                .executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        driver.get().quit();
    }

    private void createDriver(String methodName) throws MalformedURLException {
        if (local) {
            driver.set(new ChromeDriver());
        } else {
            driver.set(new DriverFactory().getDriver(methodName));
            // set current sessionId
            String id = ((RemoteWebDriver)driver.get()).getSessionId().toString();
            sessionId.set(id);
        }
        driver.get().get(HSBC_BASE_URL);
        driver.get().manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public String getSessionId() {
        return sessionId.get();
    }

    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }
}
