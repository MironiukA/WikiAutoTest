package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


import java.util.List;

public class SearchInList extends MainPageObject
{
    private static final String
        SEARCH_FOR_LIST_FROM_RESULT = "//*[@class = 'android.view.ViewGroup']" +
            "//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][contains(@text, 'Java')]";

    public SearchInList(AppiumDriver driver) {
        super(driver);
    }

    public List WaitListWithSearchResults()
    {
        return this.getListWithElementsText(By.xpath(SEARCH_FOR_LIST_FROM_RESULT));
    }

    public void PresentSearchResult()
    {
        this.findTextByList(WaitListWithSearchResults(),
                "Проверить наличие элементов",
                "Cannot find text" );
    }


}
