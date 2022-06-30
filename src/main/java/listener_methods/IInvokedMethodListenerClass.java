/**
 * Description:
 * This class is used to declare all IInvokedMethodListener methods.
 */

package listener_methods;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class IInvokedMethodListenerClass implements IInvokedMethodListener {

  /** This method will be run BEFORE every one method in the test class.
   * 	That means that this will be executed before every one annotation (method) like:
   *	@Test, @BeforeMethod, @AfterMethod, @BeforeClass, @AfterClass, @BeforeTest, @AfterTest, @AfterTest and @AfterSuite.
   * */
  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    System.out.println("Before Invocation message: class name is: '" + testResult.getTestClass().getName() + "' and method name is: '" + method.getTestMethod().getMethodName() + "'");
  }

  /** This method will be run AFTER every one method in the test class.
   * 	That means that this will be executed after every one annotation (method) like:
   * 	@Test, @BeforeMethod, @AfterMethod, @BeforeClass, @AfterClass, @BeforeTest, @AfterTest, @AfterTest and @AfterSuite.
   * */
  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    System.out.println("After Invocation message: class name is: '" + testResult.getTestClass().getName() + "' and method name is: '" + method.getTestMethod().getMethodName() + "'");
  }
}