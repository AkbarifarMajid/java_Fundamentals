package service;

import database.MitarbeiterDAO;
import model.Mitarbeiter;

import java.util.ArrayList;
import java.util.Scanner;

// Geschäftslogik für Mitarbeiter (Validierung + DAO-Aufrufe)
public class MitarbeiterService {

    private static final Scanner scanner = new Scanner(System.in);

    // Gibt alle zurück
    public static ArrayList<Mitarbeiter> serviceAlleMitarbeiter() {
        return MitarbeiterDAO.ladenAlle();
    }

    // Sucht nach ID
    public static Mitarbeiter mitarbeiterSuchen(int mitarbeiterId) {
        return MitarbeiterDAO.findeNachId(mitarbeiterId);

    }

    // Neuer Mitarbeiter wird hinzugefügt
    public static void mitarbeiterHinzufuegen(String vorname, String nachname, String position, String telefon) {

        if (vorname.isBlank() || nachname.isBlank()) {
            System.out.println("Vorname und Nachname müssen nicht leer sein.");
            return;
        }
        Mitarbeiter mitarbeiter = new Mitarbeiter(vorname,nachname,position,telefon);
        MitarbeiterDAO.einfuegen(mitarbeiter);


    }

    // Löscht
    public static boolean mitarbeiterLoeschen(int id) {

        if (id <= 0) {
            System.out.println("Ungültige Mitarbeiter-ID.");
            return false;
        }

        return MitarbeiterDAO.loeschen(id);

    }

    // Bearbeitet ein Feld eines Mitarbeiters
    public static boolean mitarbeiter_Bearbeiten(int mitarbeiter_Id,String feld_Mitarbeiter, String neu_Wert){
        Mitarbeiter mitarbeiter = MitarbeiterDAO.findeNachId(mitarbeiter_Id);

        if(mitarbeiter == null) {
            System.out.println("\nGefundener Mitarbeiter:");
            System.out.println(mitarbeiter.getId() + ": " + mitarbeiter.getVorname() + " " + mitarbeiter.getNachname() + ", " + mitarbeiter.getPosition());

        }else return false;


        switch (feld_Mitarbeiter){
            case "1" ->mitarbeiter.setVorname(neu_Wert);
            case "2" ->mitarbeiter.setNachname(neu_Wert);
            case "3" ->mitarbeiter.setPosition(neu_Wert);
            case "4" ->mitarbeiter.setTelefonnummer(neu_Wert);
            default -> {
                System.out.println("Auswahl ist nicht richtig!");
            }
        }


        return MitarbeiterDAO.mitarbeiter_Update(mitarbeiter);
    }

}
