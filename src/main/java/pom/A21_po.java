package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class A21_po {

  public WebDriver driver;

  public A21_po(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // we need this to be declare here. Without this we will not be able to use the @FindBy annotation.
  }

  // Declare a selectors here.
  @FindBy(id = "confirmButton")
  public WebElement clickMeButton1;
  
  // Declare a selectors here.
  @FindBy(id = "promtButton")
  public WebElement clickMeButton2;
}