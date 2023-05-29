package stepdefs;

import io.cucumber.java.en.When;
import pageobjects.HomePageObject;
import runtime.SharedDriver;

public class HomeStepDefs {
    private final HomePageObject homePage;
    private SharedDriver sharedDriver;

    public HomeStepDefs(SharedDriver sharedDriver) {
        this.sharedDriver = sharedDriver;
        homePage = new HomePageObject(sharedDriver.getWebDriver());
    }

    @When("^Searching with \"(.*?)\"$")
    public void searchingHomePage(String text) {
        homePage.inputSearchText(text);
        homePage.clickSearchBtn();
    }

    @When("^Search button value should be \"(.*?)\"$")
    public void checkSearchBtnVal(String text) {
        homePage.checkSearchBtnVal(text);
    }
}
