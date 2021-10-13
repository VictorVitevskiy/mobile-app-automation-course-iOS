package tests;

import factories.ArticlePageObjectFactory;
import factories.MyListsPageObjectFactory;
import factories.NavigationUIFactory;
import factories.SearchPageObjectFactory;
import lib.CoreTestCase;
import lib.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;

public class MyListsTests extends CoreTestCase {

    private static final String folder_name = "Learning programming";
    private static final String
            LOGIN = "Vitorpg1992",
            PASSWORD = "Vit199@2411";

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {

        String article_title = "Appium";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(article_title);
        searchPageObject.clickByArticleWithSubstring(article_title);

        ArticlePageObject articlePage = ArticlePageObjectFactory.get(driver);
//        articlePage.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            articlePage.addArticleToMyListForTheFirstTime(folder_name);
        } else if (Platform.getInstance().isIOS()){
            articlePage.addArticlesToMySavedForTheFirstTime();
        } else {
            articlePage.addArticlesToMySaved();
            AuthorizationPageObject authorizationPage = new AuthorizationPageObject(driver);
            authorizationPage.clickAuthorizationButton();
            authorizationPage.enterLoginData(LOGIN, PASSWORD);
            authorizationPage.submitForm();

            Assertions.assertEquals(
                    article_title,
                    articlePage.getArticleTitle(),
                    "We are not on the same page after login."
            );
            articlePage.addArticlesToMySaved();
        }
        articlePage.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(folder_name);
        }
        myListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyList() {

        String folder_name = "Learning programming";
        String first_title = "Java (programming language)";
        String second_title = "Python (programming language)";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(first_title);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyListForTheFirstTime(folder_name);
        } else {
            articlePageObject.addArticlesToMySavedForTheFirstTime();
        }
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Python");
        searchPageObject.clickByArticleWithSubstring(second_title);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleIntoExistingMyList(folder_name);
        } else {
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(folder_name);
        }
        myListsPageObject.swipeByArticleToDelete(first_title);
        myListsPageObject.waitForArticleToAppearByTitle(second_title);
    }
}
