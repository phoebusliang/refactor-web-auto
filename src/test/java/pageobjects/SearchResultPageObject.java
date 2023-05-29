package pageobjects;

import org.openqa.selenium.WebDriver;

public class SearchResultPageObject extends BasePage {

    public SearchResultPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickProperty(String name) {
        basicOperation.findElementsByScript("return $('.row.container article h2 a:contains(" + name + ")')").get(0).click();
    }
}
