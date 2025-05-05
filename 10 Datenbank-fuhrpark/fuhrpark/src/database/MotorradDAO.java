package database;

import model.Motorrad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Datenbankzugriff für Motorräder
public class MotorradDAO {

    // Datenbankzugriff für Motorräder nur Fahrzeug id und Hubraum Kraft und  hat Gangschaltung oder nein.
    public static void einfuegen(Motorrad motorrad) {
        String sql = "INSERT INTO motorrad (fahrzeug_id, hubraum, gangschaltung) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, motorrad.getId());
            statement.setInt(2, motorrad.getHubraum());
            statement.setBoolean(3, motorrad.isGangschaltung());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("beim Einfügen in Motorrad-Tabelle gibt es Problem: " + e.getMessage());
        }
    }

    // Gibt den Hubraum eines Motorrads anhand seiner Fahrzeug-ID zurück
    public static int getHubraum(int fahrzeugId) {
        String sql = "SELECT hubraum FROM motorrad WHERE fahrzeug_id = ?";
        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, fahrzeugId);
            ResultSet result = statement.executeQuery();
            if (result.next()) return result.getInt("hubraum");
        } catch (SQLException e) {
            System.out.println("beim Laden von Hubraum gibt es problem: " + e.getMessage());
        }
        return -1;
    }


    // Prüft, ob das Motorrad eine Gangschaltung hat (true/false)
    public static boolean getGangschaltung(int fahrzeugId) {
        String sql = "SELECT gangschaltung FROM motorrad WHERE fahrzeug_id = ?";
        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, fahrzeugId);
            ResultSet result = statement.executeQuery();
            if (result.next()) return result.getBoolean("gangschaltung");
        } catch (SQLException e) {
            System.out.println(" beim Laden von Gangschaltung gibt es Problem: " + e.getMessage());
        }
        return false;
    }

    // Aktualisiert die Werte für Hubraum und Gangschaltung eines Motorrads
    public static void berabeiten(Motorrad motorrad) {
        String sql =  "update motorrad set hubraum = ?, gangschaltung = ?  where fahrzeug_id=?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, motorrad.getHubraum());
            statement.setBoolean(2, motorrad.isGangschaltung());
            statement.setInt(3, motorrad.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("beim Update in Motorrad-Tabelle gibt es Problem: " + e.getMessage());
        }
    }


}
