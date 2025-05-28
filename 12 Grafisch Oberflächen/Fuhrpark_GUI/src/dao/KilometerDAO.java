package dao;

import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KilometerDAO {

    public static boolean hinzufuegenKilometer(int fahrzeugId, double startKm, double endKm) {
        double differenz = endKm - startKm;
        if (differenz <= 0) {
            System.out.println("Ungültige Kilometerangabe.");
            return false;
        }

        //  Insert in kilometer_log
        String insertSQL = "INSERT INTO kilometer_log (fahrzeug_id, start_km, end_km, differenz, datum) VALUES (?, ?, ?, ?, ?)";
       // System.out.println("Update-Fahrzeug-Kilometer ausgeführt mit Differenz = " + differenz);
        boolean inserted = DatabaseUtils.update_Mit_Parametern(insertSQL, fahrzeugId, startKm, endKm, differenz,
                java.sql.Date.valueOf(LocalDate.now())
        );

        if (!inserted) {
            System.out.println("Fehler beim Einfügen in kilometer_log.");
            return false;
        }

        //  Update der Gesamt-Kilometer im Fahrzeug
        String updateSQL = "UPDATE fahrzeug SET kilometer_Fahrzeug = kilometer_Fahrzeug + ? WHERE id = ?";

        boolean updated = DatabaseUtils.update_Mit_Parametern(updateSQL, differenz, fahrzeugId);
     //   System.out.println("Update-Fahrzeug-Kilometer ausgeführt mit Differenz = " + differenz);

        if (!updated) {
            System.out.println("Fehler beim Aktualisieren des Kilometerstandes im Fahrzeug.");
            return false;
        }

        System.out.println("Kilometer erfolgreich gespeichert.");
        return true;
    }

    public static int berechneGesamtKilometer() {
        String sql = "SELECT SUM(differenz) AS gesamt FROM kilometer_log";
        try (ResultSet rs = DatabaseUtils.suche_Mit_Parametern(sql)) {
            if (rs != null && rs.next()) {
                return rs.getInt("gesamt");
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei berechneGesamtKilometer(): " + e.getMessage());
        }
        return 0;
    }


}
