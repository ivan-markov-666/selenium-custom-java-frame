/**
 * Description:
 * This class shows how to download files from the web using FireFox browser.
 *  
 * ! ALERT: As you can see, the web driver path is not added in the code like system property.
 * 		    To make this example work - you need to add the web driver files (chromedriver.exe, geckodriver.exe) like environment variables.
 */

package tests;

import org.testng.annotations.Test;
import configuration.Configuration;
import custom_methods.WaitTypes;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterClass;

public class A14_3_DownloadFileFireFox_spec {

  private static WebDriver driver;
  private static String baseURL;
  private static Configuration config;
  private static WaitTypes wait;

  /*
   * This example shows how:
   * - To download file with FireFox.
   */
  @Test
  public void downloadFile() {
    WebElement downloadFileButton = driver.findElement(By.xpath("//*[@id='downloadButton']"));
    downloadFileButton.click();
    // This pause is used to wait the file to be downloaded.
    wait.staticWait(10000);
  }

  @BeforeClass
  public void setUp() throws Exception {
    config = new Configuration();

    // Define Download Option for FireFox Browser;
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("browser.download.dir", config.downloadFolderPath);
    profile.setPreference("browser.download.folderList", 2);
    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg");
    profile.setPreference("browser.download.manager.showWhenStarting", false);
    profile.setPreference("browser.helperApps.neverAsk.openFile",
      "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    profile.setPreference("browser.helperApps.alwaysAsk.force", false);
    profile.setPreference("browser.download.manager.useWindow", true);
    profile.setPreference("browser.download.manager.focusWhenStarting", true);
    profile.setPreference("browser.download.manager.showAlertOnComplete", true);
    profile.setPreference("browser.download.manager.closeWhenDone", true);
    FirefoxOptions options = new FirefoxOptions();
    options.setProfile(profile);
    driver = new FirefoxDriver(options);
    
    wait = new WaitTypes(driver);
    baseURL = "https://demoqa.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45)); // Set Implicit Wait.
    driver.get(baseURL + "upload-download");
  }

  @AfterClass
  public void afterClass() {
    wait.staticWait(10); // We need this static wait because we need to finish the downloading file.
    driver.quit();
  }
}