package database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseManager {

    private static Connection myFuhrpark_DB_Connection = null;

    // Methode zum Abrufen einer aktiven Verbindung
    public static Connection getMyFuhrpark_DB_Connection() throws SQLException {
        if (myFuhrpark_DB_Connection == null || myFuhrpark_DB_Connection.isClosed()) {
            try {
                Properties myConecet_Properties = new Properties();
                InputStream input_Info = DatabaseManager.class.getClassLoader().getResourceAsStream("db.properties");

                if (input_Info == null) {
                    throw new RuntimeException(" Es wurde keine Datenbank mit diesen Spezifikationen (db.properties) gefunden.");
                }

                myConecet_Properties.load(input_Info);

                String url = myConecet_Properties.getProperty("db.url");
                String user = myConecet_Properties.getProperty("db.user");
                String password = myConecet_Properties.getProperty("db.password");

                myFuhrpark_DB_Connection = DriverManager.getConnection(url, user, password);

            } catch (Exception e) {
                throw new SQLException("bei der Datenbankverbindung gibt es Problem: " + e.getMessage());
            }
        }
        return myFuhrpark_DB_Connection;
    }
}
