package com.hsbc.mobile;

import com.hsbc.mobile.data.TestConstants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.hsbc.mobile.data.TestConstants.SAUCE_TEST_URL;
import static com.hsbc.mobile.utils.Utils.isAndroidSimulator;
import static com.hsbc.mobile.utils.Utils.isIOSSimulator;

/*
    This is the class to create corresponding driver for different os/browser.
    Please config your environment in EnvVariables class if you wanna debug on Sauce.
 */

class DriverFactory {

    private DesiredCapabilities configDesiredCapability(String jobName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("name", jobName);
        if (TestConstants.BUILD_TAG != null) {
            capabilities.setCapability("build", TestConstants.BUILD_TAG);
        }
        if (isIOSSimulator()) {
            capabilities.setCapability("appiumVersion", "1.9.1");
            capabilities.setCapability("deviceName", "iPhone XS Simulator");
            capabilities.setCapability("deviceOrientation", "portrait");
            capabilities.setCapability("platformVersion", "12.0");
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("browserName", "Safari");
            return capabilities;
        }
        if (isAndroidSimulator()) {
            capabilities.setCapability("appiumVersion", "1.9.1");
            capabilities.setCapability("deviceName", "Android GoogleAPI Emulator");
            capabilities.setCapability("deviceOrientation", "portrait");
            capabilities.setCapability("browserName", "Chrome");
            capabilities.setCapability("platformVersion", "7.1");
            capabilities.setCapability("platformName", "Android");
            return capabilities;
        }
        // set desktop browser desired capabilities to launch appropriate browser on Sauce
        capabilities.setCapability("platform", TestConstants.PLATFORM);
        capabilities.setCapability("browserName", TestConstants.BROWSER_NAME);
        capabilities.setCapability("version", "latest");
        return capabilities;
    }

    WebDriver getDriver(String jobName) throws MalformedURLException {
        ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
        Capabilities capabilities = configDesiredCapability(jobName);
        webDriver.set(new RemoteWebDriver(new URL(SAUCE_TEST_URL), capabilities));
        return webDriver.get();
    }

}
