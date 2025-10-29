/**
 * Description:
 * This class shows a basic test structure with two tests.
 * 
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import configuration.Configuration;
import custom_methods.MainMethods;
import pom.A25_1_po;

public class A25_1_ClassAnotationsExample_spec {

  private static WebDriver driver;
  private static MainMethods mainMethods;
  private static A25_1_po page;
  private static String baseURL;
  private static String url;

  // Test Method 1
  @Test
  public void testMethod1() {
    mainMethods.navigateURL(url, page.fullNameInputTextElement);
  }

  // Test Method 2
  @Test
  public void testMethod2() {
    driver.get("https://google.com");
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    mainMethods = new MainMethods(driver);
    page = new A25_1_po(driver);
    baseURL = "https://demoqa.com/";
    url = baseURL + "text-box";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Configuration.Timeouts.IMPLICIT_WAIT)); // Set Implicit Wait.
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}