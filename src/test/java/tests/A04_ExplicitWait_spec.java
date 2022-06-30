/**
 * Description:
 * This class shows how to do Explicit Wait.
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class A04_ExplicitWait_spec {

  private static WebDriver driver;
  private static String baseURL;

  /*
   *  This example shows:
   *  - How to use explicit wait.
   */
  @Test
  public void explicitWait() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40)); // Declare the explicit Wait.
    WebElement fillWithUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
    fillWithUsername.sendKeys("Tester Testerov");
    WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));
    email.sendKeys("tester@test.bg");
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