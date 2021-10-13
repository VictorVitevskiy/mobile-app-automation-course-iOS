package pages.ios;

import org.openqa.selenium.remote.RemoteWebDriver;
import pages.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://*[@name='Close']/../XCUIElementTypeSearchField";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_TITLE_TPL = "id:{SUBSTRING}";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "id:{SUBSTRING}";
        SEARCH_RESULT_ELEMENT = "xpath:(//XCUIElementTypeCollectionView)[2]/XCUIElementTypeCell";
        SEARCH_RESULT_ELEMENT_BY_ORDER_TPL = "xpath:(//XCUIElementTypeCollectionView)[2]/XCUIElementTypeCell[{NUMBER}]//XCUIElementTypeStaticText[1]";
        SEARCH_EMPTY_RESULT_ELEMENT = "id:No results found";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeCell//*[@name='{TITLE}']/..//*[@name='{DESCRIPTION}']";
    }

    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
