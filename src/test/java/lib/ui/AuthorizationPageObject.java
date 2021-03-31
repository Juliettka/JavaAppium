package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
    LOGIN_BUTTON="xpath://body/div[4]/div[2]/a[text()='Log in']",
    LOGIN_INPUT="css:input[name='wpName']",
    PASSWORD_INPUT="css:input[name='wpPassword']",
    SUBMIT_BUTTON="css:button#wpLoginAttempt";

    public AuthorizationPageObject (RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 15);

        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    public void enterLoginDate(String login, String password) {
        this.cleanLoginForm();
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot enter login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot enter password", 5);
    }
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot submit form", 10);
    }

    public void cleanLoginForm(){
        this.waitForElementAndClear(LOGIN_INPUT, "Cannot find login input", 5);
    }
}

