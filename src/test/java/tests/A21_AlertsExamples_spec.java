/**
 * Description:
 * This class shows how to work with Alert windows.
 * This class shows how to use methods created for working with Alert windows.
 * 
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */
package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import configuration.Configuration;
import custom_methods.MainMethods;
import pom.A21_po;

public class A21_AlertsExamples_spec {

  private static WebDriver driver; // Declare a WebDriver.
  private static MainMethods mainMethods; // Declare a MainMethods.
  private static A21_po page; // Declare a page object model.
  private static String baseURL;
  private static String url;

  /*
   * This example shows how:
   * - To accept the alert window message (pressing on the "OK" button of the alert message).
   */
  @Test
  public void acceptTheAlertByAcceptMethod() {
    mainMethods.navigateURL(url, page.clickMeButton1); // Navigate to the URL address.
    page.clickMeButton1.click(); // Click on the "Click Me" button.
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.Timeouts.IMPLICIT_WAIT)); // Declare a wait.
    wait.until(ExpectedConditions.alertIsPresent()); // Wait until the pop-up is present.
    String getThePopUpText = driver.switchTo().alert().getText(); // Get the text of the pop-up window.
    System.out.println("The Alert message is: '" + getThePopUpText + "'"); // Print the text from the pop-up to the console log.
    driver.switchTo().alert().accept(); // Press on the "OK" button of the pop-up window.
  }

  /*
   * This example shows how:
   * - To decline the alert window message (pressing on the "CANCEL" button of the alert message).
   */
  @Test
  public void cancelTheAlertByDismissMethod() {
    mainMethods.navigateURL(url, page.clickMeButton1); // Navigate to the URL address.
    page.clickMeButton1.click(); // Click on the "Click Me" button.
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.Timeouts.IMPLICIT_WAIT)); // Declare a wait.
    wait.until(ExpectedConditions.alertIsPresent()); // Wait until the pop-up is present.
    driver.switchTo().alert().dismiss(); // Press on the "Cancel" button of the pop-up window.
  }

  /*
   * This example shows how:
   * - To filling with text on the alert message input text.
   * - To accept the alert window message (pressing on the "OK" button of the alert message).
   */
  @Test
  public void sendTextTOAlert() {
    mainMethods.navigateURL(url, page.clickMeButton2); // Navigate to the URL address.
    page.clickMeButton2.click(); // Click on the "Click Me" button.
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.Timeouts.IMPLICIT_WAIT)); // Declare a wait.
    wait.until(ExpectedConditions.alertIsPresent()); // Wait until the pop-up is present.
    driver.switchTo().alert().sendKeys("testing Text"); // Fill with the text into the alert window.
    driver.switchTo().alert().accept(); // Press on the "OK" button of the pop-up window.
  }

  /*
   * This example shows how:
   * - To using a unique method created for accepting the alert window message (pressing on the "OK" button of the alert message).
   */
  @Test
  public void acceptAlertWithMethod() {
    mainMethods.navigateURL(url, page.clickMeButton1); // Navigate to the URL address.
    mainMethods.acceptTheAlert(page.clickMeButton1, "Do you confirm action?"); // Execute the method to accept the alert.
  }

  /*
   * This example shows how:
   * - To using a unique method created for declining the alert window message (pressing on the "CANCEL" button of the alert message).
   */
  @Test
  public void dismissAlertWithMethod() {
    mainMethods.navigateURL(url, page.clickMeButton1); // Navigate to the URL address.
    mainMethods.dismissTheAlert(page.clickMeButton1, "Do you confirm action?"); // Execute the method to dismiss the alert.
  }

  /*
   * This example shows how:
   * - To using a unique method created for with text on the alert message input text and accepting the alert window message (pressing on the "OK" button of the alert message).
   */
  @Test
  public void fillTextInAlertWithMethod() {
    mainMethods.navigateURL(url, page.clickMeButton2); // Navigate to the URL address.
    mainMethods.fillWithTextInToTheAlert(page.clickMeButton2, "Please enter your name", "Fill with testing text..."); // Execute the method to fill with text into the alert and accept it.
  }

  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    mainMethods = new MainMethods(driver);
    page = new A21_po(driver);
    baseURL = "https://demoqa.com/";
    url = baseURL + "alerts";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Configuration.Timeouts.IMPLICIT_WAIT)); // Set Implicit Wait.
  }

  @AfterMethod
  public void afterClass() {
    driver.quit();
  }
}