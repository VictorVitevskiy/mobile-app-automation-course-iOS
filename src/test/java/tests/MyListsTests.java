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
            PASSWORD = "Vit19922411";

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {

        String article_title = "Appium";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(article_title);
        searchPageObject.clickByArticleWithTitle(article_title);

        ArticlePageObject articlePage = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            articlePage.addArticleToMyListForTheFirstTime(folder_name);
        } else if (Platform.getInstance().isIOS()){
            articlePage.addArticlesToMySavedForTheFirstTime();
        } else {
            articlePage.addArticlesToMySaved();
            Thread.sleep(1000);
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


}
