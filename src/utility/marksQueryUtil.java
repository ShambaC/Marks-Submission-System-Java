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
        String query = "select min(ObtMarks) 'minMarks' from (select ObtMarks from marks where paperCode = '"+ sub + "');";

        ResultSet res = dbUtil.executeQueryStatement(query);

        int minMarks = 0;

        try {
            minMarks = res.getInt("minMarks");
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState());
        }

        return minMarks;
    }

    public int highestMarkBySub(String sub) {
        String query = "select max(ObtMarks) 'maxMarks' from (select ObtMarks from marks where paperCode = '"+ sub + "');";

        ResultSet res = dbUtil.executeQueryStatement(query);

        int maxMarks = 0;

        try {
            maxMarks = res.getInt("maxMarks");
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState());
        }

        return maxMarks;
    }

    public int avgMarkBySub(String sub) {
        String query = "select avg(ObtMarks) 'avgMarks' from (select ObtMarks from marks where paperCode = '"+ sub + "');";

        ResultSet res = dbUtil.executeQueryStatement(query);

        int avgMarks = 0;

        try {
            avgMarks = res.getInt("agvMarks");
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState());
        }

        return avgMarks;
    }

    public float passPercentBySub(String sub) {
        return 1.0f;

        //TODO
    }
}
