package divaeva.hw.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FiltersPage extends BasePage {
    private static final By INPUT_MAX_PRICE = By.xpath("//input[@class='filter-price__range-field field'][2]");
    private static final By OK_BUTTON = By.xpath("//button[@class='filter-price__range-btn btn btn--graphite'][1]");
    private static final By ONE_PAGE_PRODUCTS_PRICES = By.className("price__value");
    private static final By COMPUTER_TABLE_LINK = By.xpath("//a[@href='/ua/dom-ofisnye-i-kompyuternye-stoly/ikea-utespelare-80507627/'] ");

    public FiltersPage(WebDriver driver) {
        super(driver);
    }

    @Step("Set upper price to filter with retry")
    public void setUpperPriceToFilterWithRetry(Integer upperPrice) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        WebElement maxPriceInput = wait.until(ExpectedConditions.visibilityOfElementLocated(INPUT_MAX_PRICE));
        maxPriceInput.clear();
        maxPriceInput.sendKeys(upperPrice.toString());
    }

    @Step("Click on Ok button")
    public void clickOnOkButton() {
        List<WebElement> pricesBeforeRedrawn = getDriver().findElements(By.xpath("//div[@class='list-item list-item--row']"));

        waitUntilElementVisibility(OK_BUTTON).click();
        waitForElementsRedrawn(pricesBeforeRedrawn);
    }

    public List<Integer> getPrices() {
        List<String> prices = getElementsText(ONE_PAGE_PRODUCTS_PRICES);
        List<Integer> parsedPrices = new ArrayList<>();
        for (String actualPrice : prices) {
            parsedPrices.add(new Integer(actualPrice.replaceAll(" ", "")));
        }
        return parsedPrices;
    }

    @Step("Click on computer table link ")
    public void clickOnComputerTableLink() {
        waitUntilElementVisibility(COMPUTER_TABLE_LINK).click();
    }

    private void waitForElementsRedrawn(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(1));
        while (true) {
            for (WebElement oldElement : elements) {
                try {
                    wait.until(ExpectedConditions.stalenessOf(oldElement));
                    return;
                } catch (TimeoutException ignored) {
                }
            }
        }
    }

    private List<String> getElementsText(By locator) {
        List<WebElement> foundProducts = getDriver().findElements(locator);
        List<String> elementsText = new ArrayList<>();
        for (WebElement element : foundProducts) {
            elementsText.add(element.getText());
        }
        return elementsText;
    }
}
