package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;


public class WelcomePageObject extends MainPageObject{
    private static final String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREF_LANG = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "id:Learn more about data collected",
    GET_STARTED = "id:Get started",
    NEXT_BUTTON = "id:Next",
    SKIP = "id:Skip";
    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting Learn more link in Welcome page in iOS")
    public void waitForLearnMoreLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,
                "Cannot find Learn more element",
                10);
    }

    @Step("Clicking next button in Welcome page in iOS")
    public void clickNextButton(){
        this.waitForElementAndClick(NEXT_BUTTON,
                "Cannot find Next button",
                10);
    }

    @Step("Clicking Get Started button in iOS")
    public void clickGetStartedButton(){
        this.waitForElementAndClick(GET_STARTED,
                "Cannot find Get Started button",
                10);
    }

    @Step("Waiting for New Way to Explore text in welcome page in iOS")
    public void waitForNewWayToExploreText(){
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE,
                "Cannot find New ways to explore",
                10);
    }

    @Step("Waiting for add or edit language in Welcome page in iOS")
    public void waitForAddOrEditLangText(){
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREF_LANG,
                "Cannot find Add or edit preferred languages",
                10);
    }

    @Step("Waiting for Learn more data collected text in Welcome page in iOS")
    public void waitForLearnMoreDataCollectedText(){
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,
                "Cannot find Learn more about data collected element",
                10);
    }

    @Step("Skipping Welcome page in iOS")
    public void clickSkip(){
        this.waitForElementAndClick(SKIP, "Cannot find Skip Button", 15);
    }
}
