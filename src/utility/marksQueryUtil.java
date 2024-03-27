package utility;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility class that runs certain queries on the marks table
 */
public class marksQueryUtil {
    /**
     * Database Util object for performing transactions
     */
    private DButil dbUtil;

    public marksQueryUtil() {
        dbUtil = DButil.getInstance();
    }

    /**
     * Method to get a list of all details from marks table filtered by a subject
     * @param sub subject to use as a filter
     * @param type subject type to use as a filter
     * @return Result of the query
     */
    public ResultSet marksBySub(String sub, String type) {
        String query = "select * from marks where paperCode = '" + sub + "' and paperType = '" + type + "';";

        return dbUtil.executeQueryStatement(query);
    }

    /**
     * Method to find out the lowest marks obtained in a subject
     * @param sub paper code
     * @param type paper type
     * @return lowest marks
     */
    public int lowestMarkBySub(String sub, String type) {
        String query = "select min(ObtMarks) 'minMarks' from (select ObtMarks from marks where paperCode = '"+ sub + "' and paperType = '" + type + "') as tmp;";

        ResultSet res = dbUtil.executeQueryStatement(query);

        int minMarks = 0;

        try {
            if(res.next())
                minMarks = res.getInt("minMarks");
        }
        catch(SQLException err) {
            System.err.println("lowest mark by sub error: " + err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }

        return minMarks;
    }

    /**
     * method to find out the highest marks obtained in a subject
     * @param sub paper code
     * @param type paper type
     * @return highest marks
     */
    public int highestMarkBySub(String sub, String type) {
        String query = "select max(ObtMarks) 'maxMarks' from (select ObtMarks from marks where paperCode = '"+ sub + "' and paperType = '" + type + "') as tmp;";

        ResultSet res = dbUtil.executeQueryStatement(query);

        int maxMarks = 0;

        try {
            if(res.next())
                maxMarks = res.getInt("maxMarks");
        }
        catch(SQLException err) {
            System.err.println("highest mark by sub error: " + err.getErrorCode() + " " + err.getMessage());
        }

        return maxMarks;
    }

    /**
     * method to find out the average marks obtained in a subject
     * @param sub paper code
     * @param type paper type
     * @return average marks
     */
    public float avgMarkBySub(String sub, String type) {
        String query = "select avg(ObtMarks) 'avgMarks' from (select ObtMarks from marks where paperCode = '"+ sub + "' and paperType = '" + type + "') as tmp;";

        ResultSet res = dbUtil.executeQueryStatement(query);

        float avgMarks = 0;

        try {
            if(res.next())
                avgMarks = res.getInt("avgMarks");
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }

        return avgMarks;
    }

    /**
     * Method to find the percentage of students passed in a subject
     * @param sub
     * @param type
     * @return
     */
    public float passPercentBySub(String sub, String type) {
        float passPerc = 0;
        try {
            int studentCount = 0;
            String stuCountQuery = "select count(*) 'rows' from studenttable;";
            ResultSet res = dbUtil.executeQueryStatement(stuCountQuery);
            if(res.next())
                studentCount = res.getInt("rows");
            int passNum = 0;
            String passNumQuery = "select count(*) 'rows' from marks where paperCode = '" + sub + "' and paperType = '" + type + "' and ObtMarks > (30*FullMarks/100);";
            res = dbUtil.executeQueryStatement(passNumQuery);
            if(res.next())
                passNum = res.getInt("rows");

            passPerc = (float) passNum / (float) studentCount * 100;
            System.out.println(passPerc);
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }
        return passPerc;
    }
}
