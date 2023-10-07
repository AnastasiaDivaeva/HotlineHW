package divaeva.hw.pageObjects;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class SearchPageTest extends BaseTest {
    @Test(description = "Compare search results")
    @Description("Test description: verify search result")
    public void compareSearchResults() {
        String input = "клавіатура";
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnSearchButton(input);
        SearchPage searchPage = new SearchPage(getDriver());
        String searchResultTitleValue = searchPage.getSearchResultTitle();
        Assert.assertTrue(searchResultTitleValue.contains(input));
    }
}
