/**
 * Description:
 * This class shows how to fill one form - middle-high level of automating with using: dynamically generated data using the java-faker library; actual (existing) email address; POM optimization for selectors; custom methods (or only custom methods).
 *  - POM optimization is used.
 *  - Tested data is hardcoded but generated dynamically, and the email address is actual (exists).
 *  - Usage of custom methods.
 *  - Other inputs (drop-down lists, calendar, radio option, checkbox and autocompleted input text elements) are NOT supplied randomly using custom methods. 
 *  - The test case is NOT added like comments in the code (in the second test example). 
 *  - Code in the @BeforeClass and @AfterClass annotations not extended from another class.
 *  - No Assertion to verify that the test was compleated correctly.
 *  - No declaration of variables inside the test.
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import configuration.Configuration;
import custom_methods.MainMethods;
import custom_methods.OtherMethods;
import pom.A20_6_po;

public class A20_6_FillingTheForm_spec {

  private static WebDriver driver;
  private static MainMethods mainMethods;
  private static OtherMethods otherMethods;
  private static A20_6_po element;
  private static Faker faker;
  private static String baseURL;
  private static String teMinutesMailUrl;
  private static String url;

  /*
   * This example shows how:
   * - To using few custom method.
   */
  @Test
  public void createMethod() throws UnsupportedFlavorException, IOException {
    // First we need to open email address.
    driver.get(teMinutesMailUrl);
    element.copyEmail.click();

    // We call this method from the class "OtherMethods". We are getting the copied data from the clipboard here.
    String copyEmail = otherMethods.clickboardData();

    // Lets open new tab and switch the focus of the Selenium to newly opened tab.
    ((JavascriptExecutor) driver).executeScript("window.open()"); // We can open a new tab window using the JavascriptExecutor.
    ArrayList < String > tabs = new ArrayList < String > (driver.getWindowHandles()); // Declare an ArrayList used for different browser tabs.
    driver.switchTo().window(tabs.get(1)); // Switch to the second browser tab.
    driver.get(baseURL + "automation-practice-form"); // Navigate to URL.

    element.firstName.sendKeys(faker.name().firstName());
    element.lastName.sendKeys(faker.name().lastName());
    element.email.sendKeys(copyEmail);
    element.gender.click();
    element.phone.sendKeys(faker.phoneNumber().phoneNumber().replace("-", "").replace(".", "").replace("(", "").replace(")", "").replace(" ", ""));
    element.dateOfBirth.sendKeys(Keys.CONTROL, "a");
    element.dateOfBirth.sendKeys("09 Sep 1964");
    element.dateOfBirth.sendKeys(Keys.ESCAPE);
    element.hobbie.click();
    element.uploadFile.sendKeys(Configuration.Files.UPLOAD_FILE_PATH);
    element.currentAddress.sendKeys(faker.lorem().sentence(12, 2) + Keys.ENTER + faker.lorem().sentence(24, 2));
    element.submitButton.sendKeys(Keys.ENTER);
  }

  /*
   * This example shows how:
   * - To using only custom method.
   */
  @Test
  public void createMethods() throws UnsupportedFlavorException, IOException {
    // First we need to open email address.
    mainMethods.navigateURL(teMinutesMailUrl, element.confirmText10minutesMail_Page);
    element.copyEmail.click();
    // We call this method from the class "OtherMethods". We are getting the copied data from the clipboard here.
    String copyEmail = otherMethods.clickboardData();
    // Lets open new tab and switch the focus of the Selenium to newly opened tab.
    mainMethods.openNewBrowserTab(1, baseURL + "automation-practice-form", element.firstName);
    mainMethods.fillWithText(faker.name().firstName(), element.firstName);
    mainMethods.fillWithText(faker.name().lastName(), element.lastName);
    mainMethods.fillWithText(copyEmail, element.email);
    mainMethods.clickCheckBoxRadioButtonWithoutVerify(element.gender);
    mainMethods.fillWithText(otherMethods.randomString("0123456789"), element.phone);
    element.dateOfBirth.sendKeys(Keys.CONTROL, "a");
    mainMethods.fillWithTextWithoutClearing("09 Sep 1964", element.dateOfBirth);
    element.dateOfBirth.sendKeys(Keys.ESCAPE);
    mainMethods.clickCheckBoxRadioButtonWithoutVerify(element.hobbie);
    element.uploadFile.sendKeys(Configuration.Files.UPLOAD_FILE_PATH);
    mainMethods.fillWithText(faker.lorem().sentence(24, 5), element.currentAddress);
    mainMethods.clickEnterButton(element.submitButton, element.confirmTextAutomationPracticeForm_PopUp);
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    mainMethods = new MainMethods(driver);
    otherMethods = new OtherMethods();
    element = new A20_6_po(driver);
    faker = new Faker();
    baseURL = "https://demoqa.com/";
    url = baseURL + "upload-download";
    teMinutesMailUrl = "https://10minemail.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Configuration.Timeouts.IMPLICIT_WAIT)); // Set Implicit Wait.
    driver.get(url);
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}