package dao;

import java.sql.Date;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;

// Datenbank Zuweisung von Fahrzeugen an Mitarbeiter
public class ZuweisungDAO {

    // Einneu Zuweizunh Hinzufügen
    public static boolean zuweisung_Hinzufuegen(int fahrzeugId, int mitarbeiterId, LocalDate vonDatum, LocalDate bisDatum, String bemerkung) {

        String sqlMitBis = "INSERT INTO zuweisung (fahrzeug_id, mitarbeiter_id, von_datum, bis_datum, bemerkung) VALUES (?, ?, ?, ?, ?)";
        String sqlOhneBis = "INSERT INTO zuweisung (fahrzeug_id, mitarbeiter_id, von_datum, bemerkung) VALUES (?, ?, ?, ?)";

        boolean result;

        if (bisDatum != null) {
            result = DatabaseUtils.executeUpdate(sqlMitBis, fahrzeugId, mitarbeiterId, Date.valueOf(vonDatum), Date.valueOf(bisDatum), bemerkung
            );
        } else {
            result = DatabaseUtils.executeUpdate(sqlOhneBis, fahrzeugId, mitarbeiterId, Date.valueOf(vonDatum), bemerkung
            );
        }

        // Nach erfolgreicher Registrierung aktualisieren Sie die Fahrzeugtabelle
        if (result) {
            String updateBesitzer = "UPDATE fahrzeug SET besitzer_id = ? WHERE id = ?";
            return DatabaseUtils.executeUpdate(updateBesitzer, mitarbeiterId, fahrzeugId);
        }

        return false;
    } // End zuweisung_Hinzufuegen

    // Alle zugewiesenen
    public static java.util.List<Object[]> load_All_Zuweisungen() {
        String sql_Load_all = "SELECT fahrzeug_id, mitarbeiter_id, von_datum, bis_datum, bemerkung FROM zuweisung";
        java.util.List<Object[]> datenListe = new java.util.ArrayList<>();

        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_Load_all)) {
            while (resultSet != null && resultSet.next()) {
                int fahrzeugId = resultSet.getInt("fahrzeug_id");
                int mitarbeiterId = resultSet.getInt("mitarbeiter_id");
                Date von = resultSet.getDate("von_datum");
                Date bis = resultSet.getDate("bis_datum");
                String bemerkung = resultSet.getString("bemerkung");

                datenListe.add(new Object[]{fahrzeugId, mitarbeiterId, von != null ? von.toLocalDate() : null, bis != null ? bis.toLocalDate() : null, bemerkung
                });
            }
        } catch (SQLException error) {
            System.out.println("Fehler beim Laden der Zuweisungen: " + error.getMessage());
        }

        return datenListe;
    } // End load_All_Zuweisungen

    //Anzahl aller zugewiesenen
    public static int anzahl_Zuweizung() {
        String sql_Anzahl = "SELECT COUNT(*) AS anzahl FROM zuweisung";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_Anzahl)) {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt("anzahl");
            }
        } catch (SQLException error) {
            System.out.println("Fehler bei zaehleZuweisungen(): " + error.getMessage());
        }
        return 0;
    }// End anzahl_Zuweizung

    // Kontroel Fahrzeug verfügbar ist oder nein
    public static boolean fahrzeug_Frei(int fahrzeugId, LocalDate von, LocalDate bis) {
        String sql_Fahrzeug_Frei = "SELECT COUNT(*) FROM zuweisung WHERE fahrzeug_id = ? AND (? <= bis_datum AND ? >= von_datum)";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_Fahrzeug_Frei, fahrzeugId, von, bis)) {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt(1) == 0; // dass es keine Störungen gibt
            }
        } catch (Exception error) {
           // System.out.println("bei Verfügbarkeitsprüfung gibt es Problem: " + error.getMessage());
        }
        return false;
    } // End fahrzeug_Frei





}
