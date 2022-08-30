package eu.tsystems.mms.testerra.demo.page.theinternet;

import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.*;

/**
 * Represents https://the-internet.herokuapp.com/tables
 * Resources:-
 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
 *
 *
 * Date: 30.08.2022
 * Time: 21:08
 *
 * @author Ibtisam Tanveer Khan
 */

public class DataTablesPage extends Page {

    @Check
    public final GuiElement tableTwo = new GuiElement(this.getWebDriver(), By.xpath("//table[@id='table2']"));

    public GuiElement headersTableTwo = tableTwo.getSubElement(By.xpath("//thead//th[contains(@class, 'header')]"));
    public GuiElement bodyTableTwo = tableTwo.getSubElement(By.xpath("//tbody//tr"));

    public HashMap<String, GuiElement> headersMap = new HashMap<>(); //This Hashmap stores the header name (class) as key, and GUI element as value


    public DataTablesPage (WebDriver driver) {super(driver);}

    public HashMap<String, GuiElement> getTableHeaders() {
        int headersCount = headersTableTwo.getNumberOfFoundElements();
        //System.out.println("No. of headers found: " + headersCount);

        List<GuiElement> headersList = headersTableTwo.getList();

       //Consumer<GuiElement> addHeaderToMap = header -> { headersMap.put(header) };
       //headersList.stream().forEach(addHeaderToMap);

        headersList.forEach((header) -> {
            //System.out.println(header.getText());
            headersMap.put(header.getSubElement(By.xpath("//span")).getAttribute("class"), header);
        });

        //System.out.println("Headers HashMap: " + headersMap.keySet());
        return headersMap;

    }

    public List<HashMap<String, String>> getTableBody() {

        //This list of hashmaps will contain the list of all rows
        //Each row will be the Hashmap of header clsss as key, and data as value
        //The key in the hashmap will be the same as the key in headerMap
        List<HashMap<String, String>> rowsListMap = new ArrayList<>();


        List<GuiElement> rowsList = bodyTableTwo.getList();
        for (GuiElement row: rowsList) {
            GuiElement columns = row.getSubElement(By.xpath("//td"));

            HashMap<String, String> rowsMap = new HashMap<>();

            List<GuiElement> columnsList = columns.getList();
            for (GuiElement column: columnsList) {
                if (!column.getAttribute("class").equalsIgnoreCase("action")) {
                    rowsMap.put(column.getAttribute("class"), column.getText());
                }
            }
            //System.out.println("Rows HashMap: " + rowsMap.entrySet());
            rowsListMap.add(rowsMap);
        }

        //System.out.println(Arrays.toString(rowsListMap.toArray()));
        return rowsListMap;
    }

    public void doSortTableByColumn(String columnHeader) {
        if (headersMap.containsKey(columnHeader)) {
            headersMap.get(columnHeader).click();
        }
    }

    public List<HashMap<String, String>> doSortByColumnComparator(List<HashMap<String, String>> rowsListMap, String columnHeader) {
        /*System.out.println("**************Before Sorting***************");
        System.out.println(Arrays.toString(rowsListMap.toArray()));*/

        //Collections.sort(rowsListMap, (one, two) -> one.get(columnHeader).compareTo(two.get(columnHeader)));
        //Collections.sort(rowsListMap, Comparator.comparing(value -> value.get(columnHeader)));
        if (columnHeader.equals("dues")) {
            rowsListMap.sort(Comparator.comparing(value -> Double.parseDouble(value.get(columnHeader).replace("$", ""))));
            //System.out.println(Arrays.toString(rowsListMap.toArray()));
        } else {
            rowsListMap.sort(Comparator.comparing(one -> one.get(columnHeader)));
        }

        /*System.out.println("**************After Sorting***************");
        System.out.println(Arrays.toString(rowsListMap.toArray()));*/

        return rowsListMap;

    }


}
