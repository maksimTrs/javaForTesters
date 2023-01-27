package utils;

import org.testng.annotations.DataProvider;

import java.util.Random;

import static utils.NumberGenerator.*;


public class TestDataProvider {

    private static String firstDomainLvl = getRandomFirstDomainLvl();

    private static int randomGroupNumberGenerator = (int) (Math.random() * 100 + 3);
    private static String groupName = "GroupName_" + generateNumber() + "_" + randomGroupNumberGenerator;
    private static String groupHeader = "GroupHeader_1_" + randomGroupNumberGenerator;
    private static String groupFooter = "GroupFooter_1_" + randomGroupNumberGenerator;


    private static int randomYearGenerator = ((int) (Math.random() * 25 + 1979)) + ((int) (Math.random() * 7 + 3));
    private static String firstName = "firstName_1_" + randomYearGenerator;
    private static String lastName = "lastName_1_" + (generateNumber() + getIntRandomValue() + 3) + "_" + randomYearGenerator;
    private static String lastName2 = "lastName_2_" + (generateNumber() + getIntRandomValue() - 3) + "_" + randomYearGenerator;
    private static String bookCompany = "bookCompany_1_" + randomYearGenerator;
    private static String bookMobile = "+7903" + generateNumber() + randomYearGenerator;
    private static String bookEmail = "bookEmail_1_" + randomYearGenerator + "@test." + firstDomainLvl;
    private static String bDay = "31";
    private static String bMonth = "December";
    private static boolean checkNewBookData = true;

    private static String filePath = "/opt/selenium/testimages/Test_Image2.png";
    // private  static String filePath = "E:\\MAX\\IT\\Logging\\testimages\\Test_Image2.png";
    //private  static String filePath2 = "E:\\MAX\\IT\\Logging\\testimages\\Test_Image1.jpg";
    private static String filePath2 = "/opt/selenium/testimages/Test_Image1.jpg";


    @DataProvider(name = "group-data-provider")
    public static Object[][] dataProviderForGroup() {
        return new Object[][]{
                {groupName +  getIntRandomValueForMobile(7, 2), groupHeader, groupFooter},
                {groupName +  getIntRandomValueForMobile(117, 4), groupHeader, groupFooter},
                {groupName +  getIntRandomValueForMobile(1117, 8), groupHeader, groupFooter}
        };
    }


    @DataProvider(name = "addressBook-data-provider")
    public static Object[][] dataProviderForAddressBook() {
        return new Object[][]{
                {randomYearGenerator, firstName, lastName + getIntRandomValue(), bookCompany,
                        bookMobile + getIntRandomValueForMobile(7, 2), bookEmail, bDay, bMonth, checkNewBookData},
                {randomYearGenerator, firstName, lastName + getIntRandomValue(), bookCompany,
                        bookMobile + getIntRandomValueForMobile(7, 3), bookEmail, bDay, bMonth, checkNewBookData},
                {randomYearGenerator, firstName, lastName + getIntRandomValue(), bookCompany,
                        bookMobile + getIntRandomValueForMobile(7, 4), bookEmail, bDay, bMonth, checkNewBookData}
        };
    }

    @DataProvider(name = "addressBook-data-provider-with-photo")
    public static Object[][] dataProviderForAddressBookWithPhoto() {
        return new Object[][]{
                {firstName, lastName2 + getIntRandomValue(), filePath, bookCompany,
                        bookMobile + getIntRandomValueForMobile(6, 3), bookEmail, checkNewBookData},
                {firstName, lastName2 + getIntRandomValue(), filePath2, bookCompany,
                        bookMobile + getIntRandomValueForMobile(5, 4), bookEmail, checkNewBookData}
        };
    }
}
