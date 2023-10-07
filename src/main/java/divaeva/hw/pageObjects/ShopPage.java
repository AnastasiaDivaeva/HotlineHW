package divaeva.hw.pageObjects;

import com.google.common.collect.Iterators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShopPage extends BasePage {
    private static final By BUY_BUTTON = By.xpath("//a[@data-class-state-in-cart='b-custom-button_theme_buy']");
    private static final By PLACE_ORDER_BUTTON = By.xpath("//button[contains(text(),'Оформити замовлення')]");

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnBuyButton() {
        String windowHandle = Iterators.getLast(getDriver().getWindowHandles().iterator());
        getDriver().switchTo().window(windowHandle);
        getDriver().findElement(BUY_BUTTON).click();
    }

    public boolean isOrderConfirmationFound() {
        return findElementWithVisibilityWaiter(PLACE_ORDER_BUTTON).isDisplayed();
    }
}
