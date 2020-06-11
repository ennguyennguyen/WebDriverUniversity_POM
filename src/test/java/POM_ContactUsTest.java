import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class POM_ContactUsTest {
    static WebDriver driver;
    static String url = "http://webdriveruniversity.com/Contact-Us/contactus.html";
    static POM_ContactUsPage cPage;
    static XSSFSheet contactSheet;

    @BeforeClass
    public static void setup() throws IOException {
        // Create Chrome Instance
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();

        // Create ContactUsPage Instance and get the url
        cPage = new POM_ContactUsPage(driver);
        driver.get(url);

        // Set wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Read file
        FileInputStream file = new FileInputStream("Test_Data.xlsx");
        XSSFWorkbook contactWorkbook = new XSSFWorkbook(file);
        contactSheet = contactWorkbook.getSheet("ContactUs");
    }

    @Test
    public void testSubmit() {
        int countRow = contactSheet.getLastRowNum();

        for (int row = 1; row < countRow; row++) {
            System.out.println("Iteration: " + row);
            XSSFRow currentRow = contactSheet.getRow(row);
            try {
                String firstName = currentRow.getCell(0).getStringCellValue();
                String lastName = currentRow.getCell(1).getStringCellValue();
                String email = currentRow.getCell(2).getStringCellValue();
                String comment = currentRow.getCell(3).getStringCellValue();

                cPage.getFirstName(firstName);
                cPage.getLastName(lastName);
                cPage.getEmail(email);
                cPage.getComments(comment);

            } catch (NullPointerException ex) {
                cPage.getFirstName("");
                cPage.getLastName("");
                cPage.getEmail("");
                cPage.getComments("");
            }
            cPage.clickSubmit();

            String submittedPageTitle = driver.getTitle();

            if (!submittedPageTitle.equals("Contact form handler")) {
                System.out.printf("Test case #%s status: Passed \n", row);
            } else {
                System.out.printf("Test case #%s status: Failed \n", row);
            }
            driver.navigate().back();

            cPage.clearAllField();
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