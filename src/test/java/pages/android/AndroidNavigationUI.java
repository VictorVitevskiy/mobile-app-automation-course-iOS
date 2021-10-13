package pages.android;

import org.openqa.selenium.remote.RemoteWebDriver;
import pages.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = "xpath://*[@content-desc='My lists']";
    }

    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
