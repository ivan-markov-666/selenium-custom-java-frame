package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class A22_3_po {

	public WebDriver driver;

	public A22_3_po(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // we need this to be declare here. Without this we will not be able to use the @FindBy annotation.
	}

  // Declare a selectors here.
  @FindBy(id = "framesWrapper")
  public WebElement verifyElement1;
  
  @FindBy(xpath = "//body")
  public WebElement bodyLocator;
  
  @FindBy(xpath = "//body/p")
  public WebElement paragrahLocator;
  
  @FindBy(xpath = "(//*[@id='framesWrapper']/div)[1]")
  public WebElement defaultHtmlTextLocator;
}