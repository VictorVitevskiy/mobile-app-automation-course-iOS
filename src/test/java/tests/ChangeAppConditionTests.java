package tests;

import factories.ArticlePageObjectFactory;
import factories.SearchPageObjectFactory;
import lib.CoreTestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import pages.ArticlePageObject;
import pages.SearchPageObject;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisabledIfEnvironmentVariable(named = "PLATFORM", matches = "mobile_web")
public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    @DisabledIfEnvironmentVariable(named = "PLATFORM", matches = "ios")
    public void testChangeScreenOrientationOnSearchResults() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        String title_before_rotation = articlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = articlePageObject.getArticleTitle();

        assertEquals(
                title_before_rotation,
                title_after_rotation,
                "article title have been change after screen rotation"
        );

        this.rotateScreenPortrait();
        String new_title_after_rotation = articlePageObject.getArticleTitle();


        assertEquals(
                title_before_rotation,
                new_title_after_rotation,
                "article title have been change after screen rotation"
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundUp(ofSeconds(2));
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
