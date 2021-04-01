package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import net.bytebuddy.pool.TypePool;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

import static org.openqa.selenium.ScreenOrientation.*;

public class CoreTestCase {
    protected RemoteWebDriver driver;

    @Before
    public void setUp () throws Exception {
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIosApp();
        this.openWikiPageForMobileWeb();
    }
    @After
    public void tearDown () {
        driver.quit();
    }
    protected void rotateScreenPortrait() {

        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(PORTRAIT);}
    else
    {
        System.out.println("The method rotateScreenPortrait doing nothing for platform" + Platform.getInstance().getPlatformVar());
    }
    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(LANDSCAPE); }
            else
        {
            System.out.println("The method rotateScreenLandscape doing nothing for platform" + Platform.getInstance().getPlatformVar());

        }
    }
    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        }
        else
        {
            System.out.println("The method backgroundApp doing nothing for platform" + Platform.getInstance().getPlatformVar());

        }
    }
    protected void openWikiPageForMobileWeb(){
        if (Platform.getInstance().isMw()) {
            driver.get("https://en.m.wikipedia.org");
        }
        else {
            System.out.println("The method openWikiPageForMobileWeb doing nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }
    private void skipWelcomePageForIosApp(){
        if (Platform.getInstance().isIos())
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
}
