package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Features(value = {@Feature(value = "Onboarding") })
    @DisplayName("Onboarding in iOS test")
    @Description("We are going through onboarding in iOS")
    @Step("Starting going through onboarding in iOS test")
    @Severity(value = SeverityLevel.MINOR)
    @Test
    public void testPassThroughWelcome(){
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMw()))
        {
            return;
        }
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForNewWayToExploreText();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForAddOrEditLangText();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForLearnMoreDataCollectedText();
        WelcomePageObject.clickGetStartedButton();
    }
}
