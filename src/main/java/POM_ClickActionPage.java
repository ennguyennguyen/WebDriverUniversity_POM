import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
/*
 * WHAT WE WILL COVER IN THIS FORM:
 *  - Perform action click() in multiple way:
 *      + Use webDriver click()
 *      + Use Javascript to click()
 *      + Use Action builder to click()
 *  - Use xpath to locate various types of alerts
 *
 * */
public class POM_ClickActionPage {
    WebDriver driver;

    By clickBtn1 = By.xpath("//*[@id='button1']");
    By closeBtn1 = By.xpath("//*[@id='myModalClick']//button[@class='btn btn-default']");

    By clickBtn2 = By.cssSelector("span[id='button2']");  // or can use By.cssSelector("#button2");
    By closeBtn2 = By.xpath("//*[@id='myModalJSClick']//button[@class='btn btn-default']");

    By clickBtn3 = By.id("button3");
    By closeBtn3 = By.xpath("//*[@id='myModalMoveClick']//button[@class='btn btn-default']");

    public POM_ClickActionPage(WebDriver driver){
        this.driver = driver;
    }

    // ------ USE NORMAL CLICK() ACTION FROM WEBDRIVER
    public void clickByClick(){
        driver.findElement(clickBtn1).click();
    }

    public void clickClose1(){
        driver.findElement(closeBtn1).click();
    }

    // ------ USER JAVASCRIPT TO PERFORM CLICK() ACTION
    public void clickByJSClick(){
        WebElement btn2 = driver.findElement(clickBtn2);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", btn2);
    }

    public void clickClose2(){
        WebElement close = driver.findElement(closeBtn2);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", close);
    }

    // ------ USE MOVE & ACTION CLICK TO PERFORM CLICK() ACTION
    public void clickByActionClick(){
        WebElement btn3 = driver.findElement(clickBtn3);

        Actions builder = new Actions(driver);
        builder.moveToElement(btn3).click();
    }

    public void clickClose3(){
        WebElement close = driver.findElement(closeBtn3);

        Actions builder = new Actions(driver);
        builder.moveToElement(close).click();
    }
}
