package custom_methods;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

import configuration.Configuration;

public class OtherMethods {
  private Configuration config = new Configuration();

  /**
   * Add here more java methods in this class if its needed.
   * If you want to turn on the messages in the methods you need to set the value to 'on' of the 'messages' variable from the 'configuration.Configuration' class.
   */

  /** 
   * This method is used to assign the copied value (value from the clip board) to a variable.
   * 
   * @return 				- data from the click board will be returned.
   */
  public String clickboardData() throws UnsupportedFlavorException, IOException {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      // Get data from the click board.
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Clipboard clipboard = toolkit.getSystemClipboard();
      String clickboardData = (String) clipboard.getData(DataFlavor.stringFlavor);
      if (clickboardData != null) {
        messagesMethod("Message: Using the click board data. The data is : " + clickboardData);
      } else {
        System.out.println("ERROR! It seems that the click board is empty.");
      }
      return clickboardData;
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null;
  }

  /**
   * This method is used for generation random strings with only numbers. The max size of the string should be 10 symbols.
   * 
   * @param stringCharacters			- provide the characters that will be used for random generation.
   * @return							- the random generated string will be returned.
   */
  public String randomString(String stringCharacters) {
	  String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
	  String className = this.getClass().getSimpleName();
	  try {
	    ThreadLocalRandom random = ThreadLocalRandom.current();
	    int len = 10;
	    StringBuilder sb = new StringBuilder(len);
	    for (int i = 0; i < len; i++)
	    sb.append(stringCharacters.charAt(random.nextInt(stringCharacters.length())));
	    messagesMethod("Message: Random string is generated '" + sb.toString() + "'. The string was created by using the following characted '" + stringCharacters + "'.");
	    return sb.toString();
	  } catch (Exception e) {
	    System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
	      "' method from '" + className + "' class. Error message: " + e);
	  }
	  return null;
	}

  /**
   * This method is used to generate unique random numbers.
   * 
   * @param maxNumber				- provide a maximum number for randomly generating range. The range begin form 0.
   * @param randomUniqueNumber	- provide how many unique random numbers should be created. The value should be equal or less than 'maxNumber' value. The first value starts from 0.
   * @return						- the randomly generated integer will be returned.
   */
  public ArrayList < Integer > uniqueRandomNumber(int maxNumber, int randomUniqueNumbers) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      // Generate unique random number.
      ArrayList < Integer > numbers = new ArrayList < Integer > (); // Define ArrayList to hold Integer objects
      ArrayList < Integer > finalResult = new ArrayList < Integer > (); // Define ArrayList to hold the unique generated integers.
      for (int i = 0; i <= maxNumber; i++) {
        numbers.add(i);
      }
      Collections.shuffle(numbers);
      for (int j = 0; j <= randomUniqueNumbers; j++) {
        finalResult.add(numbers.get(j)); // Add the collection to the array.
      }
      messagesMethod("Message: Random integers is generated '" + finalResult + "'.");
      return finalResult;
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null;
  }

  /**
   * This method is used to generate random unique number (the number is based on current Unix time).
   * 
   * @return				- the current unix time will be returned.
   */
  public long unixTime() {
    long unixTime = 0;
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      unixTime = Instant.now().getEpochSecond(); // Generate Unix time.
      messagesMethod("Message: The current unix time is: " + unixTime + ".");
      return unixTime; // Return the Unix time value.
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    if (unixTime == 0) {
      System.out.println("It seems that we have an issue during the execution of the method '" + methodName + "' from class '" + className); // This message will be shown if something is gone wrong with the method.
    }
    return unixTime;
  }

  /**
   * Get the method name for a depth in call stack.
   * 
   * @param depth		- depth in the call stack (0 means current method, 1 means called method, etc...).
   * @return 			- the method name will be returned.
   */
  public String getCallerMethodName(int depth) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      messagesMethod("Message: The called method name is: " + StackWalker.getInstance().walk(s -> s.skip(depth).findFirst()).get().getMethodName() + ".");
      return StackWalker.getInstance().walk(s -> s.skip(depth).findFirst()).get().getMethodName();
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null;
  }

  /**
   * Get the class name for a depth in call stack.
   * 
   * @param depth		- depth in the call stack (0 means current method, 1 means called method, etc...).
   * @return 			- the class name will be returned.
   */
  public String getCallerClassName(int depth) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      messagesMethod("Message: The called class name is: " + StackWalker.getInstance().walk(s -> s.skip(depth).findFirst()).get().getClassName() + ".");
      return StackWalker.getInstance().walk(s -> s.skip(depth).findFirst()).get().getClassName();
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null;
  }

  /**
   * The method is used to turn on and off messages.
   *
   * @param message - provide the message that should be printed.
   */
  public void messagesMethod(String message) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      if ("on".equals(config.messages)) { // ✅ Check if messages are enabled.
        System.out.println(message); // ✅ Changed to println for better formatting
      } else if ("off".equals(config.messages)) { // ✅ Messages are turned off.
        // Do nothing - messages are disabled.
      } else {
        System.out.println("WARNING! The 'messages' variable from the 'configuration.Configuration' class has an invalid value: '" + config.messages + "'. Expected 'on' or 'off'. Please review this variable.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operation was not complete. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // ✅ Fixed typos
    }
  }

  /**
   * The method is used to rename file.
   * 
   * @param oldfileNameWithPath				- provide the name with path of old file.
   * @param newFileNameWithPath				- provide the name with path of the new file.
   */
  public void renameFile(String oldfileNameWithPath, String newFileNameWithPath) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      messagesMethod("Message: file '" + oldfileNameWithPath + " will be renamed with " + newFileNameWithPath + ".");
      // File (or directory) with old name
      File file = new File(oldfileNameWithPath);
      // File (or directory) with new name
      File file2 = new File(newFileNameWithPath);
      if (file2.exists())
        throw new java.io.IOException("file exists");
      // Rename file (or directory)
      boolean success = file.renameTo(file2);
      if (!success) {
        System.out.println("ERROR! It seems that the file wasn't renamed correctlly.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
  }

  /**
   * The method is used to return all files from the folder.
   * 
   * @param destinationPath				- provide the path of the folder.
   * @return 
   */
  public ArrayList < String > returnAllFilesFromFolder(String destinationPath) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      messagesMethod("Message: locate all files from folder '" + destinationPath + "' .");
      File folder = new File(destinationPath);
      File[] listOfFiles = folder.listFiles();
      ArrayList < String > allFiles = new ArrayList < String > ();
      for (File file: listOfFiles) {
        if (file.isFile()) {
          allFiles.add(file.getName());
          System.out.println(file.getName());
        }
      }
      return allFiles;
    } catch (Exception e) {
      System.out.println("ERROR! The operadion was not compleate. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e); // This message will be shown if something is gone wrong with the method.
    }
    return null;
  }

  /**
   * The method is used to return the size of inspected file.
   * 
   * @param destinationPathWithFileName				- provide the path with the name of the file.
   * @return 											- the size of the file will be returned.
   */
  public long printFileSize(String destinationPathWithFileName) {
    Path path = Paths.get(destinationPathWithFileName);
    try {
      // size of a file (in bytes)
      long bytes = Files.size(path);
      System.out.println(String.format("%,d bytes", bytes));
      System.out.println(String.format("%,d kilobytes", bytes / 1024));
      return bytes;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

}