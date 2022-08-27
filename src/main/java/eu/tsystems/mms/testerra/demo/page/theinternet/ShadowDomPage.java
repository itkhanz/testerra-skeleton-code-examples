package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import eu.tsystems.mms.tic.testframework.utils.JSUtils;
import eu.tsystems.mms.tic.testframework.utils.TimerUtils;
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
 * Date: 27.08.2022
 * Time: 13:27
 *
 * @author Ibtisam Tanveer Khan
 */

public class ShadowDomPage extends Page {

    public GuiElement shadowRootElement = new GuiElement(this.getWebDriver(), By.xpath("//my-paragraph[1]")).shadowRoot();

    // getSubElement() on shadowRoot() only supports ByXPath
    // public GuiElement shadowRootElementText = shadowRootElement.getSubElement(By.name("my-text"));

    //TODO
    //ElementNotFoundException: >shadowRootElementText< (By.xpath: ./..//p child of >shadowRootElement< (By.xpath: //my-paragraph[1])): not found
    public GuiElement shadowRootElementText = shadowRootElement.getSubElement(By.xpath("//p"));
    //public GuiElement shadowRootElementText = shadowRootElement.getSubElement(By.xpath(".//*"));
    //public GuiElement shadowRootElementText = shadowRootElement.getSubElement(By.xpath("//slot[@name='my-text']"));

    public ShadowDomPage(WebDriver driver) {super(driver);}

    public String getShadowRootElementText () {
        int count = shadowRootElement.getNumberOfFoundElements();
        System.out.println("No of shadow roots found: " + count);


        //TODO Zero elements found in shadow root
        GuiElement childNodes = shadowRootElement.getSubElement(By.xpath("//p"));
        System.out.println("No of elements found in shadow root: " + childNodes.getNumberOfFoundElements());
        List<GuiElement> elementList = childNodes.getList();
        for (GuiElement element: elementList) {
            System.out.println(element);
            System.out.println(element.getTagName());
            System.out.println(element.getText());
            System.out.println("******************************************");
        }

        //TODO it does not return all the child nodes of shadow DOM, cannot find the P tag
        /*List<GuiElement> shadowRootElementList = shadowRootElement.getList();
        for (GuiElement shadowRootElementSingle: shadowRootElementList) {
            System.out.println("shadowRootElementSingle START");
            System.out.println(shadowRootElementSingle);
            System.out.println("shadowRootElementSingle END");
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

        String locatorJS = "return document.querySelector('my-paragraph').shadowRoot.querySelector('p')";

        //TODO Webelement shadowRootElement does not return the correct text on calling getText()
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



    /*This method always returns True. It was created to verify the shadowRoot() works as expected or not
    * Upon running, it was observed that the it can only run with the open shadow DOM.
    * To work with the closed shadow DOM, you need to know the object of the shadowDOM defined in its constructor
    * The frontend developer can tell which name he choose for the constructor and then we can acces it with the JS
    * In the below string, replace shadowRoot with the name of constructor used for closed shadow DOM and execute JS
    * String locatorJS = "return document.querySelector('my-paragraph').shadowRoot.querySelector('p')";
    *
    */
    public boolean fillShadowDOM() {

        this.getWebDriver().get("https://letcode.in/shadow");

        GuiElement fnameShadowHost = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='open-shadow']")).shadowRoot();
        GuiElement fnameShadowRootElement = fnameShadowHost.getSubElement(By.xpath("//input[@id='fname']"));
        fnameShadowRootElement.type("Ibtisam");
        System.out.println(fnameShadowRootElement.getText());

        //TODO shadowRoot cannot access the closed shadow DOM
        /*GuiElement lnameShadowHost = new GuiElement(this.getWebDriver(), By.xpath("//div[@class='field']//my-web-component")).shadowRoot();
        GuiElement lnameShadowRootElement = lnameShadowHost.getSubElement(By.xpath("//input[@id='lname']"));
        lnameShadowRootElement.type("Khan");
        System.out.println(lnameShadowRootElement.getText());

        GuiElement emailShadowHost = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='close-shadow']")).shadowRoot();
        GuiElement emailShadowRootElement = emailShadowHost.getSubElement(By.xpath("//input[@id='email']"));
        emailShadowRootElement.type("abc@gmail.com");
        System.out.println(emailShadowRootElement.getText());
        */

        TimerUtils.sleep(2000, "Read the filled input on webpage");


        return true;

    }
}
