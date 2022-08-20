package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Represents https://the-internet.herokuapp.com/checkboxes
 * Resources:-
 * https://docs.testerra.io/testerra/1-latest/index.html#_use_check_boxes
 *
 * Date: 20.08.2022
 * Time: 23:12
 *
 * @author Ibtisam Tanveer Khan
 */

public class CheckboxesPage extends Page {
    @Check
    private GuiElement checkbox1 = new GuiElement(this.getWebDriver(), By.xpath("//form[@id='checkboxes']/input[@type='checkbox'][1]"));

    @Check
    private GuiElement checkbox2 = new GuiElement(this.getWebDriver(), By.xpath("//form[@id='checkboxes']/input[@type='checkbox'][2]"));
    @Check
    private GuiElement checkboxes = new GuiElement(this.getWebDriver(), By.xpath("//form[@id='checkboxes']/input[@type='checkbox']"));


    public CheckboxesPage(WebDriver driver) {super(driver);}

    public boolean selectCheckbox(int index) {
        List<GuiElement> checkboxesList = checkboxes.getList();
        checkboxesList.get(index).select();
        if(checkboxesList.get(index).isSelected()) {
            return true;
        }
        return false;
    }

    public boolean deselectCheckbox(int index) {
        List<GuiElement> checkboxesList = checkboxes.getList();
        checkboxesList.get(index).deselect();
        if(checkboxesList.get(index).isSelected()) {
            return true;
        }
        return false;
    }



/*******************************************************************************************
    editCheckbox() will not work because the input tags do not have associated text attributes
    As no text exists therefore we cannot use getText() method and therefore logic had to be changed
 *******************************************************************************************/
/*    public boolean editCheckbox(String checkboxText, String operation) {
        List<GuiElement> checkboxesList = checkboxes.getList();
        System.out.println(checkboxes.getNumberOfFoundElements() + " elements found");
        boolean selected = false;

        for (GuiElement checkbox: checkboxesList) {
            if(checkbox.getText().equals(checkboxText)) {
                if(operation.equals("SELECT")) {
//                    checkbox.select(true);
                    checkbox.select();
                    System.out.println(checkboxText + " selected");
                } else if (operation.equals("DESELECT")) {
//                    checkbox.select(false);
                    checkbox.deselect();
                    System.out.println(checkboxText + " deselected");
                }
                selected = true;
                break;
            }
//            System.out.println(checkbox.getClass().getName());
        }

        return selected;
    }*/


    public ArrayList<String> getSelectedCheckboxes() {
        List<GuiElement> checkboxesList = checkboxes.getList();
        ArrayList<String> selectedCheckboxes = new ArrayList<>();

        for(int i = 0; i < checkboxesList.size(); i++) {
            if(checkboxesList.get(i).isSelected()) {
                int checkboxVal = i + 1;
                selectedCheckboxes.add("checkbox " + checkboxVal) ;
            }

        }
        if(selectedCheckboxes.isEmpty()) {
            System.out.println("No checkbox is selected!!!");
        } else {
            System.out.println(Arrays.toString(selectedCheckboxes.toArray()));
        }
        /*for (GuiElement checkbox: checkboxesList) {
            if(checkbox.isSelected()) {
                selectedCheckboxes.add(checkbox.getText());
                System.out.println(checkbox.getText());
            }
        }

        if(selectedCheckboxes.isEmpty()) {
            System.out.println("No checkbox is selected!!!");
        } else {
            Iterator<String> iterate = selectedCheckboxes.iterator();
            System.out.println("Following checkboxes are selected:-");
            while(iterate.hasNext()) {
                System.out.print(iterate.next());
                System.out.println();
            }
        }*/

        return selectedCheckboxes;
    }
}
