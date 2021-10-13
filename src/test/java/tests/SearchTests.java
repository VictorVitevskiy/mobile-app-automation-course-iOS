package tests;

import factories.SearchPageObjectFactory;
import lib.CoreTestCase;
import lib.Platform;
import org.junit.jupiter.api.Test;
import pages.SearchPageObject;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testEntryFieldContainsText() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.verifyTextInSearchLine("Search");
    }

    @Test
    public void testVerifyAndCancelSearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.assertSearchResultsPresent();
        searchPageObject.clickCancelSearch();
        searchPageObject.assertSearchResultsNotPresent();
    }

    @Test
    public void testCheckingWordsInSearch() {

        String search_text = "Java";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_text);
        searchPageObject.checkCorrectnessOfSearchResults(search_text);
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        assertTrue(
                amount_of_search_results > 0,
                "We found too few results"
        );
    }

    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_line = "ngdfgsdg";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testVerifyFirstThreeSearchResults() {

        String search_text = "Java";

        String first_result_title = "Java";
        String second_result_title = "JavaScript";
        String third_result_title = "Java (programming language)";
        String first_result_description;
        String second_result_description;
        if (!Platform.getInstance().isMobileWeb()) {
            first_result_description = "Island of Indonesia";
            second_result_description = "Programming language";
        } else {
            first_result_description = "Indonesian island";
            second_result_description = "High-level programming language";
        }
        String third_result_description = "Object-oriented programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_text);
        searchPageObject.waitForElementByTitleAndDescription(first_result_title, first_result_description);
        searchPageObject.waitForElementByTitleAndDescription(second_result_title, second_result_description);
        searchPageObject.waitForElementByTitleAndDescription(third_result_title, third_result_description);
    }
}
