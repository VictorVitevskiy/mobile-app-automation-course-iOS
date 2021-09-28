package pages.ios;

import io.appium.java_client.AppiumDriver;
import pages.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "id:{TITLE}";
        ELEMENT_TO_DELETE_ARTICLE = "id:swipe action delete";
    }

    public IOSMyListsPageObject(AppiumDriver<?> driver) {
        super(driver);
    }
}
