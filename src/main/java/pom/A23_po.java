package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class A23_po {

  public WebDriver driver;

  public A23_po(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // we need this to be declare here. Without this we will not be able to use the @FindBy annotation.
  }

  // Declare a selectors here.
  @FindBy(id = "showSmallModal")
  public WebElement smallModalButton;
  
  @FindBy(xpath = "//div[@class='modal-body']")
  public WebElement smallModalWindowTextContent;
  
  @FindBy(id = "closeSmallModal")
  public WebElement closeButton1;
  
  @FindBy(id = "showLargeModal")
  public WebElement largeModalButton;

  @FindBy(xpath = "//div[@class='modal-body']/p")
  public WebElement largeModalWindowTextContent;

  @FindBy(id = "closeLargeModal")
  public WebElement closeButton2;
}