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

public class AddressBookEntryPhotoTest extends Base {
    private static Logger strLogger = Logger.getLogger(AddressBookEntryPhotoTest.class);
    private static List<String> initialAddressBookData;
    private String lastName;
    private AddressBookEntryPage addressBookEntryPage;


    @BeforeMethod
    public AddressBookEntryPage createAddBookEntityWithPhoto() {
        int randomYearGenerator = ((int) (Math.random() * 25 + 1979)) + ((int) (Math.random() * 7 + 6));
        String firstName = "firstName_1_" + randomYearGenerator;
        lastName = "lastName_" + generateNumber() + "_" + randomYearGenerator;

        String filePath = "/opt/selenium/testimages/Test_Image2.png";

        String bookCompany = "bookCompany_1_" + randomYearGenerator;
        String bookMobile = "+79031" + generateNumber() + randomYearGenerator;
        String bookEmail = "bookEmail_1_" + randomYearGenerator + "@test.com";
        boolean checkNewBookData = true;


        initialAddressBookData = Arrays.asList(lastName, firstName, bookEmail, bookMobile);
        System.out.println(initialAddressBookData);


        addressBookEntryPage = new AddressBookEntryPage(driver);

//        addressBookEntryPage.createNewAddressBook(new AddressBookData(firstName, lastName, bookCompany, bookMobile,
//                bookEmail, bDay, bMonth, randomYearGenerator, true));

        addressBookEntryPage.createNewAddressBookWithPhoto(new AddressBookData().withFirstName(firstName).withLastName(lastName)
                .withFilePath(filePath)
                .withBookCompany(bookCompany).withBookMobile(bookMobile)
                .withBookEmail(bookEmail).withCheckNewBookData(checkNewBookData));

        return addressBookEntryPage;
    }


    @Test(groups = "smoke", testName = "test_create_new_Address_Book_entity_with_Photo")
    public void testBookAddRecord() {


        addressBookEntryPage.goToAddressBookPage();

        List<String> listOfAddressGroups = addressBookEntryPage.getListAddressBooks()
                .stream().map(WebElement::getText)
                .filter(row -> row.contains(lastName))
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());

        System.out.println(listOfAddressGroups);

        Assert.assertEquals(listOfAddressGroups, initialAddressBookData);

        boolean photoPresents = addressBookEntryPage.gotToTheAddressBookDetailPage(lastName).checkAccountImage();

        assertThat(photoPresents).as("test entity does not have the image!").isTrue();


        strLogger.info("********************************************************************************");
        strLogger.info("<<< Test method: " + AddressBookEntryPhotoTest.class.getMethods()[0].toString() + " passed >>>");
        strLogger.info("********************************************************************************");


    }
}

