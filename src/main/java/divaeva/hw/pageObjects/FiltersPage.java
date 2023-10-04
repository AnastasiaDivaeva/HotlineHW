package divaeva.hw.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
    private static final By OUT_OF_FILTER_PRICE_BOUND_PRODUCT = By.xpath("//*[contains(text(), 'Cougar Royal 150 White')]");
    private static final By RESULT_SEARCH_FIELD = By.xpath("//div[@aria-owns='autosuggest-autosuggest__results']");
    private static final By RESULT_SEARCH_TITLE = By.xpath("//div[@class='search__title']");

    public FiltersPage(WebDriver driver) {
        super(driver);
    }

    public void setUpperPriceToFilterWithRetry(Integer upperPrice, int retryMax, By locator) {
        for (int i = 0; i < retryMax; i++) {
            try {
                System.out.println("Retry attempt for setting upper price in filter: " + i);
                WebElement maxPriceInput = waitUntilElementVisibility(INPUT_MAX_PRICE);
                maxPriceInput.clear();
                maxPriceInput.sendKeys(upperPrice.toString());
                return;
            } catch (StaleElementReferenceException ex) {
                System.out.println("Retry failed for setting upper price in filter: " + i);
            }
        }
        throw new RuntimeException("Cannot find element: " + locator);
    }

    public List<String> getElementsTextWithRetry(By locator, int retryMax) {
        for (int i = 0; i < retryMax; i++) {
            try {
                System.out.println("Retry attempt for getting element's text: " + i);
                List<WebElement> foundProducts = getDriver().findElements(locator);
                List<String> elementsText = new ArrayList<>();
                for (WebElement element : foundProducts) {
                    elementsText.add(element.getText());
                }
                return elementsText;
            } catch (StaleElementReferenceException ex) {
                System.out.println("Retry failed for getting element's text: " + i);
            }
        }
        throw new RuntimeException("Cannot find element: " + locator);
    }

    public void clickOnOkButton() {
        waitUntilElementVisibility(OK_BUTTON).click();
        waitForItemOutOfFiltersValueBound();
    }

    private void waitForItemOutOfFiltersValueBound() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        WebElement element = getDriver().findElement(OUT_OF_FILTER_PRICE_BOUND_PRODUCT);
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, "Cougar Royal 150 White")));
    }

    public List<Integer> getPrices() {
        List<String> prices = getElementsTextWithRetry(ONE_PAGE_PRODUCTS_PRICES, 10);
        List<Integer> parsedPrices = new ArrayList<>();
        for (String actualPrice : prices) {
            parsedPrices.add(new Integer(actualPrice.replaceAll(" ", "")));
        }
        return parsedPrices;
    }

    public void clickOnComputerTableLink() {
        waitUntilElementVisibility(COMPUTER_TABLE_LINK).click();
    }

    public String getSearchResultField() {
        return waitUntilElementVisibility(RESULT_SEARCH_FIELD).getText();
    }

    public String getSearchResultTitle() {
        return waitUntilElementVisibility(RESULT_SEARCH_TITLE).getText();
    }
}
