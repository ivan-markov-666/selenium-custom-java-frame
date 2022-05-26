package custom_methods;

import java.io.File;
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
  private WaitTypes wait = new WaitTypes(driver);
  private Configuration config = new Configuration();
  private OtherMethods otherMethods = new OtherMethods();

  public MainMethods(WebDriver driver) {
    this.driver = driver;
  }

  /**
   * Add here more Selenium methods in this class if its needed.
   * If you want to turn on the messages in the methods you need to set the value to 'on' of the 'messages' variable from the 'configuration.Configuration' class.
   */

  /**
   * This method is used to get element by locator.
   *   
   * @param locator		- provide the locator of the element.
   * @param type			- provide the locator type (id, xpath, cssSelector, name, linkText, partialLinkText, className, tagName).
   * @return				- the element with used locator will be returned.
   */
  public WebElement getElement(String locator, String type) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      type = type.toLowerCase();
      if (type.equals("id")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator id: " + locator);
        return this.driver.findElement(By.id(locator));
      } else if (type.equals("xpath")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator xpath: " + locator);
        return this.driver.findElement(By.xpath(locator));
      } else if (type.equals("cssSelector")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator css: " + locator);
        return this.driver.findElement(By.cssSelector(locator));
      } else if (type.equals("name")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator name: " + locator);
        return this.driver.findElement(By.name(locator));
      } else if (type.equals("linkText")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator linktext: " + locator);
        return this.driver.findElement(By.linkText(locator));
      } else if (type.equals("partialLinkText")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator partiallinktext: " + locator);
        return this.driver.findElement(By.partialLinkText(locator));
      } else if (type.equals("className")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator className: " + locator);
        return this.driver.findElement(By.className(locator));
      } else if (type.equals("tagName")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator tagName: " + locator);
        return this.driver.findElement(By.tagName(locator));
      } else {
        System.out.println("Locator type not supported");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
    return null;
  }

  /**
   * This method is used to get elements by locator.
   * 
   * @param locator			- provide the locator of the element.
   * @param type			- provide the locator type (id, xpath, cssSelector, name, linkText, partialLinkText, className, tagName).
   * @return				- the elements with used locator will be returned.
   */
  public List < WebElement > getElementList(String locator, String type) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      type = type.toLowerCase();
      if (type.equals("id")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator id: " + locator);
        return this.driver.findElements(By.id(locator));
      } else if (type.equals("xpath")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator xpath: " + locator);
        return this.driver.findElements(By.xpath(locator));
      } else if (type.equals("cssSelector")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator cssSelector: " + locator);
        return this.driver.findElements(By.cssSelector(locator));
      } else if (type.equals("name")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator name: " + locator);
        return this.driver.findElements(By.name(locator));
      } else if (type.equals("linkText")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator linkText: " + locator);
        return this.driver.findElements(By.linkText(locator));
      } else if (type.equals("partialLinkText")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator partialLinkText: " + locator);
        return this.driver.findElements(By.partialLinkText(locator));
      } else if (type.equals("className")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator className: " + locator);
        return this.driver.findElements(By.className(locator));
      } else if (type.equals("tagName")) {
        otherMethods.messagesMetohd("Message: Try to find element with locator tagName: " + locator);
        return this.driver.findElements(By.tagName(locator));
      } else {
        System.out.println("Locator type not supported");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
    return null;
  }

  /**
   * The method is used to check if the element is present.
   * @param locator		- provide the locator of the element.
   * @param type		- provide the locator type (id, xpath, cssSelector, name, linkText, partialLinkText, className, tagName).
   * @return			- a boolean result should be returned. If the locator is available in the DOM tree - the result will be 'true'. If the locator is NOT available in the DOM tree - the result should be 'false'.
   */
  public boolean isElemenstPresent(String locator, String type) {
    List < WebElement > elementList = getElementList(locator, type);
    int size = elementList.size();
    if (config.messages == "yes") {
      otherMethods.messagesMetohd("Message: Trying to find element with locator '" + locator + "' and type '" + type + "'.");
    } else {
      if (size > 0) {
        return true;
      } else {
        return false;
      }
    }
    return false;
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
      wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen.
      otherMethods.messagesMetohd("Message: Opening a new broser tab and navigate to URL '" + url + "'. Then the automation is wating for WebElement '" + element + "' to be displayed.");
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
      wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen.
      otherMethods.messagesMetohd("Message: Navigate to URL '" + url + "'. Then the automation is wating for WebElement '" + element + "' to be displayed.");
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
      otherMethods.messagesMetohd("Message: Clearing the input text element. Filling with '" + filledData + "' data into the WebElement '" + element + "'. Asserting the '" + actualResult + "' with '" + filledData + "' to make sure that the method was compleated correctly.");
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
      otherMethods.messagesMetohd("Message: Clearing the input text element. Filling with '" + filledData + "' data into the WebElement '" + element + "'. Asserting the '" + actualResult + "' with '" + filledData + "' to make sure that the method was compleated correctly.");
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
      otherMethods.messagesMetohd("Message: Filling with '" + filledData + "' data into the WebElement '" + element + "'. Asserting the '" + actualResult + "' with '" + filledData + "' to make sure that the method was compleated correctly.");
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
        otherMethods.messagesMetohd("Message: The WebElement '" + element + "' is displayed and can be clicked. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
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
        otherMethods.messagesMetohd("Message: The WebElement '" + element + "' is displayed and can be clicked. Then a new browser tab is oppened. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
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
        otherMethods.messagesMetohd("Message: The WebElement '" + element + "' is displayed and can be clicked by pressing the 'ENTER' key. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
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
        otherMethods.messagesMetohd("Message: The WebElement '" + element + "' is displayed and can be clicked by pressing the RIGHT MOUSE button. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
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
        otherMethods.messagesMetohd("Message: The WebElement '" + element + "' is displayed and can be clicked by pressing the LEFT MOUSE button TWICE. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
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
        otherMethods.messagesMetohd("Message: The WebElement '" + element + "' is displayed and enabled and can be checked.");
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
        otherMethods.messagesMetohd("Message: The WebElement '" + element + "' is displayed and enabled and can be checked.");
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
        otherMethods.messagesMetohd("Message: Selecting by visible text '" + visibleText + "' from the drop-down list '" + element + "'. Asserting the '" + dropDownVlaue + "' with '" + visibleText + "' to make sure that the method was compleated correctly.");
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
        otherMethods.messagesMetohd("Message: Selecting by index '" + index + "' from the drop-down list '" + element + "'. Asserting the '" + actualResult + "' with '" + expectedResult + "' to make sure that the method was compleated correctly.");
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
        otherMethods.messagesMetohd("Message: Selecting value '" + value + "' from the drop-down list '" + element + "'. Asserting the '" + dropDownVlaue + "' with '" + value + "' to make sure that the method was compleated correctly.");
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
        otherMethods.messagesMetohd("Message: Selecting the drop-down list '" + dropDownListElement + "' with value by clicking the WebElement '" + dropDownListValueElement + "'. Asserting the '" + actualResult + "' with '" + expectedResult + "' to make sure that the method was compleated correctly.");
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
   * @param element 		- provide an element. The element should be used for getText().
   * @return				- the copied text from the element will be returned.
   */
  public String getText(WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    String text = "The text was not copied"; // We need this flag, to make sure that the text was copied.
    try {
      WebElement webElement = wait.waitIsDisplayed(element); // Wait the WebElement to be displayed on the screen and assign it to the variable.
      text = webElement.getText(); // Assign the text value to the variable.
      otherMethods.messagesMetohd("Message: Waiting and getting the text from the WebElement '" + element + "'. The copied text is returned.");
      return text; // Return the text values.
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
    return text;
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
      WebDriverWait wait = new WebDriverWait(driver, config.timeOut); // Declare a wait.
      wait.until(ExpectedConditions.alertIsPresent()); // Wait until the pop-up is present.
      String getThePopUpText = driver.switchTo().alert().getText(); // Get the text of the pop-up window.
      Assert.assertEquals(getThePopUpText, expectedReult); // Make an assertion to make sure that the pop-up is opened.
      driver.switchTo().alert().accept(); // Press on the "OK" button of the pop-up window.
      otherMethods.messagesMetohd("Message: The WebElement '" + element + "' is clicked and ALERT pop-up window is opened. The automation is switching to the ALERT pop-up window. The automation is getting the ALERT pop-up window text '" + getThePopUpText + "'. Asserting the '" + getThePopUpText + "' with '" + expectedReult + "' to make sure that the method was compleated correctly. Click on the 'OK' button to accept the ALERT pop-up window.");
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
      WebDriverWait wait = new WebDriverWait(driver, config.timeOut); // Declare a wait.
      wait.until(ExpectedConditions.alertIsPresent()); // Wait until the pop-up is present.
      String getThePopUpText = driver.switchTo().alert().getText(); // Get the text of the pop-up window.
      Assert.assertEquals(getThePopUpText, expectedReult); // Make an assertion to make sure that the pop-up is opened.
      driver.switchTo().alert().dismiss(); // Press on the "Cancel" button of the pop-up window.
      otherMethods.messagesMetohd("Message: The WebElement '" + element + "' is clicked and ALERT pop-up window is opened. The automation is switching to the ALERT pop-up window. The automation is getting the ALERT pop-up window text '" + getThePopUpText + "'.Asserting the '" + getThePopUpText + "' with '" + expectedReult + "' to make sure that the method was compleated correctly. Click on the 'Cancel' button to dismiss the ALERT pop-up window.");
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
      WebDriverWait wait = new WebDriverWait(driver, config.timeOut); // Declare a wait.
      wait.until(ExpectedConditions.alertIsPresent()); // Wait until the pop-up is present.
      String getThePopUpText = driver.switchTo().alert().getText(); // Get the text of the pop-up window.
      Assert.assertEquals(getThePopUpText, expectedReult); // Make an assertion to make sure that the pop-up is opened.
      driver.switchTo().alert().sendKeys(text); // Fill with the text into the alert window.
      driver.switchTo().alert().accept(); // Press on the "OK" button of the pop-up window.
      otherMethods.messagesMetohd("Message: The WebElement '" + element + "' is clicked and ALERT pop-up window is opened. The automation is switching to the ALERT pop-up window. The automation is getting the ALERT pop-up window text '" + getThePopUpText + "'.Asserting the '" + getThePopUpText + "' with '" + expectedReult + "' to make sure that the method was compleated correctly. Sending testing data '" + text + "' to fill the ALERT pop-up window. Click on the 'OK' button to accept the ALERT pop-up window.");
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
      otherMethods.messagesMetohd("Message: Checking how many iFrames contains the page.");
      if (allIframesInThePage != 0) {
        otherMethods.messagesMetohd("Message: There is/are '" + allIframesInThePage + "' iFrame/s in this page.");
        return allIframesInThePage;
      } else {
        otherMethods.messagesMetohd("Message: The page doesn't contains any iFrames.");
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
      otherMethods.messagesMetohd("Message: Switching the focus to the iFrame by ID '" + locatorId + "'. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
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
      otherMethods.messagesMetohd("Message: Switching the focus to the iFrame by index '" + locatorIndex + "'. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
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
      otherMethods.messagesMetohd("Message: Switching (back) the focus to default HTML after working with iFrame. Then the automation is wating for WebElement '" + confirmElement + "' to be displayed.");
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
      otherMethods.messagesMetohd("Message: Creating a screenshot when method fails.");
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
      otherMethods.messagesMetohd("Message: Creating a screenshot when the @Test fails.");
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
      otherMethods.messagesMetohd("Message: Getting the attribute data from element '" + element + "' from attribute '" + attribute + "'. Asserting the '" + actualResult + "' with '" + attributeValueExapectation + "' to make sure that the method was compleated correctly.");
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
  }

  /**
   * This method is used to check if the located image is available or it is broken.
   * 
   * @param element			- please provide a image WebElement.
   * @return					- the boolean value 'true' of 'false' to the class. the result is 'true' when the image is fully loaded to the UI. the result is 'false' when the image is broken.
   */
  public Object checkIfTheImageIsNotBroken(WebElement element) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      @SuppressWarnings("unused")
      boolean result;
      if (element.getAttribute("naturalWidth").equals("0")) {
        otherMethods.messagesMetohd("Message: The located image is broken.");
        return result = false;
      } else {
        otherMethods.messagesMetohd("Message: The located image is available.");
        return result = true;
      }

    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
      takeScreenShotInMethod(); // Take a screenshot when the method fails.
    }
    return null;
  }

}