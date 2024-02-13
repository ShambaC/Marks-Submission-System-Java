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
    // The connection to the Database
    private Connection conn;

    /**
     * Initializes the connection to the database.
     * <p> Connects to the database named mss. Creates one if it doesn't already exist
     * @param DBaddress     The address of the Database server
     * @param DBUser        The user to login into the database
     * @param DBPassword    The password for the user account
     */
    public DButil(String DBaddress, String DBUser, String DBPassword) throws ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        try {
            // Create connection to the DB
            conn = DriverManager.getConnection("jdbc:mariadb://"+ DBaddress + "/", DBUser, DBPassword);

            boolean isDBexist = false;
            // Get a list of all databases on the server
            ResultSet res = conn.getMetaData().getCatalogs();
            // Iterate through the list to search for the 'mss' database
            while(res.next()) {
                // Get the name of the database
                String dbName = res.getString(1);

                // Check if 'mss'
                if(dbName.equalsIgnoreCase("mss")) {
                    isDBexist = true;
                    break;
                }
            }
            // Close the query results
            res.close();

            // If database does not exist, then create it
            if(!isDBexist) {
                Statement statement = conn.createStatement();            
                String createDB = "create database mss";

                statement.executeUpdate(createDB);
                statement.close();
            }

            // Close the connection
            conn.close();
            // Open the connection to the required database
            conn = DriverManager.getConnection("jdbc:mariadb://"+ DBaddress + "/mss", DBUser, DBPassword);
        }
        catch(SQLException err) {
            err.printStackTrace();
        }
    }

    /**
     * Get the connection to the {@code mss} database
     * @return Connection object
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * close the connection to the database
     */
    public void closeConnection() {
        if(conn != null) {
            try {
                conn.close();
                conn = null;
            }
            catch(SQLException err) {
                err.printStackTrace();
            }
        }
    }
}
