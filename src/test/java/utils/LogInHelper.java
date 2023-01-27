package utils;

import org.openqa.selenium.WebDriver;
import ru.qa.addressbook.LogInPage;

import static utils.SecretGetter.handlingPassword;

public class LogInHelper {


    private static final String PASS_BASE64 = "c2VjcmV0";
    private static final String USER_NAME = "admin";

    // private static final String URL = "http://192.168.0.191/addressbook/";

    private static String host = System.getProperty("HUB_HOST");
    private static String port = "41062";
    private static final String URL = "http://" + host + ":" + port + "/www/index.php";  //  IPv4-адрес http://192.168.0.191/addressbook/  http://localhost:41062/www/index.php

    public void doSignIn(WebDriver driver) {
        LogInPage logInPage = new LogInPage(driver);
        logInPage.goTo(URL);
        logInPage.addLogInCreds(USER_NAME, handlingPassword(PASS_BASE64));
        logInPage.submitRegistrationPage();
    }
}
