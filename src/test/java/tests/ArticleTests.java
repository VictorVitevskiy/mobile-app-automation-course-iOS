package tests;

import factories.ArticlePageObjectFactory;
import factories.SearchPageObjectFactory;
import lib.CoreTestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import pages.ArticlePageObject;
import pages.SearchPageObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleTests extends CoreTestCase {

    @Test
    @DisabledIfEnvironmentVariable(named = "PLATFORM", matches = "ios")
    public void testCompareArticleTitle() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = articlePageObject.getArticleTitle();

        assertEquals(
                "Java (programming language)",
                article_title,
                "We see unexpected title"
        );
    }

    @Test
    public void testSwipeArticle() {

        String search_input = "Appium";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_input);
        searchPageObject.clickByArticleWithTitle(search_input);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement(search_input);
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testOpenArticleAndCheckTitle() {

        String title = "Java (programming language)";
        String search_input = "Appium";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_input);
        searchPageObject.clickByArticleWithTitle(search_input);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.checkArticleTitlePresent(search_input);
    }
}
