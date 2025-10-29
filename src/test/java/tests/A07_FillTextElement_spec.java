/**
 * Description:
 * This class shows how to fill text into an input text element using Selenium method "sendKeys()".
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

public class A07_FillTextElement_spec {

  private static WebDriver driver;
  private static String baseURL;

  /* 
   * This example shows how:
   * - To fill with text into the input text element.
   */
  @Test
  public void imputTextElementWithText() {
    WebElement fullName = driver.findElement(By.id("userName"));
    fullName.sendKeys("Tester Testerov");
    WebElement email = driver.findElement(By.id("userEmail"));
    email.sendKeys("testertesterov@mail.com");
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45)); // Set Implicit Wait.
    driver.get(baseURL + "text-box");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}