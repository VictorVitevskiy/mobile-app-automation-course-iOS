package factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import pages.NavigationUI;
import pages.android.AndroidNavigationUI;
import pages.ios.IOSNavigationUI;

public class NavigationUIFactory {

    public static NavigationUI get(AppiumDriver<?> driver) {

        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        } else {
            return new IOSNavigationUI(driver);
        }
    }
}
