package runtime;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SharedDriver {
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    public static final ThreadLocal session = new ThreadLocal();

    public static Object getScenarioProperty(String key) {
        return ((HashMap<String, Object>) session.get()).get(key);
    }

    public static Object setScenarioProperty(String key, Object value) {
        return ((HashMap<String, Object>) session.get()).put(key, value);
    }

    public WebDriver getWebDriver() {
        if (webDriver.get() == null) {
            webDriver.set(new ChromeDriver());
            HashMap<String, Object> context = new HashMap<String, Object>();
            session.set(context);
        }
        return webDriver.get();
    }

    @After(order = 1)
    public void after(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                takeScreenshot(scenario, scenario.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (webDriver.get() != null) {
                webDriver.get().quit();
                webDriver.remove();
                session.remove();
            }
        }
    }

    private void takeScreenshot(Scenario scenario, String name) throws IOException {
        File scrFile = ((TakesScreenshot) webDriver.get()).getScreenshotAs(OutputType.FILE);
        final byte[] embedScreenshot = ((TakesScreenshot) webDriver.get()).getScreenshotAs(OutputType.BYTES);
        FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + File.separator + "screenshot" + File.separator + scenario.getName() + "-" + String.valueOf(System.currentTimeMillis()) + ".png"));
        scenario.attach(embedScreenshot, "image/png", name);
    }
}
