package stepdefs;

import io.cucumber.java.en.Given;
import pageobjects.BasePage;
import runtime.SharedDriver;


public class StepDefs {
    private BasePage basePage;
    private SharedDriver sharedDriver;

    public StepDefs(SharedDriver sharedDriver) {
        this.sharedDriver = sharedDriver;
        this.basePage = new BasePage(sharedDriver.getWebDriver());
    }

    @Given("Step from {string} in {string} feature file")
    public void step(String scenario, String file) {
        System.out.format("Thread ID - %2d - %s from %s feature file.\n",
            Thread.currentThread().getId(), scenario, file);
    }

    @Given("^I open the \"(.*?)\" page \"(.*?)\"$")
    public void I_am_on_the_website(String page, String url) {
        basePage.openPage(url);
    }
}
