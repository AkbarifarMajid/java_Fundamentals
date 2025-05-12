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
        try {
            DatabaseUtils.executeUpdate(sql, motorrad.getId(), motorrad.getHubraum(), motorrad.isGangschaltung());

        } catch (Exception error) {
            System.out.println("beim Einfügen in die Motorrad-Tabelle gibt es Problem: " + error.getMessage());
        }
    }// End einfuegen


    // Gibt den Hubraum eines Motorrads anhand seiner Fahrzeug-ID zurück
    public static int getHubraum(int fahrzeugId) {
        String sql = "SELECT hubraum FROM motorrad WHERE fahrzeug_id = ?";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt("hubraum");
            }
        } catch (SQLException error) {
            System.out.println("beim Laden von Hubraum gibt es Problem: " + error.getMessage());
        }
        return -1;
    }// End getHubraum


    // Prüft, ob das Motorrad eine Gangschaltung hat.
    public static boolean getGangschaltung(int fahrzeugId) {
        String sql = "SELECT gangschaltung FROM motorrad WHERE fahrzeug_id = ?";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getBoolean("gangschaltung");
            }
        } catch (SQLException error) {
            System.out.println("beim Laden von Gangschaltung gibt es Problem: " + error.getMessage());
        }
        return false;
    }// End getGangschaltung

    // Aktualisiert die Werte für Hubraum und Gangschaltung eines Motorrads
    public static void berabeiten(Motorrad motorrad) {
        String sql = "UPDATE motorrad SET hubraum = ?, gangschaltung = ? WHERE fahrzeug_id = ?";
        try {
            DatabaseUtils.executeUpdate(sql,
                    motorrad.getHubraum(),
                    motorrad.isGangschaltung(),
                    motorrad.getId()
            );
        } catch (Exception error) {
            System.out.println("beim Aktualisieren der Motorrad-Tabelle gibt es Problem: " + error.getMessage());
        }
    }// End berabeiten


}
