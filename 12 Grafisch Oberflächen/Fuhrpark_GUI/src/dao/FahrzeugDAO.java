// --- Datei: FahrzeugDAO.java ---
package dao;

import model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FahrzeugDAO {

    // Fahrzeug hinzufügen
    public static void fahrzeug_einfuegen(Fahrzeug fahrzeug, String typ) {
        String sql_einguegen = "INSERT INTO fahrzeug (typ, hersteller, modell, baujahr, kennzeichen) VALUES (?, ?, ?, ?, ?)";
        int fahrzeugId = DatabaseUtils.executeInsertWithGeneratedKey(
                sql_einguegen, typ, fahrzeug.getHersteller(), fahrzeug.getModell(), fahrzeug.getBaujahr(), fahrzeug.getKennzeichen()
        );

        if (fahrzeugId > 0) {
            fahrzeug.setId(fahrzeugId);
            fahrzeug.setTyp_Fahrzeug(typ);
            switch (typ) {
                case "PKW" -> PkwDAO.einfuegen((PKW) fahrzeug);
                case "LKW" -> LkwDAO.einfuegen_LKW((LKW) fahrzeug);
                case "Motorrad" -> MotorradDAO.einfuegen((Motorrad) fahrzeug);
                case "Fahrrad" -> FahrradDAO.einfuegen((Fahrrad) fahrzeug);
            }
        }
    } // End fahrzeug_einfuegen

    //Zeigt alle Fahrzeug
    public static ArrayList<Fahrzeug> load_Alle_Fahrzeug() {
        ArrayList<Fahrzeug> liste_Fahrzeug = new ArrayList<>();
        String sql_all_F = "SELECT * FROM fahrzeug";

        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_all_F)) {
            while (resultSet != null && resultSet.next()) {
                int id = resultSet.getInt("id");
                String typ = resultSet.getString("typ");
                String hersteller = resultSet.getString("hersteller");
                String modell = resultSet.getString("modell");
                int baujahr = resultSet.getInt("baujahr");
                String kennzeichen = resultSet.getString("kennzeichen");

                Fahrzeug fahrzeug = switch (typ) {
                    case "PKW" -> new PKW(id, typ, hersteller, modell, baujahr, kennzeichen, PKW.readVonDAOSitzanzahl(id));
                    case "LKW" -> new LKW(id, typ, hersteller, modell, baujahr, kennzeichen, LKW.ReadVonDAOLadegewicht(id));
                    case "Motorrad" -> new Motorrad(id, typ, hersteller, modell, baujahr, kennzeichen, Motorrad.readVonADOHubraum(id), Motorrad.readVonADOGangschaltung(id));
                    case "Fahrrad" -> new Fahrrad(id, typ, hersteller, modell, baujahr, kennzeichen, Fahrrad.radVonADOHatKorb(id));
                    default -> null;
                };

                if (fahrzeug != null) liste_Fahrzeug.add(fahrzeug);
            }
        } catch (SQLException error) {
            System.out.println("beim Laden aller Fahrzeuge gibt es Problem: " + error.getMessage());
        }
        return liste_Fahrzeug;
    }// End load_Alle_Fahrzeug

    //Ein Fahrzeug nach ID suchen
    public static Fahrzeug find_Fahrzeug_Id(int id) {
        String sql_Find = "SELECT * FROM fahrzeug WHERE id = ?";

        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_Find, id)) {
            if (resultSet == null || !resultSet.next()) return null;

            String typ = resultSet.getString("typ");
            String hersteller = resultSet.getString("hersteller");
            String modell = resultSet.getString("modell");
            int baujahr = resultSet.getInt("baujahr");
            String kennzeichen = resultSet.getString("kennzeichen");
            double kmStand = resultSet.getDouble("kilometer_Fahrzeug");

            Fahrzeug f = switch (typ) {
                case "PKW" -> new PKW(id, typ, hersteller, modell, baujahr, kennzeichen, PKW.readVonDAOSitzanzahl(id));
                case "LKW" -> new LKW(id, typ, hersteller, modell, baujahr, kennzeichen, LKW.ReadVonDAOLadegewicht(id));
                case "Motorrad" -> new Motorrad(id, typ, hersteller, modell, baujahr, kennzeichen, Motorrad.readVonADOHubraum(id), Motorrad.readVonADOGangschaltung(id));
                case "Fahrrad" -> new Fahrrad(id, typ, hersteller, modell, baujahr, kennzeichen, Fahrrad.radVonADOHatKorb(id));
                default -> null;
            };

            if (f != null) f.setKilometerstand(kmStand);
            return f;

        } catch (SQLException error) {
            System.out.println("bei findeNachId gibt es Problem: " + error.getMessage());
            return null;
        }
    }// End find_Fahrzeug_Id

    //Fahrzeug Löschen
    public static boolean loeschen_Fahrzeug(int id) {
        return DatabaseUtils.executeUpdate("DELETE FROM fahrzeug WHERE id = ?", id);
    } //End loeschen_Fahrzeug

    //Karfstoff Fahrzeug Aktualisieren
    public static boolean kraftstoff_Aktualisieren(int id, double liter) {
        return DatabaseUtils.executeUpdate("UPDATE fahrzeug SET kraftstoff_Fahrzeug = kraftstoff_Fahrzeug + ? WHERE id = ?", liter, id);
    } // End kraftstoff_Aktualisieren

    //Fahrzeug information Bearbeiten
    public static boolean bearbeiten_Fahrzeug(Fahrzeug fahrzeug) {
        boolean mainSuccess = DatabaseUtils.executeUpdate(
                "UPDATE fahrzeug SET hersteller = ?, modell = ?, baujahr = ?, kennzeichen = ? WHERE id = ?",
                fahrzeug.getHersteller(), fahrzeug.getModell(), fahrzeug.getBaujahr(), fahrzeug.getKennzeichen(), fahrzeug.getId());

        boolean childSuccess = switch (fahrzeug) {
            case PKW pkw -> PkwDAO.bearbeiten(pkw);
            case LKW lkw -> LkwDAO.bearbeiten_LKW(lkw);
            case Motorrad motor -> MotorradDAO.bearbeiten(motor);
            case Fahrrad fahrrad -> FahrradDAO.bearbeiten(fahrrad);
            default -> false;
        };

        return mainSuccess && childSuccess;
    } // End bearbeiten_Fahrzeug

    //Anzahl alle Fahrzeug
    public static int anzahl_Fahrzeuge() {
        String sql_anzahl = "SELECT COUNT(*) AS anzahl FROM fahrzeug";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_anzahl)) {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt("anzahl");
            }
        } catch (SQLException e) {
            System.out.println("bei zaehleFahrzeuge gibt es Problem(): " + e.getMessage());
        }
        return 0;
    } // End anzahl_Fahrzeuge

    public static Integer get_Aktueller_BesitzerId(int fahrzeugId) {
        String sql_BesitzerID_Aktuali = "SELECT besitzer_id FROM fahrzeug WHERE id = ?";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_BesitzerID_Aktuali, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) {
                int id = resultSet.getInt("besitzer_id");
                return resultSet.wasNull() ? null : id;
            }
        } catch (SQLException e) {
            System.out.println("bei getAktuellerBesitzerId gibt es Problem(): " + e.getMessage());
        }
        return null;
    }// End get_Aktueller_BesitzerId



}
