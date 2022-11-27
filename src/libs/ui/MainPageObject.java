package libs.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class MainPageObject  {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }


    public WebElement waitForElementPresent(By by, String error_message, long timeout_in_seconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeout_in_seconds) ;
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by, error_message, 15);
    }

    public void assertElementHasText(WebElement element, String expected_text) {
        String actual_text = element.getAttribute("text");
        if (actual_text.equals(expected_text)) {

            //System.out.println("Success! Element was find");
            return;
        }
        else {
            Assert.fail("Test failed! Element was not find");

        }

    }
    public WebElement findElementAndClick(By by, String error_message, long timeout_in_seconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.click();
        return element;
    }

    public WebElement waitElementAndSendKey(By by, String value, String error_message, long timeout_in_seconds) {
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.sendKeys(value);
        driver.navigate().back();
        return element;
    }

    public void findTextByList(List<WebElement> list, String message, String error_message)
    {
        System.out.println(message);
        if (list.size()>=2)
        {
            int p =0;
            for(WebElement element:list){

                System.out.println(p+":"+element.getText());
                p++;
            }
        } else if (list.size() == 1) {

            System.out.println("Find only one result: " +list.get(0).getAttribute("text"));
        }
        else {

            System.out.println("Nothing found");
        }
    }
    public boolean waitForElementNotPresent(By by, String error_message, long timeout_in_seconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeout_in_seconds);
        return wait.until (
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int y1 = (int) (size.height*0.8);
        int y2 = (int) (size.height*0.2);
        action
                .press(PointOption.point(x, y1))
                .waitAction(waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, y2))
                .release()
                .perform();
       }
    public   void swipeUpQuick()
    {
        swipeUp(200);
    }
    public void swipeUpToFindElement(By by, String error_message, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped >= maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message, 15);
        // String actual_text = element.getAttribute("text");
        int left_x = (int) ((element.getLocation().getX()) * 0.1);
        int right_x = (int) ((left_x + element.getSize().getWidth()) * 0.3);
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;


        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();
    }
    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public List getListWithElementsText(By by) {
        List elements =  driver.findElements(by);
        return elements;
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeout_in_seconds) {
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        return element.getAttribute(attribute);
    }
    public   void assertElementNotPresent(By by, String error_message) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String default_message = "An element " + by.toString() + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }



}
