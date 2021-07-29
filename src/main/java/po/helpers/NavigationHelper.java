package po.helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NavigationHelper {

    protected Logger logger;

    public NavigationHelper(Logger logger) {
        this.logger = logger;
    }

    public void navigate(String url) {
        logger.info("Navigate to: " + url);

        Selenide.open(url);

        new WebDriverWait(WebDriverRunner.getWebDriver(), TimeUnit.SECONDS.convert(Configuration.timeout, TimeUnit.MILLISECONDS))
                .withMessage("Wait for URL contains: " + url)
                .until(ExpectedConditions.urlContains(url));
    }

    public void navigate(String url, List<String> urlParams) {
        navigate(String.format(url, urlParams));
    }
}
