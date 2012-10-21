package sample.code.integration.pages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage extends WebDriverPage {

    public static int WAIT_MULTIPLIER = 1;

    protected static final String BASE_URL = "http://localhost:8080/pluto/";

    protected WebDriverProvider webDriverProvider;

    public AbstractPage(WebDriverProvider driverProvider) {
        super(driverProvider);
        this.webDriverProvider = driverProvider;
    }

    public void found(String text) {
        found(getPageSource(), text);
    }

    public void found(String pageSource, String text) {
        if (!pageSource.contains(escapeHtml(text))) {
            fail("Text: '" + text + "' not found in page '" + pageSource + "'");
        }
    }

    public void found(List<String> texts) {
        for (String text : texts) {
            found(text);
        }
    }

    public void notFound(String text) {
        notFound(getPageSource(), text);
    }

    public void notFound(String pageSource, String text) {
        assertThat(pageSource.contains(escapeHtml(text)), is(false));
    }

    private String escapeHtml(String text) {
        return text.replace("<", "&lt;").replace(">", "&gt;");
    }

    public void checkForElementWithText(String tagName, String text) {
        if (getElementByTagAndText(tagName, text) == null) {
            Assert.fail(String.format("No %s with text %s found on page",
                    tagName, text));
        }
    }

    /**
     * @see #clickOnElementWithText(String, String ) clickOnElementWithText("a", text)
     */
    public void clickOnLinkWithText(String text) {
        clickOnElementWithText("a", text);
    }

    public void clickOnElementWithText(String tagName, String text) {
        WebElement element = getElementByTagAndText(tagName, text);
        if (element != null) {
            element.click();
        } else {
            Assert.fail(String.format("No %s with text %s found on page",
                    tagName, text));
        }
    }

    public WebElement getElementByTagAndText(String tagName, String text) {
        List<WebElement> elements = getElementsByTagAndText(tagName, text);
        if (elements.isEmpty()) {
            return null;
        }

        return elements.get(0);
    }

    public List<WebElement> getElementsByTagAndText(String tagName, String text) {
        List<WebElement> elementsWithText = new ArrayList<WebElement>();

        List<WebElement> elements = findElements(By.tagName(tagName));
        if (elements != null) {
            for (WebElement webElement : elements) {
                if (webElement.getText().equals(text)) {
                    elementsWithText.add(webElement);
                }
            }
        }

        return elementsWithText;
    }

    public void fillInputByName(String inputName, String value) {
        findElement(By.name(inputName)).clear();
        findElement(By.name(inputName)).sendKeys(value);
    }

    public String getInputValue(String inputName) {
        return findElement(By.name(inputName)).getAttribute("value");
    }

    public void selectListElement(String inputName, String value) {
        WebElement select = findElement(By.name(inputName));
        selectListElement(select, value);
    }

    public void selectListElement(WebElement select, String value) {
        List<WebElement> options = select.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (option.getAttribute("value").equals(value)) {
                option.click();
            }
        }
    }

    public void selectRadioButton(String inputName, String value) {
        List<WebElement> options = findElements(By.name(inputName));
        for (WebElement option : options) {
            if (option.getAttribute("value").equals(value)) {
                option.click();
            }
        }
    }

    public void submitFormByName(String formName) {
        findElement(By.name(formName)).submit();
    }
}
