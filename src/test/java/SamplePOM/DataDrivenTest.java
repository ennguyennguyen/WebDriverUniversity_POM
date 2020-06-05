package SamplePOM;

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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class DataDrivenTest {

    static WebDriver driver;
    @BeforeClass
    public static void setup(){
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @org.junit.Test
    public void TestLogin() throws InterruptedException, IOException {
        driver.get("http://webdriveruniversity.com/Login-Portal/index.html");

        FileInputStream file = new FileInputStream("LoginCredential.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int countRow = sheet.getLastRowNum();

        for(int row = 1; row <= countRow; row++){
            XSSFRow curr_row = sheet.getRow(row);
            String username = curr_row.getCell(0).getStringCellValue();
            String password = curr_row.getCell(1).getStringCellValue();

            System.out.printf("Iteration %d: Username: %s and password %s", row, username, password);
            System.out.println();

            driver.findElement(By.id("text")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("login-button")).click();

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
