package web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.allure.AllureCommon;
import helpers.allure.AllureSelenide;
import helpers.allure.LogType;
import listeners.TestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import po.helpers.ElementHelper;
import po.helpers.WebDriverSetup;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;

@Listeners({ TestListener.class })
public abstract class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ResourceBundle testData;
    protected String env;

    @BeforeSuite
    public void configureSuite() {
//         Clear allure-results folder
        AllureCommon.deleteAllureResults();

        // Allure report configuration
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true) // Add screenshot as attachments
                        .savePageSource(true) // Save html page source
                        .enableLogs(LogType.BROWSER, Level.ALL)); // Add browser logs to report

        // Selenide configuration
        Configuration.reportsFolder = "target/allure-results/attachments";
    }

    @BeforeClass
    @Parameters({"env", "browserName"})
    public void setUpBrowser(String env, String browserName) {

        this.env = env;
        testData = ResourceBundle.getBundle(this.env.toLowerCase());

        // Selenide configuration
        Configuration.timeout = 30000;
        Configuration.browserCapabilities = WebDriverSetup.getCapabilities(browserName);
        Configuration.browser = browserName;
        Configuration.startMaximized = true;
        Configuration.baseUrl = testData.getString("main.url");
        Configuration.screenshots = true;
//        Disable savePageSource for avoiding extra messages in allure assertions
        Configuration.savePageSource = false;
    }

    @BeforeMethod
    public void startTest(Method method) {
        logger.info("----- Start test: " + method.getName() + " -----");
    }

    @AfterSuite
    public void addPropsToReport(ITestContext context) {
        Properties props = new Properties();
        Map<String, String> params = context.getCurrentXmlTest().getAllParameters();
        for (Map.Entry<String,String> param : params.entrySet())
            props.setProperty(param.getKey(), param.getValue());
        AllureCommon.addAllureEnvProperties(props);
    }

}
