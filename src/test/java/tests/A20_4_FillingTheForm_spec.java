/**
 * Description:
 * This class shows how to fill one form - low-middle level of automating with using: dynamically generated data using the java-faker library; actual (existing) email address; POM optimization for locators.
 *  - POM optimization is used.
 *  - Tested data is hardcoded but generated dynamically, and the email address is actual (exists).
 *  - No usage of custom methods.
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
import configuration.Configuration;
import org.testng.annotations.BeforeClass;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import pom.A20_4_po; // Import the POM class.

public class A20_4_FillingTheForm_spec {

  private static WebDriver driver;
  private static A20_4_po locator; // Declare the POM variable.
  private static Faker faker;
  private static String baseURL;
  private static String url;
  private static String clickboardData;
  private static Configuration config;

  /*
   * This example shows how:
   * - To use Page Object Model (POM Optimization) for locators. Locators are not selectors. Make sure that you know the difference! 
   */
  @Test
  public void usingPageObjectModelForLocators() throws UnsupportedFlavorException, IOException {
    // First we need to open email address.
    driver.get("https://10minemail.com/");
    WebElement copyEmail = driver.findElement(locator.copyEmail);
    copyEmail.click();

    // Get data from the click board.
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipboard = toolkit.getSystemClipboard();
    clickboardData = (String) clipboard.getData(DataFlavor.stringFlavor);
    if (clickboardData != null) {
      System.out.println("The copied email address is: " + clickboardData);
    } else {
      System.out.println("It seems that the clickboard is empty.");
    }

    // Lets open new tab and switch the focus of the Selenium to newly opened tab.
    ((JavascriptExecutor) driver).executeScript("window.open()"); // We can open a new tab window using the JavascriptExecutor.
    ArrayList < String > tabs = new ArrayList < String > (driver.getWindowHandles()); // Declare an ArrayList used for different browser tabs.
    driver.switchTo().window(tabs.get(1)); // Switch to the second browser tab.
    driver.get(baseURL + "automation-practice-form"); // Navigate to URL.

    WebElement firstName = driver.findElement(locator.firstName);
    firstName.sendKeys(faker.name().firstName());
    WebElement lastName = driver.findElement(locator.lastName);
    lastName.sendKeys(faker.name().lastName());
    WebElement email = driver.findElement(locator.email);
    email.sendKeys(clickboardData);
    WebElement gender = driver.findElement(locator.gender);
    gender.click();
    WebElement phone = driver.findElement(locator.phone);
    phone.sendKeys(faker.phoneNumber().phoneNumber().replace("-", "").replace(".", "").replace("(", "").replace(")", "").replace(" ", ""));
    WebElement dateOfBirth = driver.findElement(locator.dateOfBirth);
    dateOfBirth.sendKeys(Keys.CONTROL, "a");
    dateOfBirth.sendKeys("09 Sep 1964");
    dateOfBirth.sendKeys(Keys.ESCAPE);
    WebElement hobbie = driver.findElement(locator.hobbie);
    hobbie.click();
    WebElement uploadFile = driver.findElement(locator.uploadFile);
    uploadFile.sendKeys(config.uploadThisFilePath + config.uploadThisFileName);
    WebElement currentAddress = driver.findElement(locator.currentAddress);
    currentAddress.sendKeys(faker.lorem().sentence(12, 2) + Keys.ENTER + faker.lorem().sentence(24, 2));
    WebElement submitButton = driver.findElement(locator.submitButton);
    submitButton.sendKeys(Keys.ENTER);
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    config = new Configuration();
    faker = new Faker();
    baseURL = "https://demoqa.com/";
    locator = new A20_4_po(); // Create a new constructor for POM class.
    url = baseURL + "automation-practice-form";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.timeOut)); // Set Implicit Wait.
    driver.get(url);
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}