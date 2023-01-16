package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class LocalDriverManager {

    public WebDriver createInstance(String browser, BrowserType browserType, String host) throws MalformedURLException {
        String path = "http://" + host + ":4444/wd/hub";
        WebDriver driver = null;


        DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());


        if (browserType.equals(BrowserType.SELENIUM_GRID)) {
            driver = WebDriverManager.getInstance(driverManagerType).remoteAddress(path).create();

        } else if (browserType.equals(BrowserType.LOCAL)) {
            driver = WebDriverManager.getInstance(driverManagerType).create();
            driver.manage().window().maximize();
        }

        return driver;
    }

    public enum BrowserType {
        LOCAL, SELENIUM_GRID
    }
}