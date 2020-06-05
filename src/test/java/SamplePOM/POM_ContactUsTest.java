package SamplePOM;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class POM_ContactUsTest {
    WebDriver driver;
    String url = "http://webdriveruniversity.com/Contact-Us/contactus.html";

    @Test
    public void testSubmit() {
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();

        POM_ContactUsPage cPage = new POM_ContactUsPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(url);

        cPage.getFirstName("Bao");
        cPage.getLastName("Nguyen");
        cPage.getEmail("bnguyen@gmail.com");
        cPage.getComments("comments");
        cPage.clickSubmit();

        String submitSuccessMessage = driver.findElement(By.xpath("//div[@id='contact_reply']//h1")).getText();
        Assert.assertEquals("Thank You for your Message!", submitSuccessMessage);

        driver.navigate().back();
    }

    @Test
    public void testReset() {
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();

        POM_ContactUsPage cPage = new POM_ContactUsPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(url);

        cPage.getFirstName("Bao");
        cPage.getLastName("Nguyen");
        cPage.getEmail("bnguyen@gmail.com");
        cPage.getComments("comments");
        cPage.clickSubmit();

        String submitSuccessMessage = driver.findElement(By.xpath("//div[@id='contact_reply']//h1")).getText();
        Assert.assertEquals("Thank You for your Message!", submitSuccessMessage);

        driver.navigate().back();
    }
}
