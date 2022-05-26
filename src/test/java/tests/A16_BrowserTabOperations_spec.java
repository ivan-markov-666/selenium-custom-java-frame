/**
 * Description:
 * This class shows how to click on a hyperlink and assert if the web page redirected the user to the expected URL address using regular assertion.
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class A16_BrowserTabOperations_spec {

  private static WebDriver driver;
  private static String baseURL;

  /*
   * This example shows how:
   * - To click on the hyperlink and assert the result.
   */
  @Test
  public void clickOnSimpleHyperlinkAndAssertTheResult() {
    WebElement hyperlink = driver.findElement(By.xpath("//*[@id='simpleLink']"));
    hyperlink.click();
    ArrayList < String > tabs = new ArrayList < String > (driver.getWindowHandles()); // Declare an ArrayList used for different browser tabs.
    driver.switchTo().window(tabs.get(1)); // Switch to the second opened tab.
    String urlNewOpenedTab = driver.getCurrentUrl(); // Get the current page URL.
    String expectedUrl = "https://demoqa.com/";
    Assert.assertEquals(urlNewOpenedTab, expectedUrl);
    driver.close(); // Close the opened tab that is on focus.
    driver.switchTo().window(tabs.get(0)); // Switch to the second browser tab.
  }

  /*
   * This example shows how:
   * - To open new tab and enter URL inside.
   */
  @Test
  public void openNewTabAndEnterURLInside() {
    ((JavascriptExecutor) driver).executeScript("window.open()"); // We can open a new tab window using the JavascriptExecutor.
    ArrayList < String > tabs = new ArrayList < String > (driver.getWindowHandles()); // Declare an ArrayList used for different browser tabs.
    driver.switchTo().window(tabs.get(1)); // Switch to the second browser tab.
    driver.get("http://google.com"); // Navigate to URL.
    driver.close(); // Close the browser tab where the Selenium is focused.
    driver.switchTo().window(tabs.get(0)); // Switch to the first browser tab.
    driver.get("http://google.com"); // Navigate to URL.
  }

  /*
   * This example shows how:
   * - To select all text in the browser (pressing of the CTRL+A keys).
   */
  @Test
  public void selectAllTextFromThePage() {
    WebElement bodyElement = driver.findElement(By.xpath("(//body)[1]"));
    bodyElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
  }

  /*
   * This example shows how:
   * - To select all text in the browser (pressing of the CTRL+A keys) second example.
   */
  @Test
  public void selectAllTextFromThePageExample2() {
    Actions actionProvider = new Actions(driver);
    Action keydown = actionProvider.keyDown(Keys.CONTROL).sendKeys("t").build();
    keydown.perform();
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    driver.get(baseURL + "links");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}