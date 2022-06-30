/**
 * Description:
 * This class is used to declare all ITestListener methods.
 */

package listener_methods;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class ITestListenerClass implements ITestListener {

  /** This will be executed BEFORE @Test annotation (method) */
  @Override
  public void onTestStart(ITestResult result) {
    System.out.println("onTestStart method. The executed Test Name is: '" + result.getName() + "'");
  }

  /** This will be executed when @Test annotation (method) is completed with no errors. */
  @Override
  public void onTestSuccess(ITestResult result) {
    System.out.println("onTestSuccess method. The executed Test Name is: '" + result.getName() + "'");
  }

  /** This will be executed when the @Test annotation (method) fails. */
  @Override
  public void onTestFailure(ITestResult result) {
    System.out.println("onTestFailure method. The executed Test Name is: '" + result.getName() + "'");
  }

  /** This will be executed when the @Test annotation (method) will be skipped. */
  @Override
  public void onTestSkipped(ITestResult result) {
    System.out.println("onTestSkipped method. The executed Test Name is: '" + result.getName() + "'");
  }

  /** This will be executed when the @Test annotation (method) fails, but with some success percentage.
   * This can be used if you set the 'successPercentage' attribute for @Test annotation (class).
   * For example if you have 10 asserts and you set the percentage of 'successPercentage' attribute to 80%,
   * and when you run the automation test 8 or more from all 10 asserts passed - then this method (listener) will be executed. */
  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    System.out.println("onTestFailedButWithinSuccessPercentage method. The executed Test Name is: '" + result.getName() + "'");
  }

  /** This will be executed BEFORE every one <test> tag from the XML suit.
   * 	For example if you want to print all names of the methods inside of the <test> that is executed from the XML file. */
  @Override
  public void onStart(ITestContext context) {
    System.out.println("onStart method. The executed Test Name is: '" + context.getName() + "'");
    ITestNGMethod methods[] = context.getAllTestMethods();
    System.out.println("These methods will be executed in this <test> tag:");
    for (ITestNGMethod method: methods) {
      System.out.println("The executed Test Name is: '" + method.getMethodName() + "'");
    }
  }

  /** This will be executed AFTER every one <test> tag from the XML suit. */
  @Override
  public void onFinish(ITestContext context) {
    System.out.println("onFinish method. The executed Test Name is: '" + context.getName() + "'");
    ITestNGMethod methods[] = context.getAllTestMethods();
    System.out.println("These methods will be executed after this <test> tag:");
    for (ITestNGMethod method: methods) {
      System.out.println("The executed Test Name is: '" + method.getMethodName() + "'");
    }
  }
}