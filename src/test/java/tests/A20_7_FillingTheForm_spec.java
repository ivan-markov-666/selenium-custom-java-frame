/**
 * Description:
 * This class shows how to fill one form - high (first test example) and very-high (second test example) level of automating with using: dynamically generated data using the Java Faker library; actual (existing) email address; POM optimization for selectors; custom methods (or only custom methods); Assertions; the testing data is generated dynamically for inputs.
 *  - POM optimisation is used.
 *  - Tested data is NOT hardcoded, generated dynamically, and the email address is actual (exists).
 *  - Usage of custom methods.
 *  - Other inputs (drop-down lists, calendar, radio option, checkbox and autocompleted input text elements) are supplied randomly using custom methods. 
 *  - The test case is added as comments in the code (in the second test example). 
 *  - Code in the @BeforeClass and @AfterClass annotations not extended from another class.
 *  - Assertion to verify that the test was completed correctly.
 *  - Declaration of variables inside the test.
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like a system property.
 * 		 To make this example work, you need to add the web driver files (chromedriver.exe, geckodriver.exe) as environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import configuration.Configuration;
import custom_methods.MainMethods;
import custom_methods.OtherMethods;
import custom_methods.WaitTypes;
import pom.A20_7_po;

public class A20_7_FillingTheForm_spec {

  private static WebDriver driver;
  private static MainMethods mainMethods;
  private static OtherMethods otherMethods;
  private static SoftAssert softassert;
  private static A20_7_po page;
  private static Faker faker;
  private static WaitTypes wait;
  private static String baseURL;
  private static String teMinutesMailUrl;
  private static String url;

  /*
   * This example shows how:
   * - To use hard assertion of inputted and outputted data.
   */
  @Test
  public void asserts() throws UnsupportedFlavorException, IOException {
    // Define the imputed data.
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String phoneNumner = otherMethods.randomString("0123456789");
    String InputDateOfBirth = "09 Sep 1964";
    String uploadFile = Configuration.Files.UPLOAD_FILE_PATH;
    String text = faker.lorem().sentence(24, 5);
    String genderExpectedResult = null;
    String dateOfBirthValue = null;
    String OutputDateOfBirth = null;
    String[] subjectsValues = {
      "Maths",
      "Accounting",
      "Physics",
      "Chemistry",
      "Computer Science",
      "Commerce",
      "Economics",
      "Social Studies",
      "Civics",
      "Biology",
      "Hindi",
      "English",
      "Arts",
      "History"
    }; // Create a new collection with 'Subjects' values.
    String subjectsExpectedResult = null;
    WebElement[] hobbiesElements = {
      page.hobbie1,
      page.hobbie2,
      page.hobbie3
    }; // Create a new collection with 'Hobbies' values.
    String hobbiesExpectedResult = null;
    String cityActualResult = null;

    // First, we need to open the email address.
    mainMethods.navigateURL(teMinutesMailUrl, page.confirmText10minutesMail_Page);
    page.copyEmail.click();
    // We call this method from the class "OtherMethods". We are getting the copied data from the clipboard here.
    String copyEmail = otherMethods.clickboardData();
    //Let's open a new tab and switch the focus of Selenium to the newly opened tab.
    mainMethods.openNewBrowserTab(1, url, page.firstName);
    mainMethods.fillWithText(firstName, page.firstName);
    mainMethods.fillWithText(lastName, page.lastName);
    mainMethods.fillWithText(copyEmail, page.email);
    genderExpectedResult = page.selectRandomGender();
    mainMethods.fillWithText(phoneNumner, page.phone);
    page.dateOfBirth.sendKeys(Keys.CONTROL, "a");
    mainMethods.fillWithTextWithoutClearing(InputDateOfBirth, page.dateOfBirth);
    dateOfBirthValue = page.dateOfBirth.getAttribute("value"); // We need to take the filled data into the "Date of Birth" input text element.
    OutputDateOfBirth = page.parseMonths(dateOfBirthValue);
    page.dateOfBirth.sendKeys(Keys.ESCAPE);
    List < String > subjects = new ArrayList < String > (); // Create a new array.
    subjects.addAll(Arrays.asList(subjectsValues)); // Add the collection to the array.
    subjectsExpectedResult = page.selectRandomSubjects(subjects, 13);
    hobbiesExpectedResult = page.selectRandomHobbies(hobbiesElements, 2);
    page.uploadFile.sendKeys(uploadFile);
    String stateActualResult = page.selectRandomStateAndCityDropDownValues(page.stateOrCityDropDownListValues, page.stateDropDownList, page.stateDropDownListActualResultValue, "no");
    if (stateActualResult != null) {
      cityActualResult = " " + page.selectRandomStateAndCityDropDownValues(page.stateOrCityDropDownListValues, page.cityDropDownList, page.cityDropDownListActualResultValue, "yes");
    } else {
      stateActualResult = "";
      cityActualResult = "";
    }
    mainMethods.fillWithText(text, page.currentAddress);
    mainMethods.clickEnterButton(page.submitButton, page.assertStudentName);

    // Hard Assertions.
    Assert.assertEquals(page.assertStudentName.getText(), firstName + " " + lastName);
    Assert.assertEquals(page.assertStudentEmail.getText(), copyEmail);
    Assert.assertEquals(page.assertGender.getText(), genderExpectedResult);
    Assert.assertEquals(page.assertMobile.getText(), phoneNumner);
    Assert.assertEquals(page.assertDateOfBirth.getText(), OutputDateOfBirth);
    Assert.assertEquals(page.assertSubjects.getText(), subjectsExpectedResult);
    Assert.assertEquals(page.assertHobbies.getText(), hobbiesExpectedResult);
    Assert.assertEquals(page.assertPicture.getText(), Configuration.Files.UPLOAD_FILE_NAME);
    Assert.assertEquals(page.assertAddress.getText(), text);
    Assert.assertEquals(page.assertStateNadCity.getText(), stateActualResult + cityActualResult);
  }

  /*
   * This example shows:
   * - The final view of the test.
   * - The test now is looking perfect.
   */
  @Test()
  public void finalTestView() throws UnsupportedFlavorException, IOException {
    // Define the imputed data.
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String copyEmail = null;
    String genderExpectedResult = null;
    String phoneNumner = otherMethods.randomString("0123456789");
    String dateOfBirthValue = null;
    String outputDateOfBirth = null;
    List < String > subjects = new ArrayList < String > (); // Create a new array for subjects.
    String[] subjectsValues = {
      "Maths",
      "Accounting",
      "Physics",
      "Chemistry",
      "Computer Science",
      "Commerce",
      "Economics",
      "Social Studies",
      "Civics",
      "Biology",
      "Hindi",
      "English",
      "Arts",
      "History"
    }; // Create a new collection with 'Subjects' values.
    String subjectsExpectedResult = null;
    WebElement[] hobbiesElements = {
      page.hobbie1,
      page.hobbie2,
      page.hobbie3
    }; // Create a new collection with 'Hobbies' values.
    String hobbiesExpectedResult = null;
    String uploadFile = Configuration.Files.UPLOAD_FILE_PATH;
    String currentAddress = faker.lorem().sentence(24, 5);
    String staetAndCity = null;

    /** 1.	Navigate to: https://10minemail.com. */
    mainMethods.navigateURL(teMinutesMailUrl, page.confirmText10minutesMail_Page);
    /** 2.	Copy the email. */
    page.copyEmail.click();
    /** 3.	Navigate to: https://demoqa.com/automation-practice-form. */
    mainMethods.openNewBrowserTab(1, url, page.firstName); //Let's open a new tab and switch the focus of Selenium to the newly opened tab.
    /** 4.	Fill in with correct data into the “First Name” input text element. */
    mainMethods.fillWithText(firstName, page.firstName);
    /** 5.	Fill in with correct data into the “Last Name” input text element. */
    mainMethods.fillWithText(lastName, page.lastName);
    /** 6.	Fill in with correct data into the “Email” input text element. */
    copyEmail = otherMethods.clickboardData(); // We call this method from the class "OtherMethods". We are getting the copied data from the clipboard here.
    mainMethods.fillWithText(copyEmail, page.email);
    /** 7.	Select a random correct option from the “Gender” section. */
    genderExpectedResult = page.selectRandomGender();
    /** 8.	Fill in with correct data into the “Mobile Number” input text element. */
    mainMethods.fillWithText(phoneNumner, page.phone);
    /** 9.	Fill in with correct data into the “Date of Birth” input text element. */
    page.selectRandomDateOfBirthValue();
    dateOfBirthValue = page.dateOfBirth.getAttribute("value"); // We need to take the filled data into the "Date of Birth" input text element.
    outputDateOfBirth = page.parseMonths(dateOfBirthValue);
    /** 10.	Select a random correct date for “Subjects”. */
    subjects.addAll(Arrays.asList(subjectsValues)); // Add the collection to the array.
    subjectsExpectedResult = page.selectRandomSubjects(subjects, 13);
    /** 11.	Check random correct value/s from the “Hobbies” section. */
    hobbiesExpectedResult = page.selectRandomHobbies(hobbiesElements, 2);
    /** 12.	Upload a random correct picture file. */
    page.uploadFile.sendKeys(uploadFile);
    /** 13.	Fill in with correct data into the “Current Address” input text element. */
    mainMethods.fillWithText(currentAddress, page.currentAddress);
    /** 14.	Select a random correct date for “State and City” inputs. */
    staetAndCity = page.selectRandomStateAndCity();
    /** 15.	Press the “Submit” button. */
    mainMethods.clickEnterButton(page.submitButton, page.assertStudentName);

    wait.staticWait(5); // We are using a static wait because the automation test sometimes fails. So we need this to make the test more stable. This is not a good practice, but sometimes we are not able to wait differently.

    /** 16.	Verify that the imputed date are saved correctly into the system. */
    /** Assertion */
    softassert.assertEquals(page.assertStudentName.getText(), firstName + " " + lastName);
    softassert.assertEquals(page.assertStudentEmail.getText(), copyEmail);
    softassert.assertEquals(page.assertGender.getText(), genderExpectedResult);
    softassert.assertEquals(page.assertMobile.getText(), phoneNumner);
    softassert.assertEquals(page.assertDateOfBirth.getText(), outputDateOfBirth);
    softassert.assertEquals(page.assertSubjects.getText(), subjectsExpectedResult);
    softassert.assertEquals(page.assertHobbies.getText(), hobbiesExpectedResult);
    softassert.assertEquals(page.assertPicture.getText(), Configuration.Files.UPLOAD_FILE_NAME);
    softassert.assertEquals(page.assertAddress.getText(), currentAddress);
    softassert.assertEquals(page.assertStateNadCity.getText(), staetAndCity);
    softassert.assertAll();
  }

  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    mainMethods = new MainMethods(driver);
    otherMethods = new OtherMethods();
    softassert = new SoftAssert();
    wait = new WaitTypes(driver);
    page = new A20_7_po(driver);
    faker = new Faker();
    baseURL = "https://demoqa.com/";
    url = baseURL + "automation-practice-form";
    teMinutesMailUrl = "https://10minemail.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Configuration.Timeouts.IMPLICIT_WAIT)); // Set Implicit Wait.
  }

  @AfterMethod
  public void afterClass() {
    driver.quit();
  }
}
