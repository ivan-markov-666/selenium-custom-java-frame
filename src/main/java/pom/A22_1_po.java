package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class A22_1_po {

  public WebDriver driver;

  public A22_1_po(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // we need this to be declare here. Without this we will not be able to use the @FindBy annotation.
  }

  // Declare a selectors here.
  @FindBy(id = "framesWrapper")
  public WebElement verifyUrlLoaded;
  
  @FindBy(id = "sampleHeading")
  public WebElement iFrameText1;
  
  @FindBy(xpath = "(//*[@id='framesWrapper']/div)[1]")
  public WebElement getThisText;
}