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
import org.openqa.selenium.support.ui.Select;

import custom_methods.OtherMethods;

public class A20_7_po {

  public WebDriver driver;
  private OtherMethods otherMethods = new OtherMethods(); // Create a constructor for OtherMethods class.
  private Random random = new Random(); // Declare a new instance of Random.

  public A20_7_po(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // we need this to be declare here. Without this we will not be able to use the @FindBy annotation.
  }

  // Declare a selectors here.
  @FindBy(xpath = "(//button[@data-clipboard-action='copy'])[2]")
  public WebElement copyEmail;

  @FindBy(xpath = "//div[@class='temp-emailbox']/h2")
  public WebElement confirmText10minutesMail_Page;

  @FindBy(id = "firstName")
  public WebElement firstName;

  @FindBy(id = "lastName")
  public WebElement lastName;

  @FindBy(id = "userEmail")
  public WebElement email;

  @FindBy(xpath = "//*[@id='gender-radio-1']/following-sibling::label")
  public WebElement genderMale;

  @FindBy(xpath = "//*[@id='gender-radio-2']/following-sibling::label")
  public WebElement genderFeamale;

  @FindBy(xpath = "//*[@id='gender-radio-3']/following-sibling::label")
  public WebElement genderOther;

  @FindBy(id = "userNumber")
  public WebElement phone;

  @FindBy(id = "dateOfBirthInput")
  public WebElement dateOfBirth;

  @FindBy(xpath = "//*[contains(text(),'January')]/parent::select")
  public WebElement dateOfBirthMonthDropDownList;

  @FindBy(xpath = "//*[contains(text(),'1900')]/parent::select")
  public WebElement dateOfBirthYearDropDownList;

  @FindBy(xpath = "//div[@class='react-datepicker__week']/div")
  public List < WebElement > dateOfBirths;

  @FindBy(id = "subjectsInput")
  public WebElement subjectInputTextElement;

  @FindBy(xpath = "//*[@id='hobbies-checkbox-1']/following-sibling::label")
  public WebElement hobbie1;

  @FindBy(xpath = "//*[@id='hobbies-checkbox-2']/following-sibling::label")
  public WebElement hobbie2;

  @FindBy(xpath = "//*[@id='hobbies-checkbox-3']/following-sibling::label")
  public WebElement hobbie3;

  @FindBy(id = "uploadPicture")
  public WebElement uploadFile;

  @FindBy(id = "currentAddress")
  public WebElement currentAddress;

  @FindBy(id = "state")
  public WebElement stateDropDownList;

  @FindBy(xpath = "//*[@id='stateCity-wrapper']//*[@tabindex='-1']")
  public List < WebElement > stateOrCityDropDownListValues; // the two drop-down lists has same locators for their values.

  @FindBy(xpath = "//*[@id='state']//*[normalize-space(text())]")
  public WebElement stateDropDownListActualResultValue;

  @FindBy(id = "city")
  public WebElement cityDropDownList;

  @FindBy(xpath = "//*[@id='city']//*[normalize-space(text())]")
  public WebElement cityDropDownListActualResultValue;

  @FindBy(id = "submit")
  public WebElement submitButton;

  @FindBy(id = "example-modal-sizes-title-lg")
  public WebElement confirmTextAutomationPracticeForm_PopUp;

  // Assertion elements.
  @FindBy(xpath = "//*[contains(text(),'Student Name')]/following-sibling::td")
  public WebElement assertStudentName;

  @FindBy(xpath = "//*[contains(text(),'Student Email')]/following-sibling::td")
  public WebElement assertStudentEmail;

  @FindBy(xpath = "//*[contains(text(),'Gender')]/following-sibling::td")
  public WebElement assertGender;

  @FindBy(xpath = "//*[contains(text(),'Mobile')]/following-sibling::td")
  public WebElement assertMobile;

  @FindBy(xpath = "//*[contains(text(),'Date of Birth')]/following-sibling::td")
  public WebElement assertDateOfBirth;

  @FindBy(xpath = "//*[contains(text(),'Subjects')]/following-sibling::td")
  public WebElement assertSubjects;

  @FindBy(xpath = "//*[contains(text(),'Hobbies')]/following-sibling::td")
  public WebElement assertHobbies;

  @FindBy(xpath = "//*[contains(text(),'Picture')]/following-sibling::td")
  public WebElement assertPicture;

  @FindBy(xpath = "//*[contains(text(),'Address')]/following-sibling::td")
  public WebElement assertAddress;

  @FindBy(xpath = "//*[contains(text(),'State and City')]/following-sibling::td")
  public WebElement assertStateNadCity;

  // Individual methods will be created here. The methods will be used only for the current test class.

  /**
   * Select random gender.
   * 
   * @return				- the "Gender" expected result will be returned.
   */
  public String selectRandomGender() {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      WebElement[] genderElements = {
        genderMale,
        genderFeamale,
        genderOther
      }; // Create a new collection.
      List < WebElement > genderList = new ArrayList < WebElement > (); // Create a new array.
      genderList.addAll(Arrays.asList(genderElements)); // Add the collection to the array.
      int randomNumber = random.ints(1, 3).findFirst().getAsInt(); // Get random element from the array.
      genderList.get(randomNumber).click(); // Clicking on the random item from the list.
      String genderExpectedResult = genderList.get(randomNumber).getText(); // Get the text of the random element.
      return genderExpectedResult;
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null;
  }

  /**
   * Parse months data.
   * 
   * @param	dateOfBirthValue		- provide a string which contains the date of birth.
   * @return						- the parsed "Date of Birth" will be returned.
   */
  public String parseMonths(String dateOfBirthValue) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      List < String > calendarList = Arrays.asList(dateOfBirthValue.split(" ")); // Split the copied value from the "Date of Birth" input text element and split it. Add the split results to a list.
      String calendarDate = calendarList.get(0).toString(); // Assign the first value of the list to a variable.
      String calendarMonth = calendarList.get(1).toString(); // Assign the first value of the list to a variable.
      String calendarYear = calendarList.get(2).toString(); // Assign the first value of the list to a variable.
      String[] monthsValues = {
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
      }; // Create a new collection.
      List < String > months = new ArrayList < String > (); // Create a new array.
      months.addAll(Arrays.asList(monthsValues)); // Add the collection to the array.
      String[] monthsValuesShort = {
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
      }; // Create a new collection.
      List < String > monthsShort = new ArrayList < String > (); // Create a new array.
      monthsShort.addAll(Arrays.asList(monthsValuesShort)); // Add the collection to the array.
      for (int n = 0; n <= 11; n++) { // Create a loop.
        if (calendarMonth.equals(monthsShort.get(n))) { // Check in the second value from the calendarList array is equals to some of the value from the monthsShort array.
          calendarMonth = months.get(n); // Assign value to the variable.
          break; // Stop the loop.
        }
      }
      String OutputDateOfBirth = calendarDate + " " + calendarMonth + "," + calendarYear; // Add new created value to the OutputDateOfBirth variable (Expected Result).
      return OutputDateOfBirth;
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null;
  }

  /**
   * Select random Hobbies method.
   * 
   * @param hobbiesElements			- provide a collection with WebElements. Those WebElements should be check boxes (they will be checked).
   * @param maxElementNumber		- provide the maximum number of random elements that should be selected.
   * @return						- the 'Hobbies' expected result will be returned.
   */
  public String selectRandomHobbies(WebElement[] hobbiesElements, int maxElementNumber) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      List < WebElement > hobbiesList = new ArrayList < WebElement > (); // Create a new array.
      hobbiesList.addAll(Arrays.asList(hobbiesElements)); // Add the collection to the array.
      String hobbieExpectedResult = "";
      int noCheckOrCheck = random.ints(0, 2).findFirst().getAsInt(); // Generate random number from 0 to 1 to take a decision if we will check some of the check boxes or not.
      if (noCheckOrCheck == 1) // If the 'noCheckOrCheck' is 1 - the automation will check randomly check boxes.
      {
        int randomLoop = random.ints(0, maxElementNumber + 1).findFirst().getAsInt(); // Generate random number from 0 to 2.
        List < Integer > howManyHobbiesWillBeChecked = null; // Create a new list.
        for (int i = 0; i <= maxElementNumber; i++) {
          if (randomLoop == i) {
            howManyHobbiesWillBeChecked = otherMethods.uniqueRandomNumber(maxElementNumber, i); // Execute the uniqueRandomNumber method to choose randomly which check box will be check.
          }
        }
        for (int i = 0; i <= randomLoop; i++) // Make a loop. Run the loop with randomly number.
        {
          hobbiesList.get(howManyHobbiesWillBeChecked.get(i)).click(); // Clicking on the random item from the list.
          hobbieExpectedResult += hobbiesList.get(howManyHobbiesWillBeChecked.get(i)).getText() + ", "; // Get the text of the random element and assign it to a String variable.
        }
        hobbieExpectedResult = hobbieExpectedResult.substring(0, hobbieExpectedResult.length() - 2); // We need to remove the last two characters from the String, because they are (always) ", ".
        return hobbieExpectedResult; // Return the getText values to the executed class.
      } else { // if the 'noCheckOrCheck' is 0 - the automation will not check any check boxes.
        return hobbieExpectedResult; // Return the getText values to the executed class.
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null; // Return null if something goes wrong.
  }

  /**
   * Select random Subjects method.
   * 
   * @param subjectValues			- provide a collection with Strings. Those Strings should be possible values that should be selected.
   * @param maxSubjectNumber		- provide the maximum number of random Strings that should be selected.
   * @return						- the expected 'Subject' expected result will be returned.
   */
  public String selectRandomSubjects(List < String > subjectValues, int maxSubjectNumber) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      List < String > subjectsValueList = new ArrayList < String > (); // Create a new array.
      subjectsValueList.addAll(subjectValues); // Add the collection to the array.
      String subjectsExpectedResult = "";
      int noSelectValueOrSelectValue = random.ints(0, 2).findFirst().getAsInt(); // Generate random number from 0 to 1 to take a decision if we will select values or not.
      if (noSelectValueOrSelectValue == 1) // If the 'noSelectValueOrSelectValue' is 1 - the automation will select randomly values for the "Subjects" input.
      {
        List < Integer > howManyValuesWillBeEntered = null; // Create a new list.
        int randomLoop = random.ints(0, maxSubjectNumber + 1).findFirst().getAsInt(); // Generate random number from 0 to 13 (the maximum possible inputs are 14).
        for (int i = 0; i <= maxSubjectNumber; i++) {
          if (randomLoop == i) {
            howManyValuesWillBeEntered = otherMethods.uniqueRandomNumber(maxSubjectNumber, i); // Execute the uniqueRandomNumber method to choose randomly which check box will be check.
          }
        }
        for (int i = 0; i <= randomLoop; i++) { // Make a loop. Run the loop with randomly number. 
          subjectInputTextElement.sendKeys(subjectsValueList.get(howManyValuesWillBeEntered.get(i))); // Select the "Subjects" input text element and sendKeys to it randomly selected values.
          subjectInputTextElement.sendKeys(Keys.TAB); // Send the "TAB" key  to the element.
          subjectsExpectedResult += subjectsValueList.get(howManyValuesWillBeEntered.get(i)) + ", "; // Add the values to a string to use it for expected result.
        }
        subjectsExpectedResult = subjectsExpectedResult.substring(0, subjectsExpectedResult.length() - 2); // We need to remove the last two characters from the String, because they are (always) ", ". 
        return subjectsExpectedResult; // Return the expected result to the executed class.
      } else { // if the 'noSelectValueOrSelectValue' is 0 - the automation will not select any data into the "Subjects" input text element.
        return subjectsExpectedResult; // Return the expected result to the executed class.
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null; // Return null if something goes wrong.
  }

  /**
   * Select random drop-down list values for State and City drop-down lists.
   * 
   * @param dropDownListValues					- provide a list with drop-down list values. You need to provide one locator for all drop-down list values.
   * @param dropDownList						- provide an element for drop-down list that should be used.
   * @param dropDownListExpectedReultElement	- provide an element from where the automation will get the selected drop-down list value.
   * @param isThisSecondDropDownList			- provide a information if this is the first drop-down list or it is the second drop-down list.
   * @return									- randomly selected drop-down value will be returned.
   */
  public String selectRandomStateAndCityDropDownValues(List < WebElement > dropDownListValues, WebElement dropDownList, WebElement dropDownListExpectedReultElement, String isThisSecondDropDownList) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      dropDownList.click(); // Should click on the State drop-down list.
      List < WebElement > dropDownListTotalValues = dropDownListValues; // Should create a array and assign all elements with used same locator to the array.
      int noSelectValueOrSelectValue; // Declare a variable for randomly choose a decision if the drop-down list will be selected, or not.
      if (isThisSecondDropDownList == "no") { // if the isThisSecondDropDownList is set to "no" - we are selecting a value from the first drop-down list.
        noSelectValueOrSelectValue = random.ints(0, 2).findFirst().getAsInt(); // Generate random number from 0 to 1 to take a decision if we will select values or not.
      } else { // if the isThisSecondDropDownList is not set to "yes" - we are selecting a value from the first drop-down list.
        noSelectValueOrSelectValue = 1; // Because we already select value for the first drop-drop list - we need to mandatory select value for the second drop-down list.
      }
      if (noSelectValueOrSelectValue == 1) { // If the 'noSelectValueOrSelectValue' is 1 - the automation will select randomly values for the drop-down list.
        int generateRandomSelectionOfTheStateDropDownList = random.ints(0, dropDownListTotalValues.size()).findFirst().getAsInt(); // We will select one random element from all drop-down values.
        dropDownListTotalValues.get(generateRandomSelectionOfTheStateDropDownList).click(); // Should click on the drop-down list value that was selected randomly.
        String stateActualResultValueNotEditeed = dropDownListExpectedReultElement.getText(); // Should get the selected value to use it for expected result.
        List < String > stateActualResultValue1 = Arrays.asList(stateActualResultValueNotEditeed.split(" option ")); // Split the expected result (because the result have some values that we doesn't need).
        List < String > stateActualResultValue2 = Arrays.asList(stateActualResultValue1.get(1).split(", selected.")); // Split the expected result (because the result have some values that we doesn't need).
        return stateActualResultValue2.get(0); // Get the real expected result values.
      } else { // if the 'noSelectValueOrSelectValue' is 0 - the automation will not select any data into the drop-down list.
        return null; // If the method doesn't pass - return null value.
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null; // If the method doesn't pass - return null value.
  }

  /**
   * Select random drop-down list values.
   * @param dropDownList					- provide a element. The element should be drop-down list (select element).
   */
  private void selectRandomDropDownListValue(WebElement dropDownList) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      dateOfBirth.click();
      Select dropdown = new Select(dropDownList); // Should select the drop-down list.
      List < WebElement > allDropDownValues = dropdown.getOptions(); // Should get all values from the drop-down list.
      int generateRandomSelectionOfTheDropDownList = random.ints(0, allDropDownValues.size()).findFirst().getAsInt(); // We will select one random element from all drop-down values.
      dropdown.selectByIndex(generateRandomSelectionOfTheDropDownList); // Should select randomly selected drop-down value.
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
  }

  /**
   * Select random "Date of Birth" value.
   */
  public void selectRandomDateOfBirthValue() {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      selectRandomDropDownListValue(dateOfBirthMonthDropDownList); // select randomly value form the month drop-down list.
      selectRandomDropDownListValue(dateOfBirthYearDropDownList); // select randomly value form the year drop-down list.
      List < WebElement > calendarDatesAllValues = dateOfBirths; // Should create a array and assign all elements with used same locator to the array.
      int generateRandomSelectionOfTheDropDownCalendar = random.ints(0, calendarDatesAllValues.size()).findFirst().getAsInt(); // We will select one random element from all calendar dates.
      calendarDatesAllValues.get(generateRandomSelectionOfTheDropDownCalendar).click(); // Click on the randomly selected date.   
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
  }

  /** 
   * Select random state and city main method.
   * 
   * @return 			- the expected result value will be returned.
   */
  public String selectRandomStateAndCity() {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      String stateActualResult = selectRandomStateAndCityDropDownValues(stateOrCityDropDownListValues, stateDropDownList, stateDropDownListActualResultValue, "no");
      String cityActualResult;
      if (stateActualResult != null) {
        cityActualResult = " " + selectRandomStateAndCityDropDownValues(stateOrCityDropDownListValues, cityDropDownList, cityDropDownListActualResultValue, "yes");
      } else {
        stateActualResult = "";
        cityActualResult = "";
      }
      return stateActualResult + cityActualResult;
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null;
  }
}