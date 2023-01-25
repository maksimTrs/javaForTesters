package ru.qa.addressbook;

import classdata.AddressBookData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static utils.PageObjectHelper.isAlertPresent;
import static utils.PageObjectHelper.isElementPresent;

public class AddressBookEntryPage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;


    @FindBy(xpath = "//li/a[text()='add new']")
    private WebElement addressBookPartitionBtn;

    @FindBy(xpath = "//a[text()='home']")
    private WebElement homeBtn;

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

    @FindBy(css = "select[name='new_group']")
    private WebElement bookSelector;


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

    @FindBy(name = "photo")
    private WebElement photoFileUploaderBtn;

    private String addressBookCheckbox = "//td[text()='%s']/preceding-sibling::td/input[@type='checkbox']";
    private String listOfCheckBoxes = "//td[@class='center']/input[@name='selected[]']";
    private String tableHeader = "table#maintable > tbody > tr:first-of-type > th > a";
    private String detailPageBtn = "//tr[@name='entry']//td[text()='%s']/following-sibling::td/a[contains(@href, 'view.php')]";


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


        if (addressBookData.isCheckNewBookData()) {
            new Select(bookSelector).selectByValue("[none]");
        } else {
            if (isElementPresent(bookSelector))
                throw new IllegalArgumentException("Issue was found - group selector is active for edit addressBook entity");
        }

        webDriverWait.until(ExpectedConditions.elementToBeClickable(addressBookSubmitBtn));
        addressBookSubmitBtn.click();

    }

    public void goToAddressBookPage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(addressBookConfirmationMsg));
        homePage.click();
    }

    public void deleteTestAddressBook(String addressBookLastName) {
        webDriverWait.until(ExpectedConditions.visibilityOf(addressBooksTable));
        driver.findElement(By.xpath(String.format(addressBookCheckbox, addressBookLastName))).click();
        deleteAddrBookBtn.click();


        if (isAlertPresent(driver)) {
            Alert al = driver.switchTo().alert();
            al.accept();
        }
    }

    public void deleteTestAddressBook() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(homeBtn));
        homeBtn.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(addressBooksTable));

        List<WebElement> listOfAddressBooks = driver.findElements(By.xpath(listOfCheckBoxes));

        for (WebElement webElement : listOfAddressBooks) {
            webElement.click();
        }
        deleteAddrBookBtn.click();

        if (isAlertPresent(driver)) {
            Alert al = driver.switchTo().alert();
            al.accept();
        }
    }


    public String checkAddrBookDeletion() {
        webDriverWait.until(ExpectedConditions.visibilityOf(deletedTextVerification));
        //  return deletedTextVerification.getText();

        try {
            return deletedTextVerification.getText();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<WebElement> getListAddressBooks() {
        webDriverWait.until(ExpectedConditions.visibilityOf(addressBooksTable));
        return allListAddressBooks;
    }

    public List<WebElement> getHeaderTable() {
        webDriverWait.until(ExpectedConditions.visibilityOf(addressBooksTable));

        return driver.findElements(By.cssSelector(tableHeader));
    }


    public AddressBookDetailedPage gotToTheAddressBookDetailPage(String addressBookLastName) {
        webDriverWait.until(ExpectedConditions.visibilityOf(addressBooksTable));
        driver.findElement(By.xpath(String.format(detailPageBtn, addressBookLastName))).click();
        return new AddressBookDetailedPage(driver);
    }


    public void createNewAddressBookWithPhoto(AddressBookData addressBookData) {
        webDriverWait.until(ExpectedConditions.visibilityOf(addressBookPartitionBtn));
        addressBookPartitionBtn.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(addressBookFirstname));
        addressBookFirstname.sendKeys(addressBookData.getFirstName());
        addressBookLastname.sendKeys(addressBookData.getLastName());
        addressBookCompany.sendKeys(addressBookData.getBookCompany());
        addressBookMobile.sendKeys(addressBookData.getBookMobile());
        addressBookEmail.sendKeys(addressBookData.getBookEmail());

        photoFileUploaderBtn.sendKeys(addressBookData.getFilePath());

        if (addressBookData.isCheckNewBookData()) {
            new Select(bookSelector).selectByValue("[none]");
        } else {
            if (isElementPresent(bookSelector))
                throw new IllegalArgumentException("Issue was found - group selector is active for edit addressBook entity");
        }

        webDriverWait.until(ExpectedConditions.elementToBeClickable(addressBookSubmitBtn));
        addressBookSubmitBtn.click();

    }

}
