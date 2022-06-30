/**
 * Description:
 * This class shows how to download files from the web using Chrome browser.
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import custom_methods.WaitTypes;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import java.util.HashMap; // Import a HashMap library. This library is using for supporting the ChromeOptions to download a file from the browser.
import java.util.Map; // Import a Map library. This library is using for supporting the ChromeOptions to download a file from the browser.
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // Import a ChromeOptions library. With this library we are able to download file using Chrome browser.
import org.testng.annotations.AfterClass;

public class A14_2_DownloadFileChrome_spec {

	private static WebDriver driver;
	private static String baseURL;
	private static WaitTypes wait;
	private static ChromeOptions options; // Declare a variable for ChromeOptions.
	private static Map<String, Object> prefs; // Declare a variable for Map.

	/*
	 * This example shows how:
	 * - To download file with Chrome.
	 */
	@Test
	public void downloadFile() {
		WebElement downloadFileButton = driver.findElement(By.xpath("//*[@id='downloadButton']"));
		downloadFileButton.click();
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		wait = new WaitTypes(driver);
		baseURL = "https://demoqa.com/";
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45)); // Set Implicit Wait.
		driver.get(baseURL + "upload-download");
		// Define Download Option for Chrome Browser;
		options = new ChromeOptions();
		prefs = new HashMap<String, Object>();
		prefs.put("download.prompt_for_download", false);
		options.setExperimentalOption("prefs", prefs);
	}

	@AfterClass
	public void afterClass() {
		wait.staticWait(10); // We need this static wait because we need to finish the downloading file.
		driver.quit();
	}
}
