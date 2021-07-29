package po.helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;

import static com.codeborne.selenide.Condition.*;

public class ElementHelper {

    protected Logger logger;

    public ElementHelper(Logger logger) {
        this.logger = logger;
    }

    public void clickEnabled(SelenideElement button) {
        button.should(exist, enabled).click();
    }

    public void click(SelenideElement button) {
        button.should(exist).click();
    }

    public void click(ElementsCollection button, int index) {
        click(button.get(index));
    }


    public void sendKeys(SelenideElement input, String value) {

        sendKeysWithoutErrorHandling(input, value);

        if (!StringUtils.equals(input.getValue(), value)) {
            throw new AssertionError(String.format("Value was not entered to input field. Value: %s", value));
        }
    }

    public void sendKeysWithoutErrorHandling(SelenideElement input, String value) {
        input.should(exist).clear();
        input.sendKeys(value);
        unFocusActiveElement();
    }

    public void clearInputField(SelenideElement input) {
        logger.warn("Clear input field with sending: Keys.BACK_SPACE");
        input.should(exist).clear();
        input.sendKeys("1");
        input.sendKeys(Keys.BACK_SPACE);

        if (StringUtils.isNotEmpty(input.getValue())) {
            throw new AssertionError("Input field was not cleared");
        }
    }

    public void unFocusActiveElement() {
        logger.info("Un focus active element");
        Selenide.executeJavaScript("!!document.activeElement ? document.activeElement.blur() : 0");
    }

    public void scrollToElement(SelenideElement element) {
        element.scrollIntoView(true).scrollTo();
    }

    public void scrollUp() {
        logger.info("Scroll up to top of the page");
        Selenide.executeJavaScript("window.scrollBy(document.body.scrollWidth / 2, -document.body.scrollHeight)");
    }
}
