/**
 * Description:
 * This class shows a medium test structure with three tests.
 * This class can be executed by suite - A24_2_ClassAnotationsExample.xml file.
 * 
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import configuration.Configuration;
import custom_methods.MainMethods;
import pom.A24_2_po;

public class A25_2_ClassAnotationsExample_spec {

  private static WebDriver driver;
  private static MainMethods mainMethods;
  private static Configuration config;
  private static A24_2_po page;
  private static String baseURL;
  private static String url;

  // Test Example 1.
  @Test
  public void textExample1() {
    mainMethods.navigateURL(url, page.addButton); // Navigate to URL.
  }

  // Test Example 2.
  @Test
  public void textExample2() {
    mainMethods.navigateURL(url, page.addButton); // Navigate to URL.
  }

  // Test Example 3.
  @Test
  public void textExample3() {
    mainMethods.navigateURL(url, page.addButton); // Navigate to URL.
  }

  // This will be executed before first @Test method execution. It will be executed one only time throughout the test case.
  @BeforeClass()
  public void beforeClass() {
    url = baseURL + "webtables";
  }

  // It will run only once, before all tests in the suite are executed.
  @BeforeSuite()
  public void beforeSuite() {
    baseURL = "https://demoqa.com/"; // Declare a base URL value.
  }

  // This will be executed before every @test annotated method.
  @BeforeMethod
  public void beforeTest() {
    driver = new ChromeDriver();
    mainMethods = new MainMethods(driver);
    config = new Configuration();
    page = new A24_2_po(driver);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.timeOut)); // Set Implicit Wait.
  }

  // This will be executed after every @test annotated method.
  @AfterMethod
  public void afterTest() {
    driver.quit();
  }
}