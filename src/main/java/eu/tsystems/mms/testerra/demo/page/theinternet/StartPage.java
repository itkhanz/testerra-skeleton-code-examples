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
package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.testerra.demo.page.theinternet.partials.FooterPartialPage;
import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import eu.tsystems.mms.tic.testframework.pageobjects.factory.PageFactory;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Sample start page of the-internet.herokuapp.com
 * <p>
 * Date: 14.05.2020
 * Time: 12:51
 *
 * @author Eric Kubenka
 */
public class StartPage extends Page {

    private FooterPartialPage footer = PageFactory.create(FooterPartialPage.class, this.getWebDriver());

    @Check
    private GuiElement headerElement = new GuiElement(this.getWebDriver(), By.cssSelector("h1.heading"));

    @Check
    private GuiElement navLinkDragAndDrop = new GuiElement(this.getWebDriver(), By.linkText("Drag and Drop"));

    @Check
    private GuiElement navLinkAddAndRemoveElements = new GuiElement(this.getWebDriver(), By.linkText("Add/Remove Elements"));
    @Check
    private GuiElement navLinkTables = new GuiElement(this.getWebDriver(), By.linkText("Sortable Data Tables"));

    @Check
    private GuiElement formAuthentication = new GuiElement(this.getWebDriver(), By.linkText("Form Authentication"));

    @Check
    private GuiElement dropdown = new GuiElement(this.getWebDriver(), By.linkText("Dropdown"));

    @Check
    private GuiElement checkboxes = new GuiElement(this.getWebDriver(), By.linkText("Checkboxes"));

    @Check
    private GuiElement hovers = new GuiElement(this.getWebDriver(), By.linkText("Hovers"));

    @Check
    private GuiElement jsAlerts = new GuiElement(this.getWebDriver(), By.linkText("JavaScript Alerts"));

    @Check
    private GuiElement multipleWindows = new GuiElement(this.getWebDriver(), By.linkText("Multiple Windows"));

    @Check
    private GuiElement nestedFrames = new GuiElement(this.getWebDriver(), By.linkText("Nested Frames"));

    @Check
    private GuiElement textEditor = new GuiElement(this.getWebDriver(), By.linkText("WYSIWYG Editor"));

    @Check
    private GuiElement jQueryUIMenu = new GuiElement(this.getWebDriver(), By.linkText("JQuery UI Menus"));

    public StartPage(WebDriver driver) {
        super(driver);
    }

    public DragAndDropPage goToDragAndDropPage() {

        this.navLinkDragAndDrop.click();
        return PageFactory.create(DragAndDropPage.class, this.getWebDriver());

    }

    public AddAndRemoveElementsPage goToAddAndRemoveElementsPage() {

        this.navLinkAddAndRemoveElements.click();
        return PageFactory.create(AddAndRemoveElementsPage.class, this.getWebDriver());
    }

    public TablePage goToTablePage() {

        this.navLinkTables.click();
        return PageFactory.create(TablePage.class, this.getWebDriver());
    }

    public FormAuthenticationPage goToLoginPage() {
        this.formAuthentication.click();
        return PageFactory.create(FormAuthenticationPage.class, this.getWebDriver());
    }

    public DropdownPage goToDropdownPage() {
        this.dropdown.click();
        return PageFactory.create(DropdownPage.class, this.getWebDriver());
    }

    public CheckboxesPage goToCheckboxesPage(){
        this.checkboxes.click();
        return PageFactory.create(CheckboxesPage.class, this.getWebDriver());
    }

    public HoversPage goToHoversPage() {
        this.hovers.click();
        return PageFactory.create(HoversPage.class, this.getWebDriver());
    }

    public JSAlertsPage goToJSAlertsPage() {
        this.jsAlerts.click();
        return PageFactory.create(JSAlertsPage.class, this.getWebDriver());
    }

    public MultipleWindowsPage goToMultipleWindowsPage() {
        this.multipleWindows.click();
        return PageFactory.create(MultipleWindowsPage.class, this.getWebDriver());
    }

    public NestedFramesPage goToNestedFramesPage() {
        this.nestedFrames.click();
        return PageFactory.create(NestedFramesPage.class, this.getWebDriver());
    }

    public TextEditorPage goToTextEditorPage() {
        this.textEditor.click();
        return PageFactory.create(TextEditorPage.class, this.getWebDriver());
    }

    public JQueryUIMenuPage goToJqueryUIMenuPage() {
        this.jQueryUIMenu.click();
        return PageFactory.create(JQueryUIMenuPage.class, this.getWebDriver());
    }
}
