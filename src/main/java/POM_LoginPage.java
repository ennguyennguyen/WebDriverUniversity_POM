import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/*
 * WHAT WE WILL COVER IN THIS FORM:
 *  - Read from file
 *  - Use xpath to locate the web element
 *  - Handle null input data
 * */
public class POM_LoginPage {
    WebDriver driver;

    By username = By.id("text");
    By password = By.id("password");
    By loginBtn = By.id("login-button");

    public POM_LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillUsername(String uname){
        driver.findElement(username).sendKeys(uname);
    }

    public void fillPassword(String pwd){
        driver.findElement(password).sendKeys(pwd);
    }
    public void clickLogin(){
        driver.findElement(loginBtn).click();
    }
    public void clearAllField(){
        driver.findElement(username).clear();
        driver.findElement(password).clear();
    }

}
