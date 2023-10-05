package divaeva.hw.pageObjects;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShopPageTest extends BaseTest {

    @Test(description = "Checking addition product to the cart")
    @Description("Test description: verify adding product to the cart")
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
