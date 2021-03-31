package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{
    protected static String
    MY_LISTS_LINK,
    CLOSE_POPUP,
    OPEN_NAVIGATION;

    public NavigationUI (RemoteWebDriver driver) {
        super(driver);
    }

    public void openNavigation(){
        if (Platform.getInstance().isMw()) {
            this.waitForElementAndClick(OPEN_NAVIGATION,
                    "Cannot click open navigation button",
                    5);
        } else
        {
            System.out.println("The method openNavigation is doing nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    public void clickMyLists() {
        if (Platform.getInstance().isMw()){
                      this.tryClickElementFewAttempts(MY_LISTS_LINK,
                    "Cannot find Navigation button to my lists",
                    5);
        } else {
        this.waitForElementAndClick(MY_LISTS_LINK,
                "Cannot find Navigation button to my lists",
                15
        );}
    }
    public void closePopup(){
        this.waitForElementAndClick(CLOSE_POPUP, "Cannot find popup", 5);
    }
}
