package pom;

import org.openqa.selenium.By;

public class A20_4_po {

  // Define a locators here. The locators are not selectors. Make sure that you know the difference!
  public By copyEmail = By.xpath("(//button[@data-clipboard-action='copy'])[2]");
  public By firstName = By.id("firstName");
  public By lastName = By.id("lastName");
  public By email = By.id("userEmail");
  public By gender = By.xpath("//*[@id='gender-radio-1']/following-sibling::label");
  public By phone = By.id("userNumber");
  public By dateOfBirth = By.id("dateOfBirthInput");
  public By hobbie = By.xpath("//*[@id='hobbies-checkbox-1']/following-sibling::label");
  public By uploadFile = By.id("uploadPicture");
  public By currentAddress = By.id("currentAddress");
  public By submitButton = By.id("submit");
}