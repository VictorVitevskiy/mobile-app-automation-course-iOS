package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            TITLE_TPL,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            SYNC_YOUR_SAVED_ARTICLES_CLOSE_BUTTON,
            FOLDER_BY_NAME_TPL;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement() {

        return this.waitForElementPresent(
                TITLE,
                "Cannot find article title on page",
                15
        );
    }

    @Step("Waiting for title element '{name_of_title}' on the article page")
    public WebElement waitForTitleElement(String name_of_title) {

        String title_xpath = getTitleByName(name_of_title);

        return this.waitForElementPresent(
                title_xpath,
                "Cannot find article title on page",
                15
        );
    }

    @Step("Get article title")
    public String getArticleTitle() {

        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));

        if (!Platform.getInstance().isMobileWeb()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    @Step("Checking for article title '{name_of_title}' present on the page")
    public void checkArticleTitlePresent(String name_of_title) {

        if (Platform.getInstance().isAndroid()) {
            this.assertElementPresent(
                    TITLE,
                    "Cannot find article title"
            );
        } else {
            String title_xpath = getTitleByName(name_of_title);
            this.waitForElementPresent(
                    title_xpath,
                    "Cannot find article title",
                    15
            );
            this.assertElementPresent(
                    title_xpath,
                    "Cannot find article title"
            );
        }
    }

    @Step("Swiping to footer on article ")
    public void swipeToFooter() {

        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    20
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    20
            );
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    20
            );
        }
    }

    @Step("Adding article to the list '{name_of_folder}' for the first time (this method only for Android)")
    public void addArticleToMyListForTheFirstTime(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                10
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                10
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                10
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of article folder",
                10
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into article folder input",
                10
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                10
        );
    }

    @Step("Adding article to existing list '{name_of_folder}' (this method only for Android)")
    public void addArticleIntoExistingMyList(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                10
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                10
        );

        String folder_xpath = getFolderByName(name_of_folder);
        this.waitForElementAndClick(
                folder_xpath,
                "Cannot find option to add article to My lists",
                10
        );
    }

    @Step("Adding article to my saved for the first time (this method only for iOS)")
    public void addArticlesToMySavedForTheFirstTime() {

        if (driver instanceof AppiumDriver) {

            TouchAction<?> action = new TouchAction<>((AppiumDriver<?>) driver);

            int screen_size_by_y = driver.manage().window().getSize().getHeight();
            int screen_size_by_x = driver.manage().window().getSize().getWidth();

            action
                    .press(PointOption.point(screen_size_by_x / 2, screen_size_by_y / 2))
                    .release()
                    .perform();

            this.addArticlesToMySaved();

            this.waitForElementAndClick(
                    SYNC_YOUR_SAVED_ARTICLES_CLOSE_BUTTON,
                    "Cannot find close sync your saved articles button",
                    15
            );
        } else {
            System.out.println("Method addArticlesToMySavedForTheFirstTime does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Adding article to my saved")
    public void addArticlesToMySaved() {

        if (Platform.getInstance().isMobileWeb()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                15
        );
    }

    @Step("Remove article from my saved if it is have been added (this method only for Mobile Web)")
    public void removeArticleFromSavedIfItAdded() {

        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    15
            );
        }
    }

    @Step("Closing the article")
    public void closeArticle() {

        if (!Platform.getInstance().isMobileWeb()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X button",
                    10
            );
        } else {
            System.out.println("Method closeArticle do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    private static String getFolderByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getTitleByName(String name_of_title) {

        return TITLE_TPL.replace("{SUBSTRING}", name_of_title);
    }
}
