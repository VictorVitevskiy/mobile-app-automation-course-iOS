package lib;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.WelcomeIOSPageObject;

import java.time.Duration;

public class CoreTestCase {

    protected RemoteWebDriver driver;

    @BeforeEach
    protected void setUp() throws Exception {

        driver = Platform.getInstance().getDriver();
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPageForMobileWeb();
    }

    @AfterEach
    protected void tearDown() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected void rotateScreenPortrait() {

        if (driver instanceof AppiumDriver) {
            AppiumDriver<?> driver = (AppiumDriver<?>) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenLandscape() {

        if (driver instanceof AppiumDriver) {
            AppiumDriver<?> driver = (AppiumDriver<?>) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void backgroundUp(Duration seconds) {

        if (driver instanceof AppiumDriver) {
            AppiumDriver<?> driver = (AppiumDriver<?>) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Method backgroundUp does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiWebPageForMobileWeb() {

        if (Platform.getInstance().isMobileWeb()) {
            driver.get("https://en.m.wikipedia.org/wiki/Main_Page");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    private void skipWelcomePageForIOSApp() {

        if (driver instanceof AppiumDriver) {
            if (Platform.getInstance().isIOS()) {
                WelcomeIOSPageObject welcomeIOSPageObject = new WelcomeIOSPageObject(driver);
                welcomeIOSPageObject.clickSkipButton();
            }
        } else {
            System.out.println("Method skipWelcomePageForIOSApp does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}
