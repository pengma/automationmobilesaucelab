package com.hsbc.mobile.utils;

import com.hsbc.mobile.data.TestConstants;

public class Utils {

    public static boolean isIOSSimulator() {
        return ("ios".equalsIgnoreCase(TestConstants.PLATFORM));
    }

    public static boolean isAndroidSimulator() {
        return "android".equalsIgnoreCase(TestConstants.PLATFORM);
    }

    public static boolean isDesktop(){
        return TestConstants.PLATFORM.contains("Windows") || TestConstants.PLATFORM.contains("macOS");
    }
}
