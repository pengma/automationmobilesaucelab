package com.hsbc.mobile.pages;

import com.hsbc.mobile.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Mark Ma
 */
public class RegistrationBasePage extends BasePage {

    @FindBy(name = "TermsAndCondition1")
    private WebElement termsAndConditionRadioButton;

    @FindBy(id = "btnContinue")
    private WebElement continueButton;

    @FindBy(id = "cancelRegBtn")
    private WebElement cancelButton;

    @FindBy(id = "doNotCancelBtn")
    private WebElement yesButtonOnDialog;

    public RegistrationBasePage(WebDriver driver) {
        super(driver);
    }

    public RegistrationBasePage acceptTermAndCondition() {
        scrollToSpecifiedElement(termsAndConditionRadioButton, true);
        toClick(termsAndConditionRadioButton);
        return this;
    }

    public RegistrationBasePage clickContinueButton() {
        toClick(continueButton);
        return this;
    }

    public RegistrationBasePage clickCancelButton() {
        toClick(cancelButton);
        return this;
    }

    public HomeBasePage clickYesButton() {
        clickElementByJS(yesButtonOnDialog);
        return new HomeBasePage(driver);
    }
}
