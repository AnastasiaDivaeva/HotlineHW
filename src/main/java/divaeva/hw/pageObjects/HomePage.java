package divaeva.hw.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By COMPUTER_TABLE_BUTTON = By.xpath("//a[@data-eventcontext='Компьютерный стол']");
    private static final By SEARCH_BUTTON = By.xpath("//input[@placeholder='Знайти товар, магазин, бренд']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on computer table button")
    public void clickOnComputerTableButton() {
        getDriver().findElement(COMPUTER_TABLE_BUTTON).click();
    }

    @Step("click on the search button and enter the value")
    public void clickOnSearchButton(String input) {
        getDriver().findElement(SEARCH_BUTTON).sendKeys(input, Keys.ENTER);
    }
}
