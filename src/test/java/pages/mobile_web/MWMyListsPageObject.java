package pages.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import pages.MyListsPageObject;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://h3[text()='{TITLE}']";
        REMOVE_FROM_SAVED_BUTTON = "xpath://h3[text()='Appium']/../../a[contains(@class,'wikimedia-unStar')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
