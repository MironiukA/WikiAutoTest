import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.PerformsActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "and80");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub/"), capabilities);

    }

    @After
    public void tearDown() {

        driver.quit();
    }

    @Test
    public void CheckElementIncludeText() {

        WebElement element_to_skip = driver.findElement(By.xpath("//*[contains(@text, 'SKIP')]"));
        element_to_skip.click();


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebElement element = waitForElementPresent(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' text",
                5
        );

        assertElementHasText(
                element,
                "Search Wikipedia"
        );

        int i = driver.findElements(By.xpath("//*[@class = 'android.view.ViewGroup']//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][contains(@text, 'Java')]")).size();




    }

    @Test

    public void SearchEx2() {
        WebElement element_to_skip = driver.findElement(By.xpath("//*[contains(@text, 'SKIP')]"));
        element_to_skip.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        findElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' text",
                5

        );

        waitElementAndSendKey(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find any text",
                10
        );


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> list =
                driver.findElements(By.xpath("//*[@class = 'android.view.ViewGroup']" +
                        "//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][contains(@text, 'Java')]"));
        findTextByList(list, "Проверить наличие элементов", "Cannot find text");


        findElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X",
                5

        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_display"),
                "WTF",
                3
        );
    }


    @Test
    public void saveTwoArticles(){
        WebElement element_to_skip = driver.findElement(By.xpath("//*[contains(@text, 'SKIP')]"));
        element_to_skip.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String first_article_name = "Testing framework for web applications";
        String second_article_name = "Use of Selenium by organisms";


        findElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' text",
                5

        );

        waitElementAndSendKey(
                By.id("org.wikipedia:id/search_src_text"),
                "Selenium",
                "Cannot find Selenium text",
                15
        );

        findElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_description'][contains(@text, '"+ first_article_name+"')]"),
                "Cannot find "+first_article_name+" text",
                15

        );
        waitForElementPresent(
                By.xpath("//*[@resource-id = 'pcs-edit-section-title-description'][contains(@text, '"+first_article_name+"')]"),
                "Cannot find "+first_article_name+"  text on page",
                15
        );

        findElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button save",
                15
        );
        findElementAndClick(
                By.xpath("//*[@class='android.widget.Button'][@text = 'ADD TO LIST']"),
                "Cannot find button save",
                15
        );

        String name_list = "Selenium";
        waitElementAndSendKey(
                By.id("org.wikipedia:id/text_input"),
                name_list,
                "Cannot find input",
                15
        );
        findElementAndClick(
                By.id("android:id/button1"),
                "Cannot find button Ok",
                15
        );



        findElementAndClick(
                By.xpath("//*[@class = 'android.widget.ImageButton'][@content-desc='Navigate up']"),
                "Cannot find button back",
                15
        );

        findElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_description'][contains(@text, '"+second_article_name+"')]"),
                "Cannot find "+second_article_name+" text",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'pcs-edit-section-title-description'][contains(@text, '"+second_article_name+"')]"),
                "Cannot find "+second_article_name+"",
                15
        );

        findElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button save",
                15
        );
        findElementAndClick(
                By.xpath("//*[@class='android.widget.Button'][@text = 'ADD TO LIST']"),
                "Cannot find button save",
                15
        );
        findElementAndClick(
                By.xpath("//*[@class='android.widget.TextView'][@text = '"+ name_list +"']"),
                "Cannot find Saved list",
                15
        );

        findElementAndClick(
                By.xpath("//*[@class = 'android.widget.ImageButton'][@content-desc='Navigate up']"),
                "Cannot find button back",
                15
        );

        findElementAndClick(
                By.xpath("/hierarchy/android.widget.FrameLayout/android" +
                        ".widget.LinearLayout/android.widget" +
                        ".FrameLayout/android.widget.FrameLayout/android.widget" +
                        ".FrameLayout/android.widget.FrameLayout/android.widget" +
                        ".LinearLayout/android.widget.FrameLayout[1]/android" +
                        ".view.ViewGroup/android.widget.ImageButton"),
                "Cannot find button back_2",
                15
        );
        findElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find Saved title",
                15
        );

        findElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text = 'Selenium']"),
                "Cannot find Selenium title",
                15
        );



        swipeElementToLeft(
                By.xpath("//android.widget.TextView[contains(@text, '"+second_article_name+"')]"),
                "Cannot find Selenium in biology");

        waitForElementNotPresent(By.xpath("//android.widget.TextView[contains(@text, '"+second_article_name+"')]"),
                "Cannot find Selenium in biology",
                10);
        ;

       findElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text,'"+first_article_name+"')]"),
                "Cannot find "+first_article_name+"",
                10
        );

        waitForElementPresent(
                By.xpath("//*[@text ='"+first_article_name+"']"),
                "Cannot find "+first_article_name+" title",
                15
        );
//
    }

    @Test
    public void checkTitleOfArticle() {
        findElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
                5
        );

        findElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitElementAndSendKey(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "java",
                "Cannot find search input",
                5
        );
        findElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );

        assertElementPresent(
                By.xpath("//android.view.View[contains(@text, 'JavaScript')]"),
                "No results found for the desired title"
        );

    }



    private WebElement waitForElementPresent(By by, String error_message, long timeout_in_seconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeout_in_seconds) ;
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by, error_message, 15);
    }

    private void assertElementHasText(WebElement element, String expected_text) {
        String actual_text = element.getAttribute("text");
        if (actual_text.equals(expected_text)) {

            //System.out.println("Success! Element was find");
            return;
        }
        else {
            Assert.fail("Test failed! Element was not find");

        }

    }
    private WebElement findElementAndClick(By by, String error_message, long timeout_in_seconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.click();
        return element;
    }

    private WebElement waitElementAndSendKey(By by, String value, String error_message, long timeout_in_seconds) {
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.sendKeys(value);
        driver.navigate().back();
        return element;
    }

    private void findTextByList(List<WebElement> list,String message,String error_message)
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
    private boolean waitForElementNotPresent(By by, String error_message, long timeout_in_seconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeout_in_seconds);
        return wait.until (
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int y1 = (int) (size.height*0.8);
        int y2 = (int) (size.height*0.2);
        action.press(x,y1).waitAction().moveTo(x,y2).release().perform();
    }
    protected  void swipeUpQuick()
    {
        swipeUp(200);
    }
    protected void swipeUpToFindElement(By by, String error_message, int maxSwipes) {
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

    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message, 15);
       // String actual_text = element.getAttribute("text");
        int left_x = (int) ((element.getLocation().getX()) * 0.1);
        int right_x = (int) ((left_x + element.getSize().getWidth()) * 0.3);
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        System.out.println(right_x);
        System.out.println(middle_y);

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();


    }
    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }
    private  void assertElementPresent(By by, String error_message) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements <= 0) {
            String default_message = "An element " + by.toString() + " supposed to be presented at the page";
            throw new AssertionError(default_message + " " + error_message);
        }
    }


}

