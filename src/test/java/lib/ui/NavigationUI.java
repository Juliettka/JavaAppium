package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{
    protected static String
    MY_LISTS_LINK,
    CLOSE_POPUP;

    public NavigationUI (RemoteWebDriver driver) {
        super(driver);
    }
    public void clickMyLists() {
        this.waitForElementAndClick(MY_LISTS_LINK,
                "Cannot find Navigation button to my lists",
                15
        );
    }
    public void closePopup(){
        this.waitForElementAndClick(CLOSE_POPUP, "Cannot find popup", 5);
    }
}
