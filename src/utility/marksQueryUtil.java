package utility;

import java.sql.ResultSet;
import java.sql.SQLException;

public class marksQueryUtil {
    private DButil dbUtil;

    public marksQueryUtil() {
        dbUtil = DButil.getInstance();
    }

    public ResultSet marksBySub(String sub) {
        String query = "select * from marks where paperCode = '" + sub + "';";

        return dbUtil.executeQueryStatement(query);
    }

    public int lowestMarkBySub(String sub) {
        String query = "select min(ObtMarks) 'minMarks' from (select ObtMarks from marks where paperCode = '"+ sub + "') as tmp;";

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

    public int highestMarkBySub(String sub) {
        String query = "select max(ObtMarks) 'maxMarks' from (select ObtMarks from marks where paperCode = '"+ sub + "') as tmp;";
        System.out.println(query);

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

    public float avgMarkBySub(String sub) {
        String query = "select avg(ObtMarks) 'avgMarks' from (select ObtMarks from marks where paperCode = '"+ sub + "') as tmp;";

        ResultSet res = dbUtil.executeQueryStatement(query);

        float avgMarks = 0;

        try {
            if(res.next())
                avgMarks = res.getInt("agvMarks");
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }

        return avgMarks;
    }

    public float passPercentBySub(String sub) {
        float passPerc = 0;
        try {
            int studentCount = 0;
            String stuCountQuery = "select count(*) 'rows' from studenttable;";
            ResultSet res = dbUtil.executeQueryStatement(stuCountQuery);
            if(res.next())
                studentCount = res.getInt("rows");
            int passNum = 0;
            String passNumQuery = "select count(*) 'rows' from marks where paperCode = '" + sub + "' and ObtMarks > (30*FullMarks/100);";
            res = dbUtil.executeQueryStatement(passNumQuery);
            if(res.next())
                passNum = res.getInt("rows");

            passPerc = passNum / studentCount * 100;
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }
        return passPerc;
    }
}
