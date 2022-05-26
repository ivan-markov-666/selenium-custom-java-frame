package custom_methods;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitTypes {
  private WebDriver driver;
  private OtherMethods otherMethods = new OtherMethods();

  public WaitTypes(WebDriver driver) {
    this.driver = driver;
  }

  /**
   * Add here more waiters in this class if its needed.
   * If you want to turn on the messages in the methods you need to set the value to 'on' of the 'messages' variable from the 'configuration.Configuration' class.
   */

  /**
   * This method is used for static wait. 
   * 
   * @param timeout			- provide a integer number that should be used for timeout in seconds.
   */
  public void staticWait(int timeout) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      Thread.sleep(timeout);
      otherMethods.messagesMetohd("Message: Static Wait is executed.");
    } catch (InterruptedException e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
  }

  /**
   * This method is used for expectation of the locator by visibilityOfElementLocated.
   * 
   * @param locator			- provide the locator that should be expected.
   * @param timeout			- a integer number that should be used for timeout in seconds. 
   * @return 					- the waited element will be returned.
   */
  public WebElement waitVisibilityOfElementLocatedBy(By locator, int timeout) {
    WebElement element = null;
    try {
      otherMethods.messagesMetohd("Message: We are waiting " + timeout + " seconds to load the locator '" + locator + "'.");
      WebDriverWait wait = new WebDriverWait(driver, timeout);
      element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      otherMethods.messagesMetohd("Message: The locator appeared on the page.");
    } catch (Exception e) {
      System.out.println("ERROR! Element is not presented in the page.");
    }
    return element;
  }

  /**
   * This method is used for expectation of the locator by elementToBeClickable.
   * 
   * @param locator			- provide the locator that should be expected.
   * @param timeout			- a integer number that should be used for timeout in seconds. 
   * @return 					- the waited element will be returned.
   */
  public WebElement waitElementToBeClickableBy(By locator, int timeout) {
    WebElement element = null;
    try {
      otherMethods.messagesMetohd("Message: We are waiting " + timeout + " seconds to load the locator '" + locator + "'.");
      WebDriverWait wait = new WebDriverWait(driver, timeout);
      element = wait.until(ExpectedConditions.elementToBeClickable(locator));
      otherMethods.messagesMetohd("Message: The locator appeared on the page.");
    } catch (Exception e) {
      System.out.println("ERROR! Element is not presented in the page.");
    }
    return element;
  }

  /**
   * This method is used for wait the element by method isDisplayed.
   * 
   * @param element			- provide the element that should be waited.
   * @return 					- the waited element will be returned.
   */
  public WebElement waitIsDisplayed(WebElement element) {
    try {
      element.isDisplayed();
      otherMethods.messagesMetohd("Message: The element '" + element + "' is displayed.");
    } catch (NoSuchElementException e) {
      throw new RuntimeException("ERROR! The element is not DISPLAYED. Error message: " + e);
    }
    return element;
  }

  /**
   * This method is used for wait the element by method isEnabled.
   * 
   * @param element			- provide the element that should be waited.
   * @return 					- the waited element will be returned.
   */
  public WebElement waitIsEnabled(WebElement element) {
    try {
      element.isEnabled();
      otherMethods.messagesMetohd("Message: The element '" + element + "' is enabled.");
    } catch (NoSuchElementException e) {
      throw new RuntimeException("ERROR! The element is not ENABLED. Error message: " + e);
    }
    return element;
  }

  /**
   * This method is used for wait the element by method isSelected.
   * 
   * @param element			- provide the element that should be waited.
   * @return 					- the waited element will be returned.
   */
  public WebElement waitIsSelected(WebElement element) {
    try {
      element.isSelected();
      otherMethods.messagesMetohd("Message: The element '" + element + "' is selected.");
    } catch (NoSuchElementException e) {
      throw new RuntimeException("ERROR! The element is not SELECTED. Error message: " + e);
    }
    return element;
  }
}