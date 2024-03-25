package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.transferObjects.marksTO;
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
        ResultSet res = dbUtil.executeQueryStatement("select * from marks;");

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
}
