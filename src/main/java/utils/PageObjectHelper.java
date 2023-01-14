package utils;

import org.openqa.selenium.*;

public class PageObjectHelper {

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public static boolean isElementPresent(WebElement locator) {
        try {
            locator.isDisplayed();
          //  locator.getText();
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
