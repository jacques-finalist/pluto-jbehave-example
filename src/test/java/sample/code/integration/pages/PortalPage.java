package sample.code.integration.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class PortalPage extends AbstractPage {

    public void open() {
        get(BASE_URL + "/");
    }

    public PortalPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void enterUsername(String username) {
        fillInputByName("j_username", username);
    }

    public void enterPassword(String password) {
        fillInputByName("j_password", password);
    }

    public boolean isShownErrorMessage() {
        return findElement(By.className("alert-error")) != null;

    }
}
