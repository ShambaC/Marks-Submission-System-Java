package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utility class to read data from an excel sheet
 */
public class ExcelUtil {

    /**
     * Method to read data from an excel sheet and return the list of rows in it
     * @param filePath Path to the excel sheet
     * @return A list of rows containing data from the file
     * @throws EncryptedDocumentException
     * @throws IOException
     */
    public List<ExcelRow> readExcel(String filePath) throws EncryptedDocumentException, IOException {
        List<ExcelRow> dataList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook wb = new XSSFWorkbook(fis);   
            XSSFSheet s = wb.getSheet("Sheet1");
            int startRow = 9; 
            int numRows = s.getPhysicalNumberOfRows();
            for (int i = startRow; i < numRows; i++) {
                Row r = s.getRow(i);
                ExcelRow excelRow = new ExcelRow();
                excelRow.course_title = getStringValue(r.getCell(1));
                excelRow.course_code = getStringValue(r.getCell(2)); 
                excelRow.TH_PR_AI = getStringValue(r.getCell(3)); 
                excelRow.half = getStringValue(r.getCell(4)); 
                excelRow.coll = getStringValue(r.getCell(5)); 
                excelRow.cate = getStringValue(r.getCell(6)); 
                excelRow.number = getNumericValue(r.getCell(7)); 
                excelRow.full_marks = getNumericValue(r.getCell(8)); 
                excelRow.marks_obtained = getNumericValue(r.getCell(9));
                dataList.add(excelRow);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * Method to get string value from a cell
     * @param cell cell to get value from
     * @return String data
     */
    private String getStringValue(Cell cell) {
        if (cell != null) {
            return cell.getStringCellValue();
        }
        return null;
    }

    /**
     * method to get numerical data from a cell
     * <p> Will return 0 if data is string
     * @param cell the cell to get data from
     * @return Numerical data
     */
    private int getNumericValue(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                return (int) cell.getNumericCellValue();
            } else if (cell.getCellType() == CellType.STRING) {
                try {
                    return Integer.parseInt(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }
        return 0; 
    }


}
