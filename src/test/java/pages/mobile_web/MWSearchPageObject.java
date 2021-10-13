package pages.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import pages.SearchPageObject;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:.header-action>button.cancel";
        SEARCH_RESULT_BY_TITLE_TPL = "xpath://div[contains(@class,'results-list-container')]//li[contains(@title,'{SUBSTRING}')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'results-list-container')]//*[@class='wikidata-description'][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_RESULT_ELEMENT_BY_ORDER_TPL = "xpath://*[contains(@class,'page-list')]//*[@title][{NUMBER}]";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[contains(@class,'page-list')]//*[@title='{TITLE}']//*[contains(@class,'wikidata-description')][contains(text(),'{DESCRIPTION}')]";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
