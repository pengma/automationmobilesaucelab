package com.hsbc.mobile.data;

import java.sql.Timestamp;

/**
 * Created by Mark Ma
 */
public interface TestConstants {

    String HSBC_BASE_URL = "https://www.hsbc.com.cn/en-cn/";
    String SAUCE_DOMAIN = "@ondemand.saucelabs.com:443";
    String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
    String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    String SAUCE_TEST_URL = "https://" + SAUCE_USERNAME + ":" + SAUCE_ACCESS_KEY + SAUCE_DOMAIN + "/wd/hub";
    String BUILD_TAG = new Timestamp(System.currentTimeMillis()).toString();
    int DEFAULT_WAIT_TIMEOUT = 20;

    //    String PLATFORM = "Windows 10";
//    String PLATFORM = "macOS 10.14";
    String PLATFORM = "Android";
//    String PLATFORM = "iOS";
String BROWSER_NAME = "Chrome";
//    String BROWSER_NAME = "Safari";
}
