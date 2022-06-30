/**
 * Description:
 * This class shows how to get parameters from the XML file.
 * 
 * !WARNING: This test class need to be executed by the A26_ProvideParametersToTestMethod.xml file.
 */
package tests;

import org.testng.annotations.Test;
import base.BaseTestClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import pom.A24_2_po;

public class A26_ProvideParametersToTestMethod_spec extends BaseTestClass {

  private static A24_2_po page;
  private static String endpoing = "webtables";

  @Test
  @Parameters({
    "firstNameValue",
    "lastNameValue",
    "emailValue",
    "ageValue",
    "salaryValue",
    "departmentValue",
    "firstNameValueEdited",
    "lastNameValueEdited",
    "emailValueEdited",
    "ageValueEdited",
    "salaryValueEdited",
    "departmentValueEdited"
  })
  public void getParametersXMLfiles(String firstNameValue, String lastNameValue, String emailValue, String ageValue, String salaryValue, String departmentValue,
    String firstNameValueEdited, String lastNameValueEdited, String emailValueEdited, String ageValueEdited, String salaryValueEdited, String departmentValueEdited) {

    System.out.println("firstNameValue: '" + firstNameValue + "'" + "\n");
    System.out.println("lastNameValue: '" + lastNameValue + "'" + "\n");
    System.out.println("emailValue: '" + emailValue + "'" + "\n");
    System.out.println("ageValue: '" + ageValue + "'" + "\n");
    System.out.println("salaryValue: '" + salaryValue + "'" + "\n");
    System.out.println("departmentValue: '" + departmentValue + "'" + "\n");
    System.out.println("firstNameValueEdited: '" + firstNameValueEdited + "'" + "\n");
    System.out.println("lastNameValueEdited: '" + lastNameValueEdited + "'" + "\n");
    System.out.println("emailValueEdited: '" + emailValueEdited + "'" + "\n");
    System.out.println("ageValueEdited: '" + ageValueEdited + "'" + "\n");
    System.out.println("salaryValueEdited: '" + salaryValueEdited + "'" + "\n");
    System.out.println("departmentValueEdited: '" + departmentValueEdited + "'" + "\n");

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
    /** 13.	Execute steps 2-5, 10 times. */
    page.steps2to5InMethod(10);
  }

  @BeforeClass
  public void beforeClass() {
    setUp(endpoing);
    page = new A24_2_po(driver);
  }

  @AfterClass
  public void afterClass() {
    terminate();
  }
}