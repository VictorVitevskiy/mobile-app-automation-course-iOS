package pages;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON,
            ELEMENT_TO_DELETE_ARTICLE;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Opening '{folder_name}' folder")
    public void openFolderByName(String folder_name) {

        String folder_name_after = getFolderByName(folder_name);
        this.waitForElementAndClick(
                folder_name_after,
                "Cannot find folder by name " + folder_name,
                30
        );
    }

    @Step("Delete '{article_title}' article from my list")
    public void swipeByArticleToDelete(String article_title) {

        this.waitForArticleToAppearByTitle(article_title);

        String article_xpath = getSavedArticleByTitle(article_title);

        if (!Platform.getInstance().isMobileWeb()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    15
            );
        }
        if (Platform.getInstance().isIOS()) {
            this.clickTrashBinElementToDeleteSavedArticle(ELEMENT_TO_DELETE_ARTICLE, "Cannot find element 'Trash bin'");
        } else if (Platform.getInstance().isMobileWeb()) {
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    @Step("Checking the absence of an article element 'article_title' in my list")
    public void waitForArticleToDisappearByTitle(String article_title) {

        String article_xpath = getSavedArticleByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                30
        );
    }

    @Step("Checking the presence of an article element 'article_title' in my list")
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

    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }
}
