/**
 * Description:
 * This class shows how to fill one form - extremely-high level of automating with: using dynamically generated data using the java-faker library; actual (existing) email address; POM optimization for selectors; custom methods (or only custom methods); Assertions and the testing data is generated dynamically for inputs; code in @BeforeClass and @AfterClass annotations are extended from methods.
 *  - POM optimization is used.
 *  - Tested data is NOT hardcoded, generated dynamically, and the email address is actual (exists).
 *  - Code in the @BeforeClass and @AfterClass annotations is extended from another class.
 *  - Other inputs (drop-down lists, calendar, radio option, checkbox and autocompleted input text elements) are supplied randomly using custom methods. 
 *  - The test case is added like comments in the code. 
 *  - Usage of custom methods.
 *  - Assertion to verify that the test was compleated correctly.
 *  - Declaration of variables inside the test.
 *  
 * This class shows best practices for automating testing a website.
 */

package tests;

import org.testng.annotations.Test;
import base.BaseTestClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pom.A20_7_po;

public class A20_8_FillingTheForm_spec extends BaseTestClass {

  private static A20_7_po page;
  private static String endpoint = "automation-practice-form";

  /*
   * This example shows how:
   * - To fill the form randomly.
   */
  @SuppressWarnings("unused")
  @Test()
  public void finalTestView() {
    // Define the imputed data.
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().safeEmailAddress();
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
    String uploadFile = config.uploadThisFilePath + config.uploadThisFileName;
    String currentAddress = faker.lorem().sentence(24, 5);
    String staetAndCity = null;

    /** 1.	Navigate to: https://demoqa.com/automation-practice-form. */
    mainMethods.navigateURL(url, page.firstName); // Lets open new tab and switch the focus of the Selenium to newly opened tab.
    /** 2.	Fill with correct data into the “First Name” input text element. */
    mainMethods.fillWithText(firstName, page.firstName);
    /** 3.	Fill with correct data into the “Last Name” input text element. */
    mainMethods.fillWithText(lastName, page.lastName);
    /** 4.	Fill with correct data into the “Email” input text element. */
    mainMethods.fillWithText(email, page.email);
    /** 5.	Select random correct option from the “Gender” section. */
    genderExpectedResult = page.selectRandomGender();
    /** 6.	Fill with correct data into the “Mobile Number” input text element. */
    mainMethods.fillWithText(phoneNumner, page.phone);
    /** 7.	Fill with correct data into the “Date of Birth” input text element. */
    page.selectRandomDateOfBirthValue();
    dateOfBirthValue = page.dateOfBirth.getAttribute("value"); // We need to take the filled data into the "Date of Birth" input text element.
    outputDateOfBirth = page.parseMonths(dateOfBirthValue);
    /** 8.	Select random correct data for “Subjects”. */
    subjects.addAll(Arrays.asList(subjectsValues)); // Add the collection to the array.
    subjectsExpectedResult = page.selectRandomSubjects(subjects, 13);
    /** 9.	Check random correct value/s from the “Hobbies” section. */
    hobbiesExpectedResult = page.selectRandomHobbies(hobbiesElements, 2);
    /** 10.	Upload random correct picture file. */
    page.uploadFile.sendKeys(uploadFile);
    /** 11.	Fill with correct data into the “Current Address” input text element. */
    mainMethods.fillWithText(currentAddress, page.currentAddress);
    /** 12.	Select random correct date for “State and City” inputs. */
    staetAndCity = page.selectRandomStateAndCity();
  }

  @BeforeClass
  public void beforeClass() {
    setUp(endpoint);
    page = new A20_7_po(driver);
  }

  @AfterClass
  public void afterClass() {
    terminate();
  }
}