package dao;

//import database.DatabaseUtils;

import java.sql.ResultSet;

public class BenutzerDAO {


     //Überprüfung der Richtigkeit von Benutzername und Passwort in der Tabelle benutzer_erweitert
    public static boolean bnutzer_Password_Control(String benutzername, String passwort) {
        String sql = "SELECT * FROM benutzer_erweitert WHERE benutzername = ? AND passwort = ?";
        ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql, benutzername, passwort);

        if (resultSet == null) {
            return false;
        }
        try {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception error) {
            System.out.println("beim Lesen des ResultSets gibt es Problem: " + error.getMessage());
            return false;
        }
    } // End bnutzer_Password_Control


     // Vollständige Registrierungsinformationen in einer Tabelle speichern benutzer_erweitert
    public static boolean neu_Benutzer_Speichen(String vorname, String nachname, String telefon,
                                                String email, String geschlecht,
                                                String benutzername, String passwort) {

        String sql = "INSERT INTO benutzer_erweitert " +
                "(vorname, nachname, telefon, email, geschlecht, benutzername, passwort) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        return DatabaseUtils.executeUpdate(sql, vorname, nachname, telefon, email, geschlecht, benutzername, passwort);
    }// End benutzerErweitertSpeichern
}
