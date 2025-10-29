package configuration;

import java.io.File;

/**
 * Central configuration class for the automation framework.
 * All configuration values should be defined here to avoid hard-coding throughout the project.
 * 
 * Usage examples:
 * - Configuration.Timeouts.IMPLICIT_WAIT
 * - Configuration.Paths.SCREENSHOTS
 * - Configuration.Browser.DEFAULT
 */
public class Configuration {

	/**
	 * Add here more configuration values in this class if needed.
	 */

	// ==================== GENERAL SETTINGS ====================
	
	/**
	 * Turn on and off the messages. The values can be "on" or "off".
	 * - "on" value will turn on the messages.
	 * - "off" value will turn off the messages.
	 */
	public static final String MESSAGES = "off";

	// ==================== TIMEOUT SETTINGS ====================
	
	/**
	 * Timeout settings for waits (in seconds).
	 */
	public static class Timeouts {
		/** Default implicit wait timeout (in seconds). */
		public static final int IMPLICIT_WAIT = 15;
		
		/** Default explicit wait timeout (in seconds). */
		public static final int EXPLICIT_WAIT = 15;
		
		/** Page load timeout (in seconds). */
		public static final int PAGE_LOAD = 30;
		
		/** Script timeout (in seconds). */
		public static final int SCRIPT = 30;
	}

	// ==================== URL SETTINGS ====================
	
	/**
	 * URL configuration.
	 */
	public static class URLs {
		/** Base URL for the application under test. */
		public static final String BASE = "https://demoqa.com/";
	}

	// ==================== BROWSER SETTINGS ====================
	
	/**
	 * Browser configuration.
	 */
	public static class Browser {
		/**
		 * Browser to use for tests. Supported values:
		 * - "chrome"
		 * - "firefox"
		 * ... (add another value if you add another driver to the automation framework).
		 */
		public static final String DEFAULT = "firefox";
		
		/**
		 * Use system property for driver or environment variable.
		 * - "yes" = use system property (manual driver setup)
		 * - "no" = use WebDriverManager (automatic driver setup)
		 */
		public static final String USE_SYSTEM_PROPERTY = "yes";
		
		/** Chrome driver executable name. */
		public static final String CHROME_DRIVER_NAME = "chromedriver.exe";
		
		/** Firefox driver executable name. */
		public static final String FIREFOX_DRIVER_NAME = "geckodriver.exe";
	}

	// ==================== PATH SETTINGS ====================
	
	/**
	 * File path configuration.
	 * All paths use File.separator for cross-platform compatibility (Windows/Mac/Linux).
	 */
	public static class Paths {
		/** Project root directory. */
		private static final String PROJECT_ROOT = System.getProperty("user.dir");
		
		/** Browser drivers folder path. */
		public static final String DRIVERS = PROJECT_ROOT + File.separator + "drivers" + File.separator;
		
		/** Excel data files folder path (for Data Driven Testing). */
		public static final String EXCEL_DATA = PROJECT_ROOT + File.separator + "ExcelData" + File.separator;
		
		/** Screenshot folder path. */
		public static final String SCREENSHOTS = PROJECT_ROOT + File.separator + "screenshots" + File.separator;
		
		/** Upload test files folder path. */
		public static final String UPLOADS = PROJECT_ROOT + File.separator + "uploads" + File.separator;
		
		/** Download folder path. */
		public static final String DOWNLOADS = PROJECT_ROOT + File.separator + "downloads" + File.separator;
	}

	// ==================== DATA DRIVEN TESTING SETTINGS ====================
	
	/**
	 * Data Driven Testing configuration.
	 */
	public static class DataDriven {
		/** Default Excel file name for Data Driven Testing. */
		public static final String EXCEL_FILE_NAME = "dataDrivenExample.xlsx";
		
		/** Full path to the default Excel file. */
		public static final String EXCEL_FILE_PATH = Paths.EXCEL_DATA + EXCEL_FILE_NAME;
	}

	// ==================== FILE UPLOAD/DOWNLOAD SETTINGS ====================
	
	/**
	 * File upload/download configuration.
	 */
	public static class Files {
		/** Default test file name for upload tests. */
		public static final String UPLOAD_FILE_NAME = "test.png";
		
		/** Full path to the default upload test file. */
		public static final String UPLOAD_FILE_PATH = Paths.UPLOADS + UPLOAD_FILE_NAME;
	}

	// ==================== SCREENSHOT SETTINGS ====================
	
	/**
	 * Screenshot configuration.
	 */
	public static class Screenshots {
		/** Screenshot file format. */
		public static final String FORMAT = ".png";
		
		/** Screenshot filename prefix for test failures. */
		public static final String FAILURE_PREFIX = "Screenshot_taken_because_test_fails_when_executed_testMethod_";
		
		/** Screenshot filename prefix for method failures. */
		public static final String METHOD_PREFIX = "Screenshot_taken_when_executed_testMethod_";
		
		/** Screenshot filename suffix with timestamp. */
		public static final String SUFFIX = "_at_time_";
	}

	// ==================== RANDOM DATA GENERATION SETTINGS ====================
	
	/**
	 * Random data generation configuration.
	 */
	public static class RandomData {
		/** Default length for random strings. */
		public static final int STRING_LENGTH = 10;
		
		/** Character sets for random generation. */
		public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
		public static final String DIGITS = "0123456789";
		public static final String ALPHANUMERIC = UPPERCASE + LOWERCASE + DIGITS;
		public static final String SPECIAL_CHARS = "!@#$%^&*()_+-=[]{}|;:,.<>?";
	}
}