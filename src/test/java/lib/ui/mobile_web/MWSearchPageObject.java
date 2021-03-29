package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        SEARCH_RESULT_ARTICLE_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@text='{TITLE}']/following-sibling::*[@text='{DESCRIPTION}']";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        SEARCH_INPUT_ID = "id:org.wikipedia:id/search_src_text";
        SEARCH_EMPTY_PAGE = "id:org.wikipedia:id/search_empty_message";
        FIRST_SEARCH_RESULT="xpath://XCUIElementTypeLink[@name='Java Indonesian island']";
        SECOND_SEARCH_RESULT="xpath://XCUIElementTypeLink[@name='JavaScript High-level programming language']";
        THIRD_SEARCH_RESULT="xpath://XCUIElementTypeLink[@name='Java (programming language) Object-oriented programming language']";

    }
    public MWSearchPageObject (RemoteWebDriver driver) {
        super(driver);
    }
}
