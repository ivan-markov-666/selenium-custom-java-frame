/**
 * Description:
 * This class shows how to do a Regular and Soft Assertion.
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert; // Import a library for Soft Assertion.
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert; // Import a library for Assertion.
import org.testng.annotations.AfterClass;

public class A15_Assertions_spec {

  private static WebDriver driver;
  private static String baseURL;
  private static SoftAssert softAssert; // Declare a SoftAssert variable.

  /*
   * This example shows how:
   * - To do a Regular Assertion.
   */
  @Test
  public void regularAssert() {
    // First lets make action.
    WebElement radioButton = driver.findElement(By.xpath("//*[@id='yesRadio']/following-sibling::label"));
    radioButton.click();

    // The system shows a message after the action - so we can use this message for assertion.
    WebElement copyText = driver.findElement(By.xpath("//span[@class='text-success']"));
    String actualResult = copyText.getText();
    String expectedResult = "Yes";

    // Make a regular assertion.
    Assert.assertEquals(actualResult, expectedResult);
  }

  /*
   * This example shows how:
   * - To do a Soft Assertion.
   */
  @Test
  public void softAsser() {
    // First lets make first action.
    WebElement radioButton = driver.findElement(By.xpath("//*[@id='yesRadio']/following-sibling::label"));
    radioButton.click();
    // The system shows a message after the action - so we can use this message for assertion.
    WebElement copyText1 = driver.findElement(By.xpath("//span[@class='text-success']"));
    String actualResult1 = copyText1.getText();
    String expectedResult1 = "Yes";

    // Seconds lets make second action.
    WebElement impressiveRadioButton = driver.findElement(By.xpath("//*[@id='impressiveRadio']/following-sibling::label"));
    impressiveRadioButton.click();
    // The system shows a message after the action - so we can use this message for assertion.
    WebElement copyText2 = driver.findElement(By.xpath("//span[@class='text-success']"));
    String actualResult2 = copyText2.getText();
    String expectedResult2 = "Impressive";

    // Make a soft assertion.
    softAssert.assertEquals(actualResult1, expectedResult1);
    softAssert.assertEquals(actualResult2, expectedResult2);
    softAssert.assertAll();
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    driver.get(baseURL + "radio-button");
    softAssert = new SoftAssert(); // Create a constructor for SoftAssert.
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}