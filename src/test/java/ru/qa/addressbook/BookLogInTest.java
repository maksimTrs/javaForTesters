package ru.qa.addressbook;

import org.testng.Assert;
import org.testng.annotations.Test;


public class BookLogInTest extends BaseTest {


    @Test(groups = "smoke", testName = "test_login")
    public void testBookLogIn() {

        HomePage homePage = new HomePage(driver);

        String homePageLogOut = homePage.checkLogOutButton();

        Assert.assertEquals(homePageLogOut, "Logout");

    }


}
