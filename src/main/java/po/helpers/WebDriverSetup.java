package po.helpers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;

public abstract class WebDriverSetup {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverSetup.class);

    public static DesiredCapabilities getCapabilities(String browserName) {
        DesiredCapabilities caps = new DesiredCapabilities();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL); // Enable access to browser logs
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL); // Enable access to performance logs
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        switch (browserName) {
            case CHROME:
                caps.setCapability(CapabilityType.BROWSER_NAME, CHROME);
                caps.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadStrategy.NONE);
                break;
            case FIREFOX:
                caps.setCapability(CapabilityType.BROWSER_NAME, FIREFOX);
                break;
            default:
                LOGGER.error(String.format("'%s' browser does not exist", browserName));
        }

//        Common capabilities
        caps.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        return caps;
    }
}
