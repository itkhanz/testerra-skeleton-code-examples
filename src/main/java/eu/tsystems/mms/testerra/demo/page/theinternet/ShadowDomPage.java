package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import eu.tsystems.mms.tic.testframework.utils.JSUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Represents https://the-internet.herokuapp.com/tinymce
 * Resources:-
 * https://docs.testerra.io/testerra/1-latest/index.html#_execute_javascript
 * https://docs.testerra.io/testerra/1-latest/index.html#_shadow_roots
 * https://www.seleniumeasy.com/selenium-tutorials/accessing-shadow-dom-elements-with-webdriver
 * 
 *
 *
 *
 * Date: 26.08.2022
 * Time: 22:17
 *
 * @author Ibtisam Tanveer Khan
 */

public class ShadowDomPage extends Page {

    public GuiElement shadowRootElement = new GuiElement(this.getWebDriver(), By.xpath("//my-paragraph")).shadowRoot();

    // getSubElement() on shadowRoot() only supports ByXPath
    // public GuiElement shadowRootElementText = shadowRootElement.getSubElement(By.name("my-text"));

    //TODO
    //ElementNotFoundException: >shadowRootElementText< (By.xpath: ./..//p child of >shadowRootElement< (By.xpath: //my-paragraph[1])): not found
    public GuiElement shadowRootElementText = shadowRootElement.getSubElement(By.xpath("//p"));
    //public GuiElement shadowRootElementText = shadowRootElement.getSubElement(By.xpath(".//*"));
    //public GuiElement shadowRootElementText = shadowRootElement.getSubElement(By.xpath("//slot[@name='my-text']"));

    public ShadowDomPage(WebDriver driver) {super(driver);}

    public String getShadowRootElementText () {
        //TODO it does not return all the child nodes of shadow DOM
        /*GuiElement childNodes = shadowRootElement.getSubElement(By.xpath(".//*"));
        List<GuiElement> elementList = childNodes.getList();
        for (GuiElement element: elementList) {
            System.out.println(element.getTagName());
            System.out.println(element.getText());
            System.out.println("******************************************");
        }*/


        String text = shadowRootElementText.getText();
        System.out.println(text);
        return text;
    }

    public String getShadowRootElementTextJS() {
        //Run this snipped in the chrome dev console to verify the output
        /*var shadowHost = document.querySelector("my-paragraph")
        var shadowRootElementText = shadowHost.shadowRoot.querySelector("p")
        shadowRootElementText.textContent*/

        //TODO Webelement shadowRootElement does not return the correct text on calling getText()
        String locatorJS = "return document.querySelector('my-paragraph').shadowRoot.querySelector('p')";

        /*WebElement shadowRootElement = (WebElement) JSUtils.executeScript(this.getWebDriver(), locatorJS);
        String text = shadowRootElement.getText();
        System.out.println(text);
        System.out.println("****************");*/

        String locatorTextJS = locatorJS + ".textContent";
        String result = (String) JSUtils.executeScript(this.getWebDriver(), locatorTextJS);
        System.out.println(result);
        //System.out.println("****************");

        return result;
    }
}
