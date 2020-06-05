import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JobSearch {

    private WebDriver driver;

    public JobSearch(WebDriver driver){
        this.driver = driver;
    }

    public void findJob(String jobTitle){
        driver.findElement(By.id("jobs-search-box-keyword-id-ember483")).sendKeys(jobTitle);
        driver.findElement(By.id("jobs-search-box-location-id-ember483")).clear();
        driver.findElement(By.className("jobs-search-box__submit-button artdeco-button artdeco-button--3 ml2")).click();
    }

    public void filterJob(){
        driver.findElement(By.xpath("//div[@id='linkedin-features-facet-values']"));
        // [Nguyen] continue here
    }
}
