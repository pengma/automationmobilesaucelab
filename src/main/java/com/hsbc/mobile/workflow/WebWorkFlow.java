package com.hsbc.mobile.workflow;

import com.hsbc.mobile.pages.HomePage;
import com.hsbc.mobile.pages.RegistrationPage;
import org.openqa.selenium.WebDriver;

public class WebWorkFlow {

    private WebDriver driver;
    private HomePage homePage;
    private RegistrationPage registrationPage;

    public WebWorkFlow(WebDriver driver){
        this.driver = driver;
        this.homePage = new HomePage(driver);
        this.registrationPage = new RegistrationPage(driver);
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
