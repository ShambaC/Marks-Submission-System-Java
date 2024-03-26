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
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

public class ExcelUtil {
    
    public static class ExcelRow {
        public String course_title;
        public String course_code;
        public String TH_PR_AI;
        public String half;
        public String coll;
        public String cate;
        public int number;
        public int full_marks;
        public String marks_obtained;
    }

    public static void main(String[] args) throws EncryptedDocumentException, IOException {
        List<ExcelRow> rows = readExcel("Sheet1");
        System.out.println("Course/Paper title" + "\t " + "Course/Paper code" + "\t " + "TH/PR/AI" + "\t" +
                "Half" + "\t " + "Coll" + "\t " + "Cate" + "\t " +
                "Number" + "\t " + "Full_marks" + "\t" + "Marks_obtained\n\n");
        for (ExcelRow row : rows) {
            if (row.number != 0 || row.full_marks != 0 || !"0".equals(row.marks_obtained) && row.marks_obtained != null && !row.marks_obtained.isEmpty()) {
                System.out.println(row.course_title + "   \t " + row.course_code + "              \t " + row.TH_PR_AI + "   \t" +
                                   row.half + "   \t " + row.coll + "   \t " + row.cate + "  \t  " +
                                   row.number + "  \t" + row.full_marks + "  \t  " + row.marks_obtained);
            } else {
                break;
            }
        }
    }
    public static List<ExcelRow> readExcel(String sheetName) throws EncryptedDocumentException, IOException {
        List<ExcelRow> dataList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\shrey\\OneDrive\\Documents\\GitHub\\BTECH 5TH SEMESTER 2023 CSCL501 70.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis);   
            XSSFSheet s = wb.getSheet(sheetName);
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
                excelRow.marks_obtained = getStringValue(r.getCell(9)); 
                dataList.add(excelRow);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    private static String getStringValue(Cell cell) {
        if (cell != null) {
            return cell.getStringCellValue();
        }
        return null;
    }

    private static int getNumericValue(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                return (int) cell.getNumericCellValue();
            } else if (cell.getCellType() == CellType.STRING) {
                try {
                    return Integer.parseInt(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing numeric value: " + e.getMessage());
                }
            }
        }
        return 0; 
    }


}
