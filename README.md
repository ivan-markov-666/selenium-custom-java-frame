# Introduction
This project is a custom framework for automation web testing.  
Using this frame, you will be able to create automation scripts for testing the web-based software quickly.  
The automation frame comprises a Selenium 4 with a TestNG testing framework. The programming language is Java.  

# Precondition
You can use the automation framework for learning, but you need to have some knowledge for:
1. Understanding of OOP.  
- https://en.wikipedia.org/wiki/Object-oriented_programming .
2. Basic Java knowledge.  
- https://dev.java/learn/ .
3. Essential knowledge of Selenium. Knowledge of Selenium methods and how to use them.  
- https://www.selenium.dev/documentation/
4. Basic knowledge of the TestNG testing framework.  
- https://testng.org/doc/

# Getting Started
Please follow the steps to set up the project:
### Installation requirements:
1. Install JDK version 9 or higher.
2. Set up environment variables for Java.
3. Install IDE that supports JAVA by your choice.
4. Add TestNG to your IDE.
5. Download and install Chrome and/or Firefox browsers and make sure that you start them once after the installation.
6. Clone the repository.
7. Open the automation framework by your IDE.
8. Maven will automatically download all dependencies including WebDriverManager (which will automatically manage browser drivers).
9. Read the README.md file to understand how to use the framework.
10. Enjoy and automate with pleasure!

###	Software dependencies
The dependencies will be downloaded automatically by Maven.  
1. selenium-java: https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
2. testng: https://mvnrepository.com/artifact/org.testng/testng
3. javafaker: https://mvnrepository.com/artifact/com.github.javafaker/javafaker
4. commons-io: https://mvnrepository.com/artifact/commons-io/commons-io
5. commons-exec: https://mvnrepository.com/artifact/org.apache.commons/commons-exec
6. poi-ooxml: https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
7. webdrivermanager: https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager

# Build and Test
To develop new tests:     
1. In this frame, we are using a POM design pattern. You need to create a PO class located in the "src/main/java/pom/" folder. For more details, you can see comments inside the example PO classes. It would be best to create the PO classes like the examples.
2. Create a spec class in the "src/test/java/" folder. For more details, you can see comments inside the example spec classes. It would be best to create the spec classes like the examples. Ensure that you cover all example classes because every class contains additional comments. 
- When you review some of the example classes, you will see that some extend "BaseTestClass". This class contains configuration methods used in test classes before and after annotations. You can add new or change the methods to your needs. Review the "BaseTestClass" for more details.
3. To execute the tests, you should use TestNG Test or TestNG Suite.

# What this framework contains
Because this is a custom framework - the following section shows what was added to the frame and how it can be used by the automation QAs.  
If you have any questions about the technologies, refer to the official documentation because this frame contains different technologies. This framework doesn't include anything unique - just a few technologies and libraries working together with usage examples.

### Custom Methods:
There are three packages in the "src/main/java/ containing custom methods.  
1. "custom_methods" package contains three classes.
- The "MainMethods" class contains Domain-Specific Language methods. Methods in this class are a combination of Selenium methods only. Review the class for more details. // more about what this is DSL you can find here: https://en.wikipedia.org/wiki/Domain-specific_language  
- The "OtherMethods" class contains Java methods. The methods inside this class are only Java-based. Review the class for more details.
- The "Wait Types" class contains different methods for waiting for elements to be present on the UI. Review the class for more details.
2. The "dataDrivenTesting_methods" package contains methods used for DDT. Review the "ImportDataFromExcel" class for more details.
3. The "listener_methods" package contains classes with methods with TestNG listeners. Review the classes for more details.

### Design Patterns:
The frame uses using POM design pattern.  

### Test Suites:
The framework can run multiple tests grouped in different suites.  
Just refer to the TestNG suit mechanism. You can review examples of suites (XML files) located at the root folder project. // https://testng.org/doc/documentation-main.html

### Configuration:
The "Configuration" class in the "src/main/java/configuration" contains this automation frame's configuration. Review the class for more details.

### Reports:
There is a local report mechanism integrated into this automation frame.  
How to generate a report please review the class "A29_1_GenerateReport_spec", "A29_2_GenerateReport_2_spec" located in "src/test/java/tests/".  You can review the "A29_GenerateReportSuite.xml" file too (this is a suite file).  
After executing the tests, the report should be generated and discovered in the "test-output" folder.    
You can add different tests in a suite, and one report will be generated for each suit run.

### Testing Data:
I. A mechanism is integrated with the framework for reading data from Excel files (Data-Driven Testing).  
1. Create a new excel file and put it inside the "src/test/java/ExcelData/" folder. When you create the Excel file, you should name each column used. The mechanism reads the first values of the columns and locates them by their names. The name of the columns is the first-row value. Every one value after the first row is a testing value. Make sure that you enter one word for each column name. If you use two words - the mechanism will not work.  
2. Use read data in your tests. For more details please review the "ReadingFromExcelFile" class located in "src/test/java/data-driven testing".  
You can see methods that are used for data-driven testing, and they are located in the "ImportDataFromExcel" class located in the same place as the example class.  

II.  Use the TestNG mechanism to read testing data from an XML file. You can review the "A26_ProvideParametersToTestMethod_spec" test class and the "A26_ProvideParametersToTestMethod.xml" file for more details.  

III. You can generate data dynamically using the faker-java library. Please review the "A19_FakerExample_spec" class for more details

### Uploads:
Because sometimes we will need to upload files, there is a folder where we can put those files. The folder is located in "uploads".   Of course, you can use any other folder on your machine.  

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

**Note:** The framework is configured to use WebDriverManager by default (`useSystemPropertyForBrowserDriver = "no"` in Configuration.java). If you prefer manual driver management, set this value to "yes" and follow the traditional setup process.

### Screenshots
The automation frame has a mechanism to generate a screenshot when something goes wrong or when some test fails. The generated screenshot can be found in the "screenshots" folder. Don't forget to refresh the folder from the IDE because the files will not appear.

### Downloads
When the automation test needs to download some resources, you can use the "downloads" folder.

### To Do:
The frame is not perfect. That's why there is a to-do list located in "./toDo/ToDo.txt".  

# Contribute
You can use the framework for free and make changes to it. I hope the frame will save configuration time and develop the custom commands.