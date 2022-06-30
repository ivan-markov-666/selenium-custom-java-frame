package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import configuration.Configuration;
import custom_methods.MainMethods;
import pom.A22_1_po;

public class A22_1_IframeExamples_spec {

  private static WebDriver driver;
  private static MainMethods mainMethods;
  private static Configuration config;
  private static A22_1_po page;
  private static String baseURL;
  private static String url;

  /*
   * This example shows how:
   * - To select the iFrame by iFrame id.
   */
  @Test
  public void changeToIframeByID() {
    mainMethods.navigateURL(url, page.verifyUrlLoaded); // Navigate to the URL address.
    driver.switchTo().frame("frame1"); // Switch to iFrame by ID.
    String textFromIframe = page.iFrameText1.getText(); // Get the text inside the iFrame to verify that we switched correctly.
    System.out.println("The text from the iFrame is: '" + textFromIframe + "'"); // Print the text on the console log.
  }

  /*
   * This example shows how:
   * - To select the iFrame by switching between main iFrame an default iFrame.
   */
  @Test
  public void changeToIframeByIndex() {
    mainMethods.navigateURL(url, page.verifyUrlLoaded); // Navigate to the URL address.
    int allIframesInThePage = driver.findElements(By.tagName("iframe")).size();
    System.out.println("There are '" + allIframesInThePage + "' iFrames in this page.");
    driver.switchTo().frame(1); // Switch to iFrame by ID.
    String textFromIframe = mainMethods.getText(page.iFrameText1); // Get the text inside the iFrame to verify that we switched correctly.
    System.out.println("The text from the iFrame is: '" + textFromIframe + "'"); // Print the text on the console log.
    driver.switchTo().defaultContent();
    String textFromDefaultPage = mainMethods.getText(page.getThisText); // Get this text outside of the iFrame to verify that we switched correctly from iFrame to the default HTML document.
    System.out.println("The text from default page is: '" + textFromDefaultPage + "'");
  }

  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    mainMethods = new MainMethods(driver);
    config = new Configuration();
    page = new A22_1_po(driver);
    baseURL = "https://demoqa.com/";
    url = baseURL + "frames";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.timeOut)); // Set Implicit Wait.
  }

  @AfterMethod
  public void afterClass() {
    driver.quit();
  }
}