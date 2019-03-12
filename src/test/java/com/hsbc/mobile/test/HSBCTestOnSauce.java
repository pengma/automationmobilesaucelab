package com.hsbc.mobile.test;

import com.hsbc.mobile.base.DesktopWebTestBase;
import org.testng.annotations.Test;

import static com.hsbc.mobile.data.TestConstants.HSBC_BASE_URL;
import static org.testng.Assert.assertEquals;

/**
 * Created by Mark Ma
 */
public class HSBCTestOnSauce extends DesktopWebTestBase {

    @Test
    public void testCancelRegister(){
        webWorkFlow.cancelRegister();
        assertEquals(HSBC_BASE_URL, driver.get().getCurrentUrl());
    }

    @Test
    public void testRegisterUser() {
        webWorkFlow.userRegister();
    }

}
