package ru.qa.addressbook;

import classdata.AddressBookData;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.NumberGenerator.generateNumber;

public class AddressBookEntryTest extends Base {

    private static Logger strLogger = Logger.getLogger(AddressBookEntryTest.class);
    private String lastName;
    private static List<String> initialAddressBookData;
    private AddressBookEntryPage addressBookEntryPage;


    @BeforeMethod
    public AddressBookEntryPage createAddBookEntity() {
        int randomYearGenerator = ((int) (Math.random() * 25 + 1979)) + ((int) (Math.random() * 7 + 3));
        String firstName = "firstName_1_" + randomYearGenerator;
        lastName = "lastName_" + generateNumber() + "_" + randomYearGenerator;
        String bookCompany = "bookCompany_1_"  + randomYearGenerator;
        String bookMobile = "+79031" + generateNumber() + randomYearGenerator;
        String bookEmail = "bookEmail_1_" + randomYearGenerator + "@test.com";
        String bDay = "25";
        String bMonth = "May";
        boolean checkNewBookData = true;


        initialAddressBookData = Arrays.asList(lastName, firstName, bookEmail, bookMobile);
        System.out.println(initialAddressBookData);


        addressBookEntryPage = new AddressBookEntryPage(driver);

//        addressBookEntryPage.createNewAddressBook(new AddressBookData(firstName, lastName, bookCompany, bookMobile,
//                bookEmail, bDay, bMonth, randomYearGenerator, true));

        addressBookEntryPage.createNewAddressBook(new AddressBookData().withFirstName(firstName).withLastName(lastName)
                .withBookCompany(bookCompany).withBookMobile(bookMobile).withBookEmail(bookEmail).withDay(bDay)
                .withMonth(bMonth).withYear(randomYearGenerator).withCheckNewBookData(checkNewBookData));

        return addressBookEntryPage;
    }

    @Test(groups = "smoke", testName = "test_create_new_Address_Book_record")
    public void testBookAddRecord() {

//        int randomYearGenerator = ((int) (Math.random() * 25 + 1979)) + ((int) (Math.random() * 7 + 3));
//        String firstName = "firstName_" + generateNumber() + "_" + randomYearGenerator;
//        String lastName = "lastName_" + generateNumber() + "_" + randomYearGenerator;
//        String bookCompany = "bookCompany_" + generateNumber() + "_" + randomYearGenerator;
//        String bookMobile = "+7903380" + randomYearGenerator;
//        String bookEmail = "bookEmail_" + generateNumber() + "_" + randomYearGenerator + "@test.com";
//        String bDay = "25";
//        String bMonth = "May";
//        boolean checkNewBookData = true;
//
//
//        List<String> initialAddressBookData = Arrays.asList(lastName, firstName, bookEmail, bookMobile);
//        System.out.println(initialAddressBookData);
//
//
//        AddressBookEntryPage addressBookEntryPage = new AddressBookEntryPage(driver);
//
////        addressBookEntryPage.createNewAddressBook(new AddressBookData(firstName, lastName, bookCompany, bookMobile,
////                bookEmail, bDay, bMonth, randomYearGenerator, true));
//
//        addressBookEntryPage.createNewAddressBook(new AddressBookData().withFirstName(firstName).withLastName(lastName)
//                .withBookCompany(bookCompany).withBookMobile(bookMobile).withBookEmail(bookEmail).withDay(bDay)
//                .withMonth(bMonth).withYear(randomYearGenerator).withCheckNewBookData(checkNewBookData));

        addressBookEntryPage.goToAddressBookPage();

        List<String> listOfAddressGroups = addressBookEntryPage.getListAddressBooks()
                .stream().map(WebElement::getText)
                .filter(row -> row.contains(lastName))
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());

        System.out.println(listOfAddressGroups);

        //   assertThat(listOfAddressGroups.stream().count()).isEqualTo(initialAddressBookData.stream().count());
//        for (int i = 0; i < listOfAddressGroups.size(); i++) {
//            // System.out.println(listOfAddressGroups.get(i) + " +++ " + initialAddressBookData.get(i));
//            Assert.assertEquals(listOfAddressGroups.get(i), initialAddressBookData.get(i));
//        }
        Assert.assertEquals(listOfAddressGroups, initialAddressBookData);

        List<String> tableHeader = addressBookEntryPage.getHeaderTable().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println(tableHeader);


        String[] expectedHeaderValues = {"Last name", "First name", "Address", "All e-mail", "All phones"};
        assertThat(tableHeader).as("check header column names").containsExactly(expectedHeaderValues);


        strLogger.info("********************************************************************************");
        strLogger.info("<<< Test method: " + AddressBookEntryTest.class.getMethods()[0].toString() + " passed >>>");
        strLogger.info("********************************************************************************");


    }
}
