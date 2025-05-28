package dao;


import model.PKW;

import java.sql.ResultSet;
import java.sql.SQLException;

// Datenbankzugriff für PKW-spezifische Daten.
public class PkwDAO {

    // Fügt ein PKW-Objekt (fahrzeug_id, sitzanzahl) in die PKW-Tabelle der Datenbank ein
    public static boolean einfuegen(PKW pkw) {
        String sql_einfueden = "INSERT INTO pkw (fahrzeug_id, sitzanzahl) VALUES (?, ?)";
        return DatabaseUtils.update_Mit_Parametern(sql_einfueden, pkw.getId(), pkw.getSitzanzahl());
    }// End einfuegen

    // Gibt die Sitzanzahl eines PKWs anhand der Fahrzeug-ID zurück
    public static int getSitzanzahl(int fahrzeugId) {
        String sql_SitzAnzahl = "SELECT sitzanzahl FROM pkw WHERE fahrzeug_id = ?";
        try (ResultSet resultSet = DatabaseUtils.suche_Mit_Parametern(sql_SitzAnzahl, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) return resultSet.getInt("sitzanzahl");
        } catch (SQLException error) {
            System.out.println("beim Laden von Sitzanzahl gibt es Problem: " + error.getMessage());
        }
            return 0;
    }// End getSitzanzahl

    // Aktualisiert die Sitzanzahl eines vorhandenen PKWs in der Datenbank
    public static boolean bearbeiten(PKW pkw) {
        String sql = "UPDATE pkw SET sitzanzahl = ? WHERE fahrzeug_id = ?";
        return DatabaseUtils.update_Mit_Parametern(sql, pkw.getSitzanzahl(), pkw.getId());
    }//End berabeiten

}
