package pages.android;

import io.appium.java_client.AppiumDriver;
import pages.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = "xpath://*[@content-desc='My lists']";
    }

    public AndroidNavigationUI(AppiumDriver<?> driver) {
        super(driver);
    }
}
