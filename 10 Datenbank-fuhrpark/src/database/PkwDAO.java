package database;

import model.PKW;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Datenbankzugriff für PKW-spezifische Daten.

public class PkwDAO {

    // Fügt ein PKW-Objekt (fahrzeug_id, sitzanzahl) in die PKW-Tabelle der Datenbank ein
    public static void einfuegen(PKW pkw) {
        String sql = "INSERT INTO pkw (fahrzeug_id, sitzanzahl) VALUES (?, ?)";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pkw.getId());  // bereits in Fahrzeug gespeichert
            statement.setInt(2, pkw.getSitzanzahl());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(" beim Einfügen in PKW-Tabelle gibt es Problem: " + e.getMessage());
        }
    }

    // Gibt die Sitzanzahl eines PKWs anhand der Fahrzeug-ID zurück
    public static int getSitzanzahl(int fahrzeugId) {
        String sql = "SELECT sitzanzahl FROM pkw WHERE fahrzeug_id = ?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, fahrzeugId);
            ResultSet result = statement.executeQuery();

            if (result.next()) return result.getInt("sitzanzahl");
        } catch (SQLException e) {
            System.out.println("beim Laden von Sitzanzahl gibt es Problem: " + e.getMessage());
        }
        return -1;
    }

    // Aktualisiert die Sitzanzahl eines vorhandenen PKWs in der Datenbank
    public static void berabeiten(PKW pkw) {
        String sql = "update pkw set sitzanzahl=? where fahrzeug_id=?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pkw.getSitzanzahl());
            statement.setInt(2, pkw.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("beim Update in PKW-Tabelle Gibt es Problem: " + e.getMessage());
        }
    }
}
