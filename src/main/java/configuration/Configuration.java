package configuration;

public class Configuration {

	/**
	 * Add here more configuration values in this class if its needed.
	 */

	/** Declare a timeout value. The value is in seconds. */
	public final int timeOut = 15;
	/** Declare a base URL value. */
	public static final String baseURL = "https://demoqa.com/";
	/**
	 * Declare used browser here. The values can be:
	 * - "chrome"
	 * - "firefox"
	 * ... (add another value if you add another driver to the automation frame).
	 */
	public static final String browser = "firefox";
	/** Declare the path to the browser drivers. */
	public static final String browsersDriverPath = System.getProperty("user.dir") + "\\drivers\\";
	/**
	 * Declare variable to select usage of the system property for driver or use
	 * environment variable. The values can be "yes" or "no".
	 * - "yes" we will use the system property way.
	 * - "no" we will use the environment variable way.
	 */
	public static final String useSystemPropertyForBrowserDriver = "no";
	/** Declare the folder path of the used Excel for Data Driven Testing. */
	public final String excelFilePath = System.getProperty("user.dir") + "\\ExcelData\\";
	/** Declare the Excel name that we will use for Data Driven Testing. */
	public final String excelFileName = "dataDrivenExample.xlsx";
	/** Declare the folder path of the uploaded file. */
	public final String uploadThisFilePath = System.getProperty("user.dir") + "\\uploads\\";
	/** Declare the name of the uploaded file. */
	public final String uploadThisFileName = "test.png";
	/** Declare the folder path of the download file. */
	public final String downloadFolderPath = System.getProperty("user.dir") + "\\downloads\\";
	/**
	 * Turn on and off the Messages. The values can be "on" or "off".
	 * - "on" value will turn on the messages.
	 * - "off" value will turn off the messages.
	 */
	public final String messages = "off";
}
