/**
 * Description:
 * This class shows how to click a radio button using Selenium method "click()".
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class A10_RadioButton_spec {

  private static WebDriver driver;
  private static String baseURL;

  /*
   * This example shows how:
   * - To click (select) the enabled radio button example 1.
   */
  @Test
  public void clickRadioButton() {
    WebElement radioButton = driver.findElement(By.xpath("//*[@id='yesRadio']/following-sibling::label"));
    radioButton.click();
  }

  /*
   * This example shows how:
   * - To click the enabled radio button example 2.
   */
  @Test
  public void clickRadioButton2() {
    WebElement impressiveRadioButton = driver.findElement(By.xpath("//*[@id='impressiveRadio']/following-sibling::label"));
    impressiveRadioButton.click();
  }

  /*
   * This example shows how:
   * - To check if the radio button is enabled and ready for clicking (selecting) using "isEnabled" method example 1.
   */
  @Test
  public void checkIfRadioButtonIsClicable() {
    WebElement disabledRadioButton = driver.findElement(By.xpath("//*[@id='noRadio']"));
    if (disabledRadioButton.isEnabled()) {
      System.out.println("Checkbox can be clicked.");
      disabledRadioButton.click();
    } else {
      System.out.println("Checkbox is disabled and cannot be clicked.");
    }
  }

  /*
   * This example shows how:
   * - To check if the radio button is enabled and ready for clicking (selecting) using "isEnabled" method example 2.
   */
  @Test
  public void clickRadioButtonAndCheckIfItsClicable() {
    WebElement checkIfRadioButtonClicable = driver.findElement(By.xpath("//*[@id='yesRadio']"));
    WebElement radioButton = driver.findElement(By.xpath("//*[@id='yesRadio']/following-sibling::label"));
    if (checkIfRadioButtonClicable.isEnabled()) {
      System.out.println("Checkbox can be clicked.");
      radioButton.click();
    } else {
      System.out.println("Checkbox is disabled and cannot be clicked.");
    }
  }

  /*
   * This example shows how:
   * - To check if the radio button is enabled and ready for clicking (selecting) by finding the "disabled" attribute in the element.
   */
  @Test
  public void clickRadioButtonAndCheckIfItsClicableVariant2() {
    WebElement checkIfRadioButtonClicable = driver.findElement(By.xpath("//*[@id='yesRadio']"));
    String checkRadioButton = checkIfRadioButtonClicable.getAttribute("disabled");
    WebElement radioButton = driver.findElement(By.xpath("//*[@id='yesRadio']/following-sibling::label"));
    if (checkRadioButton != "disabled") {
      System.out.println("Checkbox can be clicked.");
      radioButton.click();
    } else {
      System.out.println("Checkbox is disabled and cannot be clicked.");
    }
  }

  /*
   * This example shows how:
   * - To check if the radio button is already clicked (selected).
   */
  @Test
  public void checkIfRadioButtonIsALreadySelected() {
    // First we need to click on the radio button to make sure that the button is selected.
    WebElement yesRadioButton = driver.findElement(By.xpath("//*[@id='yesRadio']/following-sibling::label"));
    yesRadioButton.click();
    // Then we need to check if the radio button is already selected.
    WebElement checkRadioButton = driver.findElement(By.xpath("//*[@id='yesRadio']"));
    if (checkRadioButton.isSelected()) {
      System.out.println("Checkbox is already selected.");
    } else {
      System.out.println("Checkbox is not selected and now we can click on it.");
      yesRadioButton.click();
    }
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    driver.get(baseURL + "radio-button");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}