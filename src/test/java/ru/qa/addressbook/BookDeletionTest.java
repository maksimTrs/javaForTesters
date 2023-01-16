package ru.qa.addressbook;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class BookDeletionTest extends Base {

    private static Logger strLogger = Logger.getLogger(BookDeletionTest.class);


    @Test(groups = "smoke", testName = "test_delete_addressBook_group")
    public void testDeleteGroup() {

        strLogger.info("********************************************************************************");
        strLogger.info("Clear Group test data");
        strLogger.info("********************************************************************************");

        GroupsPage groupsPage = new GroupsPage(driver);


        groupsPage.deleteTestGroup();


        String checkDeletion = groupsPage.checkGroupDeletion();

        Assert.assertEquals(checkDeletion, "Group has been removed.\n" +
                "return to the group page");

        String checkDeletionWrongText = groupsPage.checkGroupDeletionWrongMsg();

        Assert.assertNull(checkDeletionWrongText);


        strLogger.info("********************************************************************************");
        strLogger.info("<<< Test method: " + BookDeletionTest.class.getMethods()[0].toString() + " passed >>>");
        strLogger.info("********************************************************************************");
    }
}
