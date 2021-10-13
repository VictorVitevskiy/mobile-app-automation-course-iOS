package factories;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.SearchPageObject;
import pages.android.AndroidSearchPageObject;
import pages.ios.IOSSearchPageObject;
import pages.mobile_web.MWSearchPageObject;

public class SearchPageObjectFactory {

    public static SearchPageObject get(RemoteWebDriver driver) {

        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSSearchPageObject(driver);
        } else {
            return new MWSearchPageObject(driver);
        }
    }
}
