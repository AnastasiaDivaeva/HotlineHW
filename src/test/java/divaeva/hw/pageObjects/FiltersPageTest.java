package divaeva.hw.pageObjects;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(TestListener.class)
public class FiltersPageTest extends BaseTest {

    @DataProvider(name = "searchMaxPrice")
    public Object[][] createPriceData() {
        return new Object[][]{
                {5000, 5000},
                {20000, 20000},
                {9000, 9000},
        };
    }

    @Test(dataProvider = "searchMaxPrice")
    @Description("Test description: verify price filter")
    public void checkPriceFilter(Integer upperPrice, Integer expectedValue) {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnComputerTableButton();
        FiltersPage filtersPage = new FiltersPage(getDriver());
        filtersPage.setUpperPriceToFilterWithRetry(upperPrice);
        filtersPage.clickOnOkButton();
        List<Integer> prices = filtersPage.getPrices();
        for (Integer price : prices) {
            Assert.assertTrue(price < expectedValue);
        }
    }
}
