package pages;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

public abstract class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ELEMENT_TO_DELETE_ARTICLE;

    public MyListsPageObject(AppiumDriver<?> driver) {
        super(driver);
    }

    public void openFolderByName(String folder_name) {

        String folder_name_after = getFolderByName(folder_name);
        this.waitForElementAndClick(
                folder_name_after,
                "Cannot find folder by name " + folder_name,
                30
        );
    }

    public void openArticleByTitle(String article_title) {

        String article_xpath = getSavedArticleByTitle(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Cannot find folder by name " + article_title,
                30
        );
    }

    public void swipeByArticleToDelete(String article_title) {

        this.waitForArticleToAppearByTitle(article_title);

        String article_xpath = getSavedArticleByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );

        if (Platform.getInstance().isIOS()) {
            this.clickTrashBinElementToDeleteSavedArticle(ELEMENT_TO_DELETE_ARTICLE, "Cannot find element 'Trash bin'");
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {

        String article_xpath = getSavedArticleByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                30
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {

        String article_xpath = getSavedArticleByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                30
        );
    }

    private static String getFolderByName(String folder_name) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folder_name);
    }

    private static String getSavedArticleByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
}
