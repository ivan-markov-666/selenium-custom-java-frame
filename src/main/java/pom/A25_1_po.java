package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class A25_1_po {

  public WebDriver driver;

  public A25_1_po(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // we need this to be declare here. Without this we will not be able to use the @FindBy annotation.
  }

  // Declare a selectors here.
  @FindBy(id = "userName")
  public WebElement fullNameInputTextElement;
}