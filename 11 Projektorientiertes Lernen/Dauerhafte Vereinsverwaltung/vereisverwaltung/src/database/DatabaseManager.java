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
        if (verbindung == null) {
            try (InputStream input_info = DatabaseManager.class.getClassLoader().getResourceAsStream("db.properties")) {
                Properties myproperties = new Properties();
                myproperties.load(input_info);

                String url = myproperties.getProperty("url");
                String user = myproperties.getProperty("user");
                String password = myproperties.getProperty("password");

                verbindung = DriverManager.getConnection(url, user, password);
                System.out.println("Connected");

            } catch (Exception e) {
                System.out.println("beim Herstellen der Datenbankverbindung gibt es Problem: " + e.getMessage());
            }
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
