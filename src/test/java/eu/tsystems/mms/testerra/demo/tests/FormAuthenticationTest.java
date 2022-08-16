package eu.tsystems.mms.testerra.demo.tests;

import eu.tsystems.mms.testerra.demo.AbstractTest;
import eu.tsystems.mms.testerra.demo.page.theinternet.FormAuthenticationPage;
import eu.tsystems.mms.testerra.demo.page.theinternet.SecureArea;
import eu.tsystems.mms.testerra.demo.page.theinternet.StartPage;
import eu.tsystems.mms.tic.testframework.logging.Loggable;
import eu.tsystems.mms.tic.testframework.pageobjects.factory.PageFactory;
import eu.tsystems.mms.tic.testframework.report.model.steps.TestStep;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * FormAuthenticationTest.
 * This class contains the tests cases for form authentication page including successful and failed login.
 * Url: https://the-internet.herokuapp.com/login
 * Date: 16.08.2022
 * Time: 10:55
 *
 * @author Ibtisam Tanveer Khan
 */
public class FormAuthenticationTest extends AbstractTest implements Loggable {

    /*public WebDriver driver;
    private StartPage startPage;
    private FormAuthenticationPage formAuthenticationPage;

    @BeforeMethod
    public void setupTestEnvironment() {
        TestStep.begin("1. Init driver");
        log().info("initializing webdrivber");
        //TODO initialize webdriver and startpage and loginpage once
        driver = WebDriverManager.getWebDriver();
        startPage = PageFactory.create(StartPage.class, driver);
        formAuthenticationPage = startPage.goToLoginPage();
    }*/

    @Test
    public void testT05_FormAuthenticationSuccess() {
        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        TestStep.begin("2. Navigate to Login Page");
        log().info("navigating to the login page");
        FormAuthenticationPage formAuthenticationPage = startPage.goToLoginPage();

        TestStep.begin("3. Login with correct username and password and navigate to Secure Area");
        log().info("entering username password and click on login button");
        SecureArea secureArea = formAuthenticationPage.loginSuccessful();

        TestStep.begin("4. Assert secure area Url");
        Assert.assertEquals(secureArea.getSecureAreaUrl(), "https://the-internet.herokuapp.com/secure");

        TestStep.begin("5. Assert success login message");
        Assert.assertEquals(secureArea.getSuccessMessage(), "You logged into a secure area!");

        TestStep.begin("6. Assert secure area page header");
        Assert.assertEquals(secureArea.getPageHeader(), "Secure Area");

        TestStep.begin("7. Assert secure area page subheader text");
        Assert.assertTrue(secureArea.verifyPageSubheader(), "subheader text does not contain Secure Area");

        TestStep.begin("8. Click on Logout");
        log().info("Click logout button");
        secureArea.logoutSuccessful();

        TestStep.begin("9. Assert Login page url after logging out");
        Assert.assertEquals(formAuthenticationPage.getLoginPageUrl(), "https://the-internet.herokuapp.com/login");

        TestStep.begin("10. Assert login page header after logging out");
        Assert.assertEquals(formAuthenticationPage.getPageHeader(), "Login Page");

        TestStep.begin("11. Assert successful logout message after logging out");
        Assert.assertTrue(formAuthenticationPage.getSuccessMessage().contains("You logged out of the secure area!"), "Error displaying logout success message!!!");

    }

    @Test
    public void testT06_FormAuthenticationFailed() {
        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        TestStep.begin("2. Navigate to Login Page");
        log().info("navigating to the login page");
        FormAuthenticationPage formAuthenticationPage = startPage.goToLoginPage();

        //Perform failed login with Invalid credentials
        formAuthenticationPage.loginFailed();
        Assert.assertEquals(formAuthenticationPage.getLoginPageUrl(), "https://the-internet.herokuapp.com/login");
        Assert.assertEquals(formAuthenticationPage.getPageHeader(), "Login Page");
        String errorMessage = formAuthenticationPage.getErrorMessage();
        if (errorMessage.contains("Your username is invalid")) {
            Assert.assertTrue(errorMessage.contains("Your username is invalid!"));
        } else if (errorMessage.contains("Your password is invalid")) {
            Assert.assertTrue(errorMessage.contains("Your password is invalid!"));
        } else {
            Assert.fail("Error displaying login failed message!!!");
        }

    }
}
