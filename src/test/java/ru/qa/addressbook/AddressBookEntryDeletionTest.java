package ru.qa.addressbook;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddressBookEntryDeletionTest extends Base {


    @Test(groups = "smoke", testName = "test_delete_addressBook_group")
    public void testDeleteAddressGroup() {


        AddressBookEntryPage addressBookEntryPage = new AddressBookEntryPage(driver);

        addressBookEntryPage.deleteTestAddressBook();

        String checkDeletion = addressBookEntryPage.checkAddrBookDeletion();

        Assert.assertEquals(checkDeletion, "Record successful deleted");

    }
}
