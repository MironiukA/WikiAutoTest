import libs.CoreTestCase;
import libs.ui.MainPageObject;
import libs.ui.SaveTwoArticlesPageObject;
import libs.ui.SearchInList;
import libs.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Selenium");
        SearchPageObject.clickByArticleWithSubstrDescription("Testing framework for web applications");

    }
    @Test


    public void testSearchList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        SearchInList SearchInList = new SearchInList(driver);
        SearchInList.PresentSearchResult();
        SearchPageObject.initSearchButtonX();
        SearchPageObject.checkOutResult();
    }

    @Test
    public void testSaveTwoArticles(){


        String first_article_name = "Testing framework for web applications";
        String second_article_name = "Use of Selenium by organisms";
        String name_list = "Selenium";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Selenium");
        SearchPageObject.clickByArticleWithSubstrDescription(first_article_name);
        SearchPageObject.clickByArticleTitle(first_article_name);

        SaveTwoArticlesPageObject SaveTwoArticlesPageObject = new SaveTwoArticlesPageObject(driver);
        // Добавляем первую статью в избранное
        SaveTwoArticlesPageObject.initButtonSave();
        SaveTwoArticlesPageObject.initButtonAdd();
        SaveTwoArticlesPageObject.saveNewListName(name_list);
        SaveTwoArticlesPageObject.initButtonOk();
        SaveTwoArticlesPageObject.initButtonBack();
        // Добавляем вторую статью
        SearchPageObject.clickByArticleWithSubstrDescription(second_article_name);
        SearchPageObject.clickByArticleTitle(second_article_name);
        SaveTwoArticlesPageObject.initButtonSave();
        SaveTwoArticlesPageObject.initButtonAdd();
        SaveTwoArticlesPageObject.initMyList(name_list);
        SaveTwoArticlesPageObject.initButtonBack();

        SaveTwoArticlesPageObject.initButtonSaves();
        SaveTwoArticlesPageObject.initSavedList();
        SaveTwoArticlesPageObject.initMySavedNameList(name_list);
        // Удаляем вторую статью
        SaveTwoArticlesPageObject.deleteSavedArticleTwo(second_article_name);
        SaveTwoArticlesPageObject.checkForAvailable(second_article_name);
        //Открываем первую сатью и сравниваем результат
     //   SaveTwoArticlesPageObject.initMyList(first_article_name);
        String result_after_delete = SaveTwoArticlesPageObject.compareFirstArticle(first_article_name);
        Assert.assertEquals("Article is not compare", first_article_name,result_after_delete);
    }

      @Test
    public void testAmountOfNotEmptySearch(){
        String search_line = "Linkin Park discography";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.waitForSearchResultTitleFromList(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundsArticle();

        Assert.assertTrue(
                "We found a few results",
                amount_of_search_results > 0
        );
    }
    @Test
    public void testAmountOfEmptySearch() {
        String search_line = "liwdqwdawdasdsadasd";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.getEmptyResult();
        SearchPageObject.assertThereIsNoResult();
    }
    @Test
    public void testChangeOrientationScreenInSearchResults(){
        String search_line = "Java";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();

        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstrDescription("Object-oriented programming language");

        SaveTwoArticlesPageObject SaveTwoArticlesPageObject = new SaveTwoArticlesPageObject(driver);

        String title_before_rotation = SaveTwoArticlesPageObject.getArticleTitle();

        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = SaveTwoArticlesPageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = SaveTwoArticlesPageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }
    @Test
    public void testCheckArticleTextAfterBackground(){

        String search_line = "java";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResultTitleFromList("Java (programming language)");

        driver.runAppInBackground(Duration.ofSeconds(2));

        SearchPageObject.waitForSearchResultTitleFromList("Java (programming language)");
    }


}

