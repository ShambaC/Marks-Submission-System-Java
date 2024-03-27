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

public class ExcelUtil {

    // public static void main(String[] args) throws EncryptedDocumentException, IOException {
    //     List<ExcelRow> rows = readExcel("Sheet1");
    //     System.out.println("Course/Paper title" + "\t " + "Course/Paper code" + "\t " + "TH/PR/AI" + "\t" +
    //             "Half" + "\t " + "Coll" + "\t " + "Cate" + "\t " +
    //             "Number" + "\t " + "Full_marks" + "\t" + "Marks_obtained\n\n");
    //     // for (ExcelRow row : rows) {
    //     //     if (row.number != 0 || row.full_marks != 0 || !"0".equals(row.marks_obtained) && row.marks_obtained != null && !row.marks_obtained.isEmpty()) {
    //     //         System.out.println(row.course_title + "   \t " + row.course_code + "              \t " + row.TH_PR_AI + "   \t" +
    //     //                            row.half + "   \t " + row.coll + "   \t " + row.cate + "  \t  " +
    //     //                            row.number + "  \t" + row.full_marks + "  \t  " + row.marks_obtained);
    //     //     } else {
    //     //         break;
    //     //     }
    //     // }
    // }

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

    private String getStringValue(Cell cell) {
        if (cell != null) {
            return cell.getStringCellValue();
        }
        return null;
    }

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
