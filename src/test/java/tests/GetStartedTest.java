package tests;

import lib.CoreTestCase;
import lib.Platform;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pages.WelcomeIOSPageObject;

public class GetStartedTest extends CoreTestCase {

    @Test
    @Disabled
    public void testPassThroughWelcome() {

        if (Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomeIOSPageObject welcomePage = new WelcomeIOSPageObject(driver);
        welcomePage.waitForLearnMoreLink();
        welcomePage.clickSkipButton();
    }
}
