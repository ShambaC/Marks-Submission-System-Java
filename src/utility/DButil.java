package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to perform SQL queries on mariaDB
 */

public class DButil {
    /**
     * Static reference to the singleton variable
     */
    private static DButil DBUtilInstance = null;
    /**
     * The connection to the Database
     */
    private Connection conn;

    /**
     * String to store the location of the schemas
     */
    private String pathToSchema;

    private DButil(Connection conn) {
        this.conn = conn;
    }

    /**
     * Initializes the connection to the database.
     * <p> Connects to the database named mss. Creates one if it doesn't already exist
     * @param DBaddress     The address of the Database server
     * @param DBUser        The user to login into the database
     * @param DBPassword    The password for the user account
     * @param pathToSchema  Stores the path to the schema folder
     */
    public DButil(String DBaddress, String DBUser, String DBPassword, String pathToSchema) {
        if(conn != null) return;

        try {
            //Initialize the schema path
            this.pathToSchema = pathToSchema;
            // Load the mariadb jdbc driver
            Class.forName("org.mariadb.jdbc.Driver");
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

            DBUtilInstance = new DButil(conn);
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }
        catch(ClassNotFoundException err) {
            err.printStackTrace();
        }
    }

    /**
     * Returns the singleton instance of this class
     * @return DBUtilInstance
     */
    public static  DButil getInstance() {
        return DBUtilInstance;
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

    /**
     * Method to generate a list of SQL queries or statements from a sql file.
     * @param filePath  Path to the file containing the SQL statements
     * @return  A List of Statements as read from the file
     * @throws IOException
     */
    private List<String> generateStatementFromFile(String filePath) throws IOException {
        List<String> statementList = new ArrayList<>();

        FileReader reader = new FileReader(filePath);
        BufferedReader bReader = new BufferedReader(reader);

        StringBuilder builder = new StringBuilder();
        String line;

        while((line = bReader.readLine()) != null) {
            line = line.trim();

            if(line.isEmpty() || line.startsWith("--"))
                continue;
            
            builder.append(line);

            if(line.endsWith(";")) {
                statementList.add(builder.toString());
                builder.setLength(0);
            }
        }
        bReader.close();

        return statementList;
    }

    /**
     * Method to execute update statements
     * <p> Update statements include INSERT, UPDATE and DELETE
     * <p> The result shows the number of rows effected
     * @param query Query to execute
     * @return The result of the query execution
     */
    public int executeUpdateStatement(String query) {
        int res = 0;
        try {
            Statement stmt = conn.createStatement();
            res = stmt.executeUpdate(query);
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }
        
        return res;
    }

    /**
     * Method to execute a query statement and get the result
     * @param query Query to execute
     * @return A ResultSet containing the result table
     */
    public ResultSet executeQueryStatement(String query) {
        ResultSet res = null;

        try {
            Statement stmt = conn.createStatement();
            res = stmt.executeQuery(query);
        }
        catch(SQLException err) {
            System.err.println("execute query statement error" + err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage() + " " + err.getMessage());
        }

        return res;
    }

    /**
     * Initializes the database by creating the required tables
     */
    public void dbInit() {
        boolean isMarksTableExist = false;
        boolean isStudentTableExist = false;
        boolean isUserTableExist = false;

        try {
            ResultSet res = conn.getMetaData().getTables(null, null, "%", null);
            while(res.next()) {
                String tableName = res.getString(3);

                if(tableName.equalsIgnoreCase("marks"))     isMarksTableExist = true;
                if(tableName.equalsIgnoreCase("studentTable"))   isStudentTableExist = true;
                if(tableName.equalsIgnoreCase("userTable"))      isUserTableExist = true;
            }
            res.close();

            List<String> statementList = new ArrayList<>();
            if(!isUserTableExist) {
                statementList = generateStatementFromFile(pathToSchema + "userSchema.sql");
                for(String s : statementList) {
                    int ret = executeUpdateStatement(s);
                    System.out.println(ret + " rows effected!");
                }
            }
            if(!isStudentTableExist) {
                statementList = generateStatementFromFile(pathToSchema + "studentSchema.sql");
                for(String s : statementList) {
                    int ret = executeUpdateStatement(s);
                    System.out.println(ret + " rows effected!");
                }
            }
            if(!isMarksTableExist) {
                statementList = generateStatementFromFile(pathToSchema + "marksSchema.sql");
                for(String s : statementList) {
                    int ret = executeUpdateStatement(s);
                    System.out.println(ret + " rows effected!");
                }
            }

            System.out.println("DB initialized");
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }
}
