package database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public final class DatabaseManager {

    // Statische Variable für die Datenbankverbindung (Singleton)
    private static Connection myFuhrpark_DB_Connection = null;

    // Privater Konstruktor verhindert das Erstellen von Objekten dieser Klasse,
    // Brauchen nicht object machen von diese class
    private DatabaseManager() {
        // Leerer privater Konstruktor
    }

    // Methode zum Abrufen einer aktiven Verbindung
    //ist die einzige Möglichkeit, eine Verbindung zur Datenbank herzustellen.
    public static Connection getMyFuhrpark_DB_Connection() throws SQLException {
       // Wenn die Verbindung noch nicht erstellt wurde oder bereits erstellt, abergeschlossen  wurde, wird eine neue Verbindung erstellt.
        if (myFuhrpark_DB_Connection == null || myFuhrpark_DB_Connection.isClosed()) {
            try {
                // Lädt die Datenbankkonfiguration aus der Datei db.properties
                Properties myConnect_Properties = new Properties();
                InputStream input_Info = DatabaseManager.class.getClassLoader().getResourceAsStream("db.properties");

                // Wenn die Konfigurationsdatei nicht gefunden wurde bei GUI-Fehlermeldung
                if (input_Info == null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Die Datenbank-Konfigurationsdatei (db.properties) wurde nicht gefunden.\nBitte überprüfen Sie den Projektordner.",
                            "Verbindungsfehler",
                            JOptionPane.ERROR_MESSAGE
                    );
                    System.exit(1); // Beendet das Programm sauber
                }

                // Liest die Eigenschaften (URL, Benutzername, Passwort) ein
                myConnect_Properties.load(input_Info);
                String url = myConnect_Properties.getProperty("db.url");
                String user = myConnect_Properties.getProperty("db.user");
                String password = myConnect_Properties.getProperty("db.password");

                // Stellt die Verbindung zur Datenbank
                myFuhrpark_DB_Connection = DriverManager.getConnection(url, user, password);

            } catch (Exception error) {
                JOptionPane.showMessageDialog(
                        null,
                        "Bei Database Connection gibt es Problem ! :\n" + error.getMessage(),
                        "Verbindungsfehler",
                        JOptionPane.ERROR_MESSAGE
                );
                System.exit(1);
            }
        }
        // Gibt die aktive Verbindung zurück
        return myFuhrpark_DB_Connection;

    } // End getMyFuhrpark_DB_Connection

}
