package ru.qa.addressbook;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import static utils.SecretGetter.handlingPassword;


public class BookLogInTest extends Base {

    private static Logger strLogger = Logger.getLogger(BookLogInTest.class);


    @Test(groups = "smoke", testName = "test_login")
    public void testBookLogIn() {

        HomePage homePage = new HomePage(driver);

        String homePageLogOut = homePage.checkLogOutButton();   //driver.findElement(By.xpath("//div[@id='nav']//a[text()='home']")).getText();

        Assert.assertEquals(homePageLogOut, "Logout");


        strLogger.info("********************************************************************************");
        strLogger.info("<<< Test method: " + BookLogInTest.class.getMethods()[0].toString() + " passed >>>");
        strLogger.info("********************************************************************************");


    }


}
