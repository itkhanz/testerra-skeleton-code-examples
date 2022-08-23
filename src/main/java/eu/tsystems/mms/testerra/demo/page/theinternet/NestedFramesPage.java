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

public class NestedFramesPage extends Page {


    private GuiElement frmSet = new GuiElement(this.getWebDriver(), By.xpath("//html//frameset"));
    private GuiElement topFrame = new GuiElement(this.getWebDriver(), By.name("frame-top"));
    private GuiElement bottomFrame = new GuiElement(this.getWebDriver(), By.name("frame-bottom"));

    //throws Exception org.openqa.selenium.NoSuchFrameException: no such frame
//    private GuiElement bottomFrame = new GuiElement(this.getWebDriver(), By.name("frame-bottom"), frmSet);


    public NestedFramesPage(WebDriver driver) { super(driver); }

    public String getBottomFrameVal() {
        // works but this switch is selenium way to switch to frames
        /*this.getWebDriver().switchTo().frame("frame-bottom");
        GuiElement ftGui = new GuiElement(this.getWebDriver(), By.xpath("//body"));
        System.out.println(ftGui.getText());*/

        WebElement webElement = bottomFrame.getWebElement();
        if(bottomFrame.hasFrameLogic()){
            bottomFrame.getFrameLogic().switchToCorrectFrame();
            System.out.println("switched");
        } else {
            System.out.println("frame not displayed!!!");
        }



        /*GuiElement frametxt = new GuiElement(this.getWebDriver(), By.xpath("//body"));
        System.out.println(frametxt.getText());

        WebElement ft = this.getWebDriver().findElement(By.xpath("//body"));
        System.out.println(ft.getText());*/

        WebElement frameText = webElement.findElement(By.xpath("//body"));
        System.out.println(frameText.getText());

        return frameText.getText();
    }

    public String getTopFrameVal() {

        //throws java.lang.NullPointerException
        //not switched
        /*List<GuiElement> allFrames = topFrame.getFrameLogic().getAllFramesInOrder();
        for (GuiElement frame: allFrames) {
            System.out.println(frame.toString());
            System.out.println(frame);
        }*/

        GuiElement leftFrame = new GuiElement(this.getWebDriver(), By.name("frame-left"), topFrame);
        GuiElement middleFrame = new GuiElement(this.getWebDriver(), By.name("frame-middle"), topFrame);
        GuiElement rightFrame = new GuiElement(this.getWebDriver(), By.name("frame-right"), topFrame);

        //java.lang.NullPointerException
        /*WebElement topElement = topFrame.getWebElement();
        if(topFrame.hasFrameLogic()) {
            topFrame.getFrameLogic().switchToCorrectFrame();
            System.out.println("switched to top frame");
        } else {
            System.out.println("not switched");
        }*/

        //switches successfully to middle frame
        WebElement webElement = middleFrame.getWebElement();
        if(middleFrame.hasFrameLogic()) {
            middleFrame.getFrameLogic().switchToCorrectFrame();
            System.out.println("switched to middle frame");
        } else {
            System.out.println("not switched");
        }

        //throws NoSuchElementException: no such element: Unable to locate element: {"method":"xpath","selector":"//body"}
        //WebElement frameText = webElement.findElement(By.xpath("//body"));

        // Expected condition failed: waiting for visibility of element located by By.id: content
        /*WebDriverWait wait;
        wait = new WebDriverWait(this.getWebDriver(), 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));*/

        //throws NoSuchElementException: no such element: Unable to locate element: {"method":"css selector","selector":"#content"}
        //WebElement frameText = webElement.findElement(By.id("content"));

        System.out.println(frameText.getText());

        return frameText.getText();
    }

}
