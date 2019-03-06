package com.hsbc.mobile.pages;

import com.hsbc.mobile.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Mark Ma
 */
public class HomeBasePage extends BasePage {

    @FindBy(css = ".header-dropdown.primary-button")
    private WebElement logonButton;

    @FindBy(css = ".item-list.login-links > li:nth-child(5)")
    private WebElement registerButton;

    public HomeBasePage(WebDriver driver) {
        super(driver);
    }

    public HomeBasePage clickLogonButton() {
        toClick(logonButton);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public RegistrationBasePage clickRegisterLink() {
        toClick(registerButton);
        return new RegistrationBasePage(driver);
    }
}
