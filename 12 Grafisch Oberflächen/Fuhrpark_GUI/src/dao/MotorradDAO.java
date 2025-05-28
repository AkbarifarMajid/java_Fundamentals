package dao;


import model.Motorrad;

import java.sql.ResultSet;

// Datenbankzugriff für Motorräder
public class MotorradDAO {

    // Motorräder zur Motorradtabelle hinzufügen
    public static boolean einfuegen(Motorrad motorrad) {
        String sql_einguegen = "INSERT INTO motorrad (fahrzeug_id, hubraum, gangschaltung) VALUES (?, ?, ?)";
        return DatabaseUtils.update_Mit_Parametern(sql_einguegen, motorrad.getId(), motorrad.getHubraum(), motorrad.getGangschaltung());
    }//End einfuegen

    // Motorvolumen aus Tabelle abrufen
    public static int getHubraum(int fahrzeugId) {
        String sql_getHubraum = "SELECT hubraum FROM motorrad WHERE fahrzeug_id = ?";
        try (ResultSet resultSet = DatabaseUtils.suche_Mit_Parametern(sql_getHubraum, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) return resultSet.getInt("hubraum");
        } catch (Exception e) {
            System.out.println("Fehler beim Lesen von Hubraum: " + e.getMessage());
        }
        return 0;
    }// End getHubraum

    // Überprüfen Sie, ob das Motorrad Gänge hat oder nicht
    public static boolean getGangschaltung(int fahrzeugId) {
        String sql_Gangschaltung = "SELECT gangschaltung FROM motorrad WHERE fahrzeug_id = ?";
        try (ResultSet resultSet = DatabaseUtils.suche_Mit_Parametern(sql_Gangschaltung, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) return resultSet.getBoolean("gangschaltung");
        } catch (Exception e) {
            System.out.println("Fehler beim Lesen der Gangschaltung: " + e.getMessage());
        }
        return false;
    }// End getGangschaltung

    // Motorradspezifische Daten aktualisieren
    public static boolean bearbeiten(Motorrad motorrad) {
        String sql_Bearbeiten = "UPDATE motorrad SET hubraum = ?, gangschaltung = ? WHERE fahrzeug_id = ?";
        return DatabaseUtils.update_Mit_Parametern(sql_Bearbeiten, motorrad.getHubraum(), motorrad.getGangschaltung(), motorrad.getId());
    }// End bearbeiten

}