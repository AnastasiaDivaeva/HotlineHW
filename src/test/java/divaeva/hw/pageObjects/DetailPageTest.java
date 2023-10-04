package divaeva.hw.pageObjects;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DetailPageTest extends BaseTest {

    @Test
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
