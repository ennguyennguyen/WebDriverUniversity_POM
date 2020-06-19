import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class POM_LoginTest {

    static WebDriver driver;
    String url = "http://webdriveruniversity.com/Login-Portal/index.html";
    static String filePath = "Test_Data.xlsx";

    static POM_LoginPage lPage;

    static XSSFSheet sheet;

    @BeforeClass
    public static void setup() throws IOException {
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        lPage = new POM_LoginPage(driver);

        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet("Login");
    }

    @Test
    public void TestLogin() throws InterruptedException, IOException {
        driver.get(url);

        int countRow = sheet.getLastRowNum();

        for(int row = 1; row <= countRow; row++){
            XSSFRow curr_row = sheet.getRow(row);

            try{
                String username = curr_row.getCell(0).getStringCellValue();
                String password = curr_row.getCell(1).getStringCellValue();

                System.out.printf("Iteration %d: Username: %s and password %s", row, username, password);

                lPage.fillUsername(username);
                lPage.fillPassword(password);

            } catch(NullPointerException ex){
                lPage.fillUsername("");
                lPage.fillPassword("");
            }

            lPage.clickLogin();

            Thread.sleep(1000);

            String message = driver.switchTo().alert().getText();
            Assert.assertTrue(message.equals("validation failed"));
            driver.switchTo().alert().accept();
        }
    }

    @AfterClass
    public static void teardown(){
        if (driver != null){
            driver.close();
            driver.quit();
        }
    }
}
