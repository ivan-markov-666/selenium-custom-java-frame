/**
 * Description:
 * This class shows how to check the page for broken links using Selenium method "isDisplayed()".
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class A17_BrokenLinksImages_spec {

  private static WebDriver driver;
  private static String baseURL;
  public static String status = "passed"; // this is used into the CheckAllImagesFromThePageForBrokenImages annotation

  /*
   * This example shows how:
   * - Tto check if the element is displayed.
   */
  @Test
  public void checkElementIsDisplayed() {
    WebElement image = driver
      .findElement(By.xpath("(//*[contains(text(),'Valid image')]/following-sibling::img)[1]"));
    if (image.isDisplayed()) {
      System.out.println("The element is displayed");
    } else {
      System.out.println("The element is NOT displayed");
    }
  }

  /*
   * This example shows how:
   * - To check if the element getSize height and width are different than 0.
   */
  @Test
  public void checkElementSize() {
    WebElement image = driver
      .findElement(By.xpath("(//*[contains(text(),'Valid image')]/following-sibling::img)[2]"));
    Dimension imageSize = image.getSize();
    System.out.println(imageSize);
    if (imageSize.height != 0 && imageSize.width != 0) {
      System.out.println("The element is displayed");
    } else {
      System.out.println("The element is NOT displayed");
    }
  }

  /*
   * This example shows how:
   * - To check all images from the page (for broken images).
   */
  @Test
  public void checkAllImagesFromThePageForBrokenImages() {
    Integer brokenImageCounter = 0;
    try {
      brokenImageCounter = 0;
      List < WebElement > image_list = driver.findElements(By.tagName("img"));
      // Print the total number of images on the page
      System.out.println("The page contains " + image_list.size() + " images");
      for (WebElement img: image_list) {
        if (img != null) {
          if (img.getAttribute("naturalWidth").equals("0")) {
            System.out.println(img.getAttribute("outerHTML") + " is broken.");
            brokenImageCounter++;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      status = "failed";
      System.out.println(e.getMessage());
    }
    status = "passed";
    System.out.println("The page " + baseURL + "broken" + " has " + brokenImageCounter + " broken images");
  }

  /*
   * This example shows how:
   * - To check if the link is 'broken'.
   */
  @Test
  public void checkIfLinkIsBroken() {
    WebElement brokenLink = driver
      .findElement(By.xpath("//*[contains(text(),'Click Here for Broken Link')]"));
    brokenLink.click();
    try {
      WebElement errorPage = driver.findElement(By.xpath("//div[@class='example']/p"));
      String actualErrorMessage = errorPage.getText();
      String expectedErrorMessage = "This page returned a 500 status code.";
      if (expectedErrorMessage.contains(actualErrorMessage)) {
        System.out.println("The hyperlink is valid ");
      } else {
        System.out.println("The hyperlink is NOT VALID.");
      }
    } catch (Exception e) {

    }
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45)); // Set Implicit Wait.
    driver.get(baseURL + "broken");
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}