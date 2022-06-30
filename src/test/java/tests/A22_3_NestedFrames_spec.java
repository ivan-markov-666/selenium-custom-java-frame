/**
 * Description:
 * This class shows how to work with Nested iFrames.
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
import pom.A22_3_po;

public class A22_3_NestedFrames_spec {

  private static WebDriver driver;
  private static MainMethods mainMethods;
  private static Configuration config;
  private static A22_3_po page;
  private static String baseURL;
  private static String url;

  /*
   * This example shows how:
   * - To select the iFrame inside iFrame (nested iFrame).
   */
  @Test
  public void nestedFramesExample() {
    mainMethods.navigateURL(url, page.verifyElement1); // Navigate to the URL address.
    mainMethods.changeToIframeById("frame1", page.bodyLocator);
    System.out.println("The copied text from 1st iFrame is: " + mainMethods.getText(page.bodyLocator));
    mainMethods.changeToIframeByIndex(0, page.paragrahLocator);
    System.out.println("The copied text from 2nd iFrame is: " + mainMethods.getText(page.paragrahLocator));
    mainMethods.changeFromIframeToDefaultHTML(page.defaultHtmlTextLocator);
    System.out.println("The copied text from default HTML is: " + mainMethods.getText(page.defaultHtmlTextLocator));
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    mainMethods = new MainMethods(driver);
    config = new Configuration();
    page = new A22_3_po(driver);
    baseURL = "https://demoqa.com/";
    url = baseURL + "nestedframes";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.timeOut)); // Set Implicit Wait.
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}