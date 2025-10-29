package custom_methods;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import configuration.Configuration;

public class MainMethods {


  private WebDriver driver;
  private WaitTypes wait;
  private Configuration config;
  private OtherMethods otherMethods;

  /**
   * Constructor for MainMethods.
   * Initializes all dependencies with the provided WebDriver instance.
   * 
   * @param driver - the WebDriver instance to be used for browser automation.
   */
  public MainMethods(WebDriver driver) {
	  if (driver == null) {
	    throw new IllegalArgumentException("WebDriver cannot be null!");
	  }
	  this.driver = driver;
	  this.wait = new WaitTypes(driver);
	  this.config = new Configuration();
	  this.otherMethods = new OtherMethods();
  }

  /**
   * Add here more Selenium methods in this class if its needed.
   * If you want to turn on the messages in the methods you need to set the value to 'on' of the 'messages' variable from the 'configuration.Configuration' class.
   */
  
  /**
   * Helper method to convert locator type string to Selenium By object.
   * This eliminates code duplication between getElement() and getElementList().
   * 
   * @param locator - the locator value.
   * @param type - the locator type (id, xpath, cssSelector, name, linkText, partialLinkText, className, tagName).
   * @return - the By object corresponding to the locator type.
   * @throws IllegalArgumentException if the locator type is not supported.
   */
  private By getByLocator(String locator, String type) {
    type = type.toLowerCase();
    
    if ("id".equals(type)) {
      return By.id(locator);
    } else if ("xpath".equals(type)) {
      return By.xpath(locator);
    } else if ("cssselector".equals(type)) {
      return By.cssSelector(locator);
    } else if ("name".equals(type)) {
      return By.name(locator);
    } else if ("linktext".equals(type)) {
      return By.linkText(locator);
    } else if ("partiallinktext".equals(type)) {
      return By.partialLinkText(locator);
    } else if ("classname".equals(type)) {
      return By.className(locator);
    } else if ("tagname".equals(type)) {
      return By.tagName(locator);
    } else {
      throw new IllegalArgumentException("Locator type '" + type + "' is not supported. Supported types: id, xpath, cssSelector, name, linkText, partialLinkText, className, tagName.");
    }
  }

  /**
   * This method is used to get element by locator.
   *   
   * @param locator - provide the locator of the element.
   * @param type - provide the locator type (id, xpath, cssSelector, name, linkText, partialLinkText, className, tagName).
   * @return - the element with used locator will be returned.
   * @throws ElementNotFoundException if the element cannot be found.
   * @throws IllegalArgumentException if the locator type is not supported.
   */
  public WebElement getElement(String locator, String type) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    
    try {
      otherMethods.messagesMethod("Message: Trying to find element with locator '" + locator + "' and type '" + type + "'.");
      
      By by = getByLocator(locator, type); // ✅ Използваме helper метода!
      return driver.findElement(by); // ✅ Само 1 ред вместо 30!
      
    } catch (org.openqa.selenium.NoSuchElementException e) {
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
      throw new ElementNotFoundException(
        "Element not found in DOM tree",
        locator,
        type,
        e
      );
    } catch (IllegalArgumentException e) {
      System.out.println("ERROR! " + e.getMessage());
      throw e;
    } catch (Exception e) {
      System.out.println("ERROR! The operation was not complete. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e);
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
      throw new RuntimeException("Unexpected error while finding element with locator '" + locator + "' and type '" + type + "'.", e);
    }
  }

  /**
   * This method is used to get elements by locator.
   * Note: Unlike getElement(), this method returns an empty list (not exception) if no elements are found,
   * which is the standard Selenium behavior for findElements().
   * 
   * @param locator - provide the locator of the element.
   * @param type - provide the locator type (id, xpath, cssSelector, name, linkText, partialLinkText, className, tagName).
   * @return - the list of elements with used locator will be returned. Returns empty list if no elements found.
   * @throws IllegalArgumentException if the locator type is not supported.
   */
  public List<WebElement> getElementList(String locator, String type) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    
    try {
      otherMethods.messagesMethod("Message: Trying to find elements with locator '" + locator + "' and type '" + type + "'.");
      
      By by = getByLocator(locator, type); // ✅ Използваме helper метода!
      return driver.findElements(by); // ✅ Само 1 ред вместо 30!
      
    } catch (IllegalArgumentException e) {
      System.out.println("ERROR! " + e.getMessage());
      throw e;
    } catch (Exception e) {
      System.out.println("ERROR! The operation was not complete. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e);
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
      throw new RuntimeException("Unexpected error while finding elements with locator '" + locator + "' and type '" + type + "'.", e);
    }
  }

  /**
   * The method is used to check if the element is present in the DOM tree.
   *
   * @param locator - provide the locator of the element.
   * @param type - provide the locator type (id, xpath, cssSelector, name, linkText, partialLinkText, className, tagName).
   * @return - a boolean result. Returns 'true' if the element is available in the DOM tree, 'false' otherwise.
   */
  public boolean isElementPresent(String locator, String type) {
    List<WebElement> elementList = getElementList(locator, type);
    int size = elementList.size();
    boolean isPresent = size > 0;

    if ("yes".equals(config.messages)) {
      otherMethods.messagesMethod("Message: Trying to find element with locator '" + locator + "' and type '" + type + "'. Result: " + (isPresent ? "Found" : "Not found"));
    } 

    return isPresent; 
  }
   
  /**
   * This method will open a new tab window.
   * 
   * @param browserWindow			- provide a browser tab number where we want to switch.
   * @param url           			- provide a destination URL.
   * @param element      			- provide an element. The method will wait until this element is displayed.
   */
  public void openNewBrowserTab(int browserWindow, String url, WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      ((JavascriptExecutor) driver).executeScript("window.open()"); // We can open a new tab window using the
      // JavascriptExecutor.
      ArrayList < String > tabs = new ArrayList < String > (driver.getWindowHandles()); // Declare an ArrayList used for
      // different browser tabs.
      driver.switchTo().window(tabs.get(browserWindow)); // Switch to the selected browser tab.
      driver.get(url); // Navigate to URL.
      Assert.assertEquals(driver.getCurrentUrl(), url); // Assert that the URL that browser load is the expected URL.
      wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen.
      otherMethods.messagesMethod("Message: Opening a new broser tab and navigate to URL '" + url + "'. Then the automation is wating for WebElement '" + element + "' to be displayed.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * This method is used for navigating to URL.
   * 
   * @param url				- provide a destination URL.
   * @param element			- provide an element. The method will wait until this element is displayed. 
   */
  public void navigateURL(String url, WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      driver.get(url); // Navigate to URL.
      Assert.assertEquals(driver.getCurrentUrl(), url); // Assert that the URL that browser load is the expected URL.
      wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen.
      otherMethods.messagesMethod("Message: Navigate to URL '" + url + "'. Then the automation is wating for WebElement '" + element + "' to be displayed.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.    
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * This method is used for inputing text into the text element.
   * 
   * @param filledData				- provide a data for filling the input text element.
   * @param element					- provide an element. The element should be input text element.
   */
  public void fillWithText(String filledData, WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
      webElement.clear(); // Clear the input text element.
      webElement.sendKeys(filledData); // Fill the input text element with data.
      String actualResult = webElement.getAttribute("value"); // Get the actual result string.
      Assert.assertEquals(actualResult, filledData); // Make an assertion to make sure that the method was executed correctly.
      otherMethods.messagesMethod("Message: Clearing the input text element. Filling with '" + filledData + "' data into the WebElement '" + element + "'. Asserting the '" + actualResult + "' with '" + filledData + "' to make sure that the method was compleated correctly.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * This method is used for inputing text into the text element.
   * 
   * @param filledData				- provide a data for filling the input text element.
   * @param element					- provide an element. The element should be input text element.
   */
  public void fillWithTextWithClearingUsingCtrlA(String filledData, WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
      webElement.sendKeys(Keys.CONTROL, "a");
      webElement.sendKeys(filledData); // Fill the input text element with data.
      String actualResult = webElement.getAttribute("value"); // Get the actual result string.
      Assert.assertEquals(actualResult, filledData); // Make an assertion to make sure that the method was executed correctly.
      otherMethods.messagesMethod("Message: Clearing the input text element. Filling with '" + filledData + "' data into the WebElement '" + element + "'. Asserting the '" + actualResult + "' with '" + filledData + "' to make sure that the method was compleated correctly.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * This method is used for inputing text into the text element without clearing it before.
   * 
   * @param filledData 			- provide a data for filling the input text element.
   * @param element				- provide an element. The element should be input text element.
   */
  public void fillWithTextWithoutClearing(String filledData, WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
      webElement.sendKeys(filledData); // Fill the input text element with data.
      String actualResult = webElement.getAttribute("value"); // Get the actual result string.
      Assert.assertEquals(actualResult, filledData); // Make an assertion to make sure that the method was executed correctly.
      otherMethods.messagesMethod("Message: Filling with '" + filledData + "' data into the WebElement '" + element + "'. Asserting the '" + actualResult + "' with '" + filledData + "' to make sure that the method was compleated correctly.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Click using click() method.
   * 
   * @param element				- provide an element. The element will be used for click().
   * @param confirmElement		- provide an element. The method will wait until this element is displayed. 
   */
  public void clickMethod(WebElement element, WebElement confirmElement) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      if (element.isEnabled()) {
        WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
        webElement.click(); // Click over the element.
        wait.waitIsDisplayed(confirmElement); // Wait the WebElement to be displayed on the screen.
        otherMethods.messagesMethod("Message: The WebElement '" + element + "' is displayed and can be clicked. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
      } else {
        System.out.println("ERROR! The WebElement '" + element + "' is NOT displayed and CAN'T be clicked.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Click using click() method and open a new browser tab.
   * 
   * @param element				- provide an element. The element will be used for click().
   * @param confirmElement		- provide an element. The method will wait until this element is displayed.
   */
  public void clickMethodOpenNewBrowserTab(int browserWindow, WebElement element, WebElement confirmElement) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      if (element.isEnabled()) {
        WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
        webElement.click(); // Click over the element.
        ArrayList < String > tabs = new ArrayList < String > (driver.getWindowHandles()); // Declare an ArrayList used for different browser tabs.
        driver.switchTo().window(tabs.get(browserWindow)); // Switch to the selected browser tab.
        wait.waitIsDisplayed(confirmElement); // Wait the WebElement to be displayed on the screen.
        otherMethods.messagesMethod("Message: The WebElement '" + element + "' is displayed and can be clicked. Then a new browser tab is oppened. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
      } else {
        System.out.println("ERROR! The WebElement '" + element + "' is NOT displayed and CAN'T be clicked.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Click using enter button.
   * 
   * @param element      		 - provide an element. The element will be used for click().
   * @param confirmElement   	 - provide an element. The method will wait until this element is displayed.
   */
  public void clickEnterButton(WebElement element, WebElement confirmElement) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      if (element.isEnabled()) {
        WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
        webElement.sendKeys(Keys.ENTER); // Press the 'enter' button against the element.
        wait.waitIsDisplayed(confirmElement); // Wait the WebElement to be displayed on the screen.
        otherMethods.messagesMethod("Message: The WebElement '" + element + "' is displayed and can be clicked by pressing the 'ENTER' key. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
      } else {
        System.out.println("ERROR! The WebElement '" + element + "' is NOT displayed and CAN'T be clicked.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Click mouse right button.
   * 
   * @param element    	    	 - provide an element. The element will be used for right click.
   * @param confirmElement   	 - provide an element. The method will wait until this element is displayed.
   */
  public void clickRightMouseButton(WebElement element, WebElement confirmElement) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      if (element.isEnabled()) {
        Actions actions = new Actions(driver); // Declare and instance of Actions.
        WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
        actions.contextClick(webElement).perform(); // Execute the right click of the mouse action over the element.
        wait.waitIsDisplayed(confirmElement); // Wait the WebElement to be displayed on the screen.
        otherMethods.messagesMethod("Message: The WebElement '" + element + "' is displayed and can be clicked by pressing the RIGHT MOUSE button. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
      } else {
        System.out.println("ERROR! The WebElement '" + element + "' is NOT displayed and CAN'T be clicked.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Double click left button.
   * 
   * @param element      	  - provide an element. The element will be used for click().
   * @param confirmElement    - provide an element. The method will wait until this element is displayed.
   */
  public void doubleClickMouseLeftButton(String expectedResult, WebElement element, WebElement confirmElement) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      if (element.isEnabled()) {
        Actions actions = new Actions(driver); // Declare and instance of Actions.
        WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
        actions.doubleClick(webElement).perform(); // Execute the double click action of the left mouse against the element.
        wait.waitIsDisplayed(confirmElement); // Wait the WebElement to be displayed on the screen.
        otherMethods.messagesMethod("Message: The WebElement '" + element + "' is displayed and can be clicked by pressing the LEFT MOUSE button TWICE. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
      } else {
        System.out.println("ERROR! The WebElement '" + element + "' is NOT displayed and CAN'T be clicked.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Click using click() method for check boxes and radio buttons with verify if they ware checked.
   * 
   * @param element			 - provide an element. The element should be check box or radio button. The element will be click().
   */
  public void clickCheckBoxRadioButton(WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
      if (webElement.isEnabled()) { // We need to check if the element is enabled. If not - the element can't be clicked.
        webElement.click(); // Click over the element.
        otherMethods.messagesMethod("Message: The WebElement '" + element + "' is displayed and enabled and can be checked.");
        if (!webElement.isSelected()) {
          System.out.println("ERROR! The WebElement '" + webElement + "' was NOT CHECKED."); // Show this message in the console output if the element is not selected.
        }
      } else {
        System.out.println("ERROR! The WebElement: " + element + " is not displayed and CAN'T be CHECKED."); // Show this message in the console output if the element is not displayed.
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Click using click() method for check boxes and radio buttons WITHOUT verify if they ware checked.
   * 
   * @param element 		- provide an element. The element should be check box or radio button. The element will be click().
   */
  public void clickCheckBoxRadioButtonWithoutVerify(WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
      if (webElement.isEnabled()) { // We need to check if the element is enabled. If not - the element can't be clicked.
        webElement.click(); // Click over the element.
        otherMethods.messagesMethod("Message: The WebElement '" + element + "' is displayed and enabled and can be checked.");
      } else {
        System.out.println("ERROR! The element: " + element + " is NOT enabled and CAN'T be clicked."); // Show this message in the console output if the element is disabled.
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Select drop-down list by visible text.
   * 
   * @param visibleText		 - provide a drop-down visible text value.
   * @param element   		  - provide an element. The element should be drop-down list.
   */
  public void selectDropDownListByVisibleText(String visibleText, WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      if (element.isEnabled()) {
        Select dropDownList = new Select(wait.waitIsEnabled(element)); // Wait the WebElement to be displayed on the screen and assign it to the variable. Finally select the element, because we will use a drop-down menu.
        dropDownList.selectByVisibleText(visibleText); // Select the drop-down value by visible text. 
        WebElement dropDownSelectedOption = dropDownList.getFirstSelectedOption(); // Get the selected option and assign it to WebElement variable.
        String dropDownVlaue = dropDownSelectedOption.getText(); // Get the actual result string.
        Assert.assertEquals(dropDownVlaue, visibleText); // Make an assertion to make sure that the method was executed correctly.
        otherMethods.messagesMethod("Message: Selecting by visible text '" + visibleText + "' from the drop-down list '" + element + "'. Asserting the '" + dropDownVlaue + "' with '" + visibleText + "' to make sure that the method was compleated correctly.");
      } else {
        System.out.println("ERROR! The element '" + element + "' is NOT enabled and can't be clicked.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Select drop-down list by index.
   * 
   * @param index   		- provide a drop-down index value.
   * @param element 		- provide an element. The element should be drop-down list.
   */
  public void selectDropDownListByIndex(int index, WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      if (element.isEnabled()) {
        Select dropDownList = new Select(wait.waitIsEnabled(element)); // Wait the WebElement to be displayed on the screen and assign it to the variable. Finally select the element, because we will use a drop-down menu.
        List < WebElement > options = dropDownList.getOptions(); // Get all drop down list option values and assign them to the list.
        WebElement dropDownValue = options.get(index); // Get the selected option and assign it to WebElement variable.
        String expectedResult = dropDownValue.getText(); // Get the expected result value.
        dropDownList.selectByIndex(index); // Select the drop-down value by index.
        WebElement dropDownSelectedOption = dropDownList.getFirstSelectedOption(); // Get the selected option and assign it to WebElement variable.
        String actualResult = dropDownSelectedOption.getText(); // Get the actual result string.
        Assert.assertEquals(actualResult, expectedResult); // Make an assertion to make sure that the method was executed correctly.
        otherMethods.messagesMethod("Message: Selecting by index '" + index + "' from the drop-down list '" + element + "'. Asserting the '" + actualResult + "' with '" + expectedResult + "' to make sure that the method was compleated correctly.");
      } else {
        System.out.println("ERROR! The element '" + element + "' is not enabled and can't be clicked.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Select drop-down list by value.
   * 
   * @param value   		- provide a drop-down attribute value.
   * @param element 		- provide an element. The element should be drop-down list.
   */
  public void selectDropDownListByValue(String value, WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      if (element.isEnabled()) {
        Select dropDownList = new Select(wait.waitIsEnabled(element)); // Wait the WebElement to be displayed on the screen and assign it to the variable. Finally select the element, because we will use a drop-down menu.
        dropDownList.selectByValue(value); // Select the drop-down by value.
        WebElement dropDownSelectedOption = dropDownList.getWrappedElement();
        String dropDownVlaue = dropDownSelectedOption.getAttribute("value"); // Get the attribute value of the element.
        Assert.assertEquals(dropDownVlaue, value); // Make an assertion to make sure that the method was executed correctly.
        otherMethods.messagesMethod("Message: Selecting value '" + value + "' from the drop-down list '" + element + "'. Asserting the '" + dropDownVlaue + "' with '" + value + "' to make sure that the method was compleated correctly.");
      } else {
        System.out.println("ERROR! The element '" + element + "' is not enabled and can't be clicked.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Select drop-down list by clicking.
   * 
   * @param selectorDropDownList - provide an element. The element should be drop-down list.
   * @param element              - provide an element. The element should be the drop-down list value.
   */
  public void selectDropDownListByClick(WebElement dropDownListElement, WebElement dropDownListValueElement) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      if (dropDownListElement.isEnabled()) {
        WebElement dropDownList = wait.waitIsDisplayed(dropDownListElement); // Wait the WebElement to be displayed on the screen and assign it to the variable.
        dropDownList.click(); // Click over the element.
        WebElement dropDownListValue = wait.waitIsDisplayed(dropDownListValueElement); // Wait the WebElement to be displayed on the screen and assign it to the variable.
        String expectedResult = dropDownListValue.getText(); // Get the drop-down value text. This will be used for expected result.
        dropDownListValue.click(); // Click over the element.
        Select selectDropDownList = new Select(dropDownListElement); // Select the drop-down list.
        WebElement dropDownSelectedOption = selectDropDownList.getFirstSelectedOption(); // Get the selected drop-down list value.
        String actualResult = dropDownSelectedOption.getText(); // Assign the selected drop-down list value to a variable. This will be used for actual result.
        Assert.assertEquals(actualResult, expectedResult); // Make an assertion, to make sure that the method was executed correctly.
        otherMethods.messagesMethod("Message: Selecting the drop-down list '" + dropDownListElement + "' with value by clicking the WebElement '" + dropDownListValueElement + "'. Asserting the '" + actualResult + "' with '" + expectedResult + "' to make sure that the method was compleated correctly.");
      } else {
        System.out.println("ERROR! The element '" + dropDownListElement + "' is not enabled and can't be clicked.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Copy the text from element.
   * 
   * @param element - provide an element. The element should be used for getText().
   * @return - the copied text from the element will be returned.
   * @throws RuntimeException if the text cannot be retrieved from the element.
   */
  public String getText(WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    
    try {
      WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
      String text = webElement.getText(); // Assign the text value to the variable.
      otherMethods.messagesMethod("Message: Waiting and getting the text from the WebElement '" + element + "'. The copied text is: '" + text + "'.");
      return text; // Return the text values.
    } catch (Exception e) {
      System.out.println("ERROR! The operation was not complete. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e);
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
      
      // Throw exception instead of returning error message as text
      throw new RuntimeException("Failed to get text from element. Element: " + element, e);
    }
  }

  /** 
   * Accept the Alert (pop-up window).
   * 
   * @param element			- provide an WebElement. The click() method on the element should open an alert window.
   * @param expectedReult	- provide a String that should be the alert text. This will be used for expected result.
   */
  public void AcceptTheAlert(WebElement element, String expectedReult) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      element.click(); // Click on the button to show the pop-up window.
      WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(config.timeOut)); // Declare a wait.
      wait.until(ExpectedConditions.alertIsPresent()); // Wait until the pop-up is present.
      String getThePopUpText = driver.switchTo().alert().getText(); // Get the text of the pop-up window.
      Assert.assertEquals(getThePopUpText, expectedReult); // Make an assertion to make sure that the pop-up is opened.
      driver.switchTo().alert().accept(); // Press on the "OK" button of the pop-up window.
      otherMethods.messagesMethod("Message: The WebElement '" + element + "' is clicked and ALERT pop-up window is opened. The automation is switching to the ALERT pop-up window. The automation is getting the ALERT pop-up window text '" + getThePopUpText + "'. Asserting the '" + getThePopUpText + "' with '" + expectedReult + "' to make sure that the method was compleated correctly. Click on the 'OK' button to accept the ALERT pop-up window.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /** 
   * Dismiss the Alert (pop-up window).
   * 
   * @param element			- provide an WebElement. The click() method on the element should open an alert window.
   * @param expectedReult	- provide a String that should be the alert text. This will be used for expected result.
   */
  public void DismissTheAlert(WebElement element, String expectedReult) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      element.click(); // Click on the button to show the pop-up window.
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(config.timeOut)); // Declare a wait.
      wait.until(ExpectedConditions.alertIsPresent()); // Wait until the pop-up is present.
      String getThePopUpText = driver.switchTo().alert().getText(); // Get the text of the pop-up window.
      Assert.assertEquals(getThePopUpText, expectedReult); // Make an assertion to make sure that the pop-up is opened.
      driver.switchTo().alert().dismiss(); // Press on the "Cancel" button of the pop-up window.
      otherMethods.messagesMethod("Message: The WebElement '" + element + "' is clicked and ALERT pop-up window is opened. The automation is switching to the ALERT pop-up window. The automation is getting the ALERT pop-up window text '" + getThePopUpText + "'.Asserting the '" + getThePopUpText + "' with '" + expectedReult + "' to make sure that the method was compleated correctly. Click on the 'Cancel' button to dismiss the ALERT pop-up window.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /** 
   * Accept the Alert (pop-up window).
   * 
   * @param element			- provide an WebElement. The click() method on the element should open an alert window.
   * @param expectedReult	- provide a String that should be the alert text. This will be used for expected result.
   * @param text			- provide a text. The text will be used to fill the pop-up window input text element.
   */
  public void fillWithTextInToTheAlert(WebElement element, String expectedReult, String text) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      element.click(); // Click on the button to show the pop-up window.
      WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(config.timeOut)); // Declare a wait.
      wait.until(ExpectedConditions.alertIsPresent()); // Wait until the pop-up is present.
      String getThePopUpText = driver.switchTo().alert().getText(); // Get the text of the pop-up window.
      Assert.assertEquals(getThePopUpText, expectedReult); // Make an assertion to make sure that the pop-up is opened.
      driver.switchTo().alert().sendKeys(text); // Fill with the text into the alert window.
      driver.switchTo().alert().accept(); // Press on the "OK" button of the pop-up window.
      otherMethods.messagesMethod("Message: The WebElement '" + element + "' is clicked and ALERT pop-up window is opened. The automation is switching to the ALERT pop-up window. The automation is getting the ALERT pop-up window text '" + getThePopUpText + "'.Asserting the '" + getThePopUpText + "' with '" + expectedReult + "' to make sure that the method was compleated correctly. Sending testing data '" + text + "' to fill the ALERT pop-up window. Click on the 'OK' button to accept the ALERT pop-up window.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * Check how many iFrames has the current page.
   * 
   * @return				- all number of the iFrames will be returned.
   */
  public int checkHowManyIframesThePageHas() {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      int allIframesInThePage = driver.findElements(By.tagName("iframe")).size();
      otherMethods.messagesMethod("Message: Checking how many iFrames contains the page.");
      if (allIframesInThePage != 0) {
        otherMethods.messagesMethod("Message: There is/are '" + allIframesInThePage + "' iFrame/s in this page.");
        return allIframesInThePage;
      } else {
        otherMethods.messagesMethod("Message: The page doesn't contains any iFrames.");
        return 0;
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
    return 0;
  }

  /**
   * This method will be used to switch to the iFrame by ID.
   * 
   * @param locatorId				- provide a locator ID. The locator should be of the iFrame element.
   * @param confirmElement			- provide a WebElement. The element should be located into the iFrame code. The WebElement should be used for waitIsDisplayed.
   */
  public void changeToIframeById(String locatorId, WebElement confirmElement) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      driver.switchTo().frame(locatorId); // Switch to iFrame by ID.
      wait.waitIsDisplayed(confirmElement); // Wait the WebElement to be displayed on the screen.
      otherMethods.messagesMethod("Message: Switching the focus to the iFrame by ID '" + locatorId + "'. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * This method will be used to switch to the iFrame by Index.
   * 
   * @param locatorIdenx			- provide an index used for iFrame that we want to switch.
   * @param confirmElement			- provide a WebElement. The element should be located into the iFrame code. The WebElement should be used for waitIsDisplayed.
   */
  public void changeToIframeByIndex(int locatorIndex, WebElement confirmElement) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      driver.switchTo().frame(locatorIndex); // Switch to iFrame by index.
      wait.waitIsDisplayed(confirmElement); // Wait the WebElement to be displayed on the screen.
      otherMethods.messagesMethod("Message: Switching the focus to the iFrame by index '" + locatorIndex + "'. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * This method will be used to switch back from the iFrame to the default HTML page.
   * 
   * @param confirmElement			- provide a WebElement. The element should be located outside of the iFrame code. The WebElement should be used for waitIsDisplayed.
   */
  public void changeFromIframeToDefaultHTML(WebElement confirmElement) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      driver.switchTo().defaultContent(); // Switch to the default HTML page.
      wait.waitIsDisplayed(confirmElement); // Wait the WebElement to be displayed on the screen.
      otherMethods.messagesMethod("Message: Switching (back) the focus to default HTML after working with iFrame. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * This method is used to create screenshot when something goes wrong with some method from this class.
   */
  public void takeScreenShotInMethod() {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      String getCalledMethodName = otherMethods.getCallerMethodName(3);
      String getCalledClassName = otherMethods.getCallerClassName(3);
      String filename = "Screenshot_taken_when_executed_testMethod_" + getCalledMethodName + "_from_testClass_" + getCalledClassName + "_at_time_" + otherMethods.unixTime() + ".png"; // Declare a file name.
      String directory = System.getProperty("user.dir") + "//screenshots//"; // Declare destination path for creating a new screenshot.
      File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Get the screenshot.
      FileUtils.copyFile(sourceFile, new File(directory + filename)); // Copy the screenshot to declared destination path with declared file name.
      otherMethods.messagesMethod("Message: Creating a screenshot when method fails.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
  }

  /**
   * This method is used to create screenshot when the test fails (this is used in @AfterMethod annotation).
   */
  public void takeScreenShotInAfterMethod(String executedMethodName) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      String getCalledClassName = otherMethods.getCallerClassName(2);
      String filename = "Screenshot_taken_because_test_fails_when_executed_testMethod_" + executedMethodName + "_from_testClass_" + getCalledClassName + "_at_time_" + otherMethods.unixTime() + ".png"; // Declare a file name.
      String directory = System.getProperty("user.dir") + "//screenshots//"; // Declare destination path for creating a new screenshot.
      File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Get the screenshot.
      FileUtils.copyFile(sourceFile, new File(directory + filename)); // Copy the screenshot to declared destination path with declared file name.
      otherMethods.messagesMethod("Message: Creating a screenshot when the @Test fails.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
  }

  /**
   * This method is used checking element attribute value.
   * 
   * @param attribute							- provide the attribute name.
   * @param attributeValueExapectation			- provide the attribute value.
   * @param element								- provide an element. The element should be input text element.
   */
  public void getAttributeValue(String attribute, String attributeValueExapectation, WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
      String actualResult = webElement.getAttribute(attribute); // Get the actual result string.
      Assert.assertEquals(actualResult, attributeValueExapectation); // Make an assertion to make sure that the method was executed correctly.
      otherMethods.messagesMethod("Message: Getting the attribute data from element '" + element + "' from attribute '" + attribute + "'. Asserting the '" + actualResult + "' with '" + attributeValueExapectation + "' to make sure that the method was compleated correctly.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * This method is used to check if the located image is available or it is broken.
   * ✅ UPDATED: Now properly returns boolean and throws exception on error instead of returning null.
   * 
   * @param element - please provide an image WebElement.
   * @return - returns 'true' if the image is fully loaded on the UI, 'false' if the image is broken.
   * @throws RuntimeException if unable to check the image status.
   */
  public boolean checkIfTheImageIsNotBroken(WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    
    try {
      String naturalWidth = element.getAttribute("naturalWidth");
      
      if ("0".equals(naturalWidth)) {
        otherMethods.messagesMethod("Message: The located image is broken.");
        return false;
      } else {
        otherMethods.messagesMethod("Message: The located image is available.");
        return true;
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operation was not complete. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e);
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
      
      throw new RuntimeException("Failed to check if image is broken. Element: " + element, e);
    }
  }

}