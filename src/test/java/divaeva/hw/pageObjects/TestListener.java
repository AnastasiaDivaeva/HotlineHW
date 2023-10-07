package divaeva.hw.pageObjects;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener extends BaseTest implements ITestListener {
    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "(0)", type = "text/plain")
    public static String saveTestLog(String message) {
        return message;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println("I am in onTestFailure method " + getTestMethodName(result) + "failed");

        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        if (driver instanceof WebDriver) {
            System.out.println("Screenshot capture for test case: " + getTestMethodName(result));
            saveScreenshotPNG(driver);
        }
        saveTestLog(getTestMethodName(result) + "failed and screenshot taken ");
    }
}
