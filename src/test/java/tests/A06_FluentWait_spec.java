/**
 * Description:
 * This class shows how to do Fluent Wait.
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;

public class A06_FluentWait_spec {

  private static WebDriver driver;
  private static String baseURL;

  /*
   * This example shows how:
   * - To use fluent wait.
   */
  @SuppressWarnings("deprecation")
  @Test
  public void fluentWait() {

    // Declare a Fluent wait.
    Wait < WebDriver > wait = new FluentWait < WebDriver > (driver).withTimeout(30, TimeUnit.MILLISECONDS)
      .pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

    // Use the Fluent wait.
    WebElement fullName = wait.until(new Function < WebDriver, WebElement > () {
      public WebElement apply(WebDriver driver) {
        return driver.findElement(By.id("userName"));
      }
    });
    fullName.sendKeys("Tester Testerov");
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.get(baseURL + "text-box");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}