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
3. Essential knowledge for Selenium. Knowledge of Selenium methods and how to use them.  
- https://www.selenium.dev/documentation/
4. Basic knowledge of TestNG testing framework.  
- https://testng.org/doc/

# Getting Started
Please follow the steps to set up the project:
### Installation requirements:
1. Install JDK version 9 or higher.
2. Set up environment variables for Java.
3. Install IDE that supports JAVA by your choise.
4. Add TestNG to your IDE.
5. Download the last version of chromedriver.exe, geckodriver.exe etc.
6. Download and install Chrome and Firefox browsers and make sure that you start them once after the installation.
7. Clone the repository.
8. Put downloaded WebDrivers from step 5 inside the "drivers" folder at the project's root.
9. Add the "drivers" folder from the project's root to the "PATH" environment variables.
10. If you opened the IDE - restart it.
11. Open the automation framework by your IDE.
12. Read the readme.rd file to understand how to use the framework.
13. Enjoy and automate with pleasure!

###	Software dependencies
The dependencies will be downloaded automatically by Maven.  
1. selenium-java: https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
2. testng: https://mvnrepository.com/artifact/org.testng/testng
3. javafaker: https://mvnrepository.com/artifact/com.github.javafaker/javafaker
4. commons-io: https://mvnrepository.com/artifact/commons-io/commons-io
5. commons-exec: https://mvnrepository.com/artifact/org.apache.commons/commons-exec
6. poi-ooxml: https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml

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
- "MainMethods" class contains Domain-Specific Language methods. Methods in this class are a combination of Selenium methods only. Review the class for more details. // more about what this is DSL you can find here: https://en.wikipedia.org/wiki/Domain-specific_language  
- "OtherMethods" class contains Java methods. The methods inside this class are only Java-based. Review the class for more details.
- "Wait Types" class contains different methods for waiting for elements to be present on the UI. Review the class for more details.
2. "dataDrivenTesting_methods" package contains methods used for DDT. Review the "ImportDataFromExcel" class for more details.
3. "listener_methods" package contains classes with methods with TestNG listeners. Review the classes for more details.

### Design Patterns:
The frame uses using POM design pattern.  

### Test Suites:
The framework can run multiple tests grouped in different suites.  
Just refer to the TestNG suit mechanism. You can review examples of suites (XML files) located at the root folder project. // https://testng.org/doc/documentation-main.html

### Configuration:
The "Configuration" class in the "src/main/java/configuration" contains this automation frame's configuration. Review the class for more details.

### Reports:
There is a local report mechanism integrated into this automation frame.  
How to generate report please review class "A29_1_GenerateReport_spec", "A29_2_GenerateReport_2_spec" located in "src/test/java/tests/".  You can review the "A29_GenerateReportSuite.xml" file too (this is suite file).  
After executing the tests, the report should be generated and discovered in the "test-output" folder.    
You can add different tests in a suite, and one report will be generated for each suit run.

### Testing Data:
I. A mechanism is integrated with the framework for reading data from Excel files (Data-Driven Testing).  
1. Create a new excel file and put it inside the "src/test/java/ExcelData/" folder. When you create the excel file, you should name each column used. The mechanism reads the first values of the columns and locates them by their names. The name of the columns is the first-row value. Every one value after the first row is a testing value. Make sure that you enter one word for each column name. If you use two words - the mechanism will not work.  
2. Use read data in your tests. For more details please review "ReadingFromExcelFile" class located in "src/test/java/data-driven testing".  
You can see methods that are used for data-driven testing, and they are located in the "ImportDataFromExcel" class located in the same place as the example class.  

II.  Use the TestNG mechanism to read testing data from an XML file. You can review "A26_ProvideParametersToTestMethod_spec" test class and "A26_ProvideParametersToTestMethod.xml" file for more details.  

III. You can generate data dynamically using the faker-java library. Please review the "A19_FakerExample_spec" class for more details

### Uploads:
Because sometimes we will need to upload files, there is a folder where we can put those files. The folder is located in "uploads".   Of course, you can use any other folder on your machine.  

### WebDriver
You can put the WebDrivers into the "drivers" folder. Whenever you need to update the WebDrivers - put the new versions in that folder.  
Suppose you need to add a new browser to the frame. In that case, you need to add the WebDriver to this folder and add the browser name into the "src/main/java/configuration/Configuration.java" class (review the class for more details), then you need to add the new if-else statement into the "src/test/java/base/BaseTestClass.java" class to add WebDriver code related to the browser (review the class for more details).

### Screenshots
The automation frame has a mechanism to generate a screenshot when something goes wrong or when some test fails. The generated screenshot can be found in the "screenshots" folder. Don't forget to refresh the folder from the IDE because the files will not appear.

### Downloads
When the automation test needs to download some resources, you can use the "downloads" folder.

### To Do:
The frame is not perfect. That's why there is a to-do list located in "cypress/toDo/toDo.txt".  

# Contribute
You can use the framework for free and make changes to it. I hope the frame will save configuration time and develop the custom commands.