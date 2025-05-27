
package dao;

import model.Mitarbeiter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

// Datenbankzugriffe für Mitarbeiter
public class MitarbeiterDAO {

    // Fügt neuen Mitarbeiter ein
    public static boolean mitarbeiter_einfuegen(Mitarbeiter mitarbeiter) {
        String sql_einfugen = "INSERT INTO mitarbeiter (vorname, nachname, abteilung, telefon, email, einstellungsdatum) VALUES (?, ?, ?, ?, ?, ?)";
        return DatabaseUtils.executeUpdate(sql_einfugen,
                mitarbeiter.getVorname(),
                mitarbeiter.getNachname(),
                mitarbeiter.getAbteilung(),
                mitarbeiter.getTelefon(),
                mitarbeiter.getEmail(),
                mitarbeiter.getEinstellungsdatum());
    }// End mitarbeiter_einfuegen

    // Lädt alle Mitarbeiter aus der Datenbank
    public static ArrayList<Mitarbeiter> load_all_Mitarbeiter() {
        ArrayList<Mitarbeiter> list = new ArrayList<>();
        String sql_load_All = "SELECT * FROM mitarbeiter";

        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_load_All)) {
            while (resultSet != null && resultSet.next()) {
                Mitarbeiter mitarbeiter = new Mitarbeiter(
                        resultSet.getInt("id"),
                        resultSet.getString("vorname"),
                        resultSet.getString("nachname"),
                        resultSet.getString("abteilung"),
                        resultSet.getString("telefon"),
                        resultSet.getString("email"),
                        resultSet.getString("einstellungsdatum")
                );
                list.add(mitarbeiter);
            }
        } catch (Exception error) {
            System.out.println("Fehler beim Laden der Mitarbeiter: " + error.getMessage());
        }
        return list;
    }// End load_all_Mitarbeiter

    // Findet Mitarbeiter anhand ID
    public static Mitarbeiter find_Mitarbeiter_Id(int id) {
        String sql_Find_Id = "SELECT * FROM mitarbeiter WHERE id = ?";
        try (ResultSet reault_Find_Mitarbeiter = DatabaseUtils.executePreparedSelect(sql_Find_Id, id)) {
            if (reault_Find_Mitarbeiter != null && reault_Find_Mitarbeiter.next()) {
                return new Mitarbeiter(
                        reault_Find_Mitarbeiter.getInt("id"),
                        reault_Find_Mitarbeiter.getString("vorname"),
                        reault_Find_Mitarbeiter.getString("nachname"),
                        reault_Find_Mitarbeiter.getString("abteilung"),
                        reault_Find_Mitarbeiter.getString("telefon"),
                        reault_Find_Mitarbeiter.getString("email"),
                        reault_Find_Mitarbeiter.getString("einstellungsdatum")
                );
            }
        } catch (Exception error) {
            System.out.println("beim Finden des Mitarbeiters gibt es Problem: " + error.getMessage());
        }
        return null;
    }// End find_Mitarbeiter_Id

    // Prüft, ob ein Mitarbeiter mit gleicher Email existiert
    public static boolean anzahl_Email(String email) {
        String sql_Email = "SELECT COUNT(*) AS anzahl FROM mitarbeiter WHERE email = ?";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_Email, email)) {
            return resultSet != null && resultSet.next() && resultSet.getInt("anzahl") > 0;
        } catch (Exception error) {
            System.out.println(" bei der Email-Prüfung gibt es Problem: " + error.getMessage());
            return true;
        }
    }// End anzahl_Email

    // Löscht Mitarbeiter
    public static boolean loeschen_Mitarbeiter(int id) {
        String sql_Loschen = "DELETE FROM mitarbeiter WHERE id = ?";
        return DatabaseUtils.executeUpdate(sql_Loschen, id);
    }// End loeschen_Mitarbeiter

    // Bearbeitet Mitarbeiterdaten
    public static boolean bearbeiten_Mitarbeiter(Mitarbeiter mitarbeiter) {
        String sql_Bearbeiten = "UPDATE mitarbeiter SET vorname = ?, nachname = ?, abteilung = ?, telefon = ?, email = ?, einstellungsdatum = ? WHERE id = ?";

        return DatabaseUtils.executeUpdate(sql_Bearbeiten,
                mitarbeiter.getVorname(),
                mitarbeiter.getNachname(),
                mitarbeiter.getAbteilung(),
                mitarbeiter.getTelefon(),
                mitarbeiter.getEmail(),
                mitarbeiter.getEinstellungsdatum(),
                mitarbeiter.getId());
    } // End bearbeiten_Mitarbeiter


    // Sucht Mitarbeiter nach Vorname
    public static ArrayList<Mitarbeiter> find_mit_Vorname(String vorname) {
        String sql_Vorname = "SELECT * FROM mitarbeiter WHERE vorname LIKE ?";
        ArrayList<Mitarbeiter> liste = new ArrayList<>();

        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_Vorname, "%" + vorname + "%")) {
            while (resultSet != null && resultSet.next()) {
                liste.add(new Mitarbeiter(
                        resultSet.getInt("id"),
                        resultSet.getString("vorname"),
                        resultSet.getString("nachname"),
                        resultSet.getString("abteilung"),
                        resultSet.getString("telefon"),
                        resultSet.getString("email"),
                        resultSet.getString("einstellungsdatum")
                ));
            }
        } catch (SQLException error) {
            System.out.println("Fehler bei Vorname-Suche: " + error.getMessage());
        }

        return liste;
    }// End find_mit_Vorname


    // Sucht Mitarbeiter nach Nachname
    public static ArrayList<Mitarbeiter> find_mit_Nachname(String nachname) {
        String sql_find_Nachname = "SELECT * FROM mitarbeiter WHERE nachname LIKE ?";
        ArrayList<Mitarbeiter> liste = new ArrayList<>();

        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_find_Nachname, "%" + nachname + "%")) {
            while (resultSet != null && resultSet.next()) {
                liste.add(new Mitarbeiter(
                        resultSet.getInt("id"),
                        resultSet.getString("vorname"),
                        resultSet.getString("nachname"),
                        resultSet.getString("abteilung"),
                        resultSet.getString("telefon"),
                        resultSet.getString("email"),
                        resultSet.getString("einstellungsdatum")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei Nachname-Suche: " + e.getMessage());
        }

        return liste;
    } // End find_mit_Nachname

    public static int anzahl_Mitarbeiter() {
        String sql_Anzahl = "SELECT COUNT(*) AS anzahl FROM mitarbeiter";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_Anzahl)) {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt("anzahl");
            }
        } catch (SQLException error) {
            System.out.println("Fehler bei zaehleMitarbeiter(): " + error.getMessage());
        }
        return 0;
    }// End anzahl_Mitarbeiter


}
