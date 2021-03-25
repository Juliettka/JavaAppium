package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{
    protected static String
    MY_LISTS_LINK,
    CLOSE_POPUP;

    public NavigationUI (AppiumDriver driver) {
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
