/**
 * Description:
 * This class is used for declaring methods used for Data Driven Testing (reading data from Excel files).
 * If you want to turn on the messages in the methods you need to set the value to 'on' of the 'messages' variable from the 'configuration.Configuration' class.
 */

package dataDrivenTesting_methods;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import custom_methods.OtherMethods;

public class ImportDataFromExcel {

  private static OtherMethods otherMethods = new OtherMethods();
  private static XSSFWorkbook ExcelWBook;
  private static XSSFSheet ExcelWSheet;

  /**
   * Set the path to the Excel file.
   * 
   * @param filePath - you need to provide the path to the Excel file.
   * @param sheetName - you need to provide the name of the sheet that you want to select from the Excel file.
   */
  public void setExcelFile(String filePath, String sheetName) {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    
    // try-with-resources automatically closes FileInputStream
    try (FileInputStream ExcelFile = new FileInputStream(filePath)) {
      
      // Access the Excel sheet data.
      ExcelWBook = new XSSFWorkbook(ExcelFile);
      ExcelWSheet = ExcelWBook.getSheet(sheetName);
      otherMethods.messagesMethod("Message: The Excel file located at '" + filePath + "' with sheet '" + sheetName + "' is loaded.");
      
    } catch (Exception e) {
      System.out.println("ERROR! The operation was not complete. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e);
    }
    // FileInputStream is automatically closed here by try-with-resources
  }

  /**
   * Call this method when you're done working with the Excel file.
   * Recommended to call in @AfterMethod or @AfterClass.
   */
  public void closeExcelFile() {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      if (ExcelWBook != null) {
        ExcelWBook.close();
        otherMethods.messagesMethod("Message: The Excel workbook has been closed.");
      }
    } catch (Exception e) {
      System.out.println("ERROR! The operation was not complete. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e);
    }
  }

  /** 
   * This method is used for getting all possible cells with values.
   * 
   * @param firstCellValue - provide the name of the table.
   * @return - array of boundary cells.
   */
  @SuppressWarnings("unused")
  public XSSFCell[] findCells(String firstCellValue) {
    String position = "begin";
    XSSFCell[] cells = new XSSFCell[2];
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      for (Row row: ExcelWSheet) {
        for (Cell cell: row) {
          String cellValueInString = ""; // declare the empty String.
          if (cell.getCellType() == CellType.NUMERIC) { // Check if the current (read) cell value is 'NUMERIC'.
            cellValueInString = NumberToTextConverter.toText(cell.getNumericCellValue()); // Convert the 'NUMERIC' cell value type to String and assign it to the String variable.
          } else if (cell.getCellType() == CellType.STRING) { // Check if the current (read) cell value is String.
            cellValueInString = cell.getStringCellValue(); // Assign the current (read) cell value to String variable.
          } else if (cell.getCellType() == CellType.BLANK) { // Check if the current (read) cell value is 'BLANK'.
            cellValueInString = cell.getStringCellValue(); // Assign the current (read) cell value to String variable.
          } else if (cell.getCellType() == CellType._NONE) { // Check if the current (read) cell value is '_NONE'.
            cellValueInString = cell.getStringCellValue(); // Assign the current (read) cell value to String variable.
          } else { // else - you need to update this method, because the current (read) cell value is not supported from this if-else statement.
            System.out.print("ERROR! It seems that the data read from the Excel file is not supported from your method '" + methodName + "' located in the class '" + className + "'. Please review the if-else if-else statement and edit it to support this kind of variable type.");
          }

          if (firstCellValue.equals(cellValueInString)) {
            cells[0] = (XSSFCell) cell;
            position = "end";
          } else {
            cells[1] = (XSSFCell) cell;
          }
        }
      }
      otherMethods.messagesMethod("Message: All possible Excel cells were located.");
    } catch (Exception e) {
      System.out.println("ERROR! The operation was not complete. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e);
    }
    return cells;
  }

  /** 
   * This method is used for getting the data from the Excel file.
   * 
   * @param firstCellValue - provide the name of cell from where the reading of the data begins.
   * @return - 2D array containing the test data from Excel.
   */
  public String[][] getDataFromExcelFile(String firstCellValue) {
    String[][] testData = null;
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the name of the current method.
    String className = this.getClass().getSimpleName(); // Get the name of the class.
    try {
      XSSFCell[] boundaryCells = findCells(firstCellValue);
      XSSFCell startCell = boundaryCells[0];
      XSSFCell endCell = boundaryCells[1];
      int startRow = startCell.getRowIndex() + 1;
      int endRow = endCell.getRowIndex() - 1;
      int startCol = startCell.getColumnIndex() + 1;
      int endCol = endCell.getColumnIndex() - 1;
      testData = new String[endRow - startRow + 1][endCol - startCol + 1];
      for (int i = startRow; i < endRow + 1; i++) {
        for (int j = startCol; j < endCol + 1; j++) {
          try {
            String cellValueInString = ""; // declare the empty String.
            if (ExcelWSheet.getRow(i).getCell(j).getCellType() == CellType.NUMERIC) { // Check if the current (read) cell value is 'NUMERIC'.
              cellValueInString = NumberToTextConverter.toText(ExcelWSheet.getRow(i).getCell(j).getNumericCellValue()); // Convert the 'NUMERIC' cell value type to String and assign it to the String variable.
            } else if (ExcelWSheet.getRow(i).getCell(j).getCellType() == CellType.STRING) { // Check if the current (read) cell value is String.
              cellValueInString = ExcelWSheet.getRow(i).getCell(j).getStringCellValue(); // Assign the current (read) cell value to String variable.
            } else { // else - you need to update this method, because the current (read) cell value is not supported from this if-else statement.
              System.out.print("ERROR! It seems that the data read from the Excel file is not supported from your method '" + methodName + "' located in the class '" + className + "'. Please review the if-else if-else statement and edit it to support this kind of variable type.");
            }
            testData[i - startRow][j - startCol] = cellValueInString;
          } catch (Exception e) {
            testData[i - startRow][j - startCol] = "";
          }
        }
      }
      otherMethods.messagesMethod("Message: The data from Excel file was retrieved.");
    } catch (Exception e) {
      System.out.println("ERROR! The operation was not complete. Please review the '" + methodName +
        "' method from '" + className + "' class. Error message: " + e);
    }
    return testData;
  }
}