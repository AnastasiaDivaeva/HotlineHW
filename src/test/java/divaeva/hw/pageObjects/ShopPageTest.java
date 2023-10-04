package divaeva.hw.pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShopPageTest extends BaseTest {

    @Test
    public void checkingAdditionProductToTheCart() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnComputerTableButton();
        FiltersPage filtersPage = new FiltersPage(getDriver());
        filtersPage.clickOnComputerTableLink();
        DetailPage detailPage = new DetailPage(getDriver());
        detailPage.clickOnTheBuyProductButton();
        ShopPage shopPage = new ShopPage(getDriver());
        shopPage.clickOnBuyButton();
        try {
            WebElement placeOrderButton = shopPage.findOrderConfirmation();
            Assert.assertTrue(placeOrderButton.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail();
        }
    }
}
