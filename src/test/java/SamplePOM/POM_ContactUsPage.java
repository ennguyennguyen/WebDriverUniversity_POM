package SamplePOM;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POM_ContactUsPage {

    WebDriver driver;

    By firstName = By.name("first_name");
    By lastName = By.name("last_name");
    By email = By.name("email");
    By comments = By.name("message");
    By resetBtn = By.xpath("//div[@id='form_buttons']//input[@value='RESET']");
    By submitBtn = By.xpath("//div[@id='form_buttons']//input[@value='SUBMIT']");

    public POM_ContactUsPage(WebDriver driver){
        this.driver = driver;
    }

    public void getFirstName(String fName){
        driver.findElement(firstName).sendKeys(fName);
    }

    public void getLastName(String lName){
        driver.findElement(lastName).sendKeys(lName);
    }

    public void getEmail(String mail){
        driver.findElement(email).sendKeys(mail);
    }

    public void getComments(String message){
        driver.findElement(comments).sendKeys(message);
    }

    public void clickSubmit(){
        driver.findElement(submitBtn).click();
    }

    public void clickReset(){
        driver.findElement(resetBtn).click();
    }
}
