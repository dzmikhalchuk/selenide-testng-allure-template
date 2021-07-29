package web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideWait;
import com.codeborne.selenide.WebDriverRunner;
import helpers.assertions.SoftAssert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import po.pages.MainPage;

import static com.codeborne.selenide.WebDriverRunner.url;

public class WikiTest extends BaseTest {

    private MainPage mainPage;

    @BeforeClass
    public void beforeClass() {
        mainPage = new MainPage();
        mainPage.navigate();
    }

    @AfterClass
    public void afterClass() {
        WebDriverRunner.closeWebDriver();
    }

    @Test(description = "Search")
    public void demoUITest() {
        SoftAssert testResults = new SoftAssert();

        mainPage.search("Einstein");
        testResults.assertEquals(mainPage.getTitle(), "Albert Einstein");
        testResults.assertTrue(url().contains("Einstein"), "Search result page contains incorrect URL");
        testResults.assertAll();
    }

    @Test(description = "Search fail")
    public void demoTestFail() {
        SoftAssert testResults = new SoftAssert();

        mainPage.search("Einstein");
        testResults.assertEquals(mainPage.getTitle(), "Noname");
        testResults.assertTrue(url().contains("/wiki/noname"), "Search result page contains incorrect URL");
        testResults.assertAll();
    }

}
