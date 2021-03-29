package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
    LOGIN_BUTTON="",
    LOGIN_INPUT="",
    PASSWORD_INPUT="",
    SUBMIT_BUTTON="";

    public AuthorizationPageObject (RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    public void enterLoginDate(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot enter login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot enter password", 5);

    }
}
