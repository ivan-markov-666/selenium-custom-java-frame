/**
 * Description:
 * This class shows how to fill one form - low level of automating with using: dynamically generated data using the java-faker library.
 *  - No POM optimization.
 *  - Tested data is hardcoded but generated dynamically.
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
import org.testng.annotations.BeforeMethod;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class A20_2_FillingTheForm_spec {

  private static WebDriver driver;
  private static Faker faker;
  private static String baseURL;
  private String url;

  /*
   * This example shows how
   * - To use Faker library to generate fake data - the data will be different every time.
   */
  @Test
  public void fakerExample() throws UnsupportedFlavorException, IOException {
    WebElement firstName = driver.findElement(By.xpath("//*[@id='firstName']"));
    firstName.sendKeys(faker.name().firstName());
    WebElement lastName = driver.findElement(By.xpath("//*[@id='lastName']"));
    lastName.sendKeys(faker.name().lastName());
    WebElement email = driver.findElement(By.xpath("//*[@id='userEmail']"));
    email.sendKeys(faker.internet().emailAddress());
    WebElement gender = driver.findElement(By.xpath("//*[@id='gender-radio-1']/following-sibling::label"));
    gender.click();
    WebElement phone = driver.findElement(By.xpath("//*[@id='userNumber']"));
    phone.sendKeys(faker.phoneNumber().cellPhone().replace(".", "").replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
    WebElement dateOfBirth = driver.findElement(By.xpath("//*[@id='dateOfBirthInput']"));
    dateOfBirth.sendKeys(Keys.CONTROL, "a");
    dateOfBirth.sendKeys("09 Sep 1964");
    dateOfBirth.sendKeys(Keys.ESCAPE);
    WebElement hobbie = driver.findElement(By.xpath("//*[@id='hobbies-checkbox-1']/following-sibling::label"));
    hobbie.click();
    WebElement uploadFile = driver.findElement(By.xpath("//*[@id='uploadPicture']"));
    uploadFile.sendKeys(Configuration.Files.UPLOAD_FILE_PATH);
    WebElement currentAddress = driver.findElement(By.xpath("//*[@id='currentAddress']"));
    currentAddress.sendKeys(faker.lorem().sentence(12, 2) + Keys.ENTER + faker.lorem().sentence(24, 2));
    WebElement submitButton = driver.findElement(By.xpath("//*[@id='submit']"));
    submitButton.sendKeys(Keys.ENTER);
  }

  /*
   * This example shows how
   * - To use Faker library to generate fake data - the data will be different every time.
   * - To use unique Gmail (using unix timestamp).
   */
  @Test
  public void useUniqueEmail() throws UnsupportedFlavorException, IOException {
    long unixTime = Instant.now().getEpochSecond();
    WebElement firstName = driver.findElement(By.xpath("//*[@id='firstName']"));
    firstName.sendKeys(faker.name().firstName());
    WebElement lastName = driver.findElement(By.xpath("//*[@id='lastName']"));
    lastName.sendKeys(faker.name().lastName());
    WebElement email = driver.findElement(By.xpath("//*[@id='userEmail']"));
    email.sendKeys("fake.email.fake+" + unixTime + "@gmail.com");
    WebElement gender = driver.findElement(By.xpath("//*[@id='gender-radio-1']/following-sibling::label"));
    gender.click();
    WebElement phone = driver.findElement(By.xpath("//*[@id='userNumber']"));
    phone.sendKeys(faker.phoneNumber().cellPhone().replace(".", "").replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
    WebElement dateOfBirth = driver.findElement(By.xpath("//*[@id='dateOfBirthInput']"));
    dateOfBirth.sendKeys(Keys.CONTROL, "a");
    dateOfBirth.sendKeys("09 Sep 1964");
    dateOfBirth.sendKeys(Keys.ESCAPE);
    WebElement hobbie = driver.findElement(By.xpath("//*[@id='hobbies-checkbox-1']/following-sibling::label"));
    hobbie.click();
    WebElement uploadFile = driver.findElement(By.xpath("//*[@id='uploadPicture']"));
    uploadFile.sendKeys(Configuration.Files.UPLOAD_FILE_PATH);
    WebElement currentAddress = driver.findElement(By.xpath("//*[@id='currentAddress']"));
    currentAddress.sendKeys(faker.lorem().sentence(12, 2) + Keys.ENTER + faker.lorem().sentence(24, 2));
    WebElement submitButton = driver.findElement(By.xpath("//*[@id='submit']"));
    submitButton.sendKeys(Keys.ENTER);
  }

  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    faker = new Faker();
    baseURL = "https://demoqa.com/";
    url = baseURL + "automation-practice-form";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Configuration.Timeouts.IMPLICIT_WAIT)); // Set Implicit Wait.
    driver.get(url);
  }

  @AfterMethod
  public void afterMethod() {
    driver.quit();
  }
}