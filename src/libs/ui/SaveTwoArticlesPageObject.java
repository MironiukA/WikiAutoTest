package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SaveTwoArticlesPageObject extends MainPageObject{
    public SaveTwoArticlesPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    private static final String
            SEARCH_BUTTON_SAVE = "org.wikipedia:id/page_save",

            MY_LIST_NAME = "org.wikipedia:id/text_input",
            SEARCH_BUTTON_ADD_TO_LIST = "//*[@class='android.widget.Button'][@text = 'ADD TO LIST']",
            SEARCH_BUTTON_OK = "android:id/button1",
            SEARCH_BUTTON_BACK ="//*[@class = 'android.widget.ImageButton'][@content-desc='Navigate up']",

            SEARCH_MY_LIST_NAME="//*[@class='android.widget.TextView'][@text = '{SUBSTRING}']",
            SEARCH_RETURN_LISTS = "/hierarchy/android.widget.FrameLayout/android" +
                    ".widget.LinearLayout/android.widget" +
                    ".FrameLayout/android.widget.FrameLayout/android.widget" +
                    ".FrameLayout/android.widget.FrameLayout/android.widget" +
                    ".LinearLayout/android.widget.FrameLayout[1]/android" +
                    ".view.ViewGroup/android.widget.ImageButton",
            SEARCH_FROM_SAVE = "org.wikipedia:id/nav_tab_reading_lists",
            SEARCH_MY_LIST_FROM_SAVE = "//*[@resource-id='org.wikipedia:id/item_title'][@text = '{SUBSTRING}']",
            DESCRIPTION_NAME = "//*[@resource-id ='pcs-edit-section-title-description']";


    private static String getResultNameList(String substring)
    {
        return SEARCH_MY_LIST_NAME.replace("{SUBSTRING}",substring);
    }

    private static String getResultSavedList(String substring)
    {
        return SEARCH_MY_LIST_FROM_SAVE.replace("{SUBSTRING}",substring);
    }
    public void initButtonSave()
    {
        this.findElementAndClick(By.id(SEARCH_BUTTON_SAVE),
                "Cannot find button Save",
                5
        );
    }

    public void initButtonAdd()
    {
        this.findElementAndClick(By.xpath(SEARCH_BUTTON_ADD_TO_LIST),
                "Cannot find button Save",
                5
        );
    }

    public void saveNewListName(String list_name)
    {
       this.waitElementAndSendKey(By.id(MY_LIST_NAME),
               list_name,
               "Cannot save new list",
               10);
    }

    public void initButtonOk()
    {
        this.findElementAndClick(By.id(SEARCH_BUTTON_OK),
                "Cannot find button Ok",
                5
        );
    }

    public void initButtonBack()
    {
        this.findElementAndClick(By.xpath(SEARCH_BUTTON_BACK),
                "Cannot find button Ok",
                5
        );
    }

    public void initMyList(String substring)
    {
        String search_result_substring = getResultNameList(substring);
        this.findElementAndClick(By.xpath(search_result_substring),
                "Cannot find button my List name" + substring,
                5
        );
    }

    public void initButtonSaves()
    {
        this.findElementAndClick(By.xpath(SEARCH_RETURN_LISTS),
                "Cannot find button back_2",
                5
        );
    }

    public void initSavedList()
    {
        this.findElementAndClick(By.id(SEARCH_FROM_SAVE),
                "Cannot find My List name from Save",
                10
        );
    }

    public void initMySavedNameList(String substring)
    {
        String search_result_substring = getResultSavedList(substring);
        this.findElementAndClick(By.xpath(search_result_substring),
                "Cannot find my List name" + substring,
                5
        );
    }

    public void deleteSavedArticleTwo(String substring)
    {
        String search_result_substring = getResultNameList(substring);
        this.swipeElementToLeft(By.xpath(search_result_substring),
                "Cannot find Article");
    }

    public void checkForAvailable(String substring)
    {
        String search_result_substring = getResultNameList(substring);
        this.waitForElementNotPresent(By.xpath(search_result_substring),
                "The result is still displayed",
                10);
    }
    public String compareFirstArticle(String substring)
    {
        String search_result_after_delete = getResultNameList(substring);
        String article = this.waitForElementAndGetAttribute(By.xpath(search_result_after_delete),
                "text",
                "Cannot find the title of article",10);
        return article;
    }

    public String getArticleTitle()
    {
        return this.waitForElementAndGetAttribute(By.xpath(DESCRIPTION_NAME),
                "text",
                "Cannot find attribute",10);

    }



}
