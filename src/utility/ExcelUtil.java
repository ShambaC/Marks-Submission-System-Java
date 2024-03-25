package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

    // Database Connection Details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String DB_USER = "your_database_username";
    private static final String DB_PASSWORD = "your_database_password";

    public static void main(String[] args) throws EncryptedDocumentException, IOException {
        ExcelUtil ob = new ExcelUtil();
        String userName = ob.readExcel("User_credential", 1, 0);
        System.out.println(userName);
        ob.writeExcel("User_credential", 1, 2, "PASS");

        // Assuming you want to upload marks after login
        ob.uploadMarksFromExcel("marks_sheet.xlsx");
    }

    public String readExcel(String sheetName, int rowNum, int colNum) throws EncryptedDocumentException, IOException {
        String data = "";
        try {
            FileInputStream fis = new FileInputStream("//file path");
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet(sheetName);
            Row r = s.getRow(rowNum);
            Cell c = r.getCell(colNum);
            data = c.getStringCellValue();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeExcel(String sheetName, int rowNum, int colNum, String data) throws EncryptedDocumentException, IOException {
        try {
            FileInputStream fis = new FileInputStream("//file path");
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet(sheetName);
            Row r = s.getRow(rowNum);
            Cell c = r.createCell(colNum);
            c.setCellValue(data);
            FileOutputStream fos = new FileOutputStream("//file path");
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void uploadMarksFromExcel(String filePath) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0); // Assuming marks are in the first sheet

            // Database Connection
            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                // Assuming you have a table named 'marks' with columns 'student_name' and 'marks'
                String sql = "INSERT INTO marks (student_name, marks) VALUES (?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    for (Row row : sheet) {
                        // Assuming first cell contains student name and second cell contains marks
                        String studentName = row.getCell(0).getStringCellValue();
                        double marks = row.getCell(1).getNumericCellValue();
                        statement.setString(1, studentName);
                        statement.setDouble(2, marks);
                        statement.addBatch();
                    }
                    // Execute batch insert
                    statement.executeBatch();
                    System.out.println("Marks uploaded successfully.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException | EncryptedDocumentException e) {
            e.printStackTrace();
        }
    }
}

