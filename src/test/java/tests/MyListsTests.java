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

import java.util.concurrent.TimeUnit;

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
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArticlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginDate(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login",
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
    public void testSave2ArticlesToTheList() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article = ArticlePageObject.getArticleTitle();
        System.out.println(first_article);

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);}
        else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArticlePageObject.addArticleToMySaved();}
        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Thread.sleep(2000);
            Auth.clickAuthButton();
            Thread.sleep(2000);
            Auth.enterLoginDate(login, password);
            Auth.submitForm();
            Thread.sleep(2000);
            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login",
                    first_article,
                    ArticlePageObject.getArticleTitle());
            ArticlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");}
        if (Platform.getInstance().isMw()) {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
        }
        SearchPageObject.clickByArticleWithSubstring("ndonesian island");
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

        if (Platform.getInstance().isMw()) {
            NavigationUI.openNavigation();
        }

        NavigationUI.clickMyLists();

        if (Platform.getInstance().isIos()) {
            NavigationUI.closePopup();
        }

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(first_article);
        MyListsPageObject.waitForArticleToDisappearByTitle(first_article);
        Thread.sleep(3000);
        MyListsPageObject.waitForArticleToAppearByTitle(second_article);
        Assert.assertEquals("The title of the article is not equal to the title in my list",
                "Java",
                second_article);
    }

}
