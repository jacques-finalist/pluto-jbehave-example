package sample.code.integration.pages;

import org.jbehave.web.selenium.WebDriverProvider;

public class Pages {

    private final WebDriverProvider driverProvider;

    private LoginPage loginPage;

    public Pages(WebDriverProvider driverProvider) {
        this.driverProvider = driverProvider;
    }

    public LoginPage login() {
        if (loginPage == null) {
            loginPage = new LoginPage(driverProvider);
        }

        return loginPage;
    }

}
