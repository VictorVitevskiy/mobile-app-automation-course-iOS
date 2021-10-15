package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String PATH_TO_PLATFORM_PROPERTIES = "src/test/resources/platformConfiguration.properties";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static final Properties properties = new Properties();
    private static Platform instance;

    private Platform() {}

    public static Platform getInstance() {

        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception {

        if (this.isAndroid()) {
            return new AndroidDriver<>(new URL(APPIUM_URL), this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver<>(new URL(APPIUM_URL), this.getIOSDesiredCapabilities());
        } else if (this.isMobileWeb()) {
            return new ChromeDriver(this.getMobileWebChromeOptions());
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

    public boolean isMobileWeb() {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    private boolean isPlatform(String my_platform) {
        return my_platform.equals(getPlatformVar());
    }

    public String getPlatformVar() {
        return getPlatformVariableFromFile("PLATFORM");
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
//        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("platformVersion", "11.0");
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

    private ChromeOptions getMobileWebChromeOptions() {

        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");

        return chromeOptions;
    }

    private String getPlatformVariableFromFile(String platform) {
        try (InputStream inputStream = new FileInputStream(PATH_TO_PLATFORM_PROPERTIES);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            properties.load(inputStreamReader);
            return properties.getProperty(platform);
        } catch (IOException exception) {
            System.out.println("Can't read '" + platform + "' from file");
            exception.printStackTrace();
        }
        return null;
    }
}
