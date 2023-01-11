package ru.qa.addressbook;

import classdata.AddressBookData;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBookEntryTest extends Base {

    private static Logger strLogger = Logger.getLogger(AddressBookEntryTest.class);


    @Test(groups = "smoke", testName = "test_create_new_Address_Book_record")
    public void testBookAddRecord() {

        int randomYearGenerator = (int) (Math.random() * 25 + 1981);
        String firstName = "firstName_1_" + randomYearGenerator;
        String lastName = "lastName_1_" + randomYearGenerator;
        String bookCompany = "bookCompany_1_" + randomYearGenerator;
        String bookMobile = "+7903380" + randomYearGenerator;
        String bookEmail = "bookEmail_1_" + randomYearGenerator + "@test.com";
        String bDay = "25";
        String bMonth = "May";

        List<String> initialAddressBookData = new ArrayList<>();
        initialAddressBookData.add(lastName);
        initialAddressBookData.add(firstName);
        initialAddressBookData.add(bookEmail);
        initialAddressBookData.add(bookMobile);
        System.out.println(initialAddressBookData);


        AddressBookEntryPage addressBookEntryPage = new AddressBookEntryPage(driver);
        addressBookEntryPage.createNewAddressBook(new AddressBookData(firstName, lastName, bookCompany, bookMobile,
                bookEmail, bDay, bMonth, randomYearGenerator));

        addressBookEntryPage.goToAddressBookPage();

        List<String> listOfAddressGroups = addressBookEntryPage.getListAddressBooks()
                .stream().map(WebElement::getText)
                .filter(row -> row.contains(lastName))
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());

        System.out.println(listOfAddressGroups);

        Assert.assertEquals(initialAddressBookData, listOfAddressGroups);


        strLogger.info("********************************************************************************");
        strLogger.info("<<< Test method: " + AddressBookEntryTest.class.getMethods()[0].toString() + " passed >>>");
        strLogger.info("********************************************************************************");

        strLogger.info("********************************************************************************");
        strLogger.info("Clear Address Group test data");
        strLogger.info("********************************************************************************");


        addressBookEntryPage.deleteTestAddressBook(lastName);
        String checkDeletion = addressBookEntryPage.checkAddrBookDeletion();

        Assert.assertEquals(checkDeletion, "Record successful deleted");

    }
}
