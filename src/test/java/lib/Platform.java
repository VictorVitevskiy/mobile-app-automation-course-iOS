package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance() {

        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver<?> getDriver() throws Exception {

        if (this.isAndroid()) {
            return new AndroidDriver<>(new URL(APPIUM_URL), this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver<>(new URL(APPIUM_URL), this.getIOSDesiredCapabilities());
        } else {
            throw new Exception("Cannot detect type of the driver. Platform value " + this.getPlatformVar());
        }
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    private boolean isPlatform(String my_platform) {
        return my_platform.equals(getPlatformVar());
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/z19223188/IdeaProjects/mobile-app-automation-course/apps/org.wikipedia.apk");
        capabilities.setCapability("orientation", "PORTRAIT");

        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE (2nd generation)");
        capabilities.setCapability("platformVersion", "14.3");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/z19223188/IdeaProjects/mobile-app-automation-course-iOS/apps/Wikipedia.app");
        capabilities.setCapability("orientation", "PORTRAIT");

        return capabilities;
    }
}
