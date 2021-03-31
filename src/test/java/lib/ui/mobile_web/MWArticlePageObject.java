package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        TITLE_SECOND = "id:Java";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions a#ca-watch.mw-ui-icon-wikimedia-star-base20";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        EXISTING_FOLDER = "id:org.wikipedia:id/item_container";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON="css:#page-actions li#ca-watch.mw-ui-icon-wikimedia-unStar-progressive watched button";
    }
    public MWArticlePageObject (RemoteWebDriver driver) {
        super(driver);
    }
}
