/**
 * Description:
 * This class shows how to upload file using Selenium method "sendKeys()".
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import configuration.Configuration; // Import the configuration class. This class contains all kinds of configurations used in this automation framework.

public class A14_1_UploadFile_spec {

  private static WebDriver driver;
  private static String baseURL;
  private static Configuration config; // Declare a configuration variable.

  /*
   * This example shows how:
   * - To upload file.
   */
  @Test
  public void uploadFile() {
    WebElement uploadFile = driver.findElement(By.xpath("//*[@id='uploadFile']"));
    uploadFile.sendKeys(config.uploadThisFilePath + config.uploadThisFileName);
  }

  @BeforeClass
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    config = new Configuration(); // Create a constructor for configuration class.
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45)); // Set Implicit Wait.
    driver.get(baseURL + "upload-download");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}