package factories;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.ArticlePageObject;
import pages.android.AndroidArticlePageObject;
import pages.ios.IOSArticlePageObject;
import pages.mobile_web.MWArticlePageObject;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(RemoteWebDriver driver) {

        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSArticlePageObject(driver);
        } else {
            return new MWArticlePageObject(driver);
        }
    }
}
