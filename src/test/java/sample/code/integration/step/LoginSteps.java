package sample.code.integration.step;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import sample.code.integration.pages.LoginPage;
import sample.code.integration.pages.Pages;
import sample.code.integration.pages.PortalPage;

public class LoginSteps {

    private LoginPage loginPage;
    private Pages pages;
    private PortalPage portalPage;

    public LoginSteps(Pages pages) {
        this.pages=pages;
    }

    @When("a user logs in to the portal")
    public void login() throws InterruptedException {
        loginPage = pages.login();
        loginPage.enterUsername("pluto");
        loginPage.enterPassword("pluto");
        portalPage=loginPage.clickLoginButton();
    }

    @Then("the sample portlet is shown")
    public void userDetailsAreShown() {
    	portalPage.checkForElementWithText("h2", "Book Catalog");
    }
}
