package eu.tsystems.mms.testerra.demo.page.theinternet;

import edu.umd.cs.piccolo.PRoot;
import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Represents https://the-internet.herokuapp.com/hovers
 * Resources:-
 * https://docs.testerra.io/testerra/1-latest/index.html#_mouse_over
 *
 * Date: 21.08.2022
 * Time: 15:12
 *
 * @author Ibtisam Tanveer Khan
 */

public class HoversPage extends Page {

    @Check
    private GuiElement pageHeading = new GuiElement(this.getWebDriver(), By.cssSelector("div[class='example'] h3"));
    @Check
    private GuiElement fig1 = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='content']//div[@class='figure'][1]"));

    @Check
    private GuiElement fig2 = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='content']//div[@class='figure'][2]"));

    @Check
    private GuiElement fig3 = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='content']//div[@class='figure'][3]"));

    @Check
    private GuiElement fig = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='content']//div[@class='figure']"));
    private List<GuiElement> figAll = fig.getList();
    private HashMap<Integer, GuiElement> figMap= new HashMap<>();
    private GuiElement figHeading, figLink;


    public HoversPage(WebDriver driver) {super(driver);}

    /*creates a hashmap of all the figures on page, key will be figure index, value will be figure GuiElement*/
    public void figListToHashmap() {
        for (int i = 0; i < figAll.size(); i++) {
            figMap.put(i+1, figAll.get(i));
        }
    }

    public void createFigSubelements(int index) {
        figHeading = figMap.get(index).getSubElement(By.tagName("h5"));
        figLink = figMap.get(index).getSubElement(By.linkText("View profile"));
    }

    /*
    Checks if the index is valid, and if index is valid, it creates the figHeading and figLink subElements for this index
     */
    public boolean validityCheckIndex(int index) {
        if (figMap.containsKey(index)) {
            this.createFigSubelements(index);
            return true;
        } else {
            System.out.println("Invalid index, No figure found at this index!");
            System.out.print("These are valid figure indexes: ");
            System.out.println(Arrays.toString(figMap.keySet().toArray()));
            return false;
        }
    }

    public boolean hoverOnFig(int index) {
        boolean indexValid = validityCheckIndex(index);
        if (indexValid) {
            figMap.get(index).mouseOver();
        } else {
            return false;
        }
        return true;
    }

    public void hoverOnHeading() {
        pageHeading.mouseOver();
    }

    public boolean isFigDataShown(int index) {
        boolean indexValid = validityCheckIndex(index);
        if (indexValid) {
            if(figHeading.isDisplayed() && figLink.isDisplayed()) {
                System.out.println(figHeading.getText());
                System.out.println(figLink.getAttribute("href") + " " + figLink.getText());
            } else {
                System.out.println("Figure " + index + " data is not diasplayed");
                return false;
            }
        }

        return true;
    }
}
