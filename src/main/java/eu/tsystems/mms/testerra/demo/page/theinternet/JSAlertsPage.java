package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents https://the-internet.herokuapp.com/javascript_alerts
 * Resources:-
 * https://www.selenium.dev/documentation/webdriver/browser/alerts/
 * https://docs.testerra.io/testerra/1-latest/index.html#_waiters
 * Date: 22.08.2022
 * Time: 11:42
 *
 * @author Ibtisam Tanveer Khan
 */

public class JSAlertsPage extends Page {
    @Check
    private GuiElement alertBtn = new GuiElement(this.getWebDriver(), By.xpath("//button[normalize-space()='Click for JS Alert']"));

    @Check
    private GuiElement confirmBtn = new GuiElement(this.getWebDriver(), By.xpath("//button[normalize-space()='Click for JS Confirm']"));

    @Check
    private GuiElement promptBtn = new GuiElement(this.getWebDriver(), By.xpath("//button[normalize-space()='Click for JS Prompt']"));


    private GuiElement result = new GuiElement(this.getWebDriver(), By.id("result"));


    public JSAlertsPage(WebDriver driver) {super(driver);}

    public String getResultText() {
        if(result.waits().waitForIsDisplayed()) {
            String resultText = result.getText();
            System.out.println(resultText);
            return resultText;
        }
        return "result text empty!!!";
    }

    public String clickAlert() {
        this.alertBtn.click();
        Alert jsAlert = this.getWebDriver().switchTo().alert();
        System.out.println(jsAlert.getText());
        jsAlert.accept();

        return getResultText();
    }

    public String clickConfirm(String action) {
        this.confirmBtn.click();
        Alert jsConfirm = this.getWebDriver().switchTo().alert();
        System.out.println(jsConfirm.getText());

        if (action.equals("ok")) {
            jsConfirm.accept();
        } else if (action.equals("cancel")) {
            jsConfirm.dismiss();
        } else {
            return "invalid JS Confirm action! please choose either 'ok' or 'cancel'";
        }

        return getResultText();
    }

    public String enterPrompt(String promptText, String action) {
        this.promptBtn.click();
        Alert jsPrompt = this.getWebDriver().switchTo().alert();
        System.out.println(jsPrompt.getText());

        jsPrompt.sendKeys(promptText);

        if (action.equals("ok")) {
            jsPrompt.accept();
        } else if (action.equals("cancel")) {
            jsPrompt.dismiss();
        } else {
            return "invalid JS Prompt action! please choose either 'ok' or 'cancel'";
        }

        return getResultText();
    }
}
