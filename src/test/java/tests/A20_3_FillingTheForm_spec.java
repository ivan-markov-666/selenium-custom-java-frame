/**
 * Description:
 * This class shows how to fill one form - low level of automating with using: dynamically generated data using the java-faker library; actual (existing) email address.
 *  - No POM optimization.
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
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class A20_3_FillingTheForm_spec {

  private static WebDriver driver;
  private static Faker faker;
  private static String baseURL;
  private static String url;
  private static String clickboardData;
  private static Configuration config;

  /*
   * This example shows how:
   * - To use real unique email. Opening 10 minutes mail and copy the email address.
   */
  @Test
  public void improoveSecondExample() throws UnsupportedFlavorException, IOException {
    // First we need to open email address.
    driver.get("https://10minemail.com/");
    WebElement copyEmail = driver.findElement(By.xpath("(//button[@data-clipboard-action='copy'])[2]"));
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

    WebElement firstName = driver.findElement(By.xpath("//*[@id='firstName']"));
    firstName.sendKeys(faker.name().firstName());
    WebElement lastName = driver.findElement(By.xpath("//*[@id='lastName']"));
    lastName.sendKeys(faker.name().lastName());
    WebElement email = driver.findElement(By.xpath("//*[@id='userEmail']"));
    email.sendKeys(clickboardData);
    WebElement gender = driver.findElement(By.xpath("//*[@id='gender-radio-1']/following-sibling::label"));
    gender.click();
    WebElement phone = driver.findElement(By.xpath("//*[@id='userNumber']"));
    phone.sendKeys(faker.phoneNumber().phoneNumber().replace("-", "").replace(".", "").replace("(", "").replace(")", "").replace(" ", ""));
    WebElement dateOfBirth = driver.findElement(By.xpath("//*[@id='dateOfBirthInput']"));
    dateOfBirth.sendKeys(Keys.CONTROL, "a");
    dateOfBirth.sendKeys("09 Sep 1964");
    dateOfBirth.sendKeys(Keys.ESCAPE);
    WebElement hobbie = driver.findElement(By.xpath("//*[@id='hobbies-checkbox-1']/following-sibling::label"));
    hobbie.click();
    WebElement uploadFile = driver.findElement(By.xpath("//*[@id='uploadPicture']"));
    uploadFile.sendKeys(config.uploadThisFilePath + config.uploadThisFileName);
    WebElement currentAddress = driver.findElement(By.xpath("//*[@id='currentAddress']"));
    currentAddress.sendKeys(faker.lorem().sentence(12, 2) + Keys.ENTER + faker.lorem().sentence(24, 2));
    WebElement submitButton = driver.findElement(By.xpath("//*[@id='submit']"));
    submitButton.sendKeys(Keys.ENTER);
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    config = new Configuration();
    faker = new Faker();
    baseURL = "https://demoqa.com/";
    url = baseURL + "automation-practice-form";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    driver.get(url);
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}