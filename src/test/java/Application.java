import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class Application {
    static WebDriver driver;


    @BeforeClass
    public void setup(){
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    @Test
    public void main(String[] agrs){

        // Login to LinkedIn
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAction("abc", "abc123");

        // Click on Jobs
        driver.findElement(By.id("jobs-tab-icon")).click();

        // Search Job
        JobSearch jobSearch = new JobSearch(driver);
        jobSearch.findJob("automation testing");

        // Apply jobs







    }
}
