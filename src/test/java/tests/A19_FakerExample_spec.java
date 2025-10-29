/**
 * Description:
 * This class is used to show how to use dynamic generating of testing data using external library.
 * The used library is java-faker: https://github.com/DiUS/java-faker
 */

package tests;

import org.testng.annotations.Test;
import base.BaseTestClass;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class A19_FakerExample_spec extends BaseTestClass {

  /*
   * This example shows how:
   * - To use dynamic testing data. This data is generated from the faker library, and it's different whenever we execute the class.
   */
  @Test()
  public void finalTestView() throws UnsupportedFlavorException, IOException {
    // Define the imputed data.
    for (int i = 1; i < 3; i++) {
      String email = faker.internet().safeEmailAddress();
      String firstName = faker.name().firstName();
      String lastName = faker.name().lastName();
      String cellPhone = faker.phoneNumber().cellPhone();
      String country = faker.address().country();
      String countryCode = faker.address().countryCode();
      String city = faker.address().cityName();
      String fullAddress = faker.address().fullAddress();
      String birthday = faker.date().birthday().toString();
      String loremIpsumText = faker.lorem().paragraph();
      String bookName = faker.book().title();
      String animalName = faker.animal().name();

      // Print the values to the console.
      System.out.println("Email " + i + ": " + email);
      System.out.println("First Name " + i + ": " + firstName);
      System.out.println("Last Name " + i + ": " + lastName);
      System.out.println("Cell Phone Number " + i + ": " + cellPhone);
      System.out.println("Country " + i + ": " + country);
      System.out.println("Country Code " + i + ": " + countryCode);
      System.out.println("City " + i + ": " + city);
      System.out.println("Full Address " + i + ": " + fullAddress);
      System.out.println("Birthday " + i + ": " + birthday);
      System.out.println("Text " + i + ": " + loremIpsumText);
      System.out.println("Book Name " + i + ": " + bookName);
      System.out.println("Animal " + i + ": " + animalName);

      System.out.println("\n");
    }
  }

  @BeforeClass
  public void beforeClass() {
    setUp(null);
  }

  @AfterClass
  public void afterClass() {
    terminate();
  }

}