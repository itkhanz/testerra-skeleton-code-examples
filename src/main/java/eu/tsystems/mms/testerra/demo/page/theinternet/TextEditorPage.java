package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import eu.tsystems.mms.tic.testframework.utils.JSUtils;
import eu.tsystems.mms.tic.testframework.utils.TimerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

/**
 * Represents https://the-internet.herokuapp.com/tinymce
 * Resources:-
 * https://docs.testerra.io/testerra/1-latest/index.html#_guielements_inside_frames
 * https://www.selenium.dev/documentation/webdriver/browser/frames/
 * https://stackoverflow.com/a/21713629
 * https://stackoverflow.com/a/21688223
 * https://stackoverflow.com/a/21617345
 * https://yizeng.me/2014/01/31/test-wysiwyg-editors-using-selenium-webdriver/
 * https://seleniumjavaautomation.wordpress.com/2016/05/17/selenium-automation-ckeditor-2/
 * https://stackoverflow.com/a/40104135
 * https://www.tutorialspoint.com/how-do-you-enter-text-in-the-edit-box-in-selenium
 * https://stackoverflow.com/a/9188374
 * https://stackoverflow.com/a/70853660
 * https://artoftesting.com/check-if-an-element-is-present-in-a-webpage-selenium-webdriver-java
 * https://docs.testerra.io/testerra/1-latest/index.html#_execute_javascript
 * https://www.lambdatest.com/blog/how-to-use-javascriptexecutor-in-selenium-webdriver/
 * https://www.toolsqa.com/selenium-webdriver/javascript-and-selenium-javascriptexecutor/
 *
 *
 * Date: 23.08.2022
 * Time: 13:45
 *
 * @author Ibtisam Tanveer Khan
 */

public class TextEditorPage extends Page {

    private GuiElement boldBtn = new GuiElement(this.getWebDriver(), By.cssSelector("button[title='Bold']"));
    private GuiElement italicBtn = new GuiElement(this.getWebDriver(), By.cssSelector("button[title='Italic']"));
    private GuiElement alignLeftBtn = new GuiElement(this.getWebDriver(), By.cssSelector("button[title='Align left']"));
    private GuiElement alignCenterBtn = new GuiElement(this.getWebDriver(), By.cssSelector("button[title='Align center']"));
    private GuiElement alignRightBtn = new GuiElement(this.getWebDriver(), By.cssSelector("button[title='Align right']"));
    private GuiElement alignJustifyBtn = new GuiElement(this.getWebDriver(), By.cssSelector("button[title='Justify']"));


    private GuiElement editorFrame = new GuiElement(this.getWebDriver(), By.id("mce_0_ifr"));

    //Below elements exist inside the editorFrame iFrame, so we need to switch to it first.
    private GuiElement editorBody = new GuiElement(this.getWebDriver(), By.id("tinymce"));

    public TextEditorPage (WebDriver driver) {super(driver);}

    public void clickBoldBtn() { this.boldBtn.click(); }
    public void clickItalicBtn() { this.italicBtn.click(); }
    public void clickAlignLeftBtn() { this.alignLeftBtn.click(); }
    public void clickAlignRightBtn() { this.alignRightBtn.click(); }
    public void clickAlignCenterBtn() { this.alignCenterBtn.click(); }
    public void clickAlignJustifyBtn() { this.alignJustifyBtn.click(); }

    public void switchToEditorFrame() { this.getWebDriver().switchTo().frame("mce_0_ifr"); }
    public void switchToDefaultFrame() { this.getWebDriver().switchTo().defaultContent(); }
    public void clearEditor() {
        this.switchToEditorFrame();
        this.editorBody.clear();
        this.switchToDefaultFrame();
    }


    public void writeToEditor(String textToWrite) {
        this.switchToEditorFrame();
        editorBody.click();
        editorBody.sendKeys(textToWrite);

        //You can also use JS executor to set the inner HTML to your desired text
        /*JSUtils.executeScript(
                this.getWebDriver(),
                "arguments[0].innerHTML = '<h1>This is a H1 Heading text</h1>'",
                editorBody.getWebElement()
        );*/


        TimerUtils.sleep(2000, "just waiting to read the text written to editor");
        this.switchToDefaultFrame();
    }

    public String readFromEditor(String textStyle) {
        this.switchToEditorFrame();
        String readText = "placeholder text";
        switch (textStyle) {
            case "normal":
                readText = editorBody.getText();
                break;
            case "bold":
                readText =  findTagandReturnText("strong");
                break;
            case "italic":
                readText =  findTagandReturnText("em");
                break;
            default:
                readText = editorBody.getText();
                break;
        }

        System.out.println("Following text is read from editor: ");
        System.out.println(readText);

        this.switchToDefaultFrame();

        return readText;
    }

    public String findTagandReturnText(String tag) {
        int count = 0;
        String readText = "placeholder text";
        String cssPath = "body p " + tag;

        GuiElement tagElements = new GuiElement(this.getWebDriver(), By.cssSelector(cssPath));
        count = tagElements.getNumberOfFoundElements();
        System.out.println(count + " elements found");
        if (count > 0) {
            List<GuiElement> tagElementsList = tagElements.getList();
            System.out.println(Arrays.toString(tagElementsList.toArray()));
            for(GuiElement tagElement: tagElementsList) {
                if (tagElement.getTagName().equalsIgnoreCase(tag)) {
                    readText = tagElement.getText();
                }
            }
        } else {
            readText = tag + " not found!";
        }


        return readText;
    }

    public boolean checkAlignment(String alignment) {
        this.switchToEditorFrame();

        GuiElement tagElement = new GuiElement(this.getWebDriver(), By.cssSelector("body p"));

        if (tagElement.getCssValue("text-align").equals(alignment)) {
            System.out.println("The text is aligned: " + alignment);
            this.switchToDefaultFrame();
            return true;
        }

        this.switchToDefaultFrame();
        return false;

    }


}
