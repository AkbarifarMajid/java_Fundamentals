package dao;


import model.Fahrrad;

import java.sql.ResultSet;

// Datenbankzugriff für Fahrrad
public class FahrradDAO {

    //Fügt ein Fahrradobjekt (fahrzeug_id, hat_korb) in die Datenbank ein
    public static boolean einfuegen(Fahrrad fahrrad) {
        String sql_einguegen = "INSERT INTO fahrrad (fahrzeug_id, hatkorb) VALUES (?, ?)";
        return DatabaseUtils.update_Mit_Parametern(sql_einguegen, fahrrad.getId(), fahrrad.isHatKorb());
    }// End einfuegen

    //Lädt den Korbstatus  eines Fahrrads anhand der Fahrzeug-ID
    public static boolean get_Korb(int fahrzeugId) {
        String sql_hatKorb = "SELECT hatkorb FROM fahrrad WHERE fahrzeug_id = ?";
        try (ResultSet resultSet = DatabaseUtils.suche_Mit_Parametern(sql_hatKorb, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) return resultSet.getBoolean("hatkorb");
        } catch (Exception error) {
            System.out.println("Fehler beim Lesen von hatkorb: " + error.getMessage());
        }
        return false;
    }// End get_Korb

    //Aktualisiert den Korbstatus eines Fahrrads in der Datenbank
    public static boolean bearbeiten(Fahrrad fahrrad) {
        String sql_Berabeiten = "UPDATE fahrrad SET hatkorb = ? WHERE fahrzeug_id = ?";
        return DatabaseUtils.update_Mit_Parametern(sql_Berabeiten, fahrrad.isHatKorb(), fahrrad.getId());
    }// End bearbeiten

}

