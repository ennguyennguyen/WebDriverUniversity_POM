import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseClass {

    private WebDriver driver;
    private WebElement username;
    private WebElement password;
    private WebElement signInBtn;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver.get("https://www.linkedin.com/login?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin");

        String signInTitle = this.driver.getTitle();
        Assert.assertEquals("LinkedIn Login, Sign in | LinkedIn", signInTitle);

        this.username = this.driver.findElement(By.id("username"));
        this.password = this.driver.findElement(By.id("password"));
        this.signInBtn = this.driver.findElement(By.className("btn__primary--large from__button--floating"));
    }

    public void enterUsername(String username){
        this.username.sendKeys(username);
    }

    public void enterPassword(String password){
        this.password.sendKeys(password);
    }

    public void login(){
        this.signInBtn.click();
    }

    public void loginAction(String username, String password){
        this.enterUsername(username);
        this.enterPassword(password);
        this.login();
    }
}
