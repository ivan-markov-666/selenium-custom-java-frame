/**
 * Description:
 * This class is used to declare all ISuiteListener methods.
 */

package listener_methods;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class ISuiteListenerClass implements ISuiteListener {

  /** This will be executed BEFORE every one <suite> tag from the XML suit. */
  @Override
  public void onStart(ISuite suite) {
    System.out.println("OnStart is executed.");
  }

  /** This will be executed AFTER every one <suite> tag from the XML suit. */
  @Override
  public void onFinish(ISuite suite) {
    System.out.println("onFinish is executed.");
  }
}