package ru.qa.addressbook;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class AddressBookEntryDeletionTest extends Base {

    private static Logger strLogger = Logger.getLogger(AddressBookEntryDeletionTest.class);


    @Test(groups = "smoke", testName = "test_delete_addressBook_group")
    public void testDeleteGroup() {

        strLogger.info("********************************************************************************");
        strLogger.info("Clear Address Group test data");
        strLogger.info("********************************************************************************");


        AddressBookEntryPage addressBookEntryPage = new AddressBookEntryPage(driver);

        addressBookEntryPage.deleteTestAddressBook();

        String checkDeletion = addressBookEntryPage.checkAddrBookDeletion();

        Assert.assertEquals(checkDeletion, "Record successful deleted");


        strLogger.info("********************************************************************************");
        strLogger.info("<<< Test method: " + AddressBookEntryDeletionTest.class.getMethods()[0].toString() + " passed >>>");
        strLogger.info("********************************************************************************");
    }
}
