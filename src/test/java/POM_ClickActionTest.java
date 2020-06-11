import com.sun.org.glassfish.gmbal.Description;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class POM_ClickActionTest {

    static WebDriver driver;
    static POM_ClickActionPage caPage;
    static String url = "http://webdriveruniversity.com/Click-Buttons/index.html";

    By alert1 = By.xpath("//*[@id='myModalClick']//h4");
    By alert2 = By.xpath("//*[@id='myModalJSClick']//h4");
    By alert3 = By.xpath("//*[@id='myModalMoveClick']//h4");

    @BeforeClass
    public static void setup(){
        // Create Chrome Instance
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();

        // Create ContactUsPage Instance and get the url
        caPage = new POM_ClickActionPage(driver);
        driver.get(url);

        // Set wait
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    @Description("Click the 1st button. Use the xpath locator ")
    public void TestClickByClick(){
        caPage.clickByClick();

        String message1 = driver.findElement(alert1).getAttribute("innerText");
        Assert.assertEquals( "Congratulations!", message1);

        caPage.clickClose1();
    }

    @Test(priority = 2)
    @Description("Click the 2nd button. Use the CSS locator")
    public void TestClickByJSClick(){
        caPage.clickByJSClick();

        String message2 = driver.findElement(alert2).getAttribute("innerText");
        Assert.assertEquals( "It’s that Easy!!  Well I think it is.....", message2);

        caPage.clickClose2();
    }

    @Test(priority = 3)
    @Description("Click the 3rd button. Use any locator. In this case, use the Action Move & Click")
    public void TestClickByActionClick(){
        caPage.clickByActionClick();

        String message2 = driver.findElement(alert3).getAttribute("innerText");
        Assert.assertTrue( message2.contains("Well done!"));

        caPage.clickClose3();
    }

    @AfterClass
    public static void teardown(){
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }


}
