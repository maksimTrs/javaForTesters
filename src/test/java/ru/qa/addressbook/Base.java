package ru.qa.addressbook;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static utils.SecretGetter.returnPass;

public abstract class Base {

    private static final String URL = "http://localhost/addressbook/";
    private static final String PASS_BASE64 = "c2VjcmV0";
    private static final String USER_NAME = "admin";
    protected WebDriver driver;

    @BeforeClass
    protected void setUp() {

        driver = WebDriverManager.chromedriver().create();

        driver.manage().window().maximize();

        doSignIn();

    }

    @AfterClass
    protected void tearDown() {
        driver.quit();
    }


    private void doSignIn() {
        LogInPage logInPage = new LogInPage(driver);
        logInPage.goTo(URL);
        logInPage.addLogInCreds(USER_NAME, returnPass(PASS_BASE64));
        logInPage.submitRegistrationPage();
    }

}


//  ./gradlew run
// ./gradlew test --tests "ru.qa*"

// RandomStringUtils.randomAlphanumeric(10);