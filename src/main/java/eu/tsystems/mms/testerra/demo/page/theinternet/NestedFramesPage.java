package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Represents https://the-internet.herokuapp.com/nested_frames
 * Resources:-
 * https://docs.testerra.io/testerra/1-latest/index.html#_guielements_inside_frames
 * https://www.selenium.dev/documentation/webdriver/browser/frames/
 * Date: 23.08.2022
 * Time: 13:45
 *
 * @author Ibtisam Tanveer Khan
 */

public class NestedFramesPage extends Page {


    private GuiElement frmSet = new GuiElement(this.getWebDriver(), By.xpath("//html//frameset"));
//    private GuiElement bottomFrame = frmSet.getSubElement(By.xpath("//frame[@name='frame-bottom']"));
    private GuiElement topFrame = new GuiElement(this.getWebDriver(), By.name("frame-top"));
    private GuiElement bottomFrame = new GuiElement(this.getWebDriver(), By.xpath("//frame[@name='frame-bottom']"));

    //throws Exception org.openqa.selenium.NoSuchFrameException: no such frame
//    private GuiElement bottomFrame = new GuiElement(this.getWebDriver(), By.name("frame-bottom"), frmSet);


    public NestedFramesPage(WebDriver driver) { super(driver); }

    public String getBottomFrameVal() {

        // It works but this switch is selenium way to switch to frames
        this.getWebDriver().switchTo().frame("frame-bottom");
        GuiElement frameTextElement = new GuiElement(this.getWebDriver(), By.xpath("//body"));
        String frameText = frameTextElement.getText();
        System.out.println("Text inside Bottom frame is: " + frameText);
        this.getWebDriver().switchTo().defaultContent();
        return frameText;

        //TODO cannot switch to bottom frame using Testerra methods and hence element not found exception
        /*if(bottomFrame.waits().waitForIsDisplayed()) {

            WebElement bottomElement = bottomFrame.getWebElement();

            if(bottomFrame.hasFrameLogic()){;
                bottomFrame.getFrameLogic().switchToCorrectFrame();
                System.out.println("switched to bottom frame!!!!");
            } else {
                System.out.println("could not switch to bottom frame!!!!");
            }

            WebElement frameTextElement = bottomElement.findElement(By.xpath("//body"));
            String frameText = frameTextElement.getText();
            System.out.println(frameText);

            bottomFrame.getFrameLogic().switchToDefaultFrame();
            System.out.println("switched to default frame");

            return frameText;
        } else {
            System.out.println("bottom frame is not displayed!!!!");
            return "not found";
        }
*/

    }

    public String getTopMidFrameVal() {

        // It works but this switch is selenium way to switch to frames
        this.getWebDriver().switchTo().frame("frame-top");
        this.getWebDriver().switchTo().frame("frame-middle");
        GuiElement frameTextElement = new GuiElement(this.getWebDriver(), By.xpath("//div[@id='content']"));
        String frameText = frameTextElement.getText();
        System.out.println("Text inside Top Middle frame is: " + frameText);
        this.getWebDriver().switchTo().defaultContent();
        return frameText;

        //TODO cannot switch to top frame, bottom frame switch is successful but element not found exception occurs
        /*GuiElement childFrame = new GuiElement(this.getWebDriver(), By.name(childFrameName), topFrame);

        //throws java.lang.NullPointerException
        //not switched
        *//*List<GuiElement> allFrames = topFrame.getFrameLogic().getAllFramesInOrder();
        for (GuiElement frame: allFrames) {
            System.out.println(frame.toString());
            System.out.println(frame);
        }*//*

        //throws java.lang.NullPointerException
        *//*WebElement topElement = topFrame.getWebElement();
        if(topFrame.hasFrameLogic()) {
            topFrame.getFrameLogic().switchToCorrectFrame();
            System.out.println("switched to top frame");
        } else {
            System.out.println("not switched");
        }*//*

        //switches successfully to middle frame
        WebElement webElement = childFrame.getWebElement();
        if(childFrame.hasFrameLogic()) {
            childFrame.getFrameLogic().switchToCorrectFrame();
            System.out.println("switched to frame:  " + childFrameName);
        } else {
            System.out.println("not switched");
        }

        //Try 1
        //throws NoSuchElementException: no such element: Unable to locate element: {"method":"xpath","selector":"//body"}
        //WebElement frameText = webElement.findElement(By.xpath("//body"));

        //Try 2
        // Expected condition failed: waiting for visibility of element located by By.id: content
        *//*WebDriverWait wait;
        wait = new WebDriverWait(this.getWebDriver(), 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));*//*

        //throws NoSuchElementException: no such element: Unable to locate element: {"method":"css selector","selector":"#content"}
        //WebElement frameText = webElement.findElement(By.id("content"));

        //Try 3
        //throws NoSuchElementException: no such element: Unable to locate element: {"method":"xpath","selector":"//div[@id='content']"}
        WebElement frameText = webElement.findElement(By.xpath("//div[@id='content']"));

        System.out.println(frameText.getText());

        return frameText.getText();*/
    }

    public String getTopRightFrameVal() {

        // It works but this switch is selenium way to switch to frames
        this.getWebDriver().switchTo().frame("frame-top");
        this.getWebDriver().switchTo().frame("frame-right");

        GuiElement frameTextElement = new GuiElement(this.getWebDriver(), By.xpath("//body"));
        String frameText = frameTextElement.getText();
        System.out.println("Text inside Top Right frame is: " + frameText);

        this.getWebDriver().switchTo().defaultContent();

        return frameText;
    }

}
