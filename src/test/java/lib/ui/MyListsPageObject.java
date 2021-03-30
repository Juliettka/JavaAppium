package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            TITLE_ELEMENT_IN_MY_LISTS,
            REMOVE_FROM_SAVED_BUTTON,
            DELETE_ARTICLE;

    private static String getFolderXPathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",
                name_of_folder);
    }
    private static String getSavedArticleXPathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}",
                article_title);
    }
    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}",
                article_title);
    }

    public MyListsPageObject (RemoteWebDriver driver) {
        super(driver);
    }
    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXPathByName(name_of_folder);
        this.waitForElementAndClick(folder_name_xpath,
                "Cannot find folder by name" + name_of_folder,
                5);
    }
    public void swipeByArticleToDelete(String article_title) {
        if (Platform.getInstance().isAndroid()) {
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getFolderXPathByName(article_title);
            this.swipeElementToLeft(article_xpath,
                    "cannot swipe saved article");

            //if (Platform.getInstance().isIos()) {
                //this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
            //}
            this.waitForArticleToDisappearByTitle(article_title);
        }
        else if (Platform.getInstance().isIos()) {
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getSavedArticleXPathByTitle(article_title);
           // this.clickElementToTheRightUpperCorner(article_xpath, "cannot find saved article");
            this.swipeElementToLeft(article_xpath, "cannot swipe article");
            this.waitForElementAndClick(DELETE_ARTICLE,"Cannot find delete button",
                    15);
            this.waitForArticleToDisappearByTitle(article_title);
        }
        else {
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getSavedArticleXPathByTitle(article_title);
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator,
                    "Cannot click button to remove article from saved",
                    5);
            driver.navigate().refresh();
            this.waitForArticleToDisappearByTitle(article_title);
        }
    }
    public void waitForArticleToDisappearByTitle(String article_title) {
        if (Platform.getInstance().isAndroid()) {
        String article_xpath = getFolderXPathByName(article_title);
        this.waitForElementNotPresent(article_xpath,
                "Saved article is still present with title" +article_title,
                15);}
        else {
            String article_xpath = getSavedArticleXPathByTitle(article_title);
            this.waitForElementNotPresent(article_xpath,
                    "Saved article is still present"+article_title,
                    15);
        }
    }
    public void waitForArticleToAppearByTitle(String article_title) {
        if (Platform.getInstance().isAndroid()) {
        String article_xpath = getFolderXPathByName(article_title);
        this.waitForElementPresent(article_xpath,
                "Cannot find saved article by title" +article_title,
                15);}
        else {
            String article_xpath = getSavedArticleXPathByTitle(article_title);
            this.waitForElementPresent(article_xpath, "Cannot find saved article"+article_title,
                    15);
        }
    }
    public String getArticleTitleInTheList(){
        WebElement title_element = waitForTitleInMyListElement();
        return title_element.getAttribute("text");
    }

    private WebElement waitForTitleInMyListElement () {
        return this.waitForElementPresent(TITLE_ELEMENT_IN_MY_LISTS, "Cannot find Article Title in my lists",
                15);
    }
}
