package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    private static final String
    login="JuliettkaKk",
    password="JuL1987!";
    @Test
    public void testSaveFirstArticleToMyList (){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginDate(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle());
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIos()) {
        SearchPageObject.clickCancelButton();}

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();
        if (Platform.getInstance().isIos()) {
            NavigationUI.closePopup();
        }
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
        MyListsPageObject.openFolderByName(name_of_folder);}
        MyListsPageObject.swipeByArticleToDelete(article_title);

    }
    @Test
    public void testSave2ArticlesToTheList(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
       // String search_line = "Java";
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("JavaScript");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()){
        ArticlePageObject.addArticleToMyList(name_of_folder);}
        else {ArticlePageObject.addArticleToMySaved();}
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isAndroid()) {
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");}
        SearchPageObject.clickByArticleWithSubstring("Java");
        ArticlePageObject.waitForSecondTitleElement();
        String second_article = ArticlePageObject.getSecondArticleTitle();
        if (Platform.getInstance().isAndroid()) {
        ArticlePageObject.addArticleToExistingMyList();}
        else {ArticlePageObject.addArticleToMySaved();}
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIos()) {
            SearchPageObject.clickCancelButton();
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();
        if (Platform.getInstance().isIos()) {
            NavigationUI.closePopup();
        }
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
        MyListsPageObject.openFolderByName(name_of_folder);}

        MyListsPageObject.swipeByArticleToDelete(first_article);
        MyListsPageObject.waitForArticleToDisappearByTitle(first_article);
        MyListsPageObject.waitForArticleToAppearByTitle(second_article);
       // String title_of_article_in_my_list = MyListsPageObject.getArticleTitleInTheList();

        assertEquals("The title of the article is not equal to the title in my list",
                "Java",
                second_article);
    }

}
