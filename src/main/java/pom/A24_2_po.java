package pom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;

import custom_methods.MainMethods;

public class A24_2_po {

  public WebDriver driver;
  private MainMethods mainMethods = new MainMethods(driver);
  private SoftAssert softassert = new SoftAssert();
  private Faker faker = new Faker();
  private Random random = new Random();

  public A24_2_po(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // we need this to be declare here. Without this we will not be able to use the @FindBy annotation.
  }

  // Declare a selectors here.
  @FindBy(id = "addNewRecordButton")
  public WebElement addButton;

  @FindBy(id = "firstName")
  public WebElement firstNameInputTextElement;

  @FindBy(id = "lastName")
  public WebElement lastNameInputTextElement;

  @FindBy(id = "userEmail")
  public WebElement emailInputTextElement;

  @FindBy(id = "age")
  public WebElement ageInputTextElement;

  @FindBy(id = "salary")
  public WebElement salaryInputTextElement;

  @FindBy(id = "department")
  public WebElement departmentInputTextElement;

  @FindBy(id = "submit")
  public WebElement submitButton;

  @FindBy(xpath = "//div[@class='rt-td']")
  public List < WebElement > allTableCells;

  @FindBy(xpath = "//*[contains(text(),'Next')]")
  public WebElement nextButton;

  @FindBy(xpath = "//*[contains(text(),'Preview')]")
  public WebElement previewButton;

  @FindBy(xpath = "//span[@class='-totalPages']")
  public WebElement checkHowManyPagesContainsTheForm;

  @FindBy(id = "searchBox")
  public WebElement searchInputTextElement;

  @FindBy(xpath = "//span[@title='Edit']")
  public List < WebElement > allEditElements;

  @FindBy(xpath = "//span[@title='Delete']")
  public List < WebElement > allDeleteElements;

  @FindBy(xpath = "//input[@aria-label='jump to page']")
  public WebElement navigateToDifferentReportPage;

  // Individual methods will be created here. The methods will be used only for the current test class.

  /** 
   * The method is used for filling the report form with data. This should create a new record into the report table.
   * 
   * @param firstNameValue			- provide a string. The string should be used for filling the "First Name" input text element.
   * @param lastNameValue			- provide a string. The string should be used for filling the "Last Name" input text element.
   * @param emailValue				- provide a string. The string should be used for filling the "Email" input text element.
   * @param ageValue				- provide a string. The string should be used for filling the "Age" input text element, so the string should contains only integers.
   * @param salaryValue				- provide a string. The string should be used for filling the "Salary" input text element,, so the string should contains only numbers.
   * @param departmentValue			- provide a string. The string should be used for filling the "departmentValue" input text element.
   */
  public void fillDataIntoTheTableForm(String firstNameValue, String lastNameValue, String emailValue, String ageValue, String salaryValue, String departmentValue) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      mainMethods.fillWithText(firstNameValue, firstNameInputTextElement); // Fill with correct data into the "First Name" input text element.
      mainMethods.fillWithText(lastNameValue, lastNameInputTextElement); // Fill with correct data into the "Last Name" input text element.
      mainMethods.fillWithText(emailValue, emailInputTextElement); // Fill with correct data into the "Email" input text element.
      mainMethods.fillWithText(ageValue, ageInputTextElement); // Fill with correct data into the "Age" input text element.
      mainMethods.fillWithText(salaryValue, salaryInputTextElement); // Fill with correct data into the "Salary" input text element.
      mainMethods.fillWithText(departmentValue, departmentInputTextElement); // Fill with correct data into the "Department" input text element.
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
  }

  /**
   * The method is used for verify added data to the report.
   * 
   * @param firstNameValue			- provide a string. The string should be used for filling the "First Name" input text element. The value will be used for expected result.
   * @param lastNameValue			- provide a string. The string should be used for filling the "Last Name" input text element. The value will be used for expected result.
   * @param emailValue				- provide a string. The string should be used for filling the "Email" input text element. The value will be used for expected result.
   * @param ageValue				- provide a string. The string should be used for filling the "Age" input text element, so the string should contains only integers. The value will be used for expected result.
   * @param salaryValue				- provide a string. The string should be used for filling the "Salary" input text element,, so the string should contains only numbers. The value will be used for expected result.
   * @param departmentValue			- provide a string. The string should be used for filling the "departmentValue" input text element. The value will be used for expected result.
   */
  public void verifyDataInTable(String firstNameValue, String lastNameValue, String emailValue, String ageValue, String salaryValue, String departmentValue) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      int totalPageOfTheTable = Integer.parseInt(mainMethods.getText(checkHowManyPagesContainsTheForm)); // Get all pages that the table report contains.
      for (int j = 1; j <= totalPageOfTheTable; j++) // Make a loop. The loop is starting from "1", because the first page of the table is starting from "1".
      {
        for (int i = 0; i < allTableCells.size(); i++) { // Create a loop to check every one table cell.
          if (allTableCells.get(i).getText().contains(firstNameValue)) { // check if some of the table cells contains firstNameValue.
            softassert.assertEquals(allTableCells.get(i).getText(), firstNameValue); // Make a soft assert to make sure that the system create correct record.
            softassert.assertEquals(allTableCells.get(i + 1).getText(), lastNameValue); // Make a soft assert to make sure that the system create correct record.
            softassert.assertEquals(allTableCells.get(i + 2).getText(), ageValue); // Make a soft assert to make sure that the system create correct record.
            softassert.assertEquals(allTableCells.get(i + 3).getText(), emailValue); // Make a soft assert to make sure that the system create correct record.
            softassert.assertEquals(allTableCells.get(i + 4).getText(), salaryValue); // Make a soft assert to make sure that the system create correct record.
            softassert.assertEquals(allTableCells.get(i + 5).getText(), departmentValue); // Make a soft assert to make sure that the system create correct record.
            softassert.assertAll(); // Assert now all soft asserts.
            break; // stop the execution of the loop.
          } else { // if no one table cell doesn't contains firstNameValue...
            if (nextButton.isEnabled()) // if the "Next" button is enabled.
            {
              mainMethods.clickMethod(nextButton, nextButton); // click on the "Next" button.
            }
          }
        }
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
  }

  /**
   * The method is used for verify searched data in the report. 
   * 
   * @param verifyData			- provide a string. The string should be used for filling the data into the search input text element. 
   * @param cellValue			- provide a cell number. This value is used for division the numbers. If the result is integer - the cell should be inspected by getText method.
   * @return					- return the flag value.
   */
  public String verifyOneDataInTable(String verifyData, int cellValue) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      String flag = "false"; // Create a flag, to verify that the cell check was successful.
      int totalPageOfTheTable = Integer.parseInt(mainMethods.getText(checkHowManyPagesContainsTheForm)); // Get all pages that the table report contains.
      for (int j = 1; j <= totalPageOfTheTable; j++) // Make a loop. The loop is starting from "1", because the first page of the table is starting from "1".
      {
        for (int i = 0; i < allTableCells.size(); i++) { // Create a loop to check every one table cell.

          if (i / cellValue == Math.ceil((double) i / (double) cellValue) || i == cellValue - 6) { // Check only cells that contains verifyData value.
            if (allTableCells.get(i).getText().contains(verifyData)) { // check if some of the table cells contains verifyData.
              flag = "true"; // At this stage the flag will be set to "true" value.
              break; // stop the execution of the loop.
            } else { // if no one table cell doesn't contains firstNameValue...
              if (!nextButton.getAttribute("disabled").contains("true")) // if the "Next" button is enabled.
              {
                mainMethods.clickMethod(nextButton, nextButton); // click on the "Next" button.
              }
            }
          }
        }
      }
      if (flag == "false") { // If the flag is still "false" we will return "false" value.
        return flag; // Return the flag.
      } else { // else the flag value is changed to "true" and we will return "true" value.
        return flag; // Return the flag.
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null;
  }

  /**
   * This method is used to verify that the search engine is working as expected.
   * 
   * @param firstNameValue			- provide a string. The string should be used for filling the "First Name" input text element. The value will be used for expected result.
   * @param lastNameValue			- provide a string. The string should be used for filling the "Last Name" input text element. The value will be used for expected result.
   * @param ageValue				- provide a string. The string should be used for filling the "Age" input text element, so the string should contains only integers. The value will be used for expected result.
   * @param emailValue				- provide a string. The string should be used for filling the "Email" input text element. The value will be used for expected result.
   * @param salaryValue				- provide a string. The string should be used for filling the "Salary" input text element,, so the string should contains only numbers. The value will be used for expected result.
   * @param departmentValue			- provide a string. The string should be used for filling the "departmentValue" input text element. The value will be used for expected result.
   * @param flag				- provide a string. The string should be used for flag value. If the flag value is set to 'false' - we are testing the search functionality.
   * 								  If the flag value is set to 'true' - we are testing the delete functionality.
   */
  public void verifySearchFunctionalityMainMethod(String firstNameValue, String lastNameValue, String ageValue, String emailValue, String salaryValue, String departmentValue, String flag) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      String flagValue;
      String[] allUsedReportValues = {
        firstNameValue,
        lastNameValue,
        ageValue,
        emailValue,
        salaryValue,
        departmentValue
      }; // Create a new collection.
      List < String > allUsedReportValuesList = new ArrayList < String > (); // Create a new array.
      allUsedReportValuesList.addAll(Arrays.asList(allUsedReportValues)); // Add the collection to the array.
      for (int i = 0; i < allUsedReportValuesList.size(); i++) { // Make a loop. The loop begin from 0, and will grow to the all elements from the list allUsedReportValuesList.
        if (flag == "false" || flag == "true" && i == 0) {
          int j = 6; // Declare the value that we need to enter into the method verifyOneDataInCalendar.
          mainMethods.fillWithText(allUsedReportValuesList.get(i), searchInputTextElement); // Fill with value into the search input text element.
          flagValue = verifyOneDataInTable(allUsedReportValuesList.get(i), j + i); // Execute the method verifyOneDataInCalendar.
          searchInputTextElement.clear(); // We need to clear the search input text element.
          searchInputTextElement.sendKeys("1"); // We are adding this here, because the system have some strange behavior.
          searchInputTextElement.sendKeys(Keys.BACK_SPACE); // We are adding this here, because the system have some strange behavior.
          if (flagValue == flag) { // If the flagValue is "false" - we have an error.
            if (flag == "false") {
              System.out.print("ERROR! It seems that the automation test can't find newly added data with value '" + allUsedReportValuesList.get(i) + "' anywhere in the report." + "\n"); // Print the message into the console log.
            } else if (flag == "true") {
              System.out.print("ERROR! It seems that the automation test doesnt delete the last record from the report table!"); // Print the message into the console log. 
            }
          }
        }
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
  }

  /** The method is used to click() on the last possible WebElement from the list. 
   * 
   * @param elements		- provide a list with elements. The elements should be used for click() the last element from the list.
   */
  public void editLastRecord(List < WebElement > elements) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      int totalPageOfTheTable = Integer.parseInt(mainMethods.getText(checkHowManyPagesContainsTheForm)); // Get all pages that the table report contains.
      if (totalPageOfTheTable != 1) { // Navigate to the last possible report page.
        mainMethods.fillWithText(mainMethods.getText(checkHowManyPagesContainsTheForm), navigateToDifferentReportPage); // Fill with last page into the input text element.
        checkHowManyPagesContainsTheForm.sendKeys(Keys.ENTER); // Send ENTER key to the page.
      }
      elements.get(elements.size() - 1).click(); // Click on the last edit button.
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
  }

  /** 
   * The method is used to automate execution of steps 2-5 from the Test Case.
   * 
   * @param number			- provide an integer number. The number will be used for - how many times the steps 2-5 will be executed.
   */
  public void steps2to5InMethod(int number) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      for (int i = 0; i < number; i++) {
        // Declare variables.
        String firstNameValue = faker.name().firstName();
        String lastNameValue = faker.name().lastName();
        String emailValue = faker.internet().safeEmailAddress();
        String ageValue = Integer.toString(random.ints(18, 90).findFirst().getAsInt());
        String salaryValue = Integer.toString(random.ints(500, 10000).findFirst().getAsInt());
        String departmentValue = faker.lorem().word();
        /** 2.	Press on the “Add” button. */
        mainMethods.clickMethod(addButton, firstNameInputTextElement); // Press on the "Add" button.
        /** 3.	Fill the form correct. */
        fillDataIntoTheTableForm(firstNameValue, lastNameValue, emailValue, ageValue, salaryValue, departmentValue);
        /** 4.	Press on the “Submit” button. */
        mainMethods.clickMethod(submitButton, addButton); // Press the "Submit" button.
        /** 5.	Verify that the saved data is showed into the report. */
        verifyDataInTable(firstNameValue, lastNameValue, emailValue, ageValue, salaryValue, departmentValue);
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
  }

}