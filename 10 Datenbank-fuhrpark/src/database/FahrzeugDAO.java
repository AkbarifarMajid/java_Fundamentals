package database;



import model.*;

import java.sql.*;
import java.util.ArrayList;

// Datenbankoperationen für alle Fahrzeugtypen
public class FahrzeugDAO {

    // Fügt ein Fahrzeug in die Haupttabelle ein
    public static void einfuegen(Fahrzeug fahrzeug, String typ) {
        String sql = "INSERT INTO fahrzeug (typ_Fahrzeug, hersteller_Fahrzeug, modell_Fahrzeug, baujahr_Fahrzeug) VALUES (?, ?, ?, ?)";

        int fahrzeugId = DatabaseUtils.executeInsertWithGeneratedKey(sql, typ, fahrzeug.getHersteller(), fahrzeug.getModell(), fahrzeug.getBaujahr());

        if (fahrzeugId > 0) {
            fahrzeug.setId(fahrzeugId);
            System.out.println("Fahrzeug gespeichert mit (ID: " + fahrzeugId + ")");


            // spezifische DAO aufrufen
            switch (typ) {
                case "PKW" -> PkwDAO.einfuegen((PKW) fahrzeug);
                case "LKW" -> LkwDAO.einfuegen((LKW) fahrzeug);
                case "Motorrad" -> MotorradDAO.einfuegen((Motorrad) fahrzeug);
                case "Fahrrad" -> FahrradDAO.einfuegen((Fahrrad) fahrzeug);
            }
        } else {
            System.out.println("beim Einfügen des Fahrzeugs gibt es Problem.");
        }
    }    // End einfuegen

    // Lädt alle Fahrzeuge aus der Haupttabelle
    public static ArrayList<Fahrzeug> ladenAlle() {
        ArrayList<Fahrzeug> liste = new ArrayList<>();
        String sql = "SELECT * FROM fahrzeug";

        try (ResultSet result = DatabaseUtils.executePreparedSelect(sql)) {
            while (result != null && result.next()) {
                String typ = result.getString("typ_Fahrzeug");
                String hersteller = result.getString("hersteller_Fahrzeug");
                String modell = result.getString("modell_Fahrzeug");
                int baujahr = result.getInt("baujahr_Fahrzeug");
                double kilometerstand = result.getDouble("kilometer_Fahrzeug");
                double kraftstoff = result.getDouble("kraftstoff_Fahrzeug");
                String standort = result.getString("standort_Fahrzeug");

                Fahrzeug fahrzeug;

                switch (typ) {
                    case "PKW" -> fahrzeug = new PKW(hersteller, modell, baujahr, 0);
                    case "LKW" -> fahrzeug = new LKW(hersteller, modell, baujahr, 0.0);
                    case "Motorrad" -> fahrzeug = new Motorrad(hersteller, modell, baujahr, 0, false);
                    case "Fahrrad" -> fahrzeug = new Fahrrad(hersteller, modell, baujahr, false);
                    default -> {
                        System.out.println("Unbekannter Typ: " + typ);
                        continue;
                    }
                }

                fahrzeug.setId(result.getInt("id_Fahrzeug"));
                fahrzeug.setKilometerstand(kilometerstand);
                fahrzeug.setKraftstoff(kraftstoff);
                fahrzeug.setStandort(standort);

                liste.add(fahrzeug);
            }
        } catch (SQLException error) {
            System.out.println("beim Laden der Fahrzeuge gibt es Problem: " + error.getMessage());
        }

        return liste;
    }// End ladenAlle


    // Fahrzeug nach ID suchen
    public static Fahrzeug findeNachId(int id) {
        String sql = "SELECT * FROM fahrzeug WHERE id_Fahrzeug = ?";

        try (ResultSet rs = DatabaseUtils.executePreparedSelect(sql, id)) {
            if (rs == null || !rs.next()) return null;

            String typ = rs.getString("typ_Fahrzeug");
            String hersteller = rs.getString("hersteller_Fahrzeug");
            String modell = rs.getString("modell_Fahrzeug");
            int baujahr = rs.getInt("baujahr_Fahrzeug");
            double kilometerstand = rs.getDouble("kilometer_Fahrzeug");
            double krafstoff = rs.getDouble("kraftstoff_Fahrzeug");
            String standort = rs.getString("standort_Fahrzeug");
            int besitzerId = rs.getInt("besitzer_id");
            String wartungtermin = rs.getString("wartungstermine");

            Fahrzeug fahrzeug = null;

            switch (typ) {
                case "PKW" -> {
                    ResultSet rsPkw = DatabaseUtils.executePreparedSelect("SELECT * FROM pkw WHERE fahrzeug_id = ?", id);
                    if (rsPkw != null && rsPkw.next()) {
                        int sitzanzahl = rsPkw.getInt("sitzanzahl");
                        fahrzeug = new PKW(id, typ, hersteller, modell, baujahr, sitzanzahl);
                    }
                }
                case "LKW" -> {
                    ResultSet rsLkw = DatabaseUtils.executePreparedSelect("SELECT * FROM lkw WHERE fahrzeug_id = ?", id);
                    if (rsLkw != null && rsLkw.next()) {
                        int ladegewicht = rsLkw.getInt("ladegewicht");
                        fahrzeug = new LKW(id, typ, hersteller, modell, baujahr, ladegewicht);
                    }
                }
                case "Motorrad" -> {
                    ResultSet rsMotorrad = DatabaseUtils.executePreparedSelect("SELECT * FROM motorrad WHERE fahrzeug_id = ?", id);
                    if (rsMotorrad != null && rsMotorrad.next()) {
                        int hubraum = rsMotorrad.getInt("hubraum");
                        boolean gangschaltung = rsMotorrad.getBoolean("gangschaltung");
                        fahrzeug = new Motorrad(id, typ, hersteller, modell, baujahr, hubraum, gangschaltung);
                    }
                }
                case "Fahrrad" -> {
                    ResultSet rsFahrrad = DatabaseUtils.executePreparedSelect("SELECT * FROM fahrrad WHERE fahrzeug_id = ?", id);
                    if (rsFahrrad != null && rsFahrrad.next()) {
                        boolean hatKorb = rsFahrrad.getBoolean("hat_korb");
                        fahrzeug = new Fahrrad(id, typ, hersteller, modell, baujahr, hatKorb);
                    }
                }
            }

            if (fahrzeug != null) {
                fahrzeug.setId(id);
                fahrzeug.setKilometerstand(kilometerstand);
                fahrzeug.setKraftstoff(krafstoff);
                fahrzeug.setStandort(standort);
                fahrzeug.setwartungstermine(wartungtermin);

                Mitarbeiter besitzer = MitarbeiterDAO.findeNachId(besitzerId);
                fahrzeug.setBesitzer(besitzer);
            }

            return fahrzeug;

        } catch (SQLException error) {
            System.out.println("bei Suche nach Fahrzeug gibt es Problem: " + error.getMessage());
            return null;
        }
    }// End findeNachId

    // Fahrzeug löschen
    public static boolean loeschen(int id) {
        String sql = "DELETE FROM fahrzeug WHERE id_Fahrzeug = ?";
        try {
            DatabaseUtils.executeUpdate(sql, id);
            return true;
        } catch (Exception error) {
            System.out.println("beim Löschen des Fahrzeugs gibt es Problem: " + error.getMessage());
            return false;
        }
    }// End loeschen


    // Aktualisiert den Besitzer eines Fahrzeugs
    public static boolean besitzerAktualisieren(int fahrzeugId, int mitarbeiterId) {
        String sql = "UPDATE fahrzeug SET besitzer_id = ? WHERE id_Fahrzeug = ?";
        try {
            DatabaseUtils.executeUpdate(sql, mitarbeiterId, fahrzeugId);
            return true;
        } catch (Exception error) {
            System.out.println("beim Aktualisieren des Besitzers gibt es Problem: " + error.getMessage());
            return false;
        }
    }// End besitzerAktualisieren


    // Aktualisiert den Kilometerstand eines Fahrzeugs
    public static boolean kilometerAktualisieren(int id, double neuerKmStand) {
        String sql = "UPDATE fahrzeug SET kilometer_Fahrzeug = ? WHERE id_Fahrzeug = ?";
        try {
            DatabaseUtils.executeUpdate(sql, neuerKmStand, id);
            return true;
        } catch (Exception error) {
            System.out.println("beim Aktualisieren des Kilometerstands gibt es Problem: " + error.getMessage());
            return false;
        }
    }// End kilometerAktualisieren


    // Fügt einen Wartungstermin als String hinzu
    public static boolean wartungAktualisieren(int id, String datum) {
        String sql = "UPDATE fahrzeug SET wartungstermine = ? WHERE id_Fahrzeug = ?";
        try {
            DatabaseUtils.executeUpdate(sql, datum, id);
            return true;
        } catch (Exception error) {
            System.out.println("beim Aktualisieren des Wartungstermins gibt es Problem: " + error.getMessage());
            return false;
        }
    }// End wartungAktualisieren


    // Aktualisiert Basis- und Spezialdaten eines Fahrzeugs
    public static void bearbeiten(Fahrzeug fahrzeug) {
        String sql = "UPDATE fahrzeug SET hersteller_Fahrzeug = ?, modell_Fahrzeug = ?, baujahr_Fahrzeug = ? WHERE id_Fahrzeug = ?";

        try {
            DatabaseUtils.executeUpdate(
                    sql,
                    fahrzeug.getHersteller(),
                    fahrzeug.getModell(),
                    fahrzeug.getBaujahr(),
                    fahrzeug.getId()
            );

            // nach Typ auch spezielle Werte aktualisieren
            if (fahrzeug instanceof PKW pkw) {
                PkwDAO.berabeiten(pkw);
            } else if (fahrzeug instanceof LKW lkw) {
                LkwDAO.berabeiten(lkw);
            } else if (fahrzeug instanceof Motorrad motorrad) {
                MotorradDAO.berabeiten(motorrad);
            } else if (fahrzeug instanceof Fahrrad fahrrad) {
                FahrradDAO.berabeiten(fahrrad);
            }

            System.out.printf("Fahrzeug mit ID %d wurde erfolgreich aktualisiert.\n", fahrzeug.getId());

        } catch (Exception error) {
            System.out.println("beim Bearbeiten des Fahrzeugs gibt es Problem:  " + error.getMessage());
        }
    }// End bearbeiten


    // Aktualisiert den Kraftstoffstand
    public static boolean kraftstoffAktualisieren(int id, double kraftstoff) {
        String sql = "UPDATE fahrzeug SET kraftstoff_Fahrzeug = ? WHERE id_Fahrzeug = ?";
        try {
            DatabaseUtils.executeUpdate(sql, kraftstoff, id);
            return true;
        } catch (Exception error) {
            System.out.println("beim Aktualisieren des Kraftstoffstands gibt es Problem: " + error.getMessage());
            return false;
        }
    }// End kraftstoffAktualisieren


}
