package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;


  //Stellt eine Verbindung zur MySQL-Datenbank her.
  //Liest Konfigurationsdaten aus der Datei db.properties.

public final class DatabaseManager {

    private static Connection verbindung;

    //Gibt die Singleton-Instanz der Datenbankverbindung zurück.

    public static Connection getConnection() {
        try {
            if (verbindung == null || verbindung.isClosed()) {
                try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("db.properties")) {
                    Properties props = new Properties();
                    props.load(input);

                    String url = props.getProperty("url");
                    String user = props.getProperty("user");
                    String password = props.getProperty("password");

                    verbindung = DriverManager.getConnection(url, user, password);
                    System.out.println("Connected");
                }
            }
        } catch (Exception e) {
            System.out.println("beim Herstellen der Datenbankverbindung gibt es Problem: " + e.getMessage());
        }

        return verbindung;
    }


   //Trennt die Verbindung zur Datenbank .

    public static void verbindungSchliessen() {
        if (verbindung != null) {
            try {
                verbindung.close();
                verbindung = null;
                System.out.println("Verbindung zur Datenbank geschlossen.");
            } catch (SQLException e) {
                System.out.println(" beim Schließen der Verbindung gibt es Problem: " + e.getMessage());
            }
        }
    }
}
