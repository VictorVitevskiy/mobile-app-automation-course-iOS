package pages;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyLists() {

        if (Platform.getInstance().isMobileWeb()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My lists",
                    5
            );
        }
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My lists",
                10
        );
    }

    public void openNavigation() {

        if (Platform.getInstance().isMobileWeb()) {
            this.waitForElementAndClick(
                    OPEN_NAVIGATION,
                    "Cannot find and click navigation button",
                    15
            );
        } else {
            System.out.println("Method openNavigation do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}
