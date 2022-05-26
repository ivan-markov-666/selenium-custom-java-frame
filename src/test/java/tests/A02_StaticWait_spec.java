/**
 * Description:
 * This class shows how to do Static Wait.
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class A02_StaticWait_spec {

  private static WebDriver driver;
  private static String baseURL;

  /*
   *  This example shows:
   *  - how use static wait.
   */
  @Test
  public void staticWait() {

    // Static Wait.
    try {
      System.out.println("Before Pause");
      Thread.sleep(5000);
      System.out.println("After Pause");
    } catch (InterruptedException ie) {
    	System.out.println("Something went wrong. An error has been caught: " + ie);
    }

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
    driver.get(baseURL + "text-box");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}