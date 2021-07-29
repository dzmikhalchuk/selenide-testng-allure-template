package po.helpers;

import com.codeborne.selenide.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class WaitHelper {

    private final Logger logger;
    private final static long TIMEOUT = 8000;

    public WaitHelper(Logger logger) {
        this.logger = logger;
    }

    /**
     * Wait for page to be fully loaded
     */
    public void forPageLoaded() {
        long startTime = System.currentTimeMillis();

        WebDriverRunner.getWebDriver().manage().timeouts().setScriptTimeout(Configuration.timeout, TimeUnit.MILLISECONDS);
        ExpectedCondition<Boolean> documentReadyCondition =
                driver -> Objects.requireNonNull(Selenide.executeJavaScript("return document.readyState")).toString().equals("complete");

        new WebDriverWait(WebDriverRunner.getWebDriver(), TimeUnit.SECONDS.convert(Configuration.timeout, TimeUnit.MILLISECONDS))
                .withMessage("Wait for page loaded (document.readyState=complete)")
                .until(documentReadyCondition);

        long endTime = System.currentTimeMillis();
        long diff = endTime - startTime;

        if (diff > TIMEOUT) {
            logger.info("Wait for page to be fully loaded: " + diff + " ms");
        }
    }

    public void forElementExists(SelenideElement element, String elementName) {

        long startTime = System.currentTimeMillis();

        element.should(exist.because("expect exists element: " + elementName));
        long endTime = System.currentTimeMillis();
        long diff = endTime - startTime;

        if (diff > TIMEOUT) {
            logger.info("Wait for " + elementName + " exists: " + diff + " ms");
        }
    }

    public void forElementDisplayed(SelenideElement element, String elementName) {

        long startTime = System.currentTimeMillis();

        element.should(visible.because("expect visible element: " + elementName));
        long endTime = System.currentTimeMillis();
        long diff = endTime - startTime;

        if (diff > TIMEOUT) {
            logger.info("Wait for " + elementName + " exists: " + diff + " ms");
        }
    }

    public void forAtLeastOneElementExists(ElementsCollection elements, String elementItemName) {
        long startTime = System.currentTimeMillis();
        elements.shouldHave(sizeGreaterThan(0).because("expect: at least 1 " + elementItemName + " present"));
        long endTime = System.currentTimeMillis();
        long diff = endTime - startTime;

        if (diff > TIMEOUT) {
            logger.info("Wait for at least 1 " + elementItemName + " to be present: " + (endTime - startTime) + " ms");
        }
    }
}
