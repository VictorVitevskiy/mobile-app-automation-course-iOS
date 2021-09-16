package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase {

    protected AppiumDriver<?> driver;
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    @BeforeEach
    protected void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE (2nd generation)");
        capabilities.setCapability("platformVersion", "14.3");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/u19223188/IdeaProjects/GitHubProjects/mobile-app-automation-course-iOS/apps/Wikipedia.app");
        capabilities.setCapability("orientation", "PORTRAIT");

        driver = new IOSDriver<>(new URL(APPIUM_URL), capabilities);
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
}
