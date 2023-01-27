package ru.qa.addressbook;

import classdata.AddressBookData;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataProvider;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressBookEntryPhotoTest extends Base {

    private static List<String> initialAddressBookData;
    private AddressBookEntryPage addressBookEntryPage;


    @Test(dataProvider = "addressBook-data-provider-with-photo", dataProviderClass = TestDataProvider.class,
            groups = "smoke", testName = "test_create_new_Address_Book_entity_with_Photo")
    public void testBookAddRecordWithPhoto(String firstName, String lastName, String filePath, String bookCompany,
                                           String bookMobile, String bookEmail, boolean checkNewBookData) {

        initialAddressBookData = Arrays.asList(lastName, firstName, bookEmail, bookMobile);
        System.out.println(initialAddressBookData);


        addressBookEntryPage = new AddressBookEntryPage(driver);


        addressBookEntryPage.createNewAddressBookWithPhoto(new AddressBookData().withFirstName(firstName).withLastName(lastName)
                .withFilePath(filePath)
                .withBookCompany(bookCompany).withBookMobile(bookMobile)
                .withBookEmail(bookEmail).withCheckNewBookData(checkNewBookData));


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

    }
}

