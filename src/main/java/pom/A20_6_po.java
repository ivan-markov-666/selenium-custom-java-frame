package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class A20_6_po {
	
	public WebDriver driver;

	public A20_6_po(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // we need this to be declare here. Without this we will not be able to use the @FindBy annotation.
	}

	 // Declare a selectors here.
	 @FindBy(xpath= "(//button[@data-clipboard-action='copy'])[2]")
	 public WebElement copyEmail;
	 
	 @FindBy(xpath= "//div[@class='temp-emailbox']/h2")
	 public WebElement confirmText10minutesMail_Page;
	
	 @FindBy(id = "firstName")
	 public WebElement firstName;
	 
	 @FindBy(id = "lastName")
	 public WebElement lastName;
	 
	 @FindBy(id = "userEmail")
	 public WebElement email;
	 
	 @FindBy(xpath = "//*[@id='gender-radio-1']/following-sibling::label")
	 public WebElement gender;
	 
	 @FindBy(id = "userNumber")
	 public WebElement phone;
	 
	 @FindBy(id = "dateOfBirthInput")
	 public WebElement dateOfBirth;
	 
	 @FindBy(xpath = "//*[@id='hobbies-checkbox-1']/following-sibling::label")
	 public WebElement hobbie;
	 
	 @FindBy(id = "uploadPicture")
	 public WebElement uploadFile;
	 
	 @FindBy(id = "currentAddress")
	 public WebElement currentAddress;
	 
	 @FindBy(id = "submit")
	 public WebElement submitButton;	

	 @FindBy(id = "example-modal-sizes-title-lg")
	 public WebElement confirmTextAutomationPracticeForm_PopUp;	
}
