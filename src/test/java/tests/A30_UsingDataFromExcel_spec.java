/**
 * Description:
 * This class shows how read data from Excel file (Test Data Driven).
 */

package tests;

import org.testng.annotations.Test;
import base.BaseTestClass;
import dataDrivenTesting_methods.ImportDataFromExcel;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import org.testng.annotations.AfterClass;

public class A30_UsingDataFromExcel_spec extends BaseTestClass {

  private static ImportDataFromExcel excel;

  /*
   * This example shows how:
   * - To set system property for browser driver.
   */
  @Test(dataProvider = "dataProviderName")
  public void getDataFromExcelFile(String username, String password, String gender) {
    System.out.println("The username is: " + username);
    System.out.println("The password is: " + password);
    System.out.println("The gender is: " + gender);
  }

  @BeforeClass
  public void beforeClass() {
    setUp(null);
    excel = new ImportDataFromExcel(); // Create a new constructor for ImportDataFromExcel;
    excel.setExcelFile(config.excelFilePath + config.excelFileName, "Sheet1"); // Execute the setExcelFile method. This is used to set the direction of the Excel file path and Excel sheet name.
  }

  // Marks a method as supplying data for a test method. The annotated method must return an Object[][] where each Object[] can be assigned the parameter list of the test method. The @Test method that wants to receive data from this DataProvider needs to use a dataProvider name equals to the name of this annotation.
  @DataProvider(name = "dataProviderName")
  public Object[][] dataProvider() {
    Object[][] testData = excel.getDataFromExcelFile("begin");
    return testData;
  }

  @AfterClass
  public void afterClass() throws IOException {
    terminate();
  }
}