package utility;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.io.font.constants.StandardFonts;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import com.itextpdf.kernel.geom.PageSize;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.borders.SolidBorder;

/**
 * Utility class to create PDF reports
 */
public class pdfUtil {

    /**
     * Method to generate PDF repors of particular subjects
     * @param sub Subject code
     * @param type Subject type
     * @param fileNamePath Filepath to the PDF
     */
    public void generatePDF(String sub, String type, String fileNamePath) {
        try {
            Document document = new Document(new PdfDocument(new PdfWriter(fileNamePath + ".pdf")), PageSize.A4);
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            marksQueryUtil mqu = new marksQueryUtil();

            // Retrieve marks list using marksQueryUtil
            document.add(new Paragraph("1. Retrieve Marks Data:"));
            ResultSet res = mqu.marksBySub(sub, type);

            Table table = new Table(new float[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 });

            // Adding header row
            table.addCell(new Cell().add(new Paragraph("Roll").setFont(boldFont)).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("College").setFont(boldFont)).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Category").setFont(boldFont)).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Paper Name").setFont(boldFont)).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Paper Code").setFont(boldFont)).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Paper Type").setFont(boldFont)).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Half").setFont(boldFont)).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Full Marks").setFont(boldFont)).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Obtained Marks").setFont(boldFont)).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));

            // Adding data rows 
            while(res.next()) {
                table.addCell(new Cell().add(new Paragraph(Integer.toString(res.getInt("roll")))).setFont(font).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(res.getString("coll"))).setFont(font).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(res.getString("cate"))).setFont(font).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(res.getString("paperName"))).setFont(font).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(res.getString("paperCode"))).setFont(font).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(res.getString("paperType"))).setFont(font).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(res.getString("half"))).setFont(font).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(Integer.toString(res.getInt("FullMarks")))).setFont(font).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(Integer.toString(res.getInt("ObtMarks")))).setFont(font).setBorder(new SolidBorder(1)).setTextAlignment(TextAlignment.CENTER));
            }

            document.add(table);
            
            //Perform the queries
            
            
            document.add(new Paragraph("2. Lowest Marks by Subject:"));
            int lowestMarks = mqu.lowestMarkBySub(sub, type);
            document.add(new Paragraph("Lowest Marks in " + sub + ": " + lowestMarks));

            document.add(new Paragraph("3. Highest Marks by Subject:"));
            int highestMarks = mqu.highestMarkBySub(sub, type);
            document.add(new Paragraph("Highest Marks in " + sub + ": " + highestMarks));

            document.add(new Paragraph("4. Average Marks by Subject:"));
            float avgMarks = mqu.avgMarkBySub(sub, type);
            document.add(new Paragraph("Average Marks in " + sub + ": " + avgMarks));

            document.add(new Paragraph("4. Pass percentage by Subject:"));
            float passPerc = mqu.passPercentBySub(sub, type);
            document.add(new Paragraph("Pass percentage in " + sub + ": " + passPerc));

            document.close();
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
