package ru.qa.addressbook;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.LocalDriverManager;
import utils.LogInHelper;

import java.net.MalformedURLException;

import static utils.LocalDriverManager.BrowserType.LOCAL;


public abstract class Base {

    protected WebDriver driver;
    private LogInHelper logInHelper;

    @BeforeClass
    protected void setUp() throws MalformedURLException {

//        driver = WebDriverManager.chromedriver().create();
//        driver.manage().window().maximize();

        String host = "localhost"; //   IPv4-адрес
        String browserType = "CHROME"; // FIREFOX  CHROME


        LocalDriverManager localDriverManager = new LocalDriverManager();

        if (System.getProperty("HUB_HOST") != null) {
            host = System.getProperty("HUB_HOST");
        }

        if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
            browserType = "FIREFOX";
        }
        driver = localDriverManager.createInstance(browserType, LOCAL, host);  // SELENIUM_GRID or LOCAL
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
// ./gradlew test -Psuite1
//  ./gradlew test  -Psuite1 -Psuite2