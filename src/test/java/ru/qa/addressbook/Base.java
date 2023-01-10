package ru.qa.addressbook;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import static utils.SecretGetter.returnPass;

public abstract class Base {

    protected WebDriver driver;

    private static  final String URL = "http://localhost/addressbook/";
    private static final String PASS_BASE64 = "c2VjcmV0";
    private static final String USER_NAME = "admin";


    @BeforeClass
    protected void setUp() {

        driver = WebDriverManager.chromiumdriver().create();

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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