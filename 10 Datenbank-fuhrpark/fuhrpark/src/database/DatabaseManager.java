package database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public final class DatabaseManager {

    // Statische Variable für die Datenbankverbindung
    private static Connection myFuhrpark_DB_Connection = null;

    // Privater Konstruktor verhindert das entstehen lassen von Objekten dieser Klasse
    private DatabaseManager() {

    }

    // Methode zum Abrufen einer aktiven Verbindung
    public static Connection getMyFuhrpark_DB_Connection() throws SQLException {
        if (myFuhrpark_DB_Connection == null || myFuhrpark_DB_Connection.isClosed()) {
            try {
                // Lädt die Datenbankkonfiguration aus der Datei db.properties
                Properties myConecet_Properties = new Properties();
                InputStream input_Info = DatabaseManager.class.getClassLoader().getResourceAsStream("db.properties");

                if (input_Info == null) {
                    // Wenn die Konfigurationsdatei nicht gefunden wurde
                    throw new RuntimeException(" Es wurde keine Datenbank mit diesen Spezifikationen (db.properties) gefunden.");
                }

                // Liest die Eigenschaften (URL, Benutzername, Passwort) ein
                myConecet_Properties.load(input_Info);

                String url = myConecet_Properties.getProperty("db.url");
                String user = myConecet_Properties.getProperty("db.user");
                String password = myConecet_Properties.getProperty("db.password");

                // Stellt die Verbindung zur Datenbank her
                myFuhrpark_DB_Connection = DriverManager.getConnection(url, user, password);

            } catch (Exception e) {
                throw new SQLException("bei der Datenbankverbindung gibt es Problem: " + e.getMessage());
            }
        }
        // Gibt die aktive Verbindung zurück
        return myFuhrpark_DB_Connection;
    }
}
