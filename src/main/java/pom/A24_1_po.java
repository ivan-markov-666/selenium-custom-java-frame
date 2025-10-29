package pom;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class A24_1_po {

  public WebDriver driver;

  public A24_1_po(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // we need this to be declare here. Without this we will not be able to use the @FindBy annotation.
  }

  // Declare a selectors here.
  @FindBy(id = "addNewRecordButton")
  public WebElement addButton;

  @FindBy(id = "firstName")
  public WebElement firstNameInputTextElement;

  @FindBy(id = "lastName")
  public WebElement lastNameInputTextElement;

  @FindBy(id = "userEmail")
  public WebElement emailInputTextElement;

  @FindBy(id = "age")
  public WebElement ageInputTextElement;

  @FindBy(id = "salary")
  public WebElement salaryInputTextElement;

  @FindBy(id = "department")
  public WebElement departmentInputTextElement;

  @FindBy(id = "submit")
  public WebElement submitButton;

  @FindBy(xpath = "//div[@class='rt-td']")
  public List < WebElement > allTableCells;

  @FindBy(xpath = "//*[contains(text(),'Next')]")
  public WebElement nextButton;

  @FindBy(xpath = "//*[contains(text(),'Preview')]")
  public WebElement previewButton;

  @FindBy(xpath = "//span[@class='-totalPages']")
  public WebElement checkHowManyPagesContainsTheForm;
}