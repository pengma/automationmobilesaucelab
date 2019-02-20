package com.hsbc.mobile.test;

import com.hsbc.mobile.base.DesktopWebTestBase;
import org.testng.annotations.Test;

import static com.hsbc.mobile.data.TestConstants.BASE_URL;
import static org.testng.Assert.assertEquals;

public class WebTestOnSauce extends DesktopWebTestBase {

    @Test
    public void testCancelRegister(){
        webWorkFlow.cancelRegister();
        assertEquals(BASE_URL, driver.get().getCurrentUrl());
    }

    @Test
    public void testRegisterUser1(){
        webWorkFlow.userRegister();
    }

    @Test
    public void testRegisterUser2(){
        webWorkFlow.userRegister();
    }

    @Test
    public void testRegisterUser3(){
        webWorkFlow.userRegister();
    }

    @Test
    public void testRegisterUser4(){
        webWorkFlow.userRegister();
    }

//    @DataProvider(name = "testParallel", parallel = true)
//    public Object[][] parameterName() {
//        return new Object[][]{
//                new Object[]{"123"},
//                new Object[]{"456"}
//        };
//    }
}
