package dao;

import model.LKW;

import java.sql.ResultSet;
// Datenbankzugriff für LKW
public class LkwDAO {

    // Fügt ein LKW-Objekt (fahrzeug_id, ladegewicht) in die Datenbank ein
    public static boolean einfuegen_LKW(LKW lkw) {
        String sql_einfuegen = "INSERT INTO lkw (fahrzeug_id, ladevolumen) VALUES (?, ?)";
        return DatabaseUtils.executeUpdate(sql_einfuegen, lkw.getId(), lkw.getLadevolumen());
    }// End einfuegen_LKW

    // Holt das Ladegewicht eines LKWs anhand der Fahrzeug-ID
    public static double getLadevolumen(int fahrzeugId) {
        String sql_Lade_Volumen = "SELECT ladevolumen FROM lkw WHERE fahrzeug_id = ?";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_Lade_Volumen, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) return resultSet.getDouble("ladevolumen");
        } catch (Exception e) {
            System.out.println("Fehler beim Lesen von ladevolumen: " + e.getMessage());
        }
        return 0.0;
    }// ENd getLadevolumen

    // Aktualisiert das Ladegewicht eines LKWs in der Datenbank
    public static boolean bearbeiten_LKW(LKW lkw) {
        String sql_Bearbeiten = "UPDATE lkw SET ladevolumen = ? WHERE fahrzeug_id = ?";
        return DatabaseUtils.executeUpdate(sql_Bearbeiten, lkw.getLadevolumen(), lkw.getId());
    }// End bearbeiten_LKW

}