/**
 * Description:
 * This class shows the structure of the spec (automation) class.
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

// 01. Import the package.
package tests;

// 02. Import the libraries and used classes.
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

// 03. Declare the class where all annotations and methods will be placed.
public class A01_StructureOfAutomationTest_spec {

  // 04. Declare the global variables.
  private static WebDriver driver;
  private static String baseURL;

  // 05. @Test annotations will be placed here.
  /*
   * This example shows:
   * - How to automate without any static or dynamic waits.
   */
  @Test
  public void searchUsingGoogle() {
    // Add the automation (Selenium) code here.
    WebElement acceptButton = driver.findElement(By.id("L2AGLb"));
    acceptButton.click();
    WebElement searchInputTextElement = driver.findElement(By.name("q"));
    searchInputTextElement.sendKeys("Quality Assurance");
  }

  // 06. Other annotations will be placed here.
  // @BeforeClass annotated method runs before executing test methods 
  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver(); // Create a new instance of Chrome driver.
    baseURL = "https://www.google.com/"; // Declare a base URL value.
    driver.manage().window().maximize(); // Set opened browser to 100% width and 100% high.
    driver.get(baseURL); // Navigate to base URL + page.
  }

  // @AfterClass annotated method will be executed after all the test methods of a current class have been invoked.
  @AfterClass
  public void afterClass() {
    driver.quit(); // Close the web driver when the test is done. This Selenium method will close the browser automatically.
  }
}