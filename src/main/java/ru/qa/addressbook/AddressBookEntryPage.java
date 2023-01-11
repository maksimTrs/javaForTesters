package ru.qa.addressbook;

import classdata.AddressBookData;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddressBookEntryPage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;


    @FindBy(xpath = "//li/a[text()='add new']")
    private WebElement addressBookPartitionBtn;

    @FindBy(name = "firstname")
    private WebElement addressBookFirstname;

    @FindBy(name = "lastname")
    private WebElement addressBookLastname;

    @FindBy(name = "company")
    private WebElement addressBookCompany;

    @FindBy(name = "mobile")
    private WebElement addressBookMobile;

    @FindBy(name = "email")
    private WebElement addressBookEmail;

    @FindBy(name = "bday")
    private WebElement addressBookSelectBirthdayDay;

    @FindBy(name = "bmonth")
    private WebElement addressBookSelectBirthdayMonth;

    @FindBy(name = "byear")
    private WebElement addressBookSetBirthdayYear;

    @FindBy(xpath = "//input[@value='Enter'][2]")
    private WebElement addressBookSubmitBtn;

    @FindBy(xpath = "//div[@class='msgbox']")
    private WebElement addressBookConfirmationMsg;

    @FindBy(xpath = "//a[@href='index.php']")
    private WebElement homePage;


    @FindBy(xpath = "//table[@id='maintable']")
    private WebElement addressBooksTable;

    @FindBy(xpath = "//tr[@name='entry']")
    private List<WebElement> allListAddressBooks;

    @FindBy(xpath = "//input[@value='Delete']")
    private WebElement deleteAddrBookBtn;

    @FindBy(xpath = "//div[text()='Record successful deleted']")
    private WebElement deletedTextVerification;


    public AddressBookEntryPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }


    public void createNewAddressBook(AddressBookData addressBookData) {
        webDriverWait.until(ExpectedConditions.visibilityOf(addressBookPartitionBtn));
        addressBookPartitionBtn.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(addressBookFirstname));
        addressBookFirstname.sendKeys(addressBookData.getFirstName());
        addressBookLastname.sendKeys(addressBookData.getLastName());
        addressBookCompany.sendKeys(addressBookData.getBookCompany());
        addressBookMobile.sendKeys(addressBookData.getBookMobile());
        addressBookEmail.sendKeys(addressBookData.getBookEmail());

        Select bDay = new Select(addressBookSelectBirthdayDay);
        bDay.selectByVisibleText(addressBookData.getDay());
        Select mDay = new Select(addressBookSelectBirthdayMonth);
        mDay.selectByVisibleText(addressBookData.getMonth());
        addressBookSetBirthdayYear.sendKeys("" + addressBookData.getYear());

        webDriverWait.until(ExpectedConditions.elementToBeClickable(addressBookSubmitBtn));
        addressBookSubmitBtn.click();

    }

    public void goToAddressBookPage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(addressBookConfirmationMsg));
        homePage.click();
    }

    public void deleteTestAddressBook(String addressBookLastName) {
        webDriverWait.until(ExpectedConditions.visibilityOf(addressBooksTable));
        driver.findElement(By.xpath(String.format("//td[text()='%s']/preceding-sibling::td/input[@type='checkbox']", addressBookLastName))).click();
        deleteAddrBookBtn.click();

        Alert al = driver.switchTo().alert();
        al.accept();
    }

    public String checkAddrBookDeletion() {
        webDriverWait.until(ExpectedConditions.visibilityOf(deletedTextVerification));
        return deletedTextVerification.getText();
    }

    public List<WebElement> getListAddressBooks() {
        webDriverWait.until(ExpectedConditions.visibilityOf(addressBooksTable));
        return allListAddressBooks;
    }
}
