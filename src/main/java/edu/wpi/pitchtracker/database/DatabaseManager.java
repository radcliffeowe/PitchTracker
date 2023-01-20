package edu.wpi.pitchtracker.database;

import edu.wpi.pitchtracker.database.Pitcher.PitcherDAOImpl;
import edu.wpi.pitchtracker.database.Pitches.PitchesDAOImpl;

import java.io.IOException;
import java.sql.*;

public class DatabaseManager {

    private final PitcherDAOImpl pitcherDAO = new PitcherDAOImpl();
    private final PitchesDAOImpl pitchesDAO = new PitchesDAOImpl();

    public enum ConnType {
        EMBEDDED,
        CLIENTSERVER
    }

    private ConnType connType = ConnType.EMBEDDED;

    protected String defaultUR = "jdbc:derby://localhost:1527/csDB;create=true";
    private Connection dbConnection;
    /** Singleton instance of Database Manager */
    private static DatabaseManager databaseManager;

    private DatabaseManager() {
        connectDatabase(connType);
    }

    public static DatabaseManager getInstance() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    /**
     * gets the connection object
     *
     * @return Connection
     */
    public Connection getDatabaseConnection() {
        return dbConnection;
    }

    /**
     * Connects to the database returns null object if connection failed
     *
     * @return Connection object
     * @param type
     */
    private void connectDatabase(ConnType type) {
        System.out.println("Connecting to Database Type:" + type.toString());
        if (type == ConnType.EMBEDDED) {
            dbConnection = connectEmbedded();
        } else {
            dbConnection = connectRemote(defaultUR);

            if (dbConnection == null) {
                System.out.println("Connecting to Embedded Instead");
                dbConnection = connectEmbedded();
            }
        }
    }

    /**
     * Connects to the database returns null object if connection failed
     *
     * @return Connection object
     */
    private Connection connectDatabase(ConnType type, String url) {
        System.out.println("Connecting to Database Type:" + type.toString());
        dbConnection = null;
        if (type == ConnType.EMBEDDED) {
            dbConnection = connectEmbedded();
        } else {
            dbConnection = connectRemote(url);

            if (dbConnection == null) {
                System.out.println("Connecting to Embedded Instead");
                dbConnection = connectEmbedded();
            }
        }
        return dbConnection;
    }

    private Connection connectRemote(String url) {
        Connection tempConn = null;

        System.out.println("Attempting Connection to Remote: " + url);
        try {
            // Class.forName("org.apache.derby.jdbc.ClientDriver");

            System.out.println("Remote Driver Registered");

            tempConn = DriverManager.getConnection(url); // CONNECT TO DATABASE
        } catch (SQLException e) {
            System.out.println("Remote Driver not Found");
            e.printStackTrace();
        }
        if (tempConn != null) {
            System.out.println("Derby Remote Connection Established");
        } else {
            System.out.println("Derby Remote Connection Failed");
        }
        return tempConn;
    }

    private Connection connectEmbedded() {
        Connection tempConn = null;

        System.out.println("Attempting Connection to Embedded");
        try {
            // Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            System.out.println("Embedded Driver Registered");
            tempConn = null;

            tempConn = DriverManager.getConnection("jdbc:derby:myDB;create=true"); // CONNECT TO DATABASE
            assert (tempConn != null);

        } catch (SQLException e) {
            System.out.println("Embedded Connection Failed");
            e.printStackTrace();
            return null;
        }
        if (tempConn != null) {
            System.out.println("Derby Embedded Connection Established");
        } else {
            System.out.println("Derby Embedded Connection Failed");
        }
        return tempConn;
    }

    public void dropTableIfExist(String droppingTable) throws SQLException {
        if (dbConnection
                .getMetaData()
                .getTables(null, null, droppingTable.toUpperCase(), null)
                .next()) {
            runStatement("DROP TABLE " + droppingTable);
            //      System.out.println("Dropping " + droppingTable + " table!");
        } else {
            //      System.out.println(droppingTable + " table does not Exist!");
        }
    }

    /**
     * Runs SQL statement
     *
     * @param statement statement to run
     * @throws SQLException if there is an error with the statement (santax, etc)
     */
    public void runStatement(String statement) throws SQLException {
        Statement stm = dbConnection.createStatement();
        // System.out.println("SQL: " + statement);
        stm.execute(statement);
        // System.out.println(statement);
        stm.close();
    }
    /**
     * Executes query to the apache derby database
     *
     * @param query String to be executed
     * @return return the rset containing the query
     * @throws SQLException when there is a sql problem
     */
    public ResultSet runQuery(String query) throws SQLException {
        Statement stm = dbConnection.createStatement();
        // System.out.println("SQL: " + query);
        try {
            return stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stm.close();
        return null;
    }

    public void initializeDatabaseManager() throws SQLException, IOException {
        pitcherDAO.initTable("edu/wpi/pitchtracker/Pitchers.csv");
        pitchesDAO.initTable("edu/wpi/pitchtracker/Pitches.csv");
    }
}
