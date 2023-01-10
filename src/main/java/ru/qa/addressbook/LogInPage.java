package ru.qa.addressbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @FindBy(xpath = "//input[@name='user']")
    private WebElement userName;

    @FindBy(xpath = "//input[@name='pass']")
    private WebElement userPass;

    @FindBy(xpath = "//input[@type='submit'][@value='Login']")
    private WebElement submitLogInButton;


    public LogInPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }


    public void goTo(String url) {
        this.driver.get(url);
        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(userName));
    }

    public void addLogInCreds(String user, String pass) {
        this.userName.sendKeys(user);
        this.userPass.sendKeys(pass);
    }

    public void submitRegistrationPage() {
        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(submitLogInButton));
        this.submitLogInButton.click();
    }

}
