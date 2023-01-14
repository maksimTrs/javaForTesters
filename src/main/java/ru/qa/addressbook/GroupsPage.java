package ru.qa.addressbook;

import classdata.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
    private WebElement groupsSection;

    @FindBy(xpath = "//span[@class='group']")
    private List<WebElement> allGroups;

    private String groupCheckbox = "//span[@class='group']/input[contains(@title, '%s')]";
    private String checkWrongText = "//b[text()='Notice']";
    private String groupCreatedList = "span[class='group'] > input";


    public GroupsPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }


    public void createNewGroup(GroupData groupData) {
        webDriverWait.until(ExpectedConditions.visibilityOf(groupsPartition));
        groupsPartition.click();
        newGroupButton.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(newGroupName));
        newGroupName.sendKeys(groupData.getGroupName());
        newGroupHeader.sendKeys(groupData.getGroupHeader());
        newGroupFooter.sendKeys(groupData.getGroupFooter());

        webDriverWait.until(ExpectedConditions.elementToBeClickable(newGroupSubmitButton));
        newGroupSubmitButton.click();
    }

    public void goToGroupListPage() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(groupRedirectPageButton));
        groupRedirectPageButton.click();
    }

    public void deleteTestGroup(String groupName) {
        webDriverWait.until(ExpectedConditions.visibilityOf(groupsPartition));
        groupsPartition.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(groupsSection));
        driver.findElement(By.xpath(String.format(groupCheckbox, groupName))).click();
        groupDeleteButton.click();
    }

    public void deleteTestGroup() {
        webDriverWait.until(ExpectedConditions.visibilityOf(groupsPartition));
        groupsPartition.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(groupsSection));

        List<WebElement> listOfGroup = driver.findElements(By.cssSelector(groupCreatedList));

        for (WebElement webElement : listOfGroup) {
            // driver.findElement(By.cssSelector(groupStandardCheckbox)).click();
            webElement.click();
        }

        groupDeleteButton.click();
    }

    public String checkGroupDeletion() {
        webDriverWait.until(ExpectedConditions.visibilityOf(groupDeleteConfirmationMsg));
        return groupDeleteConfirmationMsg.getText();
    }

    public String checkGroupDeletionWrongMsg() {
        //webDriverWait.until(ExpectedConditions.visibilityOf(groupDeleteConfirmationMsg));

        try {
            WebElement noticeText = driver.findElement(By.xpath(checkWrongText));
            return noticeText.getText();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<WebElement> getGroupsSection() {
        return allGroups;
    }
}
