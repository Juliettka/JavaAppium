package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import net.bytebuddy.pool.TypePool;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

import static org.openqa.selenium.ScreenOrientation.*;

public class CoreTestCase {
    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp () throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIosApp();
        this.openWikiPageForMobileWeb();
    }
    @After
    @Step("Remove driver and session")
    public void tearDown () {
        driver.quit();
    }

    @Step("Rotate screen to portrait mode")
    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(PORTRAIT);}
    else
    {
        System.out.println("The method rotateScreenPortrait doing nothing for platform" + Platform.getInstance().getPlatformVar());
    }
    }

    @Step("Rotate screen to landscape mode")
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(LANDSCAPE); }
            else
        {
            System.out.println("The method rotateScreenLandscape doing nothing for platform" + Platform.getInstance().getPlatformVar());

        }
    }

    @Step("Send mobile app to background (doing nothing to mobile web)")
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
    @Step("Open Wikipedia webpage for mobile web")
    protected void openWikiPageForMobileWeb(){
        if (Platform.getInstance().isMw()) {
            driver.get("https://en.m.wikipedia.org");
        }
        else {
            System.out.println("The method openWikiPageForMobileWeb doing nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip Welcome page for iOS")
    private void skipWelcomePageForIosApp(){
        if (Platform.getInstance().isIos())
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }

    private void createAllurePropertyFile(){
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar()) ;
            props.store(fos, "See https://github.com/allure-framework");
            fos.close();
        }
        catch (Exception e) {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
