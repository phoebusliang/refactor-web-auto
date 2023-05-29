package runtime;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SharedDriver {
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public WebDriver getWebDriver() {
        if (webDriver.get() == null) {
            before();
        }
        return webDriver.get();
    }

    @Before
    public void before() {
        webDriver.set(new ChromeDriver());
    }

    @After
    public void after(Scenario scenario) {
        webDriver.get().quit();
        webDriver.remove();
    }
}