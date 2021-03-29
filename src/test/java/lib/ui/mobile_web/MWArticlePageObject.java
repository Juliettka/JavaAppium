package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        TITLE_SECOND = "id:Java";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#ca-watch button";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        EXISTING_FOLDER = "id:org.wikipedia:id/item_container";
    }
    public MWArticlePageObject (RemoteWebDriver driver) {
        super(driver);
    }
}
