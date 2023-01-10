package ru.qa.addressbook;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class BookCreateGroupTest extends Base {

    private static Logger strLogger = Logger.getLogger(BookCreateGroupTest.class);
    // private WebDriver driver;


    @Test(groups = "smoke", testName = "test_create_addressBook_group")
    public void testBookAddGroup() {


        driver.findElement(By.xpath("//li/a[text()='groups']")).click();
        driver.findElement(By.xpath("//input[@value='New group'][1]")).click();

        //RandomStringUtils.randomAlphanumeric(10);  !!!

        driver.findElement(By.xpath("//input[@name='group_name']")).sendKeys("Group_1"); //
        //driver.findElement(By.xpath("//input[@name='group_header']")).click();
        driver.findElement(By.xpath("//textarea[@name='group_header']")).sendKeys("Group_1_1");
        // driver.findElement(By.xpath("//input[@name='group_footer']")).click();
        driver.findElement(By.xpath("//textarea[@name='group_footer']")).sendKeys("Group_1_1_1");
        driver.findElement(By.xpath("//input[@name='submit'][@value='Enter information']")).click();


        driver.findElement(By.cssSelector("a[href*='group']")).click();


        List<String> listOfAddressGroups = driver.findElements(By.xpath("//span[@class='group']"))
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println(listOfAddressGroups);

        String testGroupName = listOfAddressGroups.stream()
                .filter(group -> group.equals("Group_1"))
                .findFirst().orElse(null);

        Assert.assertEquals(testGroupName, "Group_1");


        strLogger.info("********************************************************************************");
        strLogger.info("<<< Test method: " + BookCreateGroupTest.class.getMethods()[0].toString() + " passed >>>");
        strLogger.info("********************************************************************************");

        strLogger.info("********************************************************************************");
        strLogger.info("Clear Group test data");
        strLogger.info("********************************************************************************");

        driver.findElement(By.xpath("//span[@class='group']/input[contains(@title, 'Group_1')]")).click();
        driver.findElement(By.xpath("//input[@name='delete'][2]")).click();

        String checkDelete = driver.findElement(By.xpath("//div[@class='msgbox']")).getText();

        Assert.assertEquals(checkDelete, "Group has been removed.\n" +
                "return to the group page");

    }

}
