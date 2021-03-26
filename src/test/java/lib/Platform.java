package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PlATFORM_ANDROID = "android";
    private static final String PLATFORM_WEB_MOBILE = "web_mobile";
    private static final String APPIUM_URL = "http://0.0.0.0:4723/wd/hub";
    private static Platform instance;
    private Platform() {}

    public static Platform getInstance(){
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        }
        else if (this.isIos()) {
            return new IOSDriver(URL, this.getIosDesiredCapabilities());
        } else
        {
            throw new Exception("Cannot return type of Driver. Platform is"+ this.getPlatformVar());
        }
    }
    public Boolean isAndroid() {
        return isPlatform(PlATFORM_ANDROID);
    }
    public Boolean isIos() {
        return isPlatform(PLATFORM_IOS);
    }
    public Boolean isMw() {return isPlatform(PLATFORM_WEB_MOBILE);}

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/admin/Desktop/Appium/apks/org.wikipedia.apk");
        return capabilities;
    }
    private DesiredCapabilities getIosDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 8");
        capabilities.setCapability("platformVersion", "13.3");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", "/Users/admin/Desktop/Appium/apks/Wikipedia.app");
        return capabilities;
    }
    private Boolean isPlatform (String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }
    public String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}
