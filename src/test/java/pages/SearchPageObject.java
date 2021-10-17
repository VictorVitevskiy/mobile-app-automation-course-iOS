package pages;

import io.qameta.allure.Step;
import lib.Platform;
import org.junit.platform.suite.api.SelectClasses;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_RESULT_ELEMENT_BY_ORDER_TPL,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Initialization the input field")
    public void initSearchInput() {

        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find field search init"
        );
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                10
        );
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear() {

        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button"
        );
    }

    @Step("Waiting for search cancel button to disappear")
    public void waitForCancelButtonToDisappear() {

        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button is steel present",
                10
        );
    }

    @Step("Clicking button to cancel search result")
    public void clickCancelSearch() {

        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button",
                10
        );
    }

    @Step("Typing '{search_line}' to the search line")
    public void typeSearchLine(String search_line) {

        this.waitForElementAndClear(
                SEARCH_INPUT,
                "Cannot find and type into search init",
                10
        );

        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type into search init",
                10
        );
    }

    @Step("Verifying text in search line")
    public void verifyTextInSearchLine(String search_line) {

        this.waitForElementPresent(
                SEARCH_INPUT,
                "Cannot find entry field",
                10
        );

        if (Platform.getInstance().isAndroid()) {
            this.assertElementHasText(
                    SEARCH_INPUT,
                    search_line,
                    "Cannot find expected text in entry field"
            );
        } else {
            assertElementPresent(
                    SEARCH_INPUT,
                    "Cannot find expected element"
            );
        }
    }

    @Step("Waiting for search result '{substring}'")
    public void waitForSearchResult(String substring) {

        String search_result_xpath = getResultSearchElementByDescription(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with substring " + substring
        );
    }

    @Step("Waiting for search result and select an article by '{substring}' in article description")
    public void clickByArticleWithDescription(String substring) {

        String search_result_xpath = getResultSearchElementByDescription(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring " + substring,
                10
        );
    }

    @Step("Waiting for search result and select an article by '{substring}' in article title")
    public void clickByArticleWithTitle(String substring) {

        String search_result_xpath = getResultSearchElementByTitle(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find and click search result with title " + substring,
                10
        );
    }

    @Step("Waiting for search result by article title '{title}' and description '{description}'")
    public void waitForElementByTitleAndDescription(String title, String description) {

        String search_result_xpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(
                search_result_xpath,
                String.format("Cannot find search result with title '%s' and description '%s'", title, description),
                10
        );
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultsLabel() {

        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element"
        );
    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultOfSearch() {

        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results"
        );
    }

    @Step("Checking correctness of search results with search input '{search_text}'")
    public void checkCorrectnessOfSearchResults(String search_text) {

        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find search results",
                10
        );

        int articles_number = driver.findElements(getLocatorByString(SEARCH_RESULT_ELEMENT)).size();
        int number = 0;

        while (articles_number > number) {
            number++;
            String search_element_xpath = getSearchElementByOrder("" + number);
            WebElement element = this.waitForElementPresent(
                    search_element_xpath,
                    "Search element not found"
            );

            if (!Platform.getInstance().isMobileWeb()) {
                assertTrue(
                        element.getAttribute("name").toLowerCase(Locale.ROOT)
                                .contains(search_text.toLowerCase(Locale.ROOT)),
                        "Search result does not contain the search word"
                );
            } else {
                assertTrue(
                        element.getAttribute("title").toLowerCase(Locale.ROOT)
                                .contains(search_text.toLowerCase(Locale.ROOT)),
                        "Search result does not contain the search word"
                );
            }
        }
    }

    @Step("Checking for search results present")
    public void assertSearchResultsPresent() {

        this.assertElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Found one or zero articles"
        );
    }

    @Step("Checking for search results not present")
    public void assertSearchResultsNotPresent() {

        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "Articles are still on the page"
        );
    }

    /**
     * TEMPLATES METHODS
     *
     * @param substring
     * @return
     */
    private static String getResultSearchElementByDescription(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitle(String substring) {
        return SEARCH_RESULT_BY_TITLE_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchElementByOrder(String substring) {
        return SEARCH_RESULT_ELEMENT_BY_ORDER_TPL.replace("{NUMBER}", substring);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
}
