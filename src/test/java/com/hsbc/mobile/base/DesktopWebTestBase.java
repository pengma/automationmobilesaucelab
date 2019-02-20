package com.hsbc.mobile.base;

import com.hsbc.mobile.TestBase;
import com.hsbc.mobile.workflow.WebWorkFlow;
import org.testng.annotations.BeforeMethod;

public class DesktopWebTestBase extends TestBase {

    protected WebWorkFlow webWorkFlow;

    @BeforeMethod(alwaysRun = true)
    public void initEntry() {
        this.webWorkFlow = new WebWorkFlow(driver.get());
    }
}
