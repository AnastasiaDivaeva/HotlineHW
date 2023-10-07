package divaeva.hw.pageObjects;

import com.google.common.collect.Iterators;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShopPage extends BasePage {
    private static final By BUY_BUTTON = By.xpath("//a[@data-class-state-in-cart='b-custom-button_theme_buy']");
    private static final By PLACE_ORDER_BUTTON = By.xpath("//button[contains(text(),'Оформити замовлення')]");


    public ShopPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on buy button")
    public void clickOnBuyButton() {
        String windowHandle = Iterators.getLast(getDriver().getWindowHandles().iterator());
        getDriver().switchTo().window(windowHandle);
        getDriver().findElement(BUY_BUTTON).click();
    }

    @Step("Check if the order confirmation was found")
    public boolean isOrderConfirmationFound() {
        return findElementWithVisibilityWaiter(PLACE_ORDER_BUTTON).isDisplayed();

    }
}
