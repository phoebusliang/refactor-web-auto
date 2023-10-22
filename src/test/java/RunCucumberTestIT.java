import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty",
        "html:target/cucumber-reports/CucumberReport.html",
        "json:target/cucumber-reports/Cucumber.json"
    },
    glue = {"stepdefs", "runtime"},
    monochrome = false
)
public class RunCucumberTestIT {
}
