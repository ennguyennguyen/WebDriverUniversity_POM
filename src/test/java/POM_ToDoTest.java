import com.sun.org.glassfish.gmbal.Description;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class POM_ToDoTest {

    /*
    *   [NGUYEN] SOME ISSUES:
    *   - all 2 test cases are ignored without any warning/alert.
    * */

    static WebDriver driver;
    static String url = "http://webdriveruniversity.com/To-Do-List/index.html";
    static POM_ToDoListPage todoPage;
    static String filePath = "Test_Data.xlsx";
    static XSSFSheet sheet;

    static List<String> itemToAdd = new ArrayList<String>();

    @BeforeClass
    public static void setup() throws IOException {
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();

        todoPage = new POM_ToDoListPage(driver);
        driver.get(url);

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // Read file and add into a List for further processing
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook book = new XSSFWorkbook(file);
        sheet = book.getSheet("ToDoItems");
        int countRow = sheet.getLastRowNum();
        for (int i = 1; i <= countRow; i++){
            XSSFRow currRow = sheet.getRow(i);
            try{
                itemToAdd.add(currRow.getCell(0).getStringCellValue());
            }catch (NullPointerException e){
                itemToAdd.add("");
            }
        }

    }

    @Test(priority = 1)
    @Description("Add new Item")
    public void TestAddItem(){

        /*
        * ISSUE:
        *  - todoPage.removeAllItem() doesnt work. Need to check the logic of this method
        *  - todoPage.addNewItem() doesnt work. Need to check the logic of this method
        *  "java.lang.AssertionError: expected [1. new item 1] but found [Go to potion class
                                                                          Buy new robes
                                                                          Practice magic]"
        *
        * */

        // First remove all items if there are
        todoPage.removeAllItem();

        // Iterate through the item list and then add into To do List
        for (int i = 0; i < itemToAdd.size(); i++){
            todoPage.addNewItem(itemToAdd.get(i));
        }

        for (int i = 0; i < todoPage.getItems().size(); i++){
            Assert.assertEquals(todoPage.getItemAtIndex(i).getText(), itemToAdd.get(i));
        }
    }

    @Test(priority = 2)
    @Description("Remove new Item")
    public void RemoveItem(){
        int listSize = todoPage.getItems().size();
        for(int i = 0; i < listSize; i++) {
            WebElement itemAtIndex = todoPage.getItemAtIndex(i);
            todoPage.removeItem(i);

            Assert.assertFalse(todoPage.getItems().contains(itemAtIndex));
        }

    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
