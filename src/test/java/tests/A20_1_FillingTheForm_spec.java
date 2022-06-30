/**
 * Description:
 * This class shows how to fill one form - lame level of automating.
 *  - No POM optimization.
 *  - Tested data is hardcoded.
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
import org.testng.annotations.BeforeClass;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import configuration.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class A20_1_FillingTheForm_spec {

  private WebDriver driver;
  private String baseURL;
  private String url;
  private Configuration config;

  /*
   * This example shows how:
   * - To fill a form without using POM design pattern.
   */
  @Test
  public void fillingTheFormWithCorrectDateFirstExample() throws UnsupportedFlavorException, IOException {
    WebElement firstName = driver.findElement(By.xpath("//*[@id='firstName']"));
    firstName.sendKeys("John");
    WebElement lastName = driver.findElement(By.xpath("//*[@id='lastName']"));
    lastName.sendKeys("Doe");
    WebElement email = driver.findElement(By.xpath("//*[@id='userEmail']"));
    email.sendKeys("testingfakeemail@testingewr.te");
    WebElement gender = driver.findElement(By.xpath("//*[@id='gender-radio-1']/following-sibling::label"));
    gender.click();
    WebElement phone = driver.findElement(By.xpath("//*[@id='userNumber']"));
    phone.sendKeys("2342323423");
    WebElement dateOfBirth = driver.findElement(By.xpath("//*[@id='dateOfBirthInput']"));
    dateOfBirth.sendKeys(Keys.CONTROL, "a");
    dateOfBirth.sendKeys("09 Sep 1964");
    dateOfBirth.sendKeys(Keys.ESCAPE);
    WebElement hobbie = driver.findElement(By.xpath("//*[@id='hobbies-checkbox-1']/following-sibling::label"));
    hobbie.click();
    WebElement uploadFile = driver.findElement(By.xpath("//*[@id='uploadPicture']"));
    uploadFile.sendKeys(config.uploadThisFilePath + config.uploadThisFileName);
    WebElement currentAddress = driver.findElement(By.xpath("//*[@id='currentAddress']"));
    currentAddress.sendKeys("test" + Keys.ENTER + "test2");
    WebElement submitButton = driver.findElement(By.xpath("//*[@id='submit']"));
    submitButton.sendKeys(Keys.ENTER);
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    config = new Configuration();
    baseURL = "https://demoqa.com/";
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