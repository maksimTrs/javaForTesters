package ru.qa.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GroupsPage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;


    @FindBy(xpath = "//li/a[text()='groups']")
    private WebElement groupsPartition;

    @FindBy(xpath = "//input[@value='New group'][1]")
    private WebElement newGroupButton;

    @FindBy(xpath = "//input[@name='group_name']")
    private WebElement newGroupName;

    @FindBy(xpath = "//textarea[@name='group_header']")
    private WebElement newGroupHeader;

    @FindBy(xpath = "//textarea[@name='group_footer']")
    private WebElement newGroupFooter;

    @FindBy(xpath = "//input[@name='submit'][@value='Enter information']")
    private WebElement newGroupSubmitButton;


    @FindBy(xpath = "//a[text()='group page']")
    private WebElement groupRedirectPageButton;

//    @FindBy(xpath = "//span[@class='group']/input[contains(@title, '+groupName+')]")
//    private WebElement groupCheckBox;

    @FindBy(xpath = "//input[@name='delete'][2]")
    private WebElement groupDeleteButton;

    @FindBy(xpath = "//div[@class='msgbox']")
    private WebElement groupDeleteConfirmationMsg;

    @FindBy(xpath = "//form[@method='post'][contains(@action, 'group')]")
    private WebElement listOfGroups;


    public GroupsPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }


    public void createNewGroup(String groupName, String groupHeader, String groupFooter) {
        webDriverWait.until(ExpectedConditions.visibilityOf(groupsPartition));
        groupsPartition.click();
        newGroupButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(newGroupName));
        newGroupName.sendKeys(groupName);
        newGroupHeader.sendKeys(groupHeader);
        newGroupFooter.sendKeys(groupFooter);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(newGroupSubmitButton));
        newGroupSubmitButton.click();
    }

    public void goToGroupListPage() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(groupRedirectPageButton));
        groupRedirectPageButton.click();
    }

    public void deleteTestGroup(String groupName) {
        webDriverWait.until(ExpectedConditions.visibilityOf(listOfGroups));
        driver.findElement(By.xpath(String.format("//span[@class='group']/input[contains(@title, '%s')]", groupName))).click();
        groupDeleteButton.click();
    }

    public String checkGroupDeletion() {
        webDriverWait.until(ExpectedConditions.visibilityOf(groupDeleteConfirmationMsg));
        return groupDeleteConfirmationMsg.getText();
    }
}
