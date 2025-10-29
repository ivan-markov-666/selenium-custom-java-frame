/**
 * Description:
 * This class shows how to use everything from all classes till now.
 * Every one good practice is applied here - extremely-high level of automating.
 * This class is testing working with tables.
 */

package tests;

import org.testng.annotations.Test;
import base.BaseTestClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import pom.A24_2_po;

public class A32_FinalTestClassExample_spec extends BaseTestClass {

  private static A24_2_po page;
  private static String endpoint = "webtables";

  /*
   * This example shows how:
   * - To use everything from all classes till now.
   * - To apply every one good practice.
   */
  @Test(dataProvider = "dataProviderName")
  public void finalTEstClassExample(
    String firstNameValue,
    String lastNameValue,
    String emailValue,
    String ageValue,
    String salaryValue,
    String departmentValue,
    String firstNameValueEdited,
    String lastNameValueEdited,
    String emailValueEdited,
    String ageValueEdited,
    String salaryValueEdited,
    String departmentValueEdited) {

    /** 1. Navigate to: https://demoqa.com/webtables . */
    mainMethods.navigateURL(url, page.addButton); // Navigate to URL.
    /** 2.	Press on the “Add” button. */
    mainMethods.clickMethod(page.addButton, page.firstNameInputTextElement); // Press on the "Add" button.
    /** 3.	Fill the form correct. */
    page.fillDataIntoTheTableForm(firstNameValue, lastNameValue, emailValue, ageValue, salaryValue, departmentValue);
    /** 4.	Press on the “Submit” button. */
    mainMethods.clickMethod(page.submitButton, page.addButton); // Press the "Submit" button.
    /** 5.	Verify that the saved data is showed into the report. */
    page.verifyDataInTable(firstNameValue, lastNameValue, emailValue, ageValue, salaryValue, departmentValue);
    /** 6.	Try to search for newly added record by: “First Name”, “Last Name”, “Age”, “Email”, “Salary”, “Department”. */
    page.verifySearchFunctionalityMainMethod(firstNameValue, lastNameValue, ageValue, emailValue, salaryValue, departmentValue, "false");
    /** 7.	Press on the edit icon of newly added record. */
    page.editLastRecord(page.allEditElements);
    /** 8.	Edit the data with a new one. */
    page.fillDataIntoTheTableForm(firstNameValueEdited, lastNameValueEdited, emailValueEdited, ageValueEdited, salaryValueEdited, departmentValueEdited);
    /** 9.	Press on the “Submit” button. */
    mainMethods.clickMethod(page.submitButton, page.addButton); // Press the "Submit" button.
    /** 10.	Verify that the saved data is showed into the report. */
    page.verifyDataInTable(firstNameValueEdited, lastNameValueEdited, emailValueEdited, ageValueEdited, salaryValueEdited, departmentValueEdited);
    /** 11.	Press on the delete icon. */
    page.editLastRecord(page.allDeleteElements);
    /** 12.	Verify that the record was deleted from the report. */
    page.verifySearchFunctionalityMainMethod(firstNameValueEdited, lastNameValueEdited, ageValueEdited, emailValueEdited, salaryValueEdited, departmentValueEdited, "true");
  }

  @BeforeClass
  public void beforeClass() {
    setUp(endpoint);
    page = new A24_2_po(driver);
    dataDriven("A33_WorkingWithTables.xlsx", "Sheet1");
  }

  @DataProvider(name = "dataProviderName")
  public Object[][] dataProvider() {
    Object[][] testData = excel.getDataFromExcelFile("begin");
    return testData;
  }

  @AfterClass
  public void afterClass() {
    terminate();
  }

  @AfterMethod
  public void afterMethod(ITestResult testResult) {
    if (testResult.getStatus() == ITestResult.FAILURE) {
      mainMethods.takeScreenShotInAfterMethod(testResult.getMethod().getMethodName());
    }
  }
}