package pages.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import pages.ArticlePageObject;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://div[@class='page-heading']/h1[contains(text(),'Java (programming language)')]";
        TITLE_TPL = "xpath://div[@class='page-heading']/h1[contains(text(),'{SUBSTRING}')]";
        FOOTER_ELEMENT = "xpath://footer";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions-watch a#ca-watch[title*='Remove this page']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions a#ca-watch";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
