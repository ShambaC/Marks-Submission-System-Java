package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Utility class to perform SQL queries on mariaDB
 */

public class DButil {
    private Connection conn;

    public DButil(String DBaddress, String DBUser, String DBPassword) {
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://"+ DBaddress + "/", DBUser, DBPassword);

            boolean isDBexist = false;
            ResultSet res = conn.getMetaData().getCatalogs();
            while(res.next()) {
                String dbName = res.getString(1);

                if(dbName.equalsIgnoreCase("mss")) {
                    isDBexist = true;
                    break;
                }
            }
            res.close();

            if(!isDBexist) {
                Statement statement = conn.createStatement();            
                String createDB = "create database mss";

                statement.executeUpdate(createDB);
                statement.close();
            }

            conn.close();
            conn = DriverManager.getConnection("jdbc:mariadb://"+ DBaddress + "/mss", DBUser, DBPassword);
        }
        catch(SQLException err) {
            err.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        if(conn != null) {
            try {
                conn.close();
            }
            catch(SQLException err) {
                err.printStackTrace();
            }
        }
    }
}
