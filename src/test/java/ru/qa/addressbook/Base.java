package ru.qa.addressbook;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.LocalDriverManager;
import utils.LogInHelper;

import java.net.MalformedURLException;
import java.net.URL;

import static utils.LocalDriverManager.BrowserType.LOCAL;
import static utils.LocalDriverManager.BrowserType.SELENIUM_GRID;


public abstract class Base {

    protected WebDriver driver;
    private LogInHelper logInHelper;

    @BeforeClass
    protected void setUp() throws MalformedURLException {

//        driver = WebDriverManager.chromedriver().create();
//        driver.manage().window().maximize();

        String host = "localhost"; //   IPv4-адрес
        String browserType = "CHROME";


        LocalDriverManager localDriverManager = new LocalDriverManager();

        if (System.getProperty("HUB_HOST") != null) {
            host = System.getProperty("HUB_HOST");
        }

        if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
            browserType = "FIREFOX";
        }
        driver = localDriverManager.createInstance(browserType, LOCAL, host);
        System.out.println("+++++ AT Test was started for browser = " + browserType + " +++++");

        logInHelper = new LogInHelper();
        logInHelper.doSignIn(driver);

    }

    @AfterClass
    protected void tearDown() {
        driver.quit();
    }

}

//  ./gradlew run
// ./gradlew test --tests "ru.qa*"

// RandomStringUtils.randomAlphanumeric(10);