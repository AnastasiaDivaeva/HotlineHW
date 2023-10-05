package divaeva.hw.pageObjects;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductDetailsPage extends BaseTest {

    @Test(description = "Check product information present")
    @Description("Test description: verify that product information is present ")
    public void checkProductInformationPresent() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnComputerTableButton();
        FiltersPage filtersPage = new FiltersPage(getDriver());
        filtersPage.clickOnComputerTableLink();
        DetailPage detailPage = new DetailPage(getDriver());
        detailPage.clickOnProductDetailsSection();
        String actualSectionTitle = detailPage.getCharacteristicSectionTitle();
        Assert.assertEquals(actualSectionTitle, "Характеристики");
    }
}
