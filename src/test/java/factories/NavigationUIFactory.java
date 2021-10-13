package factories;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.NavigationUI;
import pages.android.AndroidNavigationUI;
import pages.ios.IOSNavigationUI;
import pages.mobile_web.MWNavigationUI;

public class NavigationUIFactory {

    public static NavigationUI get(RemoteWebDriver driver) {

        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSNavigationUI(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }
}
