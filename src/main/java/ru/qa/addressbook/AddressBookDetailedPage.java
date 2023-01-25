package ru.qa.addressbook;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.PageObjectHelper.isElementPresent;

public class AddressBookDetailedPage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;


    @FindBy(xpath = "//div[@id='content']/img[contains(@src,'data:image/jpg;base64')]")
    private WebElement detailPageImage;

    @FindBy(name = "print")
    private WebElement printButton;


    public AddressBookDetailedPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }


    public boolean checkAccountImage() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(printButton));
        if (isElementPresent(detailPageImage)) {
            return (detailPageImage.isDisplayed() && detailPageImage.isEnabled());
        } else throw new NoSuchElementException("Test Failed - Address Book entity does not have the image!");
    }


}
