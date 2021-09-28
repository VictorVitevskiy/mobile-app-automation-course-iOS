package tests;

import factories.ArticlePageObjectFactory;
import factories.MyListsPageObjectFactory;
import factories.NavigationUIFactory;
import factories.SearchPageObjectFactory;
import lib.Platform;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.ArticlePageObject;
import pages.MyListsPageObject;
import pages.NavigationUI;
import pages.SearchPageObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyListsTests extends CoreTestCase {

    public static final String folder_name = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {

        String article_title = "Appium";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(article_title);
        searchPageObject.clickByArticleWithSubstring(article_title);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyListForTheFirstTime(folder_name);
        } else {
            articlePageObject.addArticlesToMySavedForTheFirstTime();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        Thread.sleep(2000);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(folder_name);
        }
        myListsPageObject.swipeByArticleToDelete(article_title);
    }


}
