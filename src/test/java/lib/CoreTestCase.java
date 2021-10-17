package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.WelcomeIOSPageObject;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {

    protected RemoteWebDriver driver;

    @BeforeEach
    @Step("Run driver and session")
    protected void setUp() throws Exception {

        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPageForMobileWeb();
    }

    @AfterEach
    @Step("Remove driver and session")
    protected void tearDown() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Step("Rotate screen to portrait mode")
    protected void rotateScreenPortrait() {

        if (driver instanceof AppiumDriver) {
            AppiumDriver<?> driver = (AppiumDriver<?>) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Rotate screen to landscape mode")
    protected void rotateScreenLandscape() {

        if (driver instanceof AppiumDriver) {
            AppiumDriver<?> driver = (AppiumDriver<?>) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Send mobile app to background (this method does nothing for Mobile Web)")
    protected void backgroundUp(Duration seconds) {

        if (driver instanceof AppiumDriver) {
            AppiumDriver<?> driver = (AppiumDriver<?>) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Method backgroundUp does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Open Wiki URL for Mobile Web (this method does nothing for Android and iOS)")
    protected void openWikiWebPageForMobileWeb() {

        if (Platform.getInstance().isMobileWeb()) {
            driver.get("https://en.m.wikipedia.org/wiki/Main_Page");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip welcome page screen for iOS")
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

    private void createAllurePropertyFile() {

        String path = System.getProperty("allure.results.directory");
        try (FileOutputStream stream = new FileOutputStream(path + "/environment.properties")) {
            Properties properties = new Properties();
            properties.setProperty("Environment", Platform.getInstance().getPlatformVar());
            properties.store(stream, "See https:/github.com/allure-framework/allure-app/wiki/Environment");
        } catch (Exception e) {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
