package pages.ios;

import org.openqa.selenium.remote.RemoteWebDriver;
import pages.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://XCUIElementTypeStaticText[@name='Appium']";
        TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        SYNC_YOUR_SAVED_ARTICLES_CLOSE_BUTTON = "id:places auth close";
    }

    public IOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
