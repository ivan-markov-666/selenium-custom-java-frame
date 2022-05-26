/**
 * Description:
 * This class shows how to do Explicit Wait - called from a method.
 *  - You can call any other wait method from the WaitTypes class similarly.
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;
import org.testng.annotations.Test;

import custom_methods.WaitTypes;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class A05_ExplicitWaitOutside_spec {

  private static WebDriver driver;
  private static WaitTypes wait; // Declare a wait variable.
  private static String baseURL;

  /*
   * This example shows how:
   * - To use explicit wait form outside (by using methods for explicit wait).
   */
  @Test
  public void explicitWait() {
    WebElement fillWithUsername = wait.waitVisibilityOfElementLocatedBy(By.id("userName"), 40); // We are using the explicit wait like a method.
    fillWithUsername.sendKeys("Tester Testerov");
    WebElement email = wait.waitVisibilityOfElementLocatedBy(By.id("userEmail"), 40); // We are using the explicit wait like a method.
    email.sendKeys("tester@test.bg");
    wait.waitElementToBeClickableBy(By.id("submit"), 40); // We are using the explicit wait like a method.
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    wait = new WaitTypes(driver); // Create a constructor for waits.
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.get(baseURL + "text-box");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}