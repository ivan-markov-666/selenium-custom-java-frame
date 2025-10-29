package custom_methods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.Configuration;

public class WaitTypes {
  private WebDriver driver;
  private OtherMethods otherMethods;

  /**
   * Constructor for WaitTypes.
   * Initializes the WebDriver and other dependencies.
   * 
   * @param driver - the WebDriver instance to be used for waiting operations.
   */
  public WaitTypes(WebDriver driver) {
	  if (driver == null) {
	    throw new IllegalArgumentException("WebDriver cannot be null!");
	  }
	  this.driver = driver;
	  this.otherMethods = new OtherMethods();
	}

  /**
   * Add here more waiters in this class if its needed.
   * If you want to turn on the messages in the methods you need to set the value to 'on' of the 'messages' variable from the 'configuration.Configuration' class.
   */

  /**
   * This method is used for static wait. 
   * 
   * @param timeout - provide an integer number that should be used for timeout in milliseconds.
   */
  public void staticWait(int timeout) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      Thread.sleep(timeout);
      otherMethods.messagesMethod("Message: Static Wait is executed for " + timeout + " milliseconds.");
    } catch (InterruptedException e) {
      System.out.println("ERROR! The operation was not complete. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); 
    }
  }

  /**
   * This method is used for expectation of the locator by visibilityOfElementLocated.
   * 
   * @param locator - provide the locator that should be expected.
   * @param timeout - an integer number that should be used for timeout in seconds. 
   * @return - the waited element will be returned.
   */
  public WebElement waitVisibilityOfElementLocatedBy(By locator, int timeout) {
    WebElement element = null;
    try {
      otherMethods.messagesMethod("Message: We are waiting " + timeout + " seconds to load the locator '" + locator + "'.");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
      element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      otherMethods.messagesMethod("Message: The locator appeared on the page.");
    } catch (Exception e) {
      System.out.println("ERROR! Element is not presented in the page."); // This message will be shown if something is gone wrong with the method.
    }
    return element;
  }

  /**
   * This method is used for expectation of the locator by elementToBeClickable.
   * 
   * @param locator - provide the locator that should be expected.
   * @param timeout - an integer number that should be used for timeout in seconds. 
   * @return - the waited element will be returned.
   */
  public WebElement waitElementToBeClickableBy(By locator, int timeout) {
    WebElement element = null;
    try {
      otherMethods.messagesMethod("Message: We are waiting " + timeout + " seconds to load the locator '" + locator + "'.");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
      element = wait.until(ExpectedConditions.elementToBeClickable(locator));
      otherMethods.messagesMethod("Message: The locator appeared on the page.");
    } catch (Exception e) {
      System.out.println("ERROR! Element is not presented in the page."); // This message will be shown if something is gone wrong with the method.
    }
    return element;
  }

  /**
   * This method is used to wait for the element to be displayed.
   * Now uses WebDriverWait to actually wait (up to configured timeout) for the element to become visible.
   * Uses the timeout value from Configuration class (default 15 seconds).
   * 
   * @param element - provide the element that should be waited for.
   * @return - the waited element will be returned when it becomes displayed.
   * @throws RuntimeException if the element is not displayed within the timeout period.
   */
  public WebElement waitIsDisplayed(WebElement element) {
    try {
      otherMethods.messagesMethod("Message: Waiting for element '" + element + "' to be displayed (timeout: " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds).");
      
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.Timeouts.EXPLICIT_WAIT));
      wait.until(ExpectedConditions.visibilityOf(element));
      
      otherMethods.messagesMethod("Message: The element '" + element + "' is displayed.");
    } catch (Exception e) {
      String errorMsg = "ERROR! The element is not DISPLAYED after waiting " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds. Error message: " + e.getMessage();
      System.out.println(errorMsg);
      throw new RuntimeException(errorMsg, e);
    }
    return element;
  }

  /**
   * This method is used to wait for the element to be enabled.
   * Now uses WebDriverWait to actually wait (up to configured timeout) for the element to become enabled.
   * Uses the timeout value from Configuration class (default 15 seconds).
   * 
   * @param element - provide the element that should be waited for.
   * @return - the waited element will be returned when it becomes enabled.
   * @throws RuntimeException if the element is not enabled within the timeout period.
   */
  public WebElement waitIsEnabled(WebElement element) {
    try {
      otherMethods.messagesMethod("Message: Waiting for element '" + element + "' to be enabled (timeout: " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds).");
      
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.Timeouts.EXPLICIT_WAIT));
      wait.until(driver -> element.isEnabled());
      
      otherMethods.messagesMethod("Message: The element '" + element + "' is enabled.");
    } catch (Exception e) {
      String errorMsg = "ERROR! The element is not ENABLED after waiting " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds. Error message: " + e.getMessage();
      System.out.println(errorMsg);
      throw new RuntimeException(errorMsg, e);
    }
    return element;
  }

  /**
   * This method is used to wait for the element to be selected (for checkboxes and radio buttons).
   * Now uses WebDriverWait to actually wait (up to configured timeout) for the element to become selected.
   * Uses the timeout value from Configuration class (default 15 seconds).
   * 
   * @param element - provide the element that should be waited for.
   * @return - the waited element will be returned when it becomes selected.
   * @throws RuntimeException if the element is not selected within the timeout period.
   */
  public WebElement waitIsSelected(WebElement element) {
    try {
      otherMethods.messagesMethod("Message: Waiting for element '" + element + "' to be selected (timeout: " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds).");
      
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.Timeouts.EXPLICIT_WAIT));
      wait.until(ExpectedConditions.elementToBeSelected(element));
      
      otherMethods.messagesMethod("Message: The element '" + element + "' is selected.");
    } catch (Exception e) {
      String errorMsg = "ERROR! The element is not SELECTED after waiting " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds. Error message: " + e.getMessage();
      System.out.println(errorMsg);
      throw new RuntimeException(errorMsg, e);
    }
    return element;
  }

  /**
   * Instant verification that element is displayed (no waiting).
   * Use this method only when you're certain the element is already present on the page.
   * For waiting until element appears, use waitIsDisplayed() instead.
   * 
   * @param element - provide the element that should be verified.
   * @return - the element if it is displayed.
   * @throws RuntimeException if the element is not displayed immediately.
   */
  public WebElement verifyIsDisplayed(WebElement element) {
    try {
      if (!element.isDisplayed()) {
        throw new RuntimeException("ERROR! Element is not DISPLAYED (instant check, no waiting).");
      }
      otherMethods.messagesMethod("Message: Element '" + element + "' is displayed (instant verification, no waiting).");
      return element;
    } catch (NoSuchElementException e) {
      throw new RuntimeException("ERROR! Element is not found in DOM. Error message: " + e);
    }
  }

  /**
   * Waits for element to become invisible/hidden.
   * Useful for waiting until loading spinners, modals, or overlays disappear.
   * 
   * @param element - provide the element that should become invisible.
   * @return - true when element becomes invisible, false if timeout occurs.
   */
  public boolean waitIsInvisible(WebElement element) {
    try {
      otherMethods.messagesMethod("Message: Waiting for element '" + element + "' to become invisible (timeout: " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds).");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.Timeouts.EXPLICIT_WAIT));
      boolean isInvisible = wait.until(ExpectedConditions.invisibilityOf(element));
      otherMethods.messagesMethod("Message: Element '" + element + "' is now invisible.");
      return isInvisible;
    } catch (Exception e) {
      System.out.println("ERROR! Element is still visible after " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds. Error: " + e.getMessage());
      return false;
    }
  }

  /**
   * Waits for specific text to be present in an element.
   * Useful for waiting until dynamic text loads or changes.
   * 
   * @param element - provide the element to check for text.
   * @param text - the text that should be present in the element.
   * @return - true when text is present, false if timeout occurs.
   */
  public boolean waitTextToBePresentInElement(WebElement element, String text) {
    try {
      otherMethods.messagesMethod("Message: Waiting for text '" + text + "' to be present in element '" + element + "' (timeout: " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds).");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.Timeouts.EXPLICIT_WAIT));
      boolean textPresent = wait.until(ExpectedConditions.textToBePresentInElement(element, text));
      otherMethods.messagesMethod("Message: Text '" + text + "' is now present in element.");
      return textPresent;
    } catch (Exception e) {
      System.out.println("ERROR! Text '" + text + "' not present in element after " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds. Error: " + e.getMessage());
      return false;
    }
  }

  /**
   * Waits for element's attribute to contain specific value.
   * Useful for waiting until element's state changes (e.g., class changes, disabled attribute removed).
   * 
   * @param element - provide the element to check.
   * @param attribute - the attribute name to check (e.g., "class", "value", "disabled").
   * @param value - the value that should be present in the attribute.
   * @return - true when attribute contains the value, false if timeout occurs.
   */
  public boolean waitAttributeContains(WebElement element, String attribute, String value) {
    try {
      otherMethods.messagesMethod("Message: Waiting for attribute '" + attribute + "' to contain value '" + value + "' in element '" + element + "' (timeout: " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds).");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.Timeouts.EXPLICIT_WAIT));
      boolean attributeContains = wait.until(ExpectedConditions.attributeContains(element, attribute, value));
      otherMethods.messagesMethod("Message: Attribute '" + attribute + "' now contains value '" + value + "'.");
      return attributeContains;
    } catch (Exception e) {
      System.out.println("ERROR! Attribute '" + attribute + "' does not contain value '" + value + "' after " + Configuration.Timeouts.EXPLICIT_WAIT + " seconds. Error: " + e.getMessage());
      return false;
    }
  }
}