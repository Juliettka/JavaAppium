package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        TITLE_ELEMENT_IN_MY_LISTS = "id:org.wikipedia:id/page_list_item_title";
        DELETE_ARTICLE = "id:swipe action delete";
    }
    public MWMyListsPageObject (RemoteWebDriver driver) {
        super(driver);
    }
}
