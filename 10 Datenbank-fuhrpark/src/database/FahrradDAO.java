package database;
import model.Fahrrad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FahrradDAO {

    //Fügt ein Fahrradobjekt (fahrzeug_id, hat_korb) in die Datenbank ein
    public static void einfuegen(Fahrrad fahrrad) {
        String sql = "INSERT INTO fahrrad (fahrzeug_id, hat_korb) VALUES (?, ?)";
        try {
            DatabaseUtils.executeUpdate(sql, fahrrad.getId(), fahrrad.isHatKorb());
        } catch (Exception error) {
            System.out.println("beim Einfügen in die Tabelle Fahrrad gibe es Problem: " + error.getMessage());
        }
    }// End einfuegen


    //Lädt den Korbstatus (hat_korb) eines Fahrrads anhand der Fahrzeug-ID
    public static boolean getHatKorb(int fahrzeugId) {
        String sql = "SELECT hat_korb FROM fahrrad WHERE fahrzeug_id = ?";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getBoolean("hat_korb");
            }
        } catch (SQLException error) {
            System.out.println("beim Laden von hat_korb gibt es Problem: " + error.getMessage());
        }
        return false;
    }// End getHatKorb


    //Aktualisiert den Korbstatus eines Fahrrads in der Datenbank
    public static void berabeiten(Fahrrad fahrrad) {
        String sql = "UPDATE fahrrad SET hat_korb = ? WHERE fahrzeug_id = ?";
        try {
            DatabaseUtils.executeUpdate(sql, fahrrad.isHatKorb(), fahrrad.getId());
        } catch (Exception error) {
            System.out.println("beim Aktualisieren der Tabelle Fahrrad gibt es Problem: " + error.getMessage());
        }
    }//End berabeiten


}
