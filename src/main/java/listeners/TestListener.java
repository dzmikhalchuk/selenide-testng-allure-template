package listeners;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashSet;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TestListener implements ITestListener {

    private static final Set<ITestResult> failedTests = new HashSet<>();
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {}

    @Override
    public void onTestSuccess(ITestResult result) {}

    @Override
    public void onTestFailure(ITestResult result) {

        // Issue of TestNG: ITestListener methods invokes several times
        // Collect failed test results in HashSet for avoiding taking lots of screenshots
        if (!failedTests.contains(result) && WebDriverRunner.hasWebDriverStarted()) {
            logger.info("Screenshot captured for test case: " + result.getMethod().getMethodName());
            attachScreenshot();
            attachBrowserLogs();
            attachPageSource();
            failedTests.add(result);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        // Issue of TestNG: ITestListener methods invokes several times
        // Collect failed test results in HashSet for avoiding taking lots of screenshots
        if (!failedTests.contains(result) && WebDriverRunner.hasWebDriverStarted()) {
            logger.info("Screenshot captured for test case: " + result.getMethod().getMethodName());
            attachScreenshot();
            attachBrowserLogs();
            attachPageSource();
            failedTests.add(result);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}

    @Attachment(value = "### Test finished: Page Source", type = "text/html", fileExtension = "html")
    private static byte[] attachPageSource() {
        return WebDriverRunner.getWebDriver().getPageSource().getBytes(UTF_8);
    }

    @Attachment(value = "### Test finished: Page Screenshot", type = "image/png", fileExtension = "png")
    public static byte[] attachScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "### Test finished: Browser logs", type = "text/plain")
    public static String attachBrowserLogs() {

        StringBuilder logs = new StringBuilder();

        for (LogEntry entry : WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER)) {
            logs.append(entry.getLevel()).append(" ").append(entry.getMessage()).append(System.lineSeparator());
        }

        return logs.toString();
    }
}
