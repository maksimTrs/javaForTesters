package ru.qa.addressbook;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BookDeletionTest extends Base {

    @Test(groups = "smoke", testName = "test_delete_addressBook_group")
    public void testDeleteGroup() {


        GroupsPage groupsPage = new GroupsPage(driver);

        groupsPage.deleteTestGroup();


        String checkDeletion = groupsPage.checkGroupDeletion();

        Assert.assertEquals(checkDeletion, "Group has been removed.\n" +
                "return to the group page");

        String checkDeletionWrongText = groupsPage.checkGroupDeletionWrongMsg();

        Assert.assertNull(checkDeletionWrongText);

    }
}
