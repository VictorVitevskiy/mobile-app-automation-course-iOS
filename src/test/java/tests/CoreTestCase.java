package tests;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import pages.WelcomeIOSPageObject;

import java.time.Duration;

public class CoreTestCase {

    protected AppiumDriver<?> driver;

    @BeforeEach
    protected void setUp() throws Exception {

        driver = Platform.getInstance().getDriver();
        this.skipWelcomePageForIOSPApp();
    }

    @AfterEach
    protected void tearDown() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundUp (Duration seconds) {
        driver.runAppInBackground(seconds);
    }

    private void skipWelcomePageForIOSPApp() {

        if (Platform.getInstance().isIOS()) {
            WelcomeIOSPageObject welcomeIOSPageObject = new WelcomeIOSPageObject(driver);
            welcomeIOSPageObject.clickSkipButton();
        }
    }
}
