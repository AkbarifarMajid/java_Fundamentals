package service;

import database.FahrzeugDAO;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;
import database.MitarbeiterDAO;

// Schicht zur Geschäftslogik für Fahrzeuge (Validierung, Koordination zwischen View und DAO)
public class FahrzeugService {

    private static final Scanner myScanner = new Scanner(System.in);

    public static ArrayList<Fahrzeug> serviceAlleFahrzeug() {
       return FahrzeugDAO.ladenAlle();
    }

    public static int getSitzanzahl(int fahrzeugId) {
        return PKW.readVonDAOSitzanzahl(fahrzeugId);
    }

    public static double getLadegewicht(int fahrzeugId) {
        return LKW.ReadVonDAOLadegewicht(fahrzeugId);
    }

    public static int getHubraum(int fahrzeugId) {
        return Motorrad.readVonADOHubraum(fahrzeugId);
    }

    public static boolean getGangschaltung(int fahrzeugId) {
        return Motorrad.readVonADOGangschaltung(fahrzeugId);
    }

    public static boolean getHatKorb(int fahrzeugId) {
        return Fahrrad.radVonADOHatKorb(fahrzeugId);
    }


    public static void fahrzeugHinzufuegen(String typ, String hersteller, String modell, int baujahr) {


        Fahrzeug fahrzeug = null;

        switch (typ) {
            case "1" -> {
                System.out.print("Sitzanzahl: ");
                int sitzanzahl = Integer.parseInt(myScanner.nextLine());
                fahrzeug = new PKW(hersteller, modell, baujahr, sitzanzahl);
            }
            case "2" -> {
                System.out.print("Ladegewicht (Tonnen): ");
                double ladegewicht = Double.parseDouble(myScanner.nextLine());
                fahrzeug = new LKW(hersteller, modell, baujahr, ladegewicht);
            }
            case "3" -> {
                System.out.print("Hubraum (ccm): ");
                int hubraum = Integer.parseInt(myScanner.nextLine());
                System.out.print("Hat Gangschaltung (true/false): ");
                boolean gangschaltung = Boolean.parseBoolean(myScanner.nextLine());
                fahrzeug = new Motorrad(hersteller, modell, baujahr, hubraum, gangschaltung);
            }
            case "4" -> {
                System.out.print("Hat Korb (true/false): ");
                boolean hatKorb = Boolean.parseBoolean(myScanner.nextLine());
                fahrzeug = new Fahrrad(hersteller, modell, baujahr, hatKorb);
            }
            default ->{
                System.out.println("Fahrzeug Type kann nicht Akzeptieren.");
                return;
            }

        }

        if (fahrzeug != null) {
            FahrzeugDAO.einfuegen(fahrzeug, fahrzeug.getClass().getSimpleName());

        }
    }

    // Fährt ein Fahrzeug
    public static boolean fahrzeugFahren(int id, double km){
        if (km <= 0) {
            System.out.println("Kilometer muss nicht wenige als 1 sein");
            return false;
        }

        Fahrzeug fahrzeug = FahrzeugDAO.findeNachId(id);
        if (fahrzeug == null){
            System.out.println("Es gibt keine Fahrzeug mit ID:"+ id + "in 'Datenbank");
        }

        //double vorher_KM = fahrzeug.getKilometerstand();
        fahrzeug.fahren(km);
        double neu_KM = fahrzeug.getKilometerstand();

        boolean aktualisiert = FahrzeugDAO.kilometerAktualisieren(id,neu_KM );

        if (!aktualisiert) {
            System.out.println(" beim Aktualisieren in der Datenbank gibt Es Probleme.");
            return false;
        }

        System.out.println("Neuer Kilometerstand: " + fahrzeug.getKilometerstand());
        return true;
    }

