package service;

import dao.FahrzeugDAO;
import model.*;

import java.util.ArrayList;
import dao.*;


// Schicht zur Geschäftslogik für Fahrzeuge (Validierung, Koordination zwischen View und DAO)
public class FahrzeugService {



    // List alle Fahrzeug
    public static ArrayList<Fahrzeug> serviceAlleFahrzeug() {
        return FahrzeugDAO.load_Alle_Fahrzeug();
    }

    // Hinzufügen ein neu Fahrzeug
    public static boolean fahrzeug_Hinzufuegen(Fahrzeug fahrzeug) {
        if (fahrzeug == null) return false;

        try {
            String typ = fahrzeug.getClass().getSimpleName();
            FahrzeugDAO.fahrzeug_einfuegen(fahrzeug, typ);
            return true;
        } catch (Exception error) {
            System.out.println("Fehler beim Speichern des Fahrzeugs: " + error.getMessage());
            return false;
        }
    }


    public static boolean fahrzeug_Loeschen(int id) {
        Fahrzeug f = FahrzeugDAO.find_Fahrzeug_Id(id);
        if (f == null) return false;
        return FahrzeugDAO.loeschen_Fahrzeug(id);
    }

    public static Fahrzeug fahrzeug_Suchen_ID(int fahrzeugId) {
        return Fahrzeug.fahrzeugSuchen(fahrzeugId);
    }

    public static boolean fahrzeug_Bearbeiten(Fahrzeug fahrzeug) {
        return FahrzeugDAO.bearbeiten_Fahrzeug(fahrzeug);
    }
    public static boolean kilometer_Hinzufuegen(int fahrzeugId, double startKm, double endKm) {
        return KilometerDAO.hinzufuegenKilometer(fahrzeugId, startKm, endKm);
    }

    public static boolean tanken_Hinzufuegen(int fahrzeugId, double liter) {
        return TankenDAO.tanken_Hinzufuegen(fahrzeugId, liter);
    }

    public static double berechne_Tanken(int fahrzeugId) {
        return TankenDAO.gesamt_Tanken(fahrzeugId);
    }

    //Geht id von Database
    public static Integer getBesitzerId(int fahrzeugId) {
        return FahrzeugDAO.get_Aktueller_BesitzerId(fahrzeugId);
    }

    //Anzahl Fahrzeug
    public static int getAnzahlFahrzeuge() {
        return FahrzeugDAO.anzahl_Fahrzeuge();
    }


    // Validate info Fahrzeug bevor Speichen
    public static String validate_Fahrzeug(Fahrzeug fahrzeug) {
        if (fahrzeug == null) return "Fahrzeug ist null.";

        if (fahrzeug.getHersteller().isBlank() || fahrzeug.getModell().isBlank()) {
            return "Hersteller und Modell dürfen nicht leer sein.";
        }

        int jahr = fahrzeug.getBaujahr();
        int aktuellesJahr = java.time.LocalDate.now().getYear();
        if (jahr < 1950 || jahr > aktuellesJahr) {
            return "Ungültiges Baujahr.";
        }

        if (fahrzeug.getKennzeichen().isBlank()) {
            return "Kennzeichen darf nicht leer sein.";
        }

        if (fahrzeug instanceof PKW p && p.getSitzanzahl() <= 0) {
            return "Sitzanzahl muss größer als 0 sein.";
        }

        if (fahrzeug instanceof LKW l && l.getLadevolumen() <= 0) {
            return "Ladevolumen muss positiv sein.";
        }

        return null; // alles ok
    }



}