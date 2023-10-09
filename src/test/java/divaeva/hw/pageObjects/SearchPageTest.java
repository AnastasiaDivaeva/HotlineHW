package divaeva.hw.pageObjects;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchPageTest extends BaseTest {
    @Test()
    public void compareSearchResults() {
        String input = "клавіатура";
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnSearchButton(input);
        SearchPage searchPage = new SearchPage(getDriver());
        String searchResultTitleValue = searchPage.getSearchResultTitle();
        Assert.assertTrue(searchResultTitleValue.contains(input));
    }
}
