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

        try {
            DatabaseUtils.executeUpdate(sql, pkw.getId(), pkw.getSitzanzahl());
        } catch (Exception error) {
            System.out.println("beim Einfügen in die PKW-Tabelle gibt es Problem: " + error.getMessage());
        }
    }// End einfuegen


    // Gibt die Sitzanzahl eines PKWs anhand der Fahrzeug-ID zurück
    public static int getSitzanzahl(int fahrzeugId) {
        String sql = "SELECT sitzanzahl FROM pkw WHERE fahrzeug_id = ?";

        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt("sitzanzahl");
            }
        } catch (SQLException error) {
            System.out.println("beim Laden von Sitzanzahl gibt es Problem: " + error.getMessage());
        }
        return -1;
    }// End getSitzanzahl


    // Aktualisiert die Sitzanzahl eines vorhandenen PKWs in der Datenbank
    public static void berabeiten(PKW pkw) {
        String sql = "UPDATE pkw SET sitzanzahl = ? WHERE fahrzeug_id = ?";

        try {
            DatabaseUtils.executeUpdate(sql, pkw.getSitzanzahl(), pkw.getId());
        } catch (Exception error) {
            System.out.println("beim Aktualisieren der PKW-Tabelle gibt es Problem: " + error.getMessage());
        }
    }//End berabeiten


}
