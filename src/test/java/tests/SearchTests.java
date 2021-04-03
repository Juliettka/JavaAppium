package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchTests extends CoreTestCase {

    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Searching articles")
    @Description("We are searching for article by search term '{search_line}'")
    @Step("Starting Searching articles test")
    @Severity(value = SeverityLevel.BLOCKER)
    @Test
    public void testSearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Searching and clean search")
    @Description("We are searching for article by search term '{search_line}, cleaning search input and checking that there are no results'")
    @Step("Starting Searching and clean search test")
    @Severity(value = SeverityLevel.CRITICAL)
    @Test
    public void testSearchAndCancel() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("King");
        SearchPageObject.waitForSearchResult("King James Version");
        SearchPageObject.waitForSearchResult("Influential 1611 English version of the Bible");
        SearchPageObject.clearSearchField();
        SearchPageObject.waitForEmptySearchLabel();
    }

    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Empty search results")
    @Description("We are searching for article by search term '{search_line} and check that there are no results'")
    @Step("Starting Searching empty search results test")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    public void testAmount0SearchResult(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "mghjgjhghj";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Not Empty search results")
    @Description("We are searching for article by search term '{search_line} and check that there are not empty results'")
    @Step("Starting Searching not empty search results test")
    @Severity(value = SeverityLevel.CRITICAL)
    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found few results",
                amount_of_search_results > 0
        );
    }

    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Cancel search")
    @Description("We are  initializing search input, waiting for cancel button, clicking on it and waiting that cancel button disappeared'")
    @Step("Starting cancel search test")
    @Severity(value = SeverityLevel.MINOR)
    @Test
    public void testCancelSearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Search by title and description test")
    @Description("We are searching for articles by search term '{search_line} and checking titles and descriptions'")
    @Step("Starting Search by title and description test")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    public void testSearchByTitleAndDescription(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForElementByTitleAndDescription("Java","Island of Indonesia");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript","Programming language");
    }
   //@Test
  //  public void testAssertHasText() throws Exception {
 //       MainPageObject.assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
  //              "Search container does not have text Search Wikipedia",
  //              "Search Wikipedia");
  //  }
    

}
