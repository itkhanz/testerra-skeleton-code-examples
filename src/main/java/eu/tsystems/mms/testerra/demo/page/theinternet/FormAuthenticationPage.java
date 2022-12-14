package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.testerra.demo.page.theinternet.partials.FooterPartialPage;
import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import eu.tsystems.mms.tic.testframework.pageobjects.factory.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormAuthenticationPage extends Page {
    private FooterPartialPage footer = PageFactory.create(FooterPartialPage.class, this.getWebDriver());

    protected String validUsername = "tomsmith";
    protected String validPassword = "SuperSecretPassword!";
    protected String invalidUsername = "test";
    protected String invalidPassword = "1234";

    private GuiElement header = new GuiElement(this.getWebDriver(), By.tagName("h2"));
    @Check
    private GuiElement usernameField = new GuiElement(this.getWebDriver(), By.id("username"));

    @Check
    private GuiElement passwordField = new GuiElement(this.getWebDriver(), By.id("password"));

    @Check
    private GuiElement loginButton = new GuiElement(this.getWebDriver(), By.className("radius"));

    private GuiElement errorMessage = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='flash' and @class='flash error']"));

    private GuiElement successMessage = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='flash' and @class='flash success']"));


    private GuiElement closeMessage = new GuiElement(this.getWebDriver(), By.className("close"));

    public FormAuthenticationPage(WebDriver driver) {
        super(driver);
    }

    public String getLoginPageUrl() {
        return this.getWebDriver().getCurrentUrl();
    }

    public String getPageHeader() {
        return this.header.getText();
    }

    public String getSuccessMessage () {
        return this.successMessage.getText();
    }
    public String getErrorMessage () {
        return this.errorMessage.getText();

        /*if(errorMessageText.equals("Your username is invalid!") || errorMessageText.equals("Your password is invalid!")) {
            return errorMessageText;
        }

        return "loginFailed";*/
    }

    public SecureArea loginSuccessful() {
        this.usernameField.type(this.validUsername);
        this.passwordField.type(this.validPassword);
        this.loginButton.click();

        return PageFactory.create(SecureArea.class, this.getWebDriver());
    }

    public void loginFailed() {
        this.usernameField.type(this.invalidUsername);
        this.passwordField.type(this.invalidPassword);
        this.loginButton.click();
    }



}
