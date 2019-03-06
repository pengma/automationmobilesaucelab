package com.hsbc.mobile.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceGithubPage {

    @FindBy(linkText = "i am a link")
    private WebElement theActiveLink;

    @FindBy(id = "your_comments")
    private WebElement yourCommentsSpan;

    @FindBy(id = "comments")
    private WebElement commentsTextAreaInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public WebDriver driver;
    public static String url = "https://saucelabs-sample-test-frameworks.github.io/training-test-page";

    public static SauceGithubPage visitPage(WebDriver driver) {
        SauceGithubPage page = new SauceGithubPage(driver);
        page.visitPage();
        return page;
    }

    public SauceGithubPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void visitPage() {
        this.driver.get(url);
    }

    public void followLink() {
        theActiveLink.click();
    }

    public void submitComment(String text) {
        commentsTextAreaInput.sendKeys(text);
        submitButton.click();

        // Race condition for time to populate yourCommentsSpan
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.textToBePresentInElement(yourCommentsSpan, text));
    }

    public String getSubmittedCommentText() {
        return yourCommentsSpan.getText();
    }

    public boolean isOnPage() {
        String title = "I am a page title - Sauce Labs";
        return driver.getTitle() == title;
    }

}
