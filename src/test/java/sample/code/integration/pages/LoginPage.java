package sample.code.integration.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class LoginPage extends AbstractPage {

    public void open() {
        get(BASE_URL + "pages/login");
    }

    public LoginPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void enterUsername(String username) {
        fillInputByName("j_username", username);
    }

    public void enterPassword(String password) {
        fillInputByName("j_password", password);
    }

    public PortalPage clickLoginButton(){
    	clickOnElementWithText("input","Login");
        return new PortalPage(webDriverProvider);
    }
    
    public boolean isShownErrorMessage() {
        return findElement(By.className("alert-error")) != null;
    }
    
}
