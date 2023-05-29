package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import runtime.LoadConfig;

public class BasePage {
    private WebDriver driver;
    protected BasicOperation basicOperation;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        basicOperation = new BasicOperation(driver);
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void injectJQuery() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String injection = "var scriptElt = document.createElement('script'); \n" +
            "scriptElt.src = '" + LoadConfig.load("jqueryCDN") + "'; \n" +
            "document.getElementsByTagName('head')[0].appendChild(scriptElt);";
        js.executeScript(injection);
    }

    public void waitJQuery() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        injectJQuery();
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < Long.parseLong(LoadConfig.load("timeout")) * 1000) {
            if (!(Boolean) js.executeScript("return (typeof jQuery != \'undefined\')")) {
                basicOperation.sleepTimeout("interval");
            } else {
                return;
            }
        }
    }
}
