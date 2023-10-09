package divaeva.hw.pageObjects;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ShopPageTest extends BaseTest {

    @Test()
    public void checkingAdditionProductToTheCart() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnComputerTableButton();
        FiltersPage filtersPage = new FiltersPage(getDriver());
        filtersPage.clickOnComputerTableLink();
        DetailPage detailPage = new DetailPage(getDriver());
        detailPage.clickOnTheBuyProductButton();
        ShopPage shopPage = new ShopPage(getDriver());
        shopPage.clickOnBuyButton();
        Assert.assertTrue(shopPage.isOrderConfirmationFound());
    }
}
