package helpers.allure;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllureLog {

    private static final Logger logger = LoggerFactory.getLogger(AllureLog.class);

    public AllureLog() {
    }

    @Step("{0}")
    public static void reportMessage(final String message) {
        logger.info(message);
    }
}
