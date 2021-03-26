package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        TITLE_ELEMENT_IN_MY_LISTS = "id:org.wikipedia:id/page_list_item_title";
        DELETE_ARTICLE = "id:swipe action delete";
    }
    public iOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
