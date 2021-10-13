package pages.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import pages.NavigationUI;

public class MWNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = "a[data-event-name='menu.unStar']";
        OPEN_NAVIGATION = "css:[title='Open main menu']";
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
