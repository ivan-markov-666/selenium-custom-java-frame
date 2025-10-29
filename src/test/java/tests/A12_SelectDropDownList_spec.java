/**
 * Description:
 * This class shows how to select values from a drop-down list using Selenium methods "selectByVisibleText()", "selectByIndex()", "selectByValue" and "click()".
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */


package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class A12_SelectDropDownList_spec {

  private static WebDriver driver;
  private static String baseURL;

  /*
   * This example shows how:
   * - To select Drop-Down list by text from the list.
   */
  @Test
  public void selectDropDownListByText() {
    Select dropDownList = new Select(driver.findElement(By.xpath("//*[@id='oldSelectMenu']")));
    dropDownList.selectByVisibleText("Yellow");
  }

  /*
   * This example shows how:
   * - To select Drop-Down list by index.
   */
  @Test
  public void selectDropDownListByIndex() {
    Select dropDownList = new Select(driver.findElement(By.xpath("//*[@id='oldSelectMenu']")));
    dropDownList.selectByIndex(5);
  }

  /*
   * This example shows how:
   * to select Drop-Down list by value.
   */
  @Test
  public void selectDropDownListByValue() {
    Select dropDownList = new Select(driver.findElement(By.xpath("//*[@id='oldSelectMenu']")));
    dropDownList.selectByValue("10");
  }

  /*
   * This example shows how:
   * - To select Drop-Down list by clicking on the drop-down list element and then clicking on the value element (two clicks - first click is on the drop-down list, second click is on the drop-down value).
   */
  @Test
  public void selectDropDownListByClickingOnTheElements() {
    WebElement dropDownList = driver.findElement(By.xpath("//*[@id='oldSelectMenu']"));
    dropDownList.click();
    WebElement dropDownListValue = driver.findElement(By.xpath("//*[@id='oldSelectMenu']/option[@value='4']"));
    dropDownListValue.click();
    WebElement ChangeFocus = driver.findElement(By.xpath("//*//*[contains(text(),'Old Style Select Menu')]"));
    ChangeFocus.click();
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45)); // Set Implicit Wait.
    driver.get(baseURL + "select-menu");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}