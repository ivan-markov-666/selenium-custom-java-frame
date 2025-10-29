/**
 * Description:
 * This class is used to set the configuration methods that we are using in the spec (test) classes.
 * If you want to turn on the messages in the methods, set the value to 'on' for the 'MESSAGES' variable in the 'configuration.Configuration' class. 
 */

package base;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;
import com.github.javafaker.Faker;
import configuration.Configuration;
import custom_methods.MainMethods;
import custom_methods.OtherMethods;
import custom_methods.WaitTypes;
import dataDrivenTesting_methods.ImportDataFromExcel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestClass {

  public static WebDriver driver; // Declare a WebDriver.
  public static MainMethods mainMethods; // Declare MainMethods.
  public static OtherMethods otherMethods; // Declare OtherMethods.
  public static WaitTypes wait; // Declare wait.
  public static String url; // Declare url.
  public static SoftAssert softassert; // Declare soft assert.
  public static Faker faker; // Declare faker.
  public static Random random; // Declare random.
  public static ImportDataFromExcel excel; // Declare DDT (Data-Driven Testing).
  public static Actions action; // Declare actions.

  /**
   * This method is used to declare all needed code that will be executed in the @BeforeMethod annotation.
   * 
   * @param endpoint - provide the endpoint where we want to navigate. If no endpoint is needed, use 'null' value.
   */
  public void setUp(String endpoint) {
    otherMethods = new OtherMethods(); // Declare a constructor for OtherMethods.
    softassert = new SoftAssert(); // Declare an instance for softAssert.
    faker = new Faker(); // Declare an instance for Faker.
    random = new Random(); // Declare an instance for Random.
    
    /** This if-else statement is used for configuration about usage of system property or environment variable for browser drivers. */
    if ("yes".equals(Configuration.Browser.USE_SYSTEM_PROPERTY)) {
      otherMethods.messagesMethod("Message: We are using System Property for setting up the browser driver path.");
      
      /** This if-else statement is used for setting up the system property for browser driver. */
      if ("firefox".equals(Configuration.Browser.DEFAULT)) {
        System.setProperty("webdriver.gecko.driver", Configuration.Paths.DRIVERS + Configuration.Browser.FIREFOX_DRIVER_NAME);
      } else if ("chrome".equals(Configuration.Browser.DEFAULT)) {
        System.setProperty("webdriver.chrome.driver", Configuration.Paths.DRIVERS + Configuration.Browser.CHROME_DRIVER_NAME);
      } else {
        System.out.println("ERROR! Unsupported browser: '" + Configuration.Browser.DEFAULT + "'. Please set 'DEFAULT' variable to 'firefox' or 'chrome' in the Configuration.Browser class.");
      }
    } else if ("no".equals(Configuration.Browser.USE_SYSTEM_PROPERTY)) {
      otherMethods.messagesMethod("Message: We are using WebDriverManager for automatic browser driver management.");
      otherMethods.messagesMethod("Message: WebDriverManager will automatically download and setup the correct driver version.");
    } else {
      System.out.println("ERROR! Invalid value for 'USE_SYSTEM_PROPERTY': '" + Configuration.Browser.USE_SYSTEM_PROPERTY + "'. Please set it to 'yes' or 'no' in the Configuration.Browser class.");
    }
    
    /** This if-else statement is used for starting the browser driver with WebDriverManager automatic setup. */
    if ("firefox".equals(Configuration.Browser.DEFAULT)) {
      WebDriverManager.firefoxdriver().setup(); // Automatically setup the correct Firefox driver version.
      driver = new FirefoxDriver(); // Create a new instance of Firefox driver. 
    } else if ("chrome".equals(Configuration.Browser.DEFAULT)) {
      WebDriverManager.chromedriver().setup(); // Automatically setup the correct Chrome driver version.
      driver = new ChromeDriver(); // Create a new instance of Chrome driver.
    } else {
      System.out.println("ERROR! Unsupported browser: '" + Configuration.Browser.DEFAULT + "'. Please set 'DEFAULT' variable to 'firefox' or 'chrome' in the Configuration.Browser class.");
    }
    
    mainMethods = new MainMethods(driver); // Declare a constructor for MainMethods.
    wait = new WaitTypes(driver); // Declare a constructor for WaitTypes.
    action = new Actions(driver); // Declare a constructor for Actions.
    driver.manage().window().maximize(); // Set opened browser to 100% width and 100% height.
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Configuration.Timeouts.IMPLICIT_WAIT));
    url = Configuration.URLs.BASE + endpoint;
    otherMethods.messagesMethod("Message: The 'setUp' method was executed. This method contains all needed code that will be executed in the @BeforeMethod annotation.");
  }

  /**
   * This method is used to read data from Excel file (data-driven testing).
   * 
   * @param excelFileName - provide the name of the used Excel file. If Excel file is not used, provide 'null' value.
   * @param excelSheetName - provide the name of the used Excel sheet. If Excel file is not used, provide 'null' value.
   */
  public void dataDriven(String excelFileName, String excelSheetName) {
    excel = new ImportDataFromExcel(); // Create a new constructor for ImportDataFromExcel.
    excel.setExcelFile(Configuration.Paths.EXCEL_DATA + excelFileName, excelSheetName);
    otherMethods.messagesMethod("Message: The 'dataDriven' method was executed. The automation read data from file '" + Configuration.Paths.EXCEL_DATA + excelFileName + "' and read data from sheet '" + excelSheetName + "'.");
  }

  /**
   * This method is used to declare all needed code that will be executed in the @AfterMethod annotation.
   */
  public void terminate() {
    driver.quit(); // Close the browser when the test is done.
    otherMethods.messagesMethod("Message: The 'terminate' method was executed. This method contains all needed code that will be executed in the @AfterMethod annotation.");
  }
}