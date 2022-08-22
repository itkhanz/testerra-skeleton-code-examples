package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import eu.tsystems.mms.tic.testframework.utils.TimerUtils;
import eu.tsystems.mms.tic.testframework.utils.WebDriverUtils;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverManagerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

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
        for(String newHandle: allHandles) {
            if(!parentHandler.equalsIgnoreCase(newHandle)) {
                newHandler = newHandle;
                break;
            }
        }

    }

    public void switchToWindow(String windowTitle) {
        boolean switchSuccess = false;
        if(windowTitle.equalsIgnoreCase("The Internet")) {
            switchSuccess = WebDriverUtils.findWindowAndSwitchTo(windowTitle, windowTitle, this.getWebDriver(), newHandler);
        } else {
            switchSuccess = WebDriverUtils.findWindowAndSwitchTo(windowTitle);
        }

        if (switchSuccess) {
            System.out.println("Current Window: " + this.getWindowTitle());
        } else {
            System.out.println("Failed to switch to window titled: " + windowTitle);
        }

    }


}
