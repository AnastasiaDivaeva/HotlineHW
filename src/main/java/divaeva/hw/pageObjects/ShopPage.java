package divaeva.hw.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;
import java.util.Optional;

public class ShopPage extends BasePage {
    private static final By BUY_BUTTON = By.xpath("//a[@data-class-state-in-cart='b-custom-button_theme_buy']");
    private static final By PLACE_ORDER_BUTTON = By.xpath("//button[contains(text(),'Оформити замовлення')]");

    private WebDriver marketSiteDriver;

    public ShopPage(WebDriver driver) {
        super(driver);
        this.marketSiteDriver = initializeMarketSiteDriver(driver);
    }

    private WebDriver initializeMarketSiteDriver(WebDriver driver) {
        String expectedUrlAfterRedirection = "https://markett.com.ua/ua/p1528991649-ikea-utespelare-igrovoj.html";
        Optional<WebDriver> marketDriver = driver.getWindowHandles()
                .stream()
                .map(windowId -> driver.switchTo().window(windowId))
                .filter(windowDriver -> windowDriver.getCurrentUrl().equals(expectedUrlAfterRedirection))
                .findFirst();
        if (marketDriver.isPresent()) {
            return marketDriver.get();
        } else {
            throw new NoSuchElementException("No market site window found");
        }
    }

    @Step("Click on buy button")
    public void clickOnBuyButton() {
        waitUntilElementToBeClickable(marketSiteDriver, BUY_BUTTON).click();
    }

    public boolean isOrderConfirmationFound() {
        try {
            waitUntilElementVisibility(PLACE_ORDER_BUTTON);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            return false;
        }
    }
}
