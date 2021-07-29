package po.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;

public class MainPage extends BasePage {

    private final SelenideElement title = Selenide.$("h1#firstHeading");

    @Step("Main Page > Navigate to Main Page")
    public void navigate() {
        logger.info("Main Page > Navigate to Main Page");
        navigationHelper.navigate("");
        waitHelper.forPageLoaded();
    }

    @Step("Main Page > Navigate to Main Page")
    public void search(String searchValue) {
        logger.info("Main Page > Search");
        elementHelper.sendKeys(getMainHeader().getSearchInput(), searchValue);
        elementHelper.click(getMainHeader().getSearchButton());
    }

    public String getTitle() {
        return title.should(exist).text();
    }

}
