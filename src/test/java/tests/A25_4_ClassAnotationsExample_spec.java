/**
 * Description:
 * This class shows how to extends all code for @BeforeMethod and @AfterMethod from BaseTestClass.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pom.A25_4_po;
import base.BaseTestClass;

public class A25_4_ClassAnotationsExample_spec extends BaseTestClass { // extends BaseTestClass

  private static A25_4_po page;
  private static String endpoint = "text-box";

  @Test(priority = 1, groups = {
    "positiveTest",
    "logIn"
  })
  public void textExample1() {
    mainMethods.navigateURL(url, page.fullNameInputTextElement); // Navigate to URL.
  }

  @Test(priority = 0, groups = {
    "negativeTest",
    "logIn"
  }, timeOut = 45000)
  public void textExample2() {
    mainMethods.navigateURL(url, page.fullNameInputTextElement); // Navigate to URL.
  }

  @Test(priority = 2, groups = {
    "negativeTest",
    "logIn"
  })
  public void textExample3() {
    mainMethods.navigateURL(url, page.fullNameInputTextElement); // Navigate to URL.
  }

  @BeforeMethod
  public void beforeTest() {
    setUp(endpoint); // Call setUp method, responsible for everything that should be made like precondition before starting the execution of the automation tests.
    page = new A25_4_po(driver);
  }

  @AfterMethod
  public void afterMethod() {
    terminate(); // Call terminate method, responsible for everything that should be made after execution of the automation tests.
  }
}