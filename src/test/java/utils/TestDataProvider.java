package utils;

import org.testng.annotations.DataProvider;

import java.util.Random;

import static utils.NumberGenerator.generateNumber;


public class TestDataProvider {

    private static String getRandomFirstDomainLvl() {
        StringBuilder init = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            char c = (char) (r.nextInt(26) + 'a');
            init.append(c);
        }
        return new String(init);
    }

    private static String firstDomainLvl = getRandomFirstDomainLvl();

    private static int randomGroupNumberGenerator = (int) (Math.random() * 100 + 3);
    private static String groupName = "GroupName_" + generateNumber() + "_" + randomGroupNumberGenerator;
    private static String groupHeader = "GroupHeader_1_" + randomGroupNumberGenerator;
    private static String groupFooter = "GroupFooter_1_" + randomGroupNumberGenerator;


    private static int randomYearGenerator = ((int) (Math.random() * 25 + 1979)) + ((int) (Math.random() * 7 + 3));
    private static String firstName = "firstName_1_" + randomYearGenerator;
    private static String lastName = "lastName_" + generateNumber() + "_" + randomYearGenerator;
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
                {groupName + ((int) (Math.random() * 7 + 3)), groupHeader, groupFooter},
                {groupName + ((int) (Math.random() * 7 + 4)), groupHeader, groupFooter},
                {groupName + ((int) (Math.random() * 7 + 5)), groupHeader, groupFooter}
        };
    }


    @DataProvider(name = "addressBook-data-provider")
    public static Object[][] dataProviderForAddressBook() {
        return new Object[][]{
                {randomYearGenerator, firstName, lastName + ((int) (Math.random() * 7 + 3)), bookCompany,
                        bookMobile + ((int) (Math.random() * 7 + 2)), bookEmail, bDay, bMonth, checkNewBookData},
                {randomYearGenerator, firstName, lastName + ((int) (Math.random() * 8 + 4)), bookCompany,
                        bookMobile + ((int) (Math.random() * 7 + 3)), bookEmail, bDay, bMonth, checkNewBookData},
                {randomYearGenerator, firstName, lastName + ((int) (Math.random() * 9 + 5)), bookCompany,
                        bookMobile + ((int) (Math.random() * 7 + 4)), bookEmail, bDay, bMonth, checkNewBookData}
        };
    }

    @DataProvider(name = "addressBook-data-provider-with-photo")
    public static Object[][] dataProviderForAddressBookWithPhoto() {
        return new Object[][]{
                {firstName, lastName + ((int) (Math.random() * 7 + 6)), filePath, bookCompany,
                        bookMobile + ((int) (Math.random() * 7 + 2)), bookEmail, checkNewBookData},
                {firstName, lastName + ((int) (Math.random() * 8 + 7)), filePath2, bookCompany,
                        bookMobile + ((int) (Math.random() * 7 + 3)), bookEmail, checkNewBookData}
        };
    }
}
