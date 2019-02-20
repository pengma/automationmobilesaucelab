package com.hsbc.mobile.pages;

import com.hsbc.mobile.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Mark Ma
 */
public class RegistrationPage extends Page {

    @FindBy(name = "TermsAndCondition1")
    private WebElement termsAndConditionRadioButton;

    @FindBy(id = "btnContinue")
    private WebElement continueButton;

    @FindBy(id = "cancelRegBtn")
    private WebElement cancelButton;

    @FindBy(id = "doNotCancelBtn")
    private WebElement yesButtonOnDialog;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage acceptTermAndCondition(){
        scrollToSpecifiedElement(termsAndConditionRadioButton, true);
        toClick(termsAndConditionRadioButton);
        return this;
    }

    public RegistrationPage clickContinueButton(){
        toClick(continueButton);
        return this;
    }

    public RegistrationPage clickCancelButton(){
        toClick(cancelButton);
        return this;
    }

    public HomePage clickYesButton(){
        clickElementByJS(yesButtonOnDialog);
        return new HomePage(driver);
    }
}
