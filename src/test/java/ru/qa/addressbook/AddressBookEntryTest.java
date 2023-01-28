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

public class AddressBookEntryTest extends BaseTest {

    private static List<String> initialAddressBookData;
    private AddressBookEntryPage addressBookEntryPage;


    @Test(dataProvider = "addressBook-data-provider", dataProviderClass = TestDataProvider.class, groups = "smoke", testName = "test_create_new_Address_Book_record")
    public void testBookAddRecord(int randomYearGenerator, String firstName, String lastName, String bookCompany, String bookMobile,
                                  String bookEmail, String bDay, String bMonth, boolean checkNewBookData) {

        initialAddressBookData = Arrays.asList(lastName, firstName, bookEmail, bookMobile);
        System.out.println(initialAddressBookData);


        addressBookEntryPage = new AddressBookEntryPage(driver);


        addressBookEntryPage.createNewAddressBook(new AddressBookData().withFirstName(firstName).withLastName(lastName)
                .withBookCompany(bookCompany).withBookMobile(bookMobile).withBookEmail(bookEmail).withDay(bDay)
                .withMonth(bMonth).withYear(randomYearGenerator).withCheckNewBookData(checkNewBookData));


        addressBookEntryPage.goToAddressBookPage();

        List<String> listOfAddressGroups = addressBookEntryPage.getListAddressBooks()
                .stream().map(WebElement::getText)
                .filter(row -> row.contains(lastName))
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());

        System.out.println(listOfAddressGroups);


        Assert.assertEquals(listOfAddressGroups, initialAddressBookData);

        List<String> tableHeader = addressBookEntryPage.getHeaderTable().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println(tableHeader);


        String[] expectedHeaderValues = {"Last name", "First name", "Address", "All e-mail", "All phones"};
        assertThat(tableHeader).as("check header column names").containsExactly(expectedHeaderValues);

    }
}
