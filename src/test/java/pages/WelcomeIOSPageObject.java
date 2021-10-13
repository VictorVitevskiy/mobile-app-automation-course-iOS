package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomeIOSPageObject extends MainPageObject {

    private static final String
            LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            SKIP_LINK = "id:Skip";

    public WelcomeIOSPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {

        this.waitForElementPresent(
                LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link"
        );
    }

    public void clickSkipButton() {

        this.waitForElementAndClick(
                SKIP_LINK,
                "Cannot find and click 'Skip' link",
                10
        );
    }
}
