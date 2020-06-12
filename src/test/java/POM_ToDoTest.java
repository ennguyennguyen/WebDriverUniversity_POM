import com.sun.org.glassfish.gmbal.Description;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class POM_ToDoTest {

    /*
    *   [NGUYEN] SOME ISSUES:
    *   - See row 66
    *   - all 2 test cases are ignored without any warning/alert. WTF???
    * */

    static private WebDriver driver;
    static private String url = "http://webdriveruniversity.com/To-Do-List/index.html";
    static private POM_ToDoListPage todoPage;
    static private String filePath = "Test_Data.xlsx";
    static private XSSFSheet sheet;

    @BeforeClass
    public void setup() throws IOException {
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();

        todoPage = new POM_ToDoListPage(driver);
        driver.get(url);

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook book = new XSSFWorkbook(file);
        sheet = book.getSheet("ToDoItems");
    }

    @Test(priority = 1)
    @Description("Add new Item")
    public void TestAddItem(String item){
        int countRow = sheet.getLastRowNum();
        String anItem = "";

        for (int i = 1; i < countRow; i++){
            XSSFRow curr_row = sheet.getRow(i);
            try{
                anItem = curr_row.getCell(0).getStringCellValue();
                todoPage.addNewItem(anItem);
            }catch (NullPointerException ex){
                todoPage.addNewItem("");
            }
        }

        //[NGUYEN] try this first, i assume we cannot assert 2 web elements, should change to a String
//        for(WebElement i: todoPage.getItems()){
//            if (i.equals(anItem)){
//                Assert.assertEquals(anItem, i);
//            }
//        }
    }

    @Test(priority = 2)
    @Description("Remove new Item")
    public void RemoveItem(int index){
        WebElement itemAtIndex = todoPage.getItemAtIndex(index);
        todoPage.removeItem(index);

        Assert.assertFalse(todoPage.getItems().contains(itemAtIndex));
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
