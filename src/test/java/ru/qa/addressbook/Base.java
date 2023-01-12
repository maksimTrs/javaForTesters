package ru.qa.addressbook;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.LogInHelper;


public abstract class Base {

    protected WebDriver driver;

    @BeforeClass
    protected void setUp() {

        driver = WebDriverManager.chromedriver().create();

        driver.manage().window().maximize();

        new LogInHelper().doSignIn(driver);

    }

    @AfterClass
    protected void tearDown() {
        driver.quit();
    }

}


//  ./gradlew run
// ./gradlew test --tests "ru.qa*"

// RandomStringUtils.randomAlphanumeric(10);