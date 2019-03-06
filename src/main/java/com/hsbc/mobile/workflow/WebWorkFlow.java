package com.hsbc.mobile.workflow;

import com.hsbc.mobile.pages.HomeBasePage;
import com.hsbc.mobile.pages.RegistrationBasePage;
import org.openqa.selenium.WebDriver;

/**
 * Created by Mark Ma
 */
public class WebWorkFlow {

    private WebDriver driver;
    private HomeBasePage homePage;
    private RegistrationBasePage registrationPage;

    public WebWorkFlow(WebDriver driver){
        this.driver = driver;
        this.homePage = new HomeBasePage(driver);
        this.registrationPage = new RegistrationBasePage(driver);
    }

    public void cancelRegister(){
        registrationPage = homePage
                .clickLogonButton()
                .clickRegisterLink()
                .acceptTermAndCondition()
                .clickContinueButton()
                .clickCancelButton();
        homePage = registrationPage.clickYesButton();
    }

    public void userRegister(){
        registrationPage = homePage.clickLogonButton().clickRegisterLink();
    }
}
