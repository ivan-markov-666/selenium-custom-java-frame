/**
 * Description:
 * This class shows how to use takeScreenShotInAfterMethod method. This method takes a screenshot when executed.
 */

package tests;

import org.testng.annotations.Test;
import base.BaseTestClass;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import pom.A27_po;

public class A27_TakeScreenshot_spec extends BaseTestClass {

  private static A27_po page;
  private static String endpoint = "webtables";

  /*
   * This example shows how:
   * - To get screenshot when the test fails.
   */
  @Test
  public void getScreenShotExampleWhemTestFails() {
    mainMethods.navigateURL(url, page.addButton);
    softassert.fail();
    softassert.assertAll();
  }

  /*
   * This example shows how:
   * - To get screenshot when the method fails.
   */
  @Test
  public void getScreenShotExampleWhenMethodFails() {
    mainMethods.navigateURL(url, page.fakeLocator); // Navigate to URL.
  }
  @BeforeMethod
  public void beforeMethod() {
    setUp(endpoint);
    page = new A27_po(driver);
  }

  @AfterMethod
  public void afterMethod(ITestResult testResult) {
    /* 
     * Create screenshot when test fails.
     */
    if (testResult.getStatus() == ITestResult.FAILURE) {
      mainMethods.takeScreenShotInAfterMethod(testResult.getMethod().getMethodName());
    }
    terminate();
  }
}