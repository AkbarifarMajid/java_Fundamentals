
package service;

import dao.MitarbeiterDAO;
import model.Mitarbeiter;

import java.util.ArrayList;

public class MitarbeiterService {

    // Gibt alle Mitarbeiter zurück
    public static ArrayList<Mitarbeiter> alle_Mitarbeiter() {
        return MitarbeiterDAO.load_all_Mitarbeiter();
    } // End alleMitarbeiter


    // Fügt einen neuen Mitarbeiter nach Prüfung ein
    public static boolean neu_Mitarbeiter(Mitarbeiter mitarbeiter) {
        if (mitarbeiter == null) {
            System.out.println(" Gibt es keiner Mitarbeiter");
            return false;
        }

        if (mitarbeiter.getVorname().isEmpty() || mitarbeiter.getNachname().isEmpty()) {
            System.out.println("Vorname oder Nachname darf nicht leer sein.");
            return false;
        }

        if (mitarbeiter.getEmail().isEmpty() || !mitarbeiter.getEmail().contains("@")) {
            System.out.println("Email-Adresse ist nicht gültig.");
            return false;
        }

        if (MitarbeiterDAO.anzahl_Email(mitarbeiter.getEmail())) {
            System.out.println("Email-Adresse bereits vergeben.");
            return false;
        }

        return MitarbeiterDAO.mitarbeiter_einfuegen(mitarbeiter);
    } // End neuerMitarbeiter


    // Sucht Mitarbeiter nach ID, Vorname oder Nachname
    public static ArrayList<Mitarbeiter> suche_Mitarbeiter(String feld, String wert) {
        ArrayList<Mitarbeiter> ergebnisse = new ArrayList<>();

        switch (feld) {
            case "ID":
                try {
                    int id = Integer.parseInt(wert);
                    Mitarbeiter m = MitarbeiterDAO.find_Mitarbeiter_Id(id);
                    if (m != null) ergebnisse.add(m);
                } catch (NumberFormatException e) {
                    System.out.println("ID ist nict Richtig: " + wert);
                }
                break;
            case "Vorname":
                ergebnisse = MitarbeiterDAO.find_mit_Vorname(wert);
                break;
            case "Nachname":
                ergebnisse = MitarbeiterDAO.find_mit_Nachname(wert);
                break;
            default:
                System.out.println("Unbekanntes Feld: " + feld);
        }

        return ergebnisse;
    }// End sucheMitarbeiter


    // Bearbeitet Mitarbeiter nach Validierung
    public static boolean mitarbeiter_Bearbeiten(Mitarbeiter m) {
        if (m == null || m.getId() <= 0) return false;
        return MitarbeiterDAO.bearbeiten_Mitarbeiter(m);
    } // End mitarbeiterBearbeiten


    // Löscht einen Mitarbeiter
    public static boolean mitarbeiter_Loeschen(int id) {
        if (id <= 0) {
            System.out.println("Ungültige Mitarbeiter-ID.");
            return false;
        }

        return MitarbeiterDAO.loeschen_Mitarbeiter(id);

    } // End mitarbeiterLoeschen


    public static String getNameById(int id) {
        Mitarbeiter mitarbeiter = MitarbeiterDAO.find_Mitarbeiter_Id(id);
        return (mitarbeiter != null) ? mitarbeiter.getVorname() + " " + mitarbeiter.getNachname() : "-";
    }


    // Holt ein Mitarbeiterobjekt nach ID
    public static Mitarbeiter finde_Mitarbeiter_Id(int id) {
        return MitarbeiterDAO.find_Mitarbeiter_Id(id);
    }

    // Anzahl Mitarbeiter
    public static int getAnzahlMitarbeiter() {
        return MitarbeiterDAO.anzahl_Mitarbeiter();
    }

    // Validate Mitarbeiter info
    public static String validiere(Mitarbeiter mitarbeiter) {
        if (mitarbeiter.getVorname().isEmpty() || mitarbeiter.getNachname().isEmpty() ||
                mitarbeiter.getTelefon().isEmpty() || mitarbeiter.getEmail().isEmpty() || mitarbeiter.getEinstellungsdatum().isEmpty()) {
            return "Bitte alle Felder ausfüllen!";
        }

        if (!mitarbeiter.getEmail().contains("@") || !mitarbeiter.getEmail().contains(".")) {
            return "Ungültige Email-Adresse!";
        }

        if (!mitarbeiter.getTelefon().matches("[0-9\\-+() ]{6,}")) {
            return "Telefonnummer ist ungültig!";
        }

        return null; // kein Fehler
    }

}
