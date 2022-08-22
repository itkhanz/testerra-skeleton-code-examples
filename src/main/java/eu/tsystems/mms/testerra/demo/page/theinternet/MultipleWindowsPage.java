package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import eu.tsystems.mms.tic.testframework.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 * Represents https://the-internet.herokuapp.com/windows
 * Resources:-
 * https://docs.testerra.io/testerra/1-latest/index.html#_switching_windows
 * https://www.selenium.dev/documentation/webdriver/browser/windows/
 * Date: 22.08.2022
 * Time: 14:42
 *
 * @author Ibtisam Tanveer Khan
 */
public class MultipleWindowsPage extends Page {

    @Check
    private GuiElement newWindowLink = new GuiElement(this.getWebDriver(), By.linkText("Click Here"));

    public  MultipleWindowsPage(WebDriver driver){super(driver);}

    public String getWindowUrl() {
        return this.getWebDriver().getCurrentUrl();
    }

    public String getWindowTitle() {return this.getWebDriver().getTitle();}

    private String parentHandler = this.getWebDriver().getWindowHandle();
    private String newHandler;

    public void openNewWindow() {
        this.newWindowLink.click();

        Set<String> allHandles = this.getWebDriver().getWindowHandles();
        System.out.println(Arrays.toString(allHandles.toArray()));

        for(String newHandle: allHandles) {
            if(!parentHandler.equalsIgnoreCase(newHandle)) {
                newHandler = newHandle;
                System.out.println("Parent Handler: " + parentHandler);
                System.out.println("New Handler: " + newHandler);
                break;
            }
        }

    }


    public void switchToWindow(String windowTitle) {

        /**
         * The Webdriver automatically switches to new window even if the windowTitle is passed as "The Internet" i.e. parent
         * window. To avoid it happening, I have tried the other way of calling the findWindowAndSwitchTo() method where I
         * basically exclude the handle of new Window to avoid switching to new window. In the terminal, we can see webdriver
         *  switches to parent window and this method works in preventing the webdriver to switch to new window because of
         *  handler parameter but the switchSuccess boolean returns false and results in failing of the testcase.
         **/
        //boolean switchSuccess = false;
        /*if(windowTitle.equalsIgnoreCase("The Internet")) {
            switchSuccess = WebDriverUtils.findWindowAndSwitchTo(windowTitle, windowTitle, this.getWebDriver(), newHandler);
        } else {
            switchSuccess = WebDriverUtils.findWindowAndSwitchTo(windowTitle);
        }*/

        // Switch to a window by matching title
        Optional<WebDriver> optionalWebDriver
                = WebDriverUtils.switchToWindow(webDriver -> webDriver.getTitle().equals(windowTitle));

        if (optionalWebDriver.isPresent()) {
            System.out.println("Current Window: " + this.getWindowTitle());
        } else {
            System.out.println("Failed to switch to window titled: " + windowTitle);
        }

    }


}
