package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Locate;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import eu.tsystems.mms.tic.testframework.utils.TimerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

/**
 * Represents https://the-internet.herokuapp.com/jqueryui/menu
 * Resources:-
 * https://docs.testerra.io/testerra/1-latest/index.html#_prepared_xpath_expressions
 * https://docs.testerra.io/testerra/1-latest/index.html#_mouse_over
 * https://docs.testerra.io/testerra/1-latest/index.html#_standard_webelement_checks
 * https://docs.testerra.io/testerra/1-latest/index.html#_functional_asserts
 *
 * Date: 26.08.2022
 * Time: 11:36
 *
 * @author Ibtisam Tanveer Khan
 */

public class JQueryUIMenuPage extends Page {

    private String menuItemString = "li[contains(@class, 'ui-menu-item')]";
    private GuiElement disabled = new GuiElement(this.getWebDriver(), By.xpath("//a[normalize-space()='Disabled']/parent::" + menuItemString));
    private GuiElement enabled = new GuiElement(this.getWebDriver(), By.xpath("//a[normalize-space()='Enabled']/parent::" + menuItemString));

    //private GuiElement pageHeading = new GuiElement(this.getWebDriver(), By.cssSelector("div[class='example'] h3"));

    public JQueryUIMenuPage (WebDriver driver) {super(driver);}

    public String download(String fileType) {

        GuiElement downloads = enabled.getSubElement(By.xpath("//a[normalize-space()='Downloads']/parent::" + menuItemString));
        GuiElement backToUI = enabled.getSubElement(By.xpath("//a[normalize-space()='Back to JQuery UI']/parent::" + menuItemString));

        Locate byText = Locate.prepare("//a[normalize-space()='%s']/parent::" + menuItemString);
        GuiElement dowloadBtn = downloads.getSubElement(byText.with(fileType));

        //downloads.asserts().assertIsNotDisplayed();
        enabled.mouseOver();
        //downloads.asserts().assertIsDisplayed();
        TimerUtils.sleep(1000, "Hover on Enabled");

        dowloadBtn.asserts().assertIsNotDisplayed();
        downloads.mouseOver();
        dowloadBtn.asserts().assertIsDisplayed();
        TimerUtils.sleep(1000, "Hover on Downloads");

        dowloadBtn.mouseOver();
        TimerUtils.sleep(1000, "Hover on " + fileType);

        GuiElement downloadLink = dowloadBtn.getSubElement(By.tagName("a"));
        String downloadLinkText = downloadLink.getAttribute("href");
        System.out.println(downloadLinkText);

        //Click anywhere outside the UI Menu so hover can be removed form the menu items in start
        backToUI.mouseOver();
        TimerUtils.sleep(1000, "Hover on Back button");

        return downloadLinkText;
    }

    public boolean disabledCheck() {
        //TODO should return False beause the button is disabled
        //java.lang.AssertionError: Element is not disabled, please review code! expected [false] but found [true]
        //return disabled.isEnabled();

        GuiElement hiddenItem = enabled.getSubElement(By.xpath("//a[normalize-space()='Should not see this']/parent::" + menuItemString));
        hiddenItem.asserts().assertIsNotDisplayed();

        return elementHasClass(disabled, "ui-state-disabled");

    }

    public boolean elementHasClass(GuiElement element, String className) {
        return Arrays.asList(element.getAttribute("class").split(" ")).contains(className);
    }


}
