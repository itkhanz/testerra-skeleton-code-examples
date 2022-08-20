package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

/**
 * Represents https://the-internet.herokuapp.com/dropdown
 * Resources:-
 * https://docs.testerra.io/testerra/1-latest/index.html#_use_select_boxes
 * https://www.selenium.dev/documentation/webdriver/elements/select_lists/
 * https://www.toolsqa.com/selenium-webdriver/selenium-checkbox/
 * https://www.guru99.com/select-option-dropdown-selenium-webdriver.html
 * Date: 20.08.2022
 * Time: 13:12
 *
 * @author Ibtisam Tanveer Khan
 */

public class DropdownPage extends Page {
    @Check
    private GuiElement dropdownSelector = new GuiElement(this.getWebDriver(), By.id("dropdown"));
    private Select selected = dropdownSelector.getSelectElement();

    public DropdownPage (WebDriver driver) {super(driver);}

    public void showAllOptions() {
        ArrayList<WebElement> list = (ArrayList<WebElement>) selected.getOptions();
        System.out.println("printing the value attribute and text of select options");
        for(WebElement option : list) {
            System.out.print("Value: " + option.getAttribute("value") + " , Text: " + option.getText());
            if(option.isEnabled()) {
                System.out.println();
            } else {
                System.out.println(" ,disabled ");
            }
        }
    }

    public String getSelectedOptionText() {
        WebElement option = selected.getFirstSelectedOption();
        if (option.isSelected()) {
            System.out.println("Selected option is: " + option.getText());
            return option.getText();
        }
        return "couldn't select option";
    }

    public void setOptionByIndex(int index) {
        selected.selectByIndex(index);
    }

    public void setOptionByValue(String value) {
        selected.selectByValue(value);
    }

    public void setOptionByVisibletext(String text) {
        selected.selectByVisibleText(text);
    }

}
