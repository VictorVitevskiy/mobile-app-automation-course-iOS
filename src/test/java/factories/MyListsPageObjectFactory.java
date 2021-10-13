package factories;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.MyListsPageObject;
import pages.android.AndroidMyListsPageObject;
import pages.ios.IOSMyListsPageObject;
import pages.mobile_web.MWMyListsPageObject;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(RemoteWebDriver driver) {

        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSMyListsPageObject(driver);
        } else {
            return new MWMyListsPageObject(driver);
        }
    }
}
