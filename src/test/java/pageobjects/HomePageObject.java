package pageobjects;

import runtime.LoadConfig;
import runtime.SharedDriver;
import org.openqa.selenium.WebDriver;


public class HomePageObject extends BasePage {
    public HomePageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void inputSearchText(String text) {
        basicOperation.findElementsByScript(LoadConfig.load("searchBox")).get(0).sendKeys(text);
//        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id")));
//        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
//            .withTimeout(Duration.ofSeconds(30))
//            .pollingEvery(Duration.ofSeconds(5))
//            .ignoring(NoSuchElementException.class);
    }

    public void clickSearchBtn() {
        basicOperation.findElementsByScript(LoadConfig.load("searchBtn")).get(0).click();
    }

    public void checkSearchBtnVal(String text) {
        basicOperation.sleepTimeout("interval");
        waitJQuery();
        basicOperation.getFromByScript(LoadConfig.load("searchBtnVal"), result -> result.toString().equals(text));
//        if (btnVal == "null") {
//            SharedDriver.setScenarioProperty("status", false);
//        }
    }
}

