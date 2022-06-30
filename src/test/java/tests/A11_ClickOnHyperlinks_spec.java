/**
 * Description:
 * This class shows how to click a text with hyperlink using Selenium method "click()".
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
import org.testng.annotations.AfterClass;

public class A11_ClickOnHyperlinks_spec {

  private static WebDriver driver;
  private static String baseURL;

  /*
   * This example shows how:
   * - To click on the regular hyperlink.
   */
  @Test
  public void clickOnSimpleHyperlink() {
    WebElement hyperlink = driver.findElement(By.xpath("//*[@id='simpleLink']"));
    hyperlink.click();
  }

  /*
   * This example shows how:
   * - To click on the dynamic hyperlink.
   */
  @Test
  public void clickOnDynamicHyperlink() {
    WebElement hyperlink = driver.findElement(By.xpath("//*[@id='dynamicLink']"));
    hyperlink.click();
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45)); // Set Implicit Wait.
    driver.get(baseURL + "links");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}