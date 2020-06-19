import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
 * WHAT WE WILL COVER IN THIS FORM:
 *  - Handle Enter action
 *  - Use xpath to locate various types of Web Element
 *
 * */

public class POM_ToDoListPage {
    WebDriver driver;

    By newItem = By.xpath("//*[@id='container']//input[@type='text']");
    By plusBtn = By.id("plus-icon");
    By itemList = By.xpath("//*[@id='container']//ul");

    public POM_ToDoListPage(WebDriver driver){
        this.driver = driver;
    }

    public void addNewItem(String name){

        // Check if the text field to add new item is not displayed
        if(!driver.findElement(newItem).getAttribute("style").isEmpty()){

            // -> click +
            driver.findElement(plusBtn).click();

            // -> enter new Item
            driver.findElement(newItem).sendKeys(name);

            // -> Enter
            driver.findElement(newItem).sendKeys(Keys.ENTER);
        }
    }

    public List<WebElement> getItems(){
        List<WebElement> items = driver.findElements(itemList);
        return items;
    }

    public WebElement getItemAtIndex(int index){
        List<WebElement> items = driver.findElements(itemList);
        return items.get(index);
    }

    public void removeItem(int index){
        List<WebElement> items = driver.findElements(itemList);
        try {
            if (items.size() > index){
                items.remove(index);
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public void removeAllItem(){
        List<WebElement> items = driver.findElements(itemList);

        for (int i = 0; i < items.size(); i++){
            items.remove(items.get(i));
        }
    }
}
