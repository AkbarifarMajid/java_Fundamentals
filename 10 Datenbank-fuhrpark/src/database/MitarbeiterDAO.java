package database;

import model.Mitarbeiter;

import java.sql.*;
import java.util.ArrayList;

// Datenbankzugriffe für Mitarbeiter
public class MitarbeiterDAO {

    // Fügt neuen Mitarbeiter ein
    public static void einfuegen(Mitarbeiter mitarbeiter) {
        String sql = "INSERT INTO mitarbeiter (vorname_Mitarbeiter, nachname_Mitarbeiter, position_Mitarbeiter, telefon_Mitarbeiter) VALUES (?, ?, ?, ?)";
        try {
            int id = DatabaseUtils.executeInsertWithGeneratedKey(
                    sql,
                    mitarbeiter.getVorname(),
                    mitarbeiter.getNachname(),
                    mitarbeiter.getPosition(),
                    mitarbeiter.getTelefonnummer()
            );
            mitarbeiter.setId(id);
            System.out.println("Neu Mitarbeiter mit (ID: " + id + ") gespeichert");
        } catch (Exception e) {
            System.out.println("Fehler beim Einfügen des Mitarbeiters: " + e.getMessage());
        }
    }// End einfuegen


    // Gibt eine Liste aller Mitarbeiter zurück
    public static ArrayList<Mitarbeiter> ladenAlle() {
        ArrayList<Mitarbeiter> liste = new ArrayList<>();
        String sql = "SELECT * FROM mitarbeiter";

        try (ResultSet rs = DatabaseUtils.executePreparedSelect(sql)) {
            while (rs != null && rs.next()) {
                Mitarbeiter m = new Mitarbeiter(
                        rs.getString("vorname_Mitarbeiter"),
                        rs.getString("nachname_Mitarbeiter"),
                        rs.getString("position_Mitarbeiter"),
                        rs.getString("telefon_Mitarbeiter")
                );
                m.setId(rs.getInt("id_Mitarbeiter"));
                liste.add(m);
            }
        } catch (SQLException error) {
            System.out.println("beim Laden der Mitarbeiter gibt es Problem: " + error.getMessage());
        }

        return liste;
    }// End ladenAlle


    // Einzelner Mitarbeiter nach ID
    public static Mitarbeiter findeNachId(int id) {
        String sql = "SELECT * FROM mitarbeiter WHERE id_Mitarbeiter = ?";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql, id)) {
            if (resultSet != null && resultSet.next()) {
                Mitarbeiter mitarbeiter = new Mitarbeiter(
                        resultSet.getString("vorname_Mitarbeiter"),
                        resultSet.getString("nachname_Mitarbeiter"),
                        resultSet.getString("position_Mitarbeiter"),
                        resultSet.getString("telefon_Mitarbeiter")
                );
                mitarbeiter.setId(id);
                return mitarbeiter;
            }
        } catch (SQLException error) {
            System.out.println("bei Mitarbeitersuche gibt es Problem: " + error.getMessage());
        }
        return null;
    } // End findeNachId


    // Löscht einen Mitarbeiter aus der Datenbank
    public static boolean loeschen(int id) {
        String sql = "DELETE FROM mitarbeiter WHERE id_Mitarbeiter = ?";
        try {
            DatabaseUtils.executeUpdate(sql, id);
            return true;
        } catch (Exception error) {
            System.out.println("beim Löschen des Mitarbeiters gibt es Problem: " + error.getMessage());
            return false;
        }
    }// End loeschen


    // mitarbeiter Bearbeiten
    public static boolean mitarbeiter_Update(Mitarbeiter mitarbeiter) {
        String sql = """
        UPDATE mitarbeiter
        SET vorname_Mitarbeiter = ?, 
            nachname_Mitarbeiter = ?, 
            position_Mitarbeiter = ?, 
            telefon_Mitarbeiter = ?
        WHERE id_Mitarbeiter = ?
    """;

        try {
            DatabaseUtils.executeUpdate(sql, mitarbeiter.getVorname(), mitarbeiter.getNachname(), mitarbeiter.getPosition(), mitarbeiter.getTelefonnummer(), mitarbeiter.getId()
            );
            return true;
        } catch (Exception error) {
            System.out.println("beim Aktualisieren des Mitarbeiters gibt es Problem: " + error.getMessage());
            return false;
        }
    }// End mitarbeiter_Update



}
