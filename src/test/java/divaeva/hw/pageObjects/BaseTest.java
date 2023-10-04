package divaeva.hw.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://hotline.ua/");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
