package lib.ui;

import io.appium.java_client.AppiumDriver;
//import sun.jvm.hotspot.utilities.Assert;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_SUBSTRING_TPL,
            SEARCH_RESULT_ARTICLE_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_INPUT_ID,
            SEARCH_EMPTY_PAGE,
            FIRST_SEARCH_RESULT,
            SECOND_SEARCH_RESULT,
            THIRD_SEARCH_RESULT;
    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementWithTitleAndDescriptionXpath(String title, String description)
    {
        return SEARCH_RESULT_ARTICLE_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /*TEMPLATES METHODS*/

    @Step("Initializing the search field")
    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click init search element",15);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking init search element",
                5);
    }

    @Step("Waiting for the button to cancel search")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,
                "Cannot find Cancel Button", 5);
    }

    @Step("Waiting for the button to cancel search to be disappeared")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,
                "Cancel Button is still present", 5);
    }

    @Step("Click button to cancel search")
    public void clickCancelButton() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot click Cancel button", 5);
    }

    @Step("Typing '{search_line}' in search input")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT,
                search_line,
                "Cannot find input and type search line",
                15);
    }
  //  public void checkThreeSearchResults() throws Exception {
  //      Assert.assertTrue(MainPageObject.assertElementContainsText(FIRST_SEARCH_RESULT,
  //              "This search result 'Java' does not contain Java",
 //               "java") &&
  //              MainPageObject.assertElementContainsText(SECOND_SEARCH_RESULT,
  //                      "This search result 'JavaScript' does not contain Java",
  //                      "java")
   //             && MainPageObject.assertElementContainsText(THIRD_SEARCH_RESULT,
 //               "This search result 'Java (programming language)' does not contain Java",
  //              "java"));
 //   }

    @Step("Waiting for search result")
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 15);
    }

    @Step("Waiting for search result with expected title and description")
    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElementWithTitleAndDescriptionXpath(title, description);
        this.waitForElementPresent(search_result_xpath,
                "Cannot find element with " + title +"and"+ description,
                15);
    }

    @Step("Waiting for search result and Clicking article that contains expected substring ")
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring" + substring, 10);
    }

    @Step("Getting amount of articles")
    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty results label")
    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    @Step("Checking that there are no  search results")
    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed to find no results");
    }

    @Step("Cleaning search input")
    public void clearSearchField(){
        this.waitForElementAndClear(SEARCH_INPUT_ID, "Input field not found to clear it", 15);
    }

    @Step("Waiting for empty search results label")
    public void waitForEmptySearchLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_PAGE, "There is results on the page", 15);
    }
}
