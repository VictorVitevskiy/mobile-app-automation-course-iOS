package tests;

import lib.Platform;
import org.junit.jupiter.api.Test;
import pages.WelcomeIOSPageObject;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() {

        if (Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomeIOSPageObject welcomePage = new WelcomeIOSPageObject(driver);
        welcomePage.waitForLearnMoreLink();
        welcomePage.clickSkipButton();
    }
}
