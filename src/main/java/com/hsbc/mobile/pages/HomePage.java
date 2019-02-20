package com.hsbc.mobile.pages;

import com.hsbc.mobile.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Mark Ma
 */
public class HomePage extends Page {

    @FindBy(css = ".header-dropdown.primary-button")
    private WebElement logonButton;

    @FindBy(css = ".item-list.login-links > li:nth-child(5)")
    private WebElement registerButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickLogonButton() {
        toClick(logonButton);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public RegistrationPage clickRegisterLink(){
        toClick(registerButton);
        return new RegistrationPage(driver);
    }
}
