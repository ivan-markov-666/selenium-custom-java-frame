/**
 * Description:
 * This class shows how to check a box using Selenium method "click()".
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

public class A09_CheckBox_spec {

  private static WebDriver driver;
  private static String baseURL;

  /*
   * This example shows how:
   * - to click on the check-box input.
   */
  @Test
  public void clickOnCheckBox() {
    WebElement checkBox = driver.findElement(By.xpath("//span[@class='rct-checkbox']/*[local-name() = 'svg']"));
    checkBox.click();
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    driver.get(baseURL + "checkbox");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}