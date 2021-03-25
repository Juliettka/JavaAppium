package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;

import java.time.Duration;

import static org.openqa.selenium.ScreenOrientation.*;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;

    @Override
    protected void setUp () throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIosApp();
    }
    @Override
    protected void tearDown () throws Exception {
        driver.quit();
        super.tearDown();
    }
    protected void rotateScreenPortrait() {
        driver.rotate(PORTRAIT);
    }
    protected void rotateScreenLandscape() {
        driver.rotate(LANDSCAPE);
    }
    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }
    private void skipWelcomePageForIosApp(){
        if (Platform.getInstance().isIos())
        {
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
}
