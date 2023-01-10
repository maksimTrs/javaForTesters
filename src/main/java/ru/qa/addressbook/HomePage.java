package ru.qa.addressbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;


    @FindBy(xpath = "//input[@name='logout']/following-sibling::a")
    private WebElement homeBtn;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public String checkLogOutButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(homeBtn));
        return homeBtn.getText();
    }
}
