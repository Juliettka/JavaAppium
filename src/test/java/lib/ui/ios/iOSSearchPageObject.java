package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ARTICLE_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@text='{TITLE}']/following-sibling::*[@text='{DESCRIPTION}']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Cancel']";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_INPUT_ID = "id:org.wikipedia:id/search_src_text";
        SEARCH_EMPTY_PAGE = "id:org.wikipedia:id/search_empty_message";
        FIRST_SEARCH_RESULT="xpath://XCUIElementTypeLink[@name='Java Indonesian island']";
        SECOND_SEARCH_RESULT="xpath://XCUIElementTypeLink[@name='JavaScript High-level programming language']";
        THIRD_SEARCH_RESULT="xpath://XCUIElementTypeLink[@name='Java (programming language) Object-oriented programming language']";

    }
    public iOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