    // Tanken eines Fahrzeugs
    public static boolean fahrzeugTanken(int id,double liter) {
        if (liter <= 0) {
            System.out.println("Liter dar nicht wenige als 1 liter sein.");
            return false;
        }
        Fahrzeug fahrzeug = FahrzeugDAO.findeNachId(id);
        if (fahrzeug == null) {
            System.out.println("Fahrzeug mit ID " + id + " nicht gefunden.");
            return false;
        }
        fahrzeug.tanken(liter);
        boolean aktualisiert = FahrzeugDAO.kraftstoffAktualisieren(id, fahrzeug.getKraftstoff());

        if (aktualisiert) {
            System.out.println("Neuer Kraftstoffstand: " + fahrzeug.getKraftstoff() + " Liter");
            return true;
        } else {
            System.out.println(" beim Aktualisieren der Datenbank gibt Problem.");
            return false;
        }

    }

    // Fügt Wartung hinzu
    public static boolean wartungEintragen(int id,String datum) {

        Fahrzeug f = FahrzeugDAO.findeNachId(id);

        if (f == null) {
            return false;
        }
        FahrzeugDAO.wartungAktualisieren(id,datum);

        return true;
    }

    // Zuweisung eines Fahrzeugs zu einem Mitarbeiter
    public static boolean fahrzeugZuweisen(int fahrzeugId, int mitarbeiterId) {

        Fahrzeug fahrzeug = FahrzeugDAO.findeNachId(fahrzeugId);
        if (fahrzeug == null) {
            System.out.println("Fahrzeug mit ID " + fahrzeugId + " nicht gefunden.");
            return false;
        }

        Mitarbeiter mitarbeiter = MitarbeiterDAO.findeNachId(mitarbeiterId);
        if (mitarbeiter == null) {
            System.out.println("Mitarbeiter mit ID " + mitarbeiterId + " nicht gefunden.");
            return false;
        }
        fahrzeug.setBesitzer(mitarbeiter);
        return FahrzeugDAO.besitzerAktualisieren(fahrzeugId, mitarbeiterId);

    }

    // Löscht Fahrzeug
    public static boolean fahrzeugLoeschen(int id) {
        Fahrzeug fahrzeug = FahrzeugDAO.findeNachId(id);

        if (fahrzeug == null) {
            System.out.println("Fahrzeug mit ID " + id + " wurde nicht gefunden.");
            return false;
        }

        return FahrzeugDAO.loeschen(id);
    }

    // Sucht Fahrzeug per ID
    public static Fahrzeug fahrzeugSuchen(int fahrzeugId) {
        return Fahrzeug.fahrzeugSuchen(fahrzeugId);

    }

    // Bearbeiten der Fahrzeugdaten
    public static void fahrzeugBearbeiten(int id,String typ, String hersteller, String modell, int baujahr) {


        Fahrzeug fahrzeug = null;

        switch (typ) {
            case "PKW" -> {
                System.out.print("Sitzanzahl: ");
                int sitzanzahl = Integer.parseInt(myScanner.nextLine());
                fahrzeug = new PKW(id,typ ,hersteller, modell, baujahr, sitzanzahl);
            }
            case "LKW" -> {
                System.out.print("Ladegewicht (Tonnen): ");
                double ladegewicht = Double.parseDouble(myScanner.nextLine());
                fahrzeug = new LKW(id,typ,hersteller, modell, baujahr, ladegewicht);
            }
            case "Motorrad" -> {
                System.out.print("Hubraum (ccm): ");
                int hubraum = Integer.parseInt(myScanner.nextLine());
                System.out.print("Hat Gangschaltung (true/false): ");
                boolean gangschaltung = Boolean.parseBoolean(myScanner.nextLine());
                fahrzeug = new Motorrad(id,typ,hersteller, modell, baujahr, hubraum, gangschaltung);
            }
            case "Fahrrad" -> {
                System.out.print("Hat Korb (true/false): ");
                boolean hatKorb = Boolean.parseBoolean(myScanner.nextLine());
                fahrzeug = new Fahrrad(id,typ,hersteller, modell, baujahr, hatKorb);
            }
            default ->{
                System.out.println("Fahrzeug Type kann nicht Akzeptieren.");
                return;
            }

        }

        if (fahrzeug != null) {
            FahrzeugDAO.berabeiten(fahrzeug);

        }
    }

}
