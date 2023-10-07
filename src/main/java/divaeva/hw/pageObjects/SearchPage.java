package divaeva.hw.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {
    private static final By RESULT_SEARCH_TITLE = By.xpath("//div[@class='search__title']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the title of search results")
    public String getSearchResultTitle() {
        return findElementWithVisibilityWaiter(RESULT_SEARCH_TITLE).getText();
    }
}
