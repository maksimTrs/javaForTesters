package ru.qa.addressbook;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.log4testng.Logger;
import utils.LocalDriverManager;
import utils.LogInHelper;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.List;

import static utils.LocalDriverManager.BrowserType.SELENIUM_GRID;


public abstract class Base {

    public Logger strLogger = Logger.getLogger(Base.class);
    protected WebDriver driver;
    private LogInHelper logInHelper;

    @BeforeClass
    public void setUp() throws MalformedURLException {


        //String host = "192.168.0.191"; //   IPv4-адрес 192.168.0.191   localhost
        String host = System.getProperty("HUB_HOST");
        String browserType = "CHROME"; // FIREFOX  CHROME


        LocalDriverManager localDriverManager = new LocalDriverManager();

        if (System.getProperty("HUB_HOST") != null && (!(System.getProperty("HUB_HOST").isEmpty()))) {
            host = System.getProperty("HUB_HOST");
        }

        if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("FIREFOX")) {
            browserType = "FIREFOX";
        }
        driver = localDriverManager.createInstance(browserType, SELENIUM_GRID, host);  // SELENIUM_GRID or LOCAL
        System.out.println("+++++ AT Test was started for browser = " + browserType + " +++++");

        logInHelper = new LogInHelper();
        logInHelper.doSignIn(driver);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] parameters) {
        strLogger.info("********************************************************************************");
        strLogger.info("<<< Test method: " + method.getName() + " with parameters " + List.of(parameters) + " was started >>>");
        strLogger.info("********************************************************************************");

    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        strLogger.info("********************************************************************************");
        strLogger.info("<<< Test method: " + method.getName() + " was finished >>>");
        strLogger.info("********************************************************************************");

    }


}

//  ./gradlew run
// ./gradlew test --tests "ru.qa*"
// ./gradlew test -Psuite1
//  ./gradlew test  -Psuite1 -Psuite2