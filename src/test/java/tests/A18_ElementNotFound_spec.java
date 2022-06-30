/**
 * Description:
 * This class shows how to check if element exist in the DOM tree using custom method "isElemenstPresent()".
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import custom_methods.MainMethods;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
public class A18_ElementNotFound_spec {

  private static WebDriver driver;
  private static String baseURL;
  private static MainMethods mainMethod;

  /*
   * This example shows how:
   * - To check if the element is presented on the page.
   */
  @Test
  public void checkForElement() {
    boolean validResult = mainMethod.isElemenstPresent("(//*[contains(text(),'Valid image')]/following-sibling::img)[2]", "xpath");
    System.out.println("The result is: " + validResult);
    boolean invalidResult = mainMethod.isElemenstPresent("(//*[contains(text(),'Valid image')]/following-sibling::img)[3]", "xpath");
    System.out.println("The result is: " + invalidResult);
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    mainMethod = new MainMethods(driver); // Create a new constructor for MainMethods.
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45)); // Set Implicit Wait.
    driver.get(baseURL + "broken");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}