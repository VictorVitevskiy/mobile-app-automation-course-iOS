package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class MainPageObject {

    protected AppiumDriver<?> driver;

    public MainPageObject(AppiumDriver<?> driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeout_in_seconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeout_in_seconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message) {

        return waitForElementPresent(by, error_message, 15);
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeout_in_seconds) {

        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeout_in_seconds) {

        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeout_in_seconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeout_in_seconds);
        wait.withMessage(error_message);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeout_in_seconds) {

        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(By by, String expected_text, String error_message) {

        WebElement element = waitForElementPresent(by, error_message);
        Assertions.assertTrue(
                element.getAttribute("text").contains(expected_text),
                error_message
        );
    }

    public void swipeUp(Duration time_of_swipe){

        TouchAction<?> action = new TouchAction<>(driver);
        Dimension size = driver.manage().window().getSize();

        int x = size.width / 2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);

        action
                .press(point(x, start_y))
                .waitAction(waitOptions(time_of_swipe))
                .moveTo(point(x, end_y))
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(ofMillis(200));
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {

        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "cannot find element by swipe UP. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            already_swiped++;
        }
    }

    public void swipeElementToLeft(By by, String error_message) {

        WebElement element = waitForElementPresent(by, error_message);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(point(right_x, middle_y))
                .waitAction(waitOptions(ofMillis(300)))
                .moveTo(point(left_x, middle_y))
                .release()
                .perform();
    }

    public int getAmountOfElements(By by) {

        List<?> elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String error_message) {

        int amount_of_elements = getAmountOfElements(by);

        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeout_in_seconds) {

        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent(By by, String error_message) {

        if (driver.findElements(by).size() < 1) {
            String default_message = "An element '" + by.toString() + "' supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
}
