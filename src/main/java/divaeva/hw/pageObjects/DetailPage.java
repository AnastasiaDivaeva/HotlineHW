package divaeva.hw.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailPage extends BasePage {
    private static final By PRODUCT_BUTTON = By.partialLinkText("IKEA UTESPELARE");
    private static final By ABOUT_PRODUCT_BUTTON = By.xpath("//b[contains(text(),'Про товар')]");
    private static final By TITLE_CHARACTERISTIC = By.xpath("//h2[contains(text(),'Характеристики')]");
    private static final By BUY_BUTTON = By.xpath("//a[@data-eventcategory='Pages Product Prices'][contains(text(),'markett')]");

    public DetailPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on product details section")
    public void clickOnProductDetailsSection() {
        getDriver().findElement(PRODUCT_BUTTON).click();
        findElementWithVisibilityWaiter(ABOUT_PRODUCT_BUTTON).click();
    }

    @Step("Get the characteristic section title")
    public String getCharacteristicSectionTitle() {
        return findElementWithVisibilityWaiter(TITLE_CHARACTERISTIC).getText();
    }

    @Step("Click on the buy product button")
    public void clickOnTheBuyProductButton() {
        findElementWithVisibilityWaiter(BUY_BUTTON).click();
    }
}
