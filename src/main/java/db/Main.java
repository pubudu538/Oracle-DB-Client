package db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;

/**
 * Created by pubudu on 4/20/16.
 */
public class Main {

    private static String host;
    private static String port;
    private static String username;
    private static String password;
    private static String dbName;
    private static String tableName;
    private static String columnName;
    private static Integer numberOfConnections;
    private static Log log = LogFactory.getLog(Main.class);

    public static void main(String[] argv) {

        PropertyConfigurator.configure(System.getProperty("log4j.properties.file.path", "src/main/conf/log4j.properties"));

        readConfigValues();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            log.info("Oracle JDBC Driver Registered!");

        } catch (ClassNotFoundException e) {
            log.error("Oracle JDBC Driver is missing? - " + e.getMessage(), e);
            return;
        }

        ArrayList<OracleClient> clientList = new ArrayList<OracleClient>();

        for (int i = 0; i < numberOfConnections; i++) {
            String connectionName = "Conn_";
            clientList.add(new OracleClient(connectionName+i, host, port, dbName, username, password));
        }


        while (true) {
            log.info("Waiting..");

            for (int i = 0; i < numberOfConnections; i++) {
                clientList.get(i).queryValues(tableName,columnName);
                clientList.get(i).closeConnections();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error(e);
                e.printStackTrace();
            }
        }
    }


    private static void readConfigValues() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(System.getProperty("config.properties.file.path", "src/main/conf/config.properties"));
            prop.load(input);

            host = prop.getProperty("host");
            port = prop.getProperty("port");
            dbName = prop.getProperty("db_name");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            tableName = prop.getProperty("table_name");
            numberOfConnections = Integer.parseInt(prop.getProperty("number_of_connections"));
            columnName = prop.getProperty("column_name");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
