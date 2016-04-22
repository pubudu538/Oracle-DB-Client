package db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OracleClient {

    private String host;
    private String port;
    private String username;
    private String password;
    private String dbName;
    private String connectionName;
    private static Log log = LogFactory.getLog(OracleClient.class);
    private static final DateFormat dateFormat = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");
    private Connection connection = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public OracleClient(String connectionName, String host, String port, String dbName, String username, String password) {

        this.connectionName = connectionName;
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.username = username;
        this.password = password;

    }

    private Connection getConnection() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName, username,
                        password);
                connection.setAutoCommit(true);

            } catch (SQLException e) {
                log.error("Connection Failed! Check output console - " + e.getMessage(), e);
            }

            if (connection != null) {
                log.info("Successfully created connection!!! [Connection Name] - " + connectionName + "");
            } else {
                log.error("Failed to make connection!");
            }

        }

        return connection;

    }

    public void queryValues(String tableName, String coloumnName) {

        String data = "";
        Statement smt = null;

        String query = "SELECT * FROM " + tableName;
        Connection dbCon = getConnection();

        try {
            smt = dbCon.createStatement();
            pstmt = dbCon.prepareStatement(query); // create a statement
            rs = pstmt.executeQuery();

            // rs contains all return values for your query
            while (rs.next()) {
                data = rs.getString(coloumnName);
            }

        } catch (SQLException e) {
            log.error("Could not query results - " + e.getMessage(), e);
        } finally {
            log.info("[Connection Name] " + connectionName + " [Data] - " + data);
            try {
                if (smt != null) {
                    smt.close();
                }

            } catch (SQLException e) {
                log.error("Could not close the Statement  - " + e.getMessage(), e);
            }
        }
    }

    public void closeConnections() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("Could not close result set - " + e.getMessage(), e);
            }
        }

        if (pstmt != null)
            try {
                pstmt.close();
            } catch (SQLException e) {
                log.error("Could not close prep - " + e.getMessage(), e);
            }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Could not close db connection - " + e.getMessage(), e);
            }
        }
    }


    public void insertRecordIntoDbUserTable() throws SQLException {

        Connection dbConnection = null;
        Statement statement = null;

        String insertTableSQL = "INSERT INTO DBUSER"
                + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
                + "(1,'sample','system', " + "to_date('"
                + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(insertTableSQL);

            log.info("Record is inserted into DBUSER table!");

        } catch (SQLException e) {
            log.error("Could not insert values - " + e.getMessage(), e);
        } finally {

            if (statement != null) {
                statement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }

    private String getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return dateFormat.format(today.getTime());

    }

    public void createDbUserTable() throws SQLException {

        Connection con = getConnection();
        Statement statement = null;

        String createTableSQL = "CREATE TABLE DBUSER("
                + "USER_ID NUMBER(5) NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "CREATED_BY VARCHAR(20) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) "
                + ")";

        try {

            statement = con.createStatement();
            statement.execute(createTableSQL);

            log.info("Table \"dbuser\" is created!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (con != null) {
                con.close();
            }

        }

    }

}