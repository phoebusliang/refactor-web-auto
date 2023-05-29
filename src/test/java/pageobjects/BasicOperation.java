package pageobjects;

import runtime.LoadConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicOperation {

    WebDriver driver = null;

    public BasicOperation(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> waitFor(Supplier<List<WebElement>> action, Predicate<List<WebElement>> checker, String error_template) {
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < Long.parseLong(LoadConfig.load("timeout")) * 1000) {
            List<WebElement> elements = action.get();
            if (checker.test(elements)) {
                return elements;
            }
            sleepTimeout("interval");
        }
        throw new AssertionError(error_template);
    }

    public Object waitForText(Supplier<Object> action, Predicate<Object> checker, String error_template) {
        long start = System.currentTimeMillis();
        Object value;
        while (System.currentTimeMillis() - start < Long.parseLong(LoadConfig.load( "timeout")) * 1000) {
            value = action.get();
            if (checker.test(value)) {
                return value;
            }
            sleepTimeout("interval");
        }
//        return null;
        throw new AssertionError(error_template);
    }

    public void sleepTimeout(String time) {
        try {
            Thread.sleep(Long.parseLong(LoadConfig.load( time)) * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<WebElement> findElementsByScript(String script) {
        final String actualScript = script.replace("$", "jQuery").concat(".toArray()");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Supplier<List<WebElement>> action = () -> (List<WebElement>) js.executeScript(actualScript);
        Predicate<List<WebElement>> checker = elements -> elements.size() > 0 && elements.get(0).isEnabled();
        return waitFor(action, checker, "Element not found!");
    }

    public Object getFromByScript(String script, Predicate<Object> verify) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Supplier<Object> action = () -> (Object) js.executeScript(script);
        Predicate<Object> checker = verify;
        return waitForText(action, checker, "Element not found!");
    }

    public void waitJSComplete() {
        long start = System.currentTimeMillis();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (System.currentTimeMillis() - start < Long.parseLong(LoadConfig.load("timeout")) * 1000) {
            sleepTimeout("interval");
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                break;
            }
            sleepTimeout("interval");
        }
    }
}