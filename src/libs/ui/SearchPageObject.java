package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_BUTTON_SKIP ="org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_INIT_ELEMENT ="//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT ="org.wikipedia:id/search_src_text",

            SEARCH_RESULT_BY_TITLE_DESCRIPTION_SUBSTRING_TPL ="//*[@resource-id = 'pcs-edit-section-title-description'][contains(@text, '{SUBSTRING}')]",
            SEARCH_RESULT_PAGE = "org.wikipedia:id/search_results_display",
            SEARCH_BUTTON_X = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENTS = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']",
            SEARCH_EMPTY_RESULT_ELEMENTS = "//*[contains(@text, 'No results')]",

            SEARCH_RESULT_BY_TITLE_FROM_LIST_SUBSTRING_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = '{SUBSTRING}']",
            SEARCH_RESULT_BY_DESCRIPTION_SUBSTRING_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_description'][contains(@text, '{SUBSTRING}')]";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_DESCRIPTION_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }

    private static String getResultSearchElementByTitle(String substring_title)
    {
        return SEARCH_RESULT_BY_TITLE_DESCRIPTION_SUBSTRING_TPL.replace("{SUBSTRING}",substring_title);
    }

    private static String getResultSearchElementByTitleByList(String substring_title)
    {
        return SEARCH_RESULT_BY_TITLE_FROM_LIST_SUBSTRING_TPL.replace("{SUBSTRING}",substring_title);
    }

    /*TEMPLATES METHODS*/

    public void initSearchButton()
    {
        this.findElementAndClick(By.id(SEARCH_BUTTON_SKIP),
                "Cannot find Skip button",
                5);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.findElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitElementAndSendKey(By.id(SEARCH_INPUT),
                search_line,
                "Cannot find and type into search input",
                20);
    }

    public void clickByArticleWithSubstrDescription(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Cannot find result with substring" +substring);
        this.findElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click result" + substring,
                5);
    }

    public void initSearchButtonX()
    {
        this.findElementAndClick(By.id(SEARCH_BUTTON_X),
                "Cannot find X button",
                5);
    }

    public void checkOutResult()
    {
        this.waitForElementNotPresent(By.id(SEARCH_RESULT_PAGE),
                "The result is still displayed",
                3);
    }

    public void clickByArticleTitle(String substring_title)
    {
        String search_result_xpath = getResultSearchElementByTitle(substring_title);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Cannot find result with substring " +substring_title);
        this.findElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click result " + substring_title,
                5);
    }

    public void waitForSearchResultTitleFromList (String substring_title)
    {
        String search_result_xpath = getResultSearchElementByTitleByList(substring_title);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Cannot find result with substring " +substring_title);


    }

    public void clickByArticleWithSubTitle(String substring_title)
    {
        String search_result_xpath = getResultSearchElementByTitleByList(substring_title);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Cannot find result with substring " +substring_title);
        this.findElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click result " + substring_title,
                5);
    }


    public int getAmountOfFoundsArticle()
    {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENTS),
                "Cannot find SEARCH_RESULT_ELEMENTS",
                10);
        return  this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENTS));
    }

    public void getEmptyResult()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENTS),
                "Cannot find empty result label by the request ",
                10);
    }

    public void assertThereIsNoResult()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENTS),
                "We didn't find some result");
    }




}
