package pages;

import io.qameta.allure.Step;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
            LOGIN_BUTTON = "xpath://body/div//a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button[name='wploginattempt']";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Clicking button for authorization")
    public void clickAuthorizationButton() {

        this.waitForElementPresent(
                LOGIN_BUTTON,
                "Cannot find Authorization button",
                15
        );
        this.tryClickElementWithFewAttempts(
                LOGIN_BUTTON,
                "Cannot click Authorization button",
                15
        );
    }

    @Step("Enter user data to log in")
    public void enterLoginData(String login, String password) {

        this.waitForElementAndSendKeys(
                LOGIN_INPUT,
                login,
                "Cannot find and put a login into login input",
                15
        );
        this.waitForElementAndSendKeys(
                PASSWORD_INPUT,
                password,
                "Cannot find and put a password into password input",
                15
        );
    }

    @Step("Clicking button to submit user data")
    public void submitForm() {

        this.waitForElementAndClick(
                SUBMIT_BUTTON,
                "Cannot find and click submit button",
                15
        );
    }
}
