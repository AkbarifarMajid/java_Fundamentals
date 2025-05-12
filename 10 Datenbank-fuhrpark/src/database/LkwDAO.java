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
        try {
            DatabaseUtils.executeUpdate(sql, lkw.getId(), lkw.getLadegewicht());
        } catch (Exception error) {
            System.out.println("beim Einfügen in die Tabelle LKW gibt es Problem: " + error.getMessage());
        }
    } // End einfuegen


    // Holt das Ladegewicht eines LKWs anhand der Fahrzeug-ID
    public static double getLadegewicht(int fahrzeugId) {
        String sql = "SELECT ladegewicht FROM lkw WHERE fahrzeug_id = ?";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getDouble("ladegewicht");
            }
        } catch (SQLException error) {
            System.out.println("beim Laden von Ladegewicht gibt es Problem: " + error.getMessage());
        }
        return -1;
    } // End getLadegewicht

    // Aktualisiert das Ladegewicht eines LKWs in der Datenbank
    public static void berabeiten(LKW lkw) {
        String sql = "UPDATE lkw SET ladegewicht = ? WHERE fahrzeug_id = ?";
        try {
            DatabaseUtils.executeUpdate(sql, lkw.getLadegewicht(), lkw.getId());
        } catch (Exception error) {
            System.out.println("beim Aktualisieren der Tabelle LKW gibt es Problem: " + error.getMessage());
        }
    }// End berabeiten

}
