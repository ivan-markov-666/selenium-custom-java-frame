/**
 * Description:
 * This class shows how to work with Tables.
 * This class shows how to use methods created for working with Alert windows.
 * 
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import configuration.Configuration;
import custom_methods.MainMethods;
import pom.A24_1_po;

public class A24_1_WorkingWithTables_spec {

  private static WebDriver driver;
  private static MainMethods mainMethods;
  private static Configuration config;
  private static Random random;
  private static A24_1_po page;
  private static SoftAssert softassert;
  private static Faker faker;
  private static String baseURL;
  private static String url;

  /*
   * This example shows how:
   * - To automate tables.
   */
  @Test
  public void tableExample1() {
    // Declare variables.
    String firstNameValue = faker.name().firstName();
    String lastNameValue = faker.name().lastName();
    String emailValue = faker.internet().safeEmailAddress();
    String ageValue = Integer.toString(random.ints(18, 90).findFirst().getAsInt());
    String salaryValue = Integer.toString(random.ints(500, 10000).findFirst().getAsInt());
    String departmentValue = faker.lorem().word();

    /** 1.	Navigate to: https://demoqa.com/webtables . */
    mainMethods.navigateURL(url, page.addButton); // Navigate to URL.
    /** 2.	Press on the “Add” button. */
    mainMethods.clickMethod(page.addButton, page.firstNameInputTextElement); // Press on the "Add" button.
    /** 3.	Fill the form correct. */
    mainMethods.fillWithText(firstNameValue, page.firstNameInputTextElement); // Fill with correct data into the "First Name" input text element.
    mainMethods.fillWithText(lastNameValue, page.lastNameInputTextElement); // Fill with correct data into the "Last Name" input text element.
    mainMethods.fillWithText(emailValue, page.emailInputTextElement); // Fill with correct data into the "Email" input text element.
    mainMethods.fillWithText(ageValue, page.ageInputTextElement); // Fill with correct data into the "Age" input text element.
    mainMethods.fillWithText(salaryValue, page.salaryInputTextElement); // Fill with correct data into the "Salary" input text element.
    mainMethods.fillWithText(departmentValue, page.departmentInputTextElement); // Fill with correct data into the "Department" input text element.
    /** 4.	Press on the “Submit” button. */
    mainMethods.clickMethod(page.submitButton, page.addButton); // Press the "Submit" button.
    /** 5.	Verify that the saved data is showed into the report. */
    int totalPageOfTheTable = Integer.parseInt(mainMethods.getText(page.checkHowManyPagesContainsTheForm)); // Get all pages that the table report contains.
    for (int j = 1; j <= totalPageOfTheTable; j++) // Make a loop. The loop is starting from "1", because the first page of the table is starting from "1".
    {
      for (int i = 0; i <= page.allTableCells.size(); i++) { // Create a loop to check every one table cell.
        if (page.allTableCells.get(i).getText().contains(firstNameValue)) { // check if some of the table cells contains firstNameValue.
          softassert.assertEquals(page.allTableCells.get(i).getText(), firstNameValue); // Make a soft assert to make sure that the system create correct record.
          softassert.assertEquals(page.allTableCells.get(i + 1).getText(), lastNameValue); // Make a soft assert to make sure that the system create correct record.
          softassert.assertEquals(page.allTableCells.get(i + 2).getText(), ageValue); // Make a soft assert to make sure that the system create correct record.
          softassert.assertEquals(page.allTableCells.get(i + 3).getText(), emailValue); // Make a soft assert to make sure that the system create correct record.
          softassert.assertEquals(page.allTableCells.get(i + 4).getText(), salaryValue); // Make a soft assert to make sure that the system create correct record.
          softassert.assertEquals(page.allTableCells.get(i + 5).getText(), departmentValue); // Make a soft assert to make sure that the system create correct record.
          softassert.assertAll(); // Assert now all soft asserts.
          break; // stop the execution of the loop.
        } else { // if no one table cell doesn't contains firstNameValue...
          if (!page.nextButton.getAttribute("disabled").contains("true")) // if the "Next" button is enabled.
          {
            mainMethods.clickMethod(page.nextButton, page.nextButton); // click on the "Next" button. 
          }
        }
      }
    }
  }

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    mainMethods = new MainMethods(driver);
    config = new Configuration();
    page = new A24_1_po(driver);
    random = new Random();
    softassert = new SoftAssert();
    faker = new Faker();
    baseURL = "https://demoqa.com/";
    url = baseURL + "webtables";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.timeOut)); // Set Implicit Wait.
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}