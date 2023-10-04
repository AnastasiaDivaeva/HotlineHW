package divaeva.hw.pageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class FiltersPageTest extends BaseTest {
    private static final By MAX_PRICE_INPUT = By.xpath("//input[@class='filter-price__range-field field'][2]");

    @DataProvider(name = "searchMaxPrice")
    public Object[][] createPriceData() {
        return new Object[][]{
                {5000, 5000},
                {20000, 20000},
                {9000, 9000},
        };
    }

    @Test(dataProvider = "searchMaxPrice")
    public void checkPriceFilter(Integer upperPrice, Integer expectedValue) {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnComputerTableButton();
        FiltersPage filtersPage = new FiltersPage(getDriver());
        filtersPage.setUpperPriceToFilterWithRetry(upperPrice, 10, MAX_PRICE_INPUT);
        filtersPage.clickOnOkButton();
        List<Integer> prices = filtersPage.getPrices();
        for (Integer price : prices) {
            Assert.assertTrue(price < expectedValue);
        }
    }

    @Test
    public void compareSearchResults() {
        String input = "клавіатура";
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnSearchButton(input);
        FiltersPage filtersPage = new FiltersPage(getDriver());
        String searchResultFieldValue = filtersPage.getSearchResultField();
        String searchResultTitleValue = filtersPage.getSearchResultTitle();
        Assert.assertTrue(searchResultTitleValue.contains(searchResultFieldValue));
    }
}
