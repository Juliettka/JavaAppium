package lib.ui;

import io.appium.java_client.AppiumDriver;
//import org.openqa.selenium.Platform;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            TITLE_SECOND,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            EXISTING_FOLDER;
    public ArticlePageObject (RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for title element")
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find Article Title",
                20);
    }

    @Step("Waiting for title element of second article")
    public WebElement waitForSecondTitleElement() {
        return this.waitForElementPresent(TITLE_SECOND, "Cannot find Article Title",
                20);
    }

    @Step("Getting title of second article")
    public String getSecondArticleTitle() {
        WebElement title_element  = waitForSecondTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else  if (Platform.getInstance().isIos()) {
            return title_element.getAttribute("name");
        }
        else {
            return title_element.getText();
        }
    }

    @Step("Getting Article title")
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIos()){
            return title_element.getAttribute("name");
        }
        else {
            return title_element.getText();
        }
    }

    @Step("Swiping article to the footer")
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()){
        this.swipeUpToFindElement(FOOTER_ELEMENT,
                "Cannot find the end of article",
                40);}
        else if (Platform.getInstance().isIos()) {
            this.swipeUpTillElementAppears(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        }
    }

    @Step("Adding article to my reading list")
    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "no more options",
                15
        );
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "no reading list",
                5);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,
                "GOT IT button not found",
                15);
        this.waitForElementAndClear(MY_LIST_NAME_INPUT,
                "input not found",
                5);

        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                name_of_folder,
                "no input",
                5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5);
    }

    @Step("Adding article to existing reading list")
    public void addArticleToExistingMyList(){
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "no more options",
                15
        );
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "no reading list",
                5);
        this.waitForElementAndClick(
                EXISTING_FOLDER,
                "cannot find already created list",
                5
        );
    }

    @Step("Closing article")
    public void closeArticle(){
        if (Platform.getInstance().isIos() || Platform.getInstance().isAndroid()) {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                  "Cannot close article",
                15
        );}
        else {
            System.out.println("The method CloseArticle is doing nothing for platform " +Platform.getInstance().getPlatformVar() );
        }
    }

    @Step("Adding article to my saved articles")
    public void addArticleToMySaved(){
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option add to my reading list", 15);

    }

    @Step("Removing article from my saved article if it has been already added to my reading list")
    public void removeArticleFromMySavedIfItIsAdded(){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click Remove from my list button",
                    15);
            this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button Add to my list",
                    15);
        }
    }

    @Step("Checking if article has been opened by waiting for title element")
    public void articleOpened(){
        this.waitForTitleElement();
    }

    @Step("checking article title element")
    public void checkArticleTitleElement(){
        this.assertElementPresent(TITLE, "Article title element is not present");
    }
}
