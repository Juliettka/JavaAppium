package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_RESULT_ARTICLE_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@text='{TITLE}']/following-sibling::*[@text='{DESCRIPTION}']";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_INPUT_ID = "id:org.wikipedia:id/search_src_text";
        SEARCH_EMPTY_PAGE = "id:org.wikipedia:id/search_empty_message";
        FIRST_SEARCH_RESULT="xpath://*[contains(@text,'Java')]";
        SECOND_SEARCH_RESULT="xpath://*[contains(@text,'JavaScript')]";
        THIRD_SEARCH_RESULT="xpath://*[contains(@text,'Java (programming language)')]";
    }
    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
