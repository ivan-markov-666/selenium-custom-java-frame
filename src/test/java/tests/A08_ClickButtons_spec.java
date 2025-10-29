/**
 * Description:
 * This class shows how to click a button using Selenium method "click()".
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions; // Import the Action library. With this library, we can simulate pressing buttons from the keyboard.
import org.testng.annotations.AfterClass;

public class A08_ClickButtons_spec {

  private static WebDriver driver;
  private static String baseURL;
  private static Actions actions; // Declare an Action variable.

  /*
   * This example shows how:
   * - To click left mouse button once.
   */
  @Test
  public void clickButton() {
    WebElement clickButton = driver.findElement(By.xpath("//button[starts-with(text(),'Click Me')]"));
    clickButton.click();
  }

  /*
   * This example shows how:
   * - To click Enter button.
   */
  @Test
  public void clickButtonUsingSendKeys() {
    WebElement clickButton = driver.findElement(By.xpath("//button[starts-with(text(),'Click Me')]"));
    clickButton.sendKeys(Keys.ENTER);
  }

  /*
   * This example shows how:
   * - To click right mouse button once.
   */
  @Test
  public void rightClickButton() {
    WebElement rightClickButton = driver.findElement(By.xpath("//*[@id='rightClickBtn']"));
    actions.contextClick(rightClickButton).perform();
  }

  /*
   * This example shows how:
   * - To click left mouse button twice.
   */
  @Test
  public void doubleClickButton() {
    WebElement doubleClickButton = driver.findElement(By.xpath("//*[@id='doubleClickBtn']"));
    actions.doubleClick(doubleClickButton).perform();
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    actions = new Actions(driver); // Create a new instance of Action.
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45)); // Set Implicit Wait.
    driver.get(baseURL + "buttons");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}