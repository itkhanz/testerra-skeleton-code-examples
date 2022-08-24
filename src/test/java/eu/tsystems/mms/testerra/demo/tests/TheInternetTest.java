/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Eric Kubenka
 */
package eu.tsystems.mms.testerra.demo.tests;

import eu.tsystems.mms.testerra.demo.model.theinternet.UserModel;
import eu.tsystems.mms.testerra.demo.model.theinternet.UserModelFactory;
import eu.tsystems.mms.testerra.demo.page.theinternet.*;
import eu.tsystems.mms.tic.testframework.annotations.Fails;
import eu.tsystems.mms.tic.testframework.logging.Loggable;
import eu.tsystems.mms.tic.testframework.pageobjects.factory.PageFactory;
import eu.tsystems.mms.tic.testframework.report.FailureCorridor;
import eu.tsystems.mms.tic.testframework.report.model.steps.TestStep;
import eu.tsystems.mms.tic.testframework.utils.TimerUtils;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverManager;
import io.cucumber.java.bs.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import eu.tsystems.mms.testerra.demo.AbstractTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Sample Description goes here.
 * <p>
 * Created Date: 17.02.2020
 * Last Edited Date: 22.08.2022
 *
 * @created by author Eric Kubenka
 * @edited by author Ibtisam Tanveer Khan
 */
public class TheInternetTest extends AbstractTest implements Loggable {

    private static final UserModelFactory userModelFactory = new UserModelFactory();
    //private enum CheckboxMethods {SELECT, DESELECT};

    @Test(enabled = false)
    public void testT01_DoDragAndDrop() {

        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);
        DragAndDropPage dragAndDropPage = startPage.goToDragAndDropPage();
        //System.out.println("Navigating to DragAndDropPage");
        log().debug("Navigating to DragAndDropPage");

        Assert.assertEquals(dragAndDropPage.getColumnAText(), "A");
        Assert.assertEquals(dragAndDropPage.getColumnBText(), "B");
        //System.out.println("Before DragAndDrop: Getting the text of Column A and B");
        log().info("Before DragAndDrop: Getting the text of Column A and B");

        dragAndDropPage.doDragAndDrop();
        //System.out.println("Performing the Drag and Drop Operation");
        log().warn("Performing the Drag and Drop Operation");

        Assert.assertEquals(dragAndDropPage.getColumnAText(), "B");
        Assert.assertEquals(dragAndDropPage.getColumnBText(), "A");
        //System.out.println("After DragAndDrop: Asserting the text of Column A and B");
        log().info("After DragAndDrop: Asserting the text of Column A and B");

    }

    @Test
    public void testT02_AddElementsTest() {

        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        AddAndRemoveElementsPage addAndRemoveElementsPage = startPage.goToAddAndRemoveElementsPage();

        Assert.assertEquals(addAndRemoveElementsPage.getElementCount(), 0);
        addAndRemoveElementsPage = addAndRemoveElementsPage.doAddElement();

        Assert.assertEquals(addAndRemoveElementsPage.getElementCount(), 1);
        addAndRemoveElementsPage = addAndRemoveElementsPage.doAddElement();

        Assert.assertEquals(addAndRemoveElementsPage.getElementCount(), 2);
        addAndRemoveElementsPage = addAndRemoveElementsPage.doAddElement();

        Assert.assertEquals(addAndRemoveElementsPage.getElementCount(), 3);
        addAndRemoveElementsPage = addAndRemoveElementsPage.doRemoveElement();

        Assert.assertEquals(addAndRemoveElementsPage.getElementCount(), 2);
    }

    @Test
    public void testT03_SortTables() {

        final UserModel userJohnSmith = userModelFactory.createJohnSmith();
        final UserModel userNonExisting = userModelFactory.createNonExisting();

        TestStep.begin("1. Init driver");
        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        TestStep.begin("2. Navigate to tables");
        TablePage tablePage = startPage.goToTablePage();

        TestStep.begin("3. Assert Last Name column present");
        final List<String> availAbleColumnNames = tablePage.getAvailAbleColumnNames();
        Assert.assertTrue(availAbleColumnNames.contains("Last Name"));

        TestStep.begin("4. Get data of first entry");
        HashMap<String, String> row1BeforeSorting = tablePage.getRowDataByIndex(1);

        TestStep.begin("5. Sort by Last Name");
        tablePage = tablePage.doSortTableByColumn("Last Name");

        TestStep.begin("6. Assert another data set is now in row 1");
        HashMap<String, String> row1AfterSorting = tablePage.getRowDataByIndex(1);
        Assert.assertNotEquals(row1AfterSorting.get("Last Name"), row1BeforeSorting.get("Last Name"));

        TestStep.begin("7. Assert user model shown");
        Assert.assertTrue(tablePage.isUserShown(userJohnSmith));
        Assert.assertFalse(tablePage.isUserShown(userNonExisting));
    }

    @Test
    @Fails(ticketString = "http://jira.mms/JIRA-1337", description = "Will fail because user does not exist")
    @FailureCorridor.Low
    public void testT04_TableEntryNotPresent() {

        final UserModel userNonExisting = userModelFactory.createNonExisting();
        TestStep.begin("1. Init driver");

        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        TestStep.begin("2. Navigate to tables");
        TablePage tablePage = startPage.goToTablePage();

        TestStep.begin("3. Assert user shown.");
        Assert.assertTrue(tablePage.isUserShown(userNonExisting));
    }

    @Test
    public void testT05_Dropdownlist() {
        TestStep.begin("1. Init driver");
        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        TestStep.begin("2. Navigate to Dropdown page");
        DropdownPage dropdownPage = startPage.goToDropdownPage();

        log().info("Printing all the options of a dropdown");
        dropdownPage.showAllOptions();

        TestStep.begin("3. Select Option 1 by index");
        dropdownPage.setOptionByIndex(1);
        Assert.assertEquals(dropdownPage.getSelectedOptionText(), "Option 1");

        TestStep.begin("4. Select Option 2 by value attribute");
        dropdownPage.setOptionByValue("2");
        Assert.assertEquals(dropdownPage.getSelectedOptionText(), "Option 2");

        TestStep.begin("5. Select Option 1 by visible text");
        dropdownPage.setOptionByVisibletext("Option 1");
        Assert.assertEquals(dropdownPage.getSelectedOptionText(), "Option 1");
    }

    @Test
    public void testT06_Checkboxes() {

        TestStep.begin("1. Init driver");
        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        TestStep.begin("2. Navigate to Checkboxes page");
        CheckboxesPage checkboxesPage = startPage.goToCheckboxesPage();

        TestStep.begin("3. Get initial selected checkboxes");
        checkboxesPage.getSelectedCheckboxes();

        TestStep.begin("4. deselect checkbox 2");
//        Assert.assertTrue(checkboxesPage.editCheckbox("checkbox 2", CheckboxMethods.DESELECT.toString()));
        Assert.assertFalse(checkboxesPage.deselectCheckbox(1));

        TestStep.begin("5. select checkbox 1 ");
//        Assert.assertTrue(checkboxesPage.editCheckbox("checkbox 1", CheckboxMethods.SELECT.toString()));
        Assert.assertTrue(checkboxesPage.selectCheckbox(0));

        TestStep.begin("6. Get selected checboxes");
        ArrayList<String> selectedCheckboxes = checkboxesPage.getSelectedCheckboxes();
        Assert.assertTrue(selectedCheckboxes.contains("checkbox 1"));
        Assert.assertFalse(selectedCheckboxes.contains("checkbox 2"));

        TestStep.begin("7. select checkbox 2 ");
        Assert.assertTrue(checkboxesPage.selectCheckbox(1));

        TestStep.begin("8. All checboxes selected ");
        selectedCheckboxes = checkboxesPage.getSelectedCheckboxes();
        Assert.assertTrue(selectedCheckboxes.contains("checkbox 1"));
        Assert.assertTrue(selectedCheckboxes.contains("checkbox 2"));

    }

    @Test
    public void testT07_Hovers() {
        TestStep.begin("1. Init driver");
        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        TestStep.begin("2. Navigate to Hovers page");
        HoversPage hoversPage = startPage.goToHoversPage();

        TestStep.begin("3. Create Hashmap of all figures elements");
        hoversPage.figListToHashmap();

        TestStep.begin("4. Verify that initially no figure data is displayed");
        for (int i = 1; i<=3; i++) {
            Assert.assertFalse(hoversPage.isFigDataShown(i));
        }

        TestStep.begin("5. Hover on Figure 2");
        Assert.assertTrue(hoversPage.hoverOnFig(2));

        TestStep.begin("6. Verify that figure 2 data is displayed on hover");
        Assert.assertTrue(hoversPage.isFigDataShown(2));

        //Problem Mouse pointer stays over figure 2 and a seprate hover is performed on page heading
        //TODO Find a a way to remove hover
        /*TestStep.begin("7. Change hover to page heading");
        hoversPage.hoverOnHeading();

        TestStep.begin("8. Verify that figure 2 data is not displayed without hover");
        Assert.assertFalse(hoversPage.hoverOnFig(2));*/

    }

    @Test
    public void testT08_JSAlerts() {
        TestStep.begin("1. Init driver");
        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        TestStep.begin("2. Navigate to JS Alerts page");
        JSAlertsPage jsAlertsPage = startPage.goToJSAlertsPage();

        TestStep.begin("3. Click for JS Alert");
        Assert.assertTrue(jsAlertsPage.clickAlert().contains("You successfully clicked an alert"));

        TestStep.begin("4. Accept JS Confirm alert");
        Assert.assertTrue(jsAlertsPage.clickConfirm("ok").contains("You clicked: Ok"));

        TestStep.begin("5. Cancel JS Confirm alert");
        Assert.assertTrue(jsAlertsPage.clickConfirm("cancel").contains("You clicked: Cancel"));

        TestStep.begin("6. Send a JS Prompt alert text");
        Assert.assertTrue(jsAlertsPage.enterPrompt("I am a test", "ok").contains("You entered: I am a test"));

        TestStep.begin("6. Cancel a JS Prompt alert text");
        Assert.assertTrue(jsAlertsPage.enterPrompt("I am a test", "cancel").contains("You entered: null"));

    }

    @Test
    public void testT09_MultipleWindows() {
        TestStep.begin("1. Init driver");
        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        TestStep.begin("2. Navigate to JS Alerts page");
        MultipleWindowsPage multipleWindowsPage = startPage.goToMultipleWindowsPage();

        TestStep.begin("3. Open New Window");
        multipleWindowsPage.openNewWindow();

        TestStep.begin("4. Switch to  New Window");
        multipleWindowsPage.switchToWindow("New Window");

        TestStep.begin("5. Verify the Url of new window");
        Assert.assertEquals(multipleWindowsPage.getWindowUrl(), "https://the-internet.herokuapp.com/windows/new");

        TestStep.begin("6. Switch back to Parent Window");
        multipleWindowsPage.switchToWindow("The Internet");

        TestStep.begin("7. Verify the Url of Parent window");
        Assert.assertEquals(multipleWindowsPage.getWindowUrl(), "https://the-internet.herokuapp.com/windows");

    }

    @Test
    public void testT10_NestedFrames() {
        TestStep.begin("1. Init driver");
        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        TestStep.begin("2. Navigate to Nested Frames page");
        NestedFramesPage nestedFramesPage = startPage.goToNestedFramesPage();

        TestStep.begin("3. Verify the content of bottom frame");
        Assert.assertTrue(nestedFramesPage.getBottomFrameVal().contains("BOTTOM"));

        TestStep.begin("4. Verify the content of middle frame inside top frame");
        Assert.assertEquals(nestedFramesPage.getTopMidFrameVal(),"MIDDLE");

        TestStep.begin("5. Verify the content of right frame inside top frame");
        Assert.assertTrue(nestedFramesPage.getTopRightFrameVal().contains("RIGHT"));
    }

    @Test
    public void testT11_TextEditor() {
        TestStep.begin("1. Init driver");
        final WebDriver driver = WebDriverManager.getWebDriver();
        StartPage startPage = PageFactory.create(StartPage.class, driver);

        TestStep.begin("2. Navigate to an iFrame containing the TinyMCE WYSIWYG Editor page");
        TextEditorPage textEditorPage = startPage.goToTextEditorPage();


        TestStep.begin("3. Writing a normal text to editor");
        textEditorPage.clearEditor();
        textEditorPage.writeToEditor("This is a normal text");
        Assert.assertTrue(textEditorPage.readFromEditor("normal").contains("This is a normal text"));

        TestStep.begin("4. Make the text align right");
        textEditorPage.clickAlignRightBtn();
        TimerUtils.sleep(1000, "See if the text is aligned right");
        Assert.assertTrue(textEditorPage.checkAlignment("right"), "Text is not aligned right");

        TestStep.begin("4. Make the text align center");
        textEditorPage.clickAlignCenterBtn();
        TimerUtils.sleep(1000, "See if the text is aligned center");
        Assert.assertTrue(textEditorPage.checkAlignment("center"), "Text is not aligned center");

        TestStep.begin("4. Make the text align justify");
        textEditorPage.clickAlignJustifyBtn();
        TimerUtils.sleep(1000, "See if the text is aligned justify");
        Assert.assertTrue(textEditorPage.checkAlignment("justify"), "Text is not aligned justify");

        TestStep.begin("4. Make the text align left");
        textEditorPage.clickAlignLeftBtn();
        TimerUtils.sleep(1000, "See if the text is aligned left");
        Assert.assertTrue(textEditorPage.checkAlignment("left"), "Text is not aligned left");

        TestStep.begin("4. Writing bolder text");
        textEditorPage.clearEditor();
        textEditorPage.clickBoldBtn();
        textEditorPage.writeToEditor("This is a bold text");
        textEditorPage.clickBoldBtn();
        Assert.assertTrue(textEditorPage.readFromEditor("bold").contains("This is a bold text"));

        TestStep.begin("4. Writing Italic text");
        textEditorPage.clearEditor();
        textEditorPage.clickItalicBtn();
        textEditorPage.writeToEditor("This is an italic text");
        textEditorPage.clickItalicBtn();
        Assert.assertTrue(textEditorPage.readFromEditor("italic").contains("This is an italic text"));

    }

}
