package database;

import model.LKW;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LkwDAO {

    // Fügt ein LKW-Objekt (fahrzeug_id, ladegewicht) in die Datenbank ein
    public static void einfuegen(LKW lkw) {
        String sql = "INSERT INTO lkw (fahrzeug_id, ladegewicht) VALUES (?, ?)";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, lkw.getId());
            statement.setDouble(2, lkw.getLadegewicht());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("beim Einfügen in LKW-Tabelle gibt es Problem: " + e.getMessage());
        }
    }

    // Holt das Ladegewicht eines LKWs anhand der Fahrzeug-ID
    public static double getLadegewicht(int fahrzeugId) {
        String sql = "SELECT ladegewicht FROM lkw WHERE fahrzeug_id = ?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, fahrzeugId);
            ResultSet result = statement.executeQuery();

            if (result.next()) return result.getDouble("ladegewicht");
        } catch (SQLException e) {
            System.out.println(" beim Laden von Ladegewicht gibt es Problem: " + e.getMessage());
        }
        return -1;
    }

    // Aktualisiert das Ladegewicht eines LKWs in der Datenbank
    public static void berabeiten(LKW lkw) {
        String sql = "update lkw set ladegewicht = ? where fahrzeug_id=?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, lkw.getLadegewicht());
            statement.setInt(2, lkw.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("beim Update in LKW-Tabelle Gibt es Problem: " + e.getMessage());
        }
    }
}
