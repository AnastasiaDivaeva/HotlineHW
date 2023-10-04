package divaeva.hw.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailPage extends BasePage {
    private static final By PRODUCT_BUTTON = By.partialLinkText("IKEA UTESPELARE");
    private static final By ABOUT_PRODUCT_BUTTON = By.xpath("//b[contains(text(),'Про товар')]");
    private static final By TITLE_CHARACTERISTIC = By.xpath("//h2[contains(text(),'Характеристики')]");
    private static final By BUY_BUTTON = By.xpath("//*[@id=\"tabs\"]/div[3]/div[2]/div[2]/div[1]/div[1]/div/div[1]/div");

    public DetailPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnProductDetailsSection() {
        getDriver().findElement(PRODUCT_BUTTON).click();
        waitUntilElementVisibility(ABOUT_PRODUCT_BUTTON).click();
    }

    public String getCharacteristicSectionTitle() {
        return waitUntilElementVisibility(TITLE_CHARACTERISTIC).getText();
    }

    public void clickOnTheBuyProductButton() {
        waitUntilElementVisibility(BUY_BUTTON).click();
    }

}
