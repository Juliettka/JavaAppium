package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "App Conditions") })
    @DisplayName("Changing orientation on Search results")
    @Description("We search some articles, open article from search result, change device orientation and check that article title has not been changed")
    @Step("Starting Changing orientation on Search results test")
    @Severity(value = SeverityLevel.NORMAL)
    public void testChangeOrientationOnSearchResults() {
        if (Platform.getInstance().isMw()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Appium";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals("Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation);
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals("Article title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation);
    }


    @Features(value = {@Feature(value = "App Conditions") })
    @DisplayName("Checking article title in background test")
    @Description("We search some articles, wait for an article in search results, send app in background, open again and check that article in search results")
    @Step("Starting Checking article title in background test")
    @Severity(value = SeverityLevel.MINOR)
    @Test
    public void testCheckArticleInBackground(){
        if (Platform.getInstance().isMw()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Appium";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult("Appium");
        this.backgroundApp(5);
        SearchPageObject.waitForSearchResult("Appium");
    }
}
