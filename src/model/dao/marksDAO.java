package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.transferObjects.marksTO;
import model.transferObjects.studentTO;
import utility.DButil;

/**
 * Marks Data Access Object to modfiy the marks table in DB
 */
public class marksDAO {
    /**
     * Inserts a row into the student table
     * @param sTO The student transfer object containing information to push into the DB
     */
    public void storeStudents(studentTO sTO) {
        String query = "insert into studenttable values(";

        query += Integer.toString(sTO.roll) + ", ";
        query += "'" + sTO.coll + "', ";
        query += "'" + sTO.cate + "');";

        System.out.println(query);

        DButil dbUtil = DButil.getInstance();
        int res = dbUtil.executeUpdateStatement(query);
        System.out.println(res + " rows effected");
    }
    /**
     * Inserts a row into the marks table
     * @param mTO The marks transfer object containing information to push into the DB
     */
    public void store(marksTO mTO) {
        String query = "insert into marks values(";

        query += Integer.toString(mTO.roll) + ", ";
        query += "'" + mTO.coll + "', ";
        query += "'" + mTO.cate + "', ";
        query += "'" + mTO.paperName + "', ";
        query += "'" + mTO.paperCode + "', ";
        query += "'" + mTO.paperType + "', ";
        query += "'" + mTO.half + "', ";
        query += Integer.toString(mTO.FullMarks) + ", ";
        query += Integer.toString(mTO.ObtMarks) + ");";

        System.out.println(query);

        DButil dbUtil = DButil.getInstance();
        int res = dbUtil.executeUpdateStatement(query);
        System.out.println(res + " rows effected");
    }

    /**
     * Method to return a list of all rows in marks table
     * @return A list of marks transfer objects
     */
    public List<marksTO> retrieve() {
        List<marksTO> mToList = new ArrayList<marksTO>();

        DButil dbUtil = DButil.getInstance();
        ResultSet res = dbUtil.executeQueryStatement("select * from marks;");

        try {
            while (res.next()) {
                int roll = res.getInt("roll");
                String coll = res.getString("coll");
                String cate = res.getString("cate");
                String paperName = res.getString("paperName");
                String paperCode = res.getString("paperCode");
                String paperType = res.getString("paperType");
                String half = res.getString("half");
                int FullMarks = res.getInt("FullMarks");
                int ObtMarks = res.getInt("ObtMarks");

                marksTO mTO = new marksTO(roll, coll, cate, paperName, paperCode, paperType, half, FullMarks, ObtMarks);
                mToList.add(mTO);
            }
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }

        return mToList;
    }

    /**
     * Method to return a list of all rows in marks table for a particular student
     * @param rollNum The roll number of the student
     * @return A list of marks transfer objects
     */
    public List<marksTO> retrieveOne(String rollNum) {
        List<marksTO> mToList = new ArrayList<marksTO>();

        DButil dbUtil = DButil.getInstance();
        ResultSet res = dbUtil.executeQueryStatement("select * from marks where roll=" + rollNum + ";");

        try {
            while (res.next()) {
                int roll = res.getInt("roll");
                String coll = res.getString("coll");
                String cate = res.getString("cate");
                String paperName = res.getString("paperName");
                String paperCode = res.getString("paperCode");
                String paperType = res.getString("paperType");
                String half = res.getString("half");
                int FullMarks = res.getInt("FullMarks");
                int ObtMarks = res.getInt("ObtMarks");

                marksTO mTO = new marksTO(roll, coll, cate, paperName, paperCode, paperType, half, FullMarks, ObtMarks);
                mToList.add(mTO);
            }
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }

        return mToList;
    }

    /**
     * Method to return a list of all rows in students table
     * @return A list of Student transfer objects
     */
    public List<studentTO> retrieveStudent() {
        List<studentTO> sToList = new ArrayList<studentTO>();

        DButil dbUtil = DButil.getInstance();
        ResultSet res = dbUtil.executeQueryStatement("select * from studenttable;");

        try {
            while (res.next()) {
                int roll = res.getInt("roll");
                String coll = res.getString("coll");
                String cate = res.getString("cate");

                studentTO sTO = new studentTO(roll, coll, cate);
                sToList.add(sTO);
            }
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }

        return sToList;
    }
}
