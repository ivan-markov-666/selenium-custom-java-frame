/**
 * Description:
 * This class shows how to generate local report.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

/*
 * This example shows how:
 * - To generate local (HTML) report.
 */
public class A29_2_GenerateReport_2_spec {
  private static SoftAssert softassert = new SoftAssert();

  // This test will pass.
  @Test
  public void testMethod1() {
    System.out.println("This test will pass.");
    Reporter.log("This will be executed during the execution of the testMethod1. This test will pass.", true);
  }

  // This method will fail!
  @Test
  public void testMethod2() {
    System.out.println("This test will fail!");
    softassert.fail();
    softassert.assertAll();
    Reporter.log("This will be executed during the execution of the testMethod2. This test will fail!", true);
  }

  // This method will be skipped...
  @Test(enabled = false)
  public void testMethod3() {
    System.out.println("This test will fail!");
    Reporter.log("This will be executed during the execution of the testMethod3. This test will be skipped.", true);
  }

  @BeforeMethod
  public void beforeMethod() {
    Reporter.log("This will be executed once before class.", true);
  }

  @AfterMethod
  public void afterMethod() {
    Reporter.log("This will be executed once after class.", true);
  }

  @BeforeClass
  public void beforeClass() {
    Reporter.log("This will be executed before every one method.", true);
  }

  @AfterClass
  public void afterClass() {
    Reporter.log("This will be executed after every one method.", true);
  }

  @BeforeTest
  public void beforeTest() {
    Reporter.log("This will be executed before the first test method.", true);
  }

  @AfterTest
  public void afterTest() {
    Reporter.log("This will be executed after the last test method.", true);
  }

  @BeforeSuite
  public void beforeSuite() {
    Reporter.log("This will be executed before the suit.", true);
  }

  @AfterSuite
  public void afterSuite() {
    Reporter.log("This will be executed after the suit.", true);
  }
}