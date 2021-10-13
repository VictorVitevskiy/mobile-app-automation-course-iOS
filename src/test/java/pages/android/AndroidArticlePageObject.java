package pages.android;

import org.openqa.selenium.remote.RemoteWebDriver;
import pages.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_header_view']//android.widget.TextView[contains(@text,'{SUBSTRING}}')]";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        OPTIONS_BUTTON = "xpath://*[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[contains(@text,'Add to reading list')]";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "id:android:id/button1";
        CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']";
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
