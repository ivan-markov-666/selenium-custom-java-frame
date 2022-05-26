/**
 * Description:
 * This class shows how to use this @Test in another @Test - part2.
 */

package tests;

import org.testng.annotations.Test;
import base.BaseTestClass;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import org.testng.annotations.AfterClass;

public class A31_2_UsingTestMethodInAnotherTestMethod_spec extends BaseTestClass {

  private static final A31_1_UsingTestMethodInAnotherTestMethod_spec firstTest = new A31_1_UsingTestMethodInAnotherTestMethod_spec();

  /*
   * This example shows how:
   * - To use @Test in this @Test.
   */
  @Test()
  public void secondTest() {
    firstTest.firstTest();
    System.out.println("This is the second @Test.");
  }

  @BeforeClass
  public void beforeClass() {
    setUp(null);
  }

  @AfterClass
  public void afterClass() throws IOException {
    terminate();
  }
}