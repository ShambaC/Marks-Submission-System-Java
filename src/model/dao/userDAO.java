package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.transferObjects.userTO;
import utility.DButil;

public class userDAO {
    /**
     * Inserts a row into the user table
     * @param uTO user Transfer Object, has sensitive data
     */
    public void store(userTO uTO) {
        String query = "insert into usertable values(";

        query += "'" + uTO.email + "', ";
        query += "'" + uTO.passHash + "');";

        System.out.println(query);

        DButil dbUtil = DButil.getInstance();
        int res = dbUtil.executeUpdateStatement(query);
        System.out.println(res + " rows effected");
    }

    /**
     * Method to return a list of all rows in user table
     * @return A list of user transfer objects
     */
    public List<userTO> retrieve() {
        List<userTO> uToList = new ArrayList<userTO>();

        DButil dbUtil = DButil.getInstance();
        ResultSet res = dbUtil.executeQueryStatement("select * from usertable;");

        try {
            while (res.next()) {
                String email = res.getString("email");
                String passHash = res.getString("passHash");

                userTO uTO = new userTO(email, passHash);
                uToList.add(uTO);
            }
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState());
        }

        return uToList;
    }

    /**
     * Method to get user data of a specific user
     * @param id email of the user to get data for
     * @return user Transfer object for that particular user
     */
    public userTO retrieveOne(String id) {
        userTO uTO = null;

        DButil dbUtil = DButil.getInstance();
        ResultSet res = dbUtil.executeQueryStatement("select * from usertable where email='" + id + "';");

        try {
            while (res.next()) {
                String email = res.getString("email");
                String passHash = res.getString("passHash");

                uTO = new userTO(email, passHash);
            }
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState());
        }

        return uTO;
    }
}
