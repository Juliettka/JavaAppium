package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
//import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import sun.jvm.hotspot.utilities.Assert;
import org.junit.Assert;
import lib.Platform;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    @Step("Checking that element presents on the page")
    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    @Step("Checking that element does not present on the page")
    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "/n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    @Step("Waiting when element appears and click on it")
    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    @Step("Waiting when input field appears and type text")
    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    @Step("Waiting element to be appeared and delete text")
    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;

    }

    @Step("Checking that element has expected text")
    public String assertElementHasText(String locator, String error_message, String expected_text) throws Exception {
        WebElement element = waitForElementPresent(locator, error_message, 5);
        String actual_text = element.getAttribute("text");
        if (!actual_text.toLowerCase().equals(expected_text.toLowerCase())) {
            throw new Exception(error_message);
        } else {
            return actual_text;
        }
    }

    @Step("Checking that element is presented")
    public boolean isElementPresent(String locator){
        return getAmountOfElements(locator)> 0;
    }

    @Step("Trying to click on element few times")
    public void tryClickElementFewAttempts(String locator, String error_message, int amount_of_attempts){
        int current_attempts = 0;
        boolean need_more_attempts = true;
        while (need_more_attempts){
            try {
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e) {
                if (current_attempts>amount_of_attempts){
                    this.waitForElementAndClick(locator,error_message,5);
                }
            }
            ++current_attempts;
        }
    }
   // public static boolean assertElementContainsText(String locator, String error_message, String expected_text) throws Exception {
      //  WebElement element = waitForElementPresent(locator, error_message, 5);
    //    if (Platform.getInstance().isAndroid()) {
     //   String actual_text = element.getAttribute("text");
     //   if (actual_text.toLowerCase().contains(expected_text.toLowerCase()))
     //       return true;
    //    else throw new Exception(error_message);}
    //    else {
      //      String actual_text = element.getAttribute("name");
      //      if (actual_text.toLowerCase().contains(expected_text.toLowerCase()))
       //         return true;
       //     else throw new Exception(error_message);}
      //  }

    @Step("Swiping up")
    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver)driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        }
        else {
            System.out.println("Method swipeUp is doing nothing for platform"+Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Swiping up quickly")
    public void swipeUpQuick() {
        swipeUp(200);
    }

    @Step("Scrolling web page")
    public void scrollWebPageUp(){
        if (Platform.getInstance().isMw()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0,250)");
        } else {
            System.out.println("Method scrollWebPageUp is doing nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Scrolling web page till element not visible")
    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;
        WebElement element = this.waitForElementPresent(locator, error_message, 15);
        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped>max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    @Step("Swiping up to find element")
    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swipping up, \n" + error_message,
                        0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    @Step("Swiping up till element presents on the page")
    public void swipeUpTillElementAppears(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator))
        {
            if (already_swiped>max_swipes){
                Assert.assertTrue(error_message,this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    @Step("Checking if element presents on th page")
    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 1).getLocation().getY();
        if (Platform.getInstance().isMw()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object is_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y-=Integer.parseInt(is_result.toString());
        }
        int screen_size_by_y= driver.manage().window().getSize().getHeight();
        return element_location_by_y<screen_size_by_y;


    }

    @Step("Swiping element to the left")
    public void swipeElementToLeft(String locator, String error_message) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, error_message, 10);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            TouchAction action = new TouchAction((AppiumDriver)driver);
            action.press(PointOption.point(right_x, middle_y));
            action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));
            if (Platform.getInstance().isAndroid()) {
                action.moveTo(PointOption.point(left_x, middle_y));
            } else {
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(PointOption.point(offset_x, 0));
            }

            action.release();
            action.perform();
        }
        else {
            System.out.println("The method swipeToTheLeft doing nothing for platform" +Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Clicking element to the right upper corner")
    public void clickElementToTheRightUpperCorner(String locator, String error_message){
        if (driver instanceof AppiumDriver) {
        WebElement element = this.waitForElementPresent(locator+"//..", error_message, 5);
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (lower_y+upper_y)/2;
        int width = element.getSize().getWidth();
        int point_to_click_x = (right_x + width) -3;
        int point_to_click_y  = middle_y;
        TouchAction action = new TouchAction((AppiumDriver)driver);
        action.tap(PointOption.point(point_to_click_x, point_to_click_y)).perform();}
        else {
            System.out.println("The method clickElementToTheRightUpperCorner is doing nothing for platform"+Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Getting amount of elements")
    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    @Step("Checking that element is not presented on the page")
    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element" + locator + "is supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    @Step("Checking that element presents on the page")
    public void assertElementPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements == 0) {
            String default_message = "An element " + locator + " is supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    @Step("Waiting for element and getting attribute")
    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    @Step("Getting type of locator")
    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        }
        else if (by_type.equals("css"))
        {
            return By.cssSelector(locator);
        }else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator" + locator_with_type);
        }
    }

    @Step("Taking screenshot")
    public String takeScreenshot(String name){
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") +"/"+ name + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken"+path);
        }
        catch (Exception e){
            System.out.println("Cannot take screenshot. Error: "+e.getMessage());
        }
        return path;
    }
    @Attachment
    public static byte[] screenshot(String path) {
        byte bytes[]= new byte[0];
        try{
            bytes = Files.readAllBytes(Paths.get(path));
        }
        catch (IOException e) {
            System.out.println("Cannot get bytes from screenshot. Error: "+ e.getMessage());
        }
        return bytes;
    }
}
