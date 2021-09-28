package factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import pages.MyListsPageObject;
import pages.android.AndroidMyListsPageObject;
import pages.ios.IOSMyListsPageObject;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(AppiumDriver<?> driver) {

        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else {
            return new IOSMyListsPageObject(driver);
        }
    }
}
