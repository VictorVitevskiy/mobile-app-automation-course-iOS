package pages.ios;

import org.openqa.selenium.remote.RemoteWebDriver;
import pages.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "id:{TITLE}";
        ELEMENT_TO_DELETE_ARTICLE = "id:swipe action delete";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
