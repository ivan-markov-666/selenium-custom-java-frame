/**
 * Description:
 * This class shows how to copy text from element containing text with Selenium method "getText()".
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class A13_CopyText_spec {

	private static WebDriver driver;
	private static String baseURL;

	/*
	 * This example shows how:
	 * - To copy text from the element containing text.
	 */
	@Test
	public void copyTextContent() {
		WebElement copyText = driver.findElement(By.xpath("(//*[@id='linkWrapper']/h5/strong)[1]"));
		String text = copyText.getText();
		System.out.println(text);
	}
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		baseURL = "https://demoqa.com/";
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45)); // Set Implicit Wait.
		driver.get(baseURL + "links");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
