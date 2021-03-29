package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK="id:Saved";
        CLOSE_POPUP = "xpath://XCUIElementTypeButton[@name='Close']";
    }
    public MWNavigationUI (RemoteWebDriver driver) {
        super(driver);
    }
}
