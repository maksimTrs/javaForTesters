package utils;

import org.openqa.selenium.WebDriver;
import ru.qa.addressbook.LogInPage;

import static utils.SecretGetter.handlingPassword;

public class LogInHelper {

    private static final String URL = "http://localhost/addressbook/";
    private static final String PASS_BASE64 = "c2VjcmV0";
    private static final String USER_NAME = "admin";


    public void doSignIn(WebDriver driver) {
        LogInPage logInPage = new LogInPage(driver);
        logInPage.goTo(URL);
        logInPage.addLogInCreds(USER_NAME, handlingPassword(PASS_BASE64));
        logInPage.submitRegistrationPage();
    }
}