# Introduction
This project is a custom framework for automation web testing.  
Using this frame, you will be able to create automation scripts for testing the web-based software quickly.  
The automation frame comprises Selenium 4 with a TestNG testing framework. The programming language is Java.  

**NOTE:** This framework is **100% FREE** for any use - personal, commercial, or otherwise. Fork it, modify it, use it in paid projects - no restrictions!

# Precondition
You can use the automation framework for learning, but you need to have some knowledge of:
1. Understanding of OOP.  
2. Basic Java knowledge.  
3. Essential knowledge of Selenium. Knowledge of Selenium methods and how to use them.  
4. Basic knowledge of the TestNG testing framework.  

# Getting Started
Please follow the steps to set up the project:

### Installation requirements:
1. Install JDK version 9 or higher (project uses Java 21).
2. Set up environment variables for Java.
3. Install IDE that supports JAVA by your choice.
4. Add TestNG to your IDE.
5. Download and install Chrome and/or Firefox browsers and make sure that you start them once after the installation.
6. Clone the repository.
7. Open the automation framework by your IDE.
8. Maven will automatically download all dependencies including WebDriverManager (which will automatically manage browser drivers).
9. Read the README.md file to understand how to use the framework.
10. Enjoy and automate with pleasure!

### Software dependencies
The dependencies will be downloaded automatically by Maven:
1. **selenium-java** (4.38.0): https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
2. **testng** (7.11.0): https://mvnrepository.com/artifact/org.testng/testng
3. **javafaker** (1.0.2): https://mvnrepository.com/artifact/com.github.javafaker/javafaker
4. **commons-io** (2.20.0): https://mvnrepository.com/artifact/commons-io/commons-io
5. **commons-exec** (1.5.0): https://mvnrepository.com/artifact/org.apache.commons/commons-exec
6. **poi-ooxml** (5.4.1): https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
7. **webdrivermanager** (6.3.2): https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager
8. **log4j-core** (2.25.2): https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
9. **slf4j-api** (2.0.9): https://mvnrepository.com/artifact/org.slf4j/slf4j-api
10. **log4j-slf4j2-impl** (2.25.2): https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j2-impl

# Build and Test
To develop new tests:     
1. In this frame, we are using a POM design pattern. You need to create a PO class located in the "src/main/java/pom/" folder. For more details, you can see comments inside the example PO classes. It would be best to create the PO classes like the examples.
2. Create a spec class in the "src/test/java/tests/" folder. For more details, you can see comments inside the example spec classes. It would be best to create the spec classes like the examples. Ensure that you cover all example classes because every class contains additional comments. 
   - When you review some of the example classes, you will see that some extend "BaseTestClass". This class contains configuration methods used in test classes before and after annotations. You can add new or change the methods to your needs. Review the "BaseTestClass" for more details.
3. To execute the tests, you should use TestNG Test or TestNG Suite.

# What this framework contains
Because this is a custom framework - the following section shows what was added to the frame and how it can be used by the automation QAs.  
If you have any questions about the technologies, refer to the official documentation because this frame contains different technologies. This framework doesn't include anything unique - just a few technologies and libraries working together with usage examples.

### Custom Methods:
There are three packages in the "src/main/java/" containing custom methods:  
1. **"custom_methods"** package contains four classes:
   - The **"MainMethods"** class contains Domain-Specific Language methods. Methods in this class are a combination of Selenium methods only. Review the class for more details. 
   - The **"OtherMethods"** class contains Java methods. The methods inside this class are only Java-based. Review the class for more details.
   - The **"WaitTypes"** class contains different methods for waiting for elements to be present on the UI. Review the class for more details.
   - The **"ElementNotFoundException"** class is a custom exception for better error handling when elements are not found.
2. The **"dataDrivenTesting_methods"** package contains methods used for DDT. Review the "ImportDataFromExcel" class for more details.
3. The **"listener_methods"** package contains classes with methods with TestNG listeners. Review the classes for more details.

### Design Patterns:
The framework uses the POM (Page Object Model) design pattern.  

### Test Suites:
The framework can run multiple tests grouped in different suites.  
Just refer to the TestNG suite mechanism. You can review examples of suites (XML files) located at the root folder project. 

### Configuration:
The "Configuration" class in the "src/main/java/configuration" contains this automation frame's configuration. Review the class for more details.

### Logging:
The framework uses **SLF4J with Log4j2** for logging:
- Log configuration file: `src/test/resources/log4j2.xml`
- Logs are saved to: `logs/test-execution.log`
- Console output with formatted timestamps
- Configurable log levels for different packages (Selenium, TestNG, custom code)

### Reports:
There is a local report mechanism integrated into this automation frame.  
How to generate a report please review the class "A29_1_GenerateReport_spec", "A29_2_GenerateReport_2_spec" located in "src/test/java/tests/". You can review the "A29_GenerateReportSuite.xml" file too (this is a suite file).  
After executing the tests, the report should be generated and discovered in the "test-output" folder.    
You can add different tests in a suite, and one report will be generated for each suit run.

### Testing Data:
**I. Data-Driven Testing with Excel:**  
A mechanism is integrated with the framework for reading data from Excel files (Data-Driven Testing).  
1. Create a new excel file and put it inside the "src/test/java/ExcelData/" folder (Note: This should be "ExcelData/" in project root, not in src/test/java). When you create the Excel file, you should name each column used. The mechanism reads the first values of the columns and locates them by their names. The name of the columns is the first-row value. Every one value after the first row is a testing value. Make sure that you enter one word for each column name. If you use two words - the mechanism will not work.  
2. Use read data in your tests. For more details please review the "ReadingFromExcelFile" class located in "src/test/java/dataDrivenTesting_methods/".  
You can see methods that are used for data-driven testing, and they are located in the "ImportDataFromExcel" class located in "src/main/java/dataDrivenTesting_methods/".  

**II. TestNG Parameters:**  
Use the TestNG mechanism to read testing data from an XML file. You can review the "A26_ProvideParametersToTestMethod_spec" test class and the "A26_ProvideParametersToTestMethod.xml" file for more details.  

**III. Faker Library:**  
You can generate data dynamically using the faker-java library. Please review the "A19_FakerExample_spec" class for more details.

### Uploads:
Because sometimes we will need to upload files, there is a folder where we can put those files. The folder is located in "uploads/". Of course, you can use any other folder on your machine.  

### WebDriver
This framework uses **WebDriverManager** for automatic browser driver management. WebDriverManager automatically downloads and configures the correct driver version for your installed browser. You don't need to manually download chromedriver.exe, geckodriver.exe, or manage driver paths.

**How it works:**
- WebDriverManager automatically detects your browser version
- Downloads the compatible driver version
- Caches drivers for future use
- No manual configuration needed

**To add a new browser:**
1. Add the browser name to the "src/main/java/configuration/Configuration.java" class
2. Add the corresponding WebDriverManager setup and driver initialization in "src/test/java/base/BaseTestClass.java"
3. WebDriverManager will handle the rest automatically

**Note:** The framework is configured to use WebDriverManager by default. The `USE_SYSTEM_PROPERTY` setting in Configuration.java determines whether to use manual driver paths or WebDriverManager. Set to "no" for automatic management (recommended).

### Screenshots
The automation frame has a mechanism to generate a screenshot when something goes wrong or when some test fails. The generated screenshot can be found in the "screenshots/" folder. Don't forget to refresh the folder from the IDE because the files will not appear immediately.

### Downloads
When the automation test needs to download some resources, you can use the "downloads/" folder.

### Logs
The framework automatically generates log files in the "logs/" folder:
- `test-execution.log` - contains detailed execution logs
- Logs include timestamps, thread info, log levels, and messages
- Configuration can be modified in `src/test/resources/log4j2.xml`

### Project Structure
```
project-root/
├── ExcelData/              # Excel files for data-driven testing
├── downloads/              # Downloaded files during test execution
├── logs/                   # Log files (auto-generated)
├── screenshots/            # Screenshots on test failures
├── uploads/                # Files to be uploaded during tests
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── configuration/
│   │       ├── custom_methods/
│   │       ├── dataDrivenTesting_methods/
│   │       ├── listener_methods/
│   │       └── pom/
│   └── test/
│       ├── java/
│       │   └── tests/
│       └── resources/
│           └── log4j2.xml  # Logging configuration
├── pom.xml
└── *.xml                   # TestNG suite files
```

### To Do:
The framework is not perfect. That's why there is a to-do list located in "./toDo/ToDo.txt".  

# Contribute
You can use the framework for free and make changes to it. I hope the frame will save configuration time and help you develop custom commands efficiently.

**This framework is completely FREE and OPEN SOURCE!** Fork it, use it commercially, modify it - no restrictions!