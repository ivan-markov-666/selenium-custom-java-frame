/**
 * Description:
 * This class shows how to work with Modal Windows.
 * This class shows how to use methods created for working with Alert windows.
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
import pom.A23_po;

public class A23_ModalWindowsExample_spec {

  private static WebDriver driver;
  private static MainMethods mainMethods;
  private static Configuration config;
  private static A23_po page;
  private static String baseURL;
  private static String url;

  /*
   * This example shows how:
   * - To automate modal windows.
   */
  @Test
  public void modalWindowsExample() {
    mainMethods.navigateURL(url, page.smallModalButton); // Navigate to the URL address.
    mainMethods.clickMethod(page.smallModalButton, page.smallModalWindowTextContent); // Click on the "Small modal" button.
    System.out.println("The copied text from thye small Modal window is: " + mainMethods.getText(page.smallModalWindowTextContent));
    mainMethods.clickMethod(page.closeButton1, page.largeModalButton);
    mainMethods.clickMethod(page.largeModalButton, page.largeModalWindowTextContent);
    System.out.println("The copied text from thye large Modal window is: " + mainMethods.getText(page.largeModalWindowTextContent));
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    mainMethods = new MainMethods(driver);
    config = new Configuration();
    page = new A23_po(driver);
    baseURL = "https://demoqa.com/";
    url = baseURL + "modal-dialogs";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.timeOut)); // Set Implicit Wait.
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}