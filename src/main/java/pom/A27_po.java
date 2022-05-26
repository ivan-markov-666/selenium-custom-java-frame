package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class A27_po {

  public WebDriver driver;

  public A27_po(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // we need this to be declare here. Without this we will not be able to use the @FindBy annotation.
  }

  // Declare a selectors here.
  @FindBy(id = "addNewRecordButton")
  public WebElement addButton;
  
  @FindBy(id = "fakefakefake")
  public WebElement fakeLocator;
 }

