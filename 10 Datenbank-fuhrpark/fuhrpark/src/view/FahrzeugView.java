package view;

import model.*;

import java.util.ArrayList;
import service.FahrzeugService;
import java.util.Scanner;

public class FahrzeugView {
   public static Scanner myScanner = new Scanner(System.in);

    public static void anzeigenAlle ( ArrayList<Fahrzeug> liste){
        for (Fahrzeug myFahrzeug : liste) {
            System.out.println("\nFahrzeug-ID: " + myFahrzeug.getId() + "\nTyp: " + myFahrzeug.getClass().getSimpleName());
            System.out.println("Hersteller: " + myFahrzeug.getHersteller() + " *" + " Modell: " + myFahrzeug.getModell());
            System.out.println("Baujahr: " + myFahrzeug.getBaujahr() + " *" + " Kilometerstand: " + myFahrzeug.getKilometerstand());
            System.out.println("Kraftstoff: " + myFahrzeug.getKraftstoff() + " *" + " Standort: " + myFahrzeug.getStandort());

            if (myFahrzeug instanceof PKW) {
                int sitze = FahrzeugService.getSitzanzahl(myFahrzeug.getId());
                System.out.println("Sitzanzahl: " + sitze);

            }
            else if (myFahrzeug instanceof LKW) {
                double gewicht = FahrzeugService.getLadegewicht(myFahrzeug.getId());
                System.out.println("Ladegewicht: " + gewicht + " Tonnen");

            }
            else if (myFahrzeug instanceof Motorrad) {
                int hubraum = FahrzeugService.getHubraum(myFahrzeug.getId());
                boolean gang =FahrzeugService.getGangschaltung(myFahrzeug.getId());

                System.out.println("Hubraum: " + hubraum + " ccm");
                System.out.println("Gangschaltung: " + (gang ? "Ja" : "Nein"));
            }
            else if (myFahrzeug instanceof Fahrrad) {
                boolean korb =FahrzeugService.getHatKorb(myFahrzeug.getId());
                System.out.println("Hat Korb: " + (korb ? "Ja" : "Nein"));
            }
        }
    }

    public static void fahrzeugSuchen() {
        System.out.println("\n--- Fahrzeug suchen ---");

        System.out.print("Fahrzeug-ID: ");
        int id = Integer.parseInt(myScanner.nextLine());

        Fahrzeug gefundFarhzeug = FahrzeugService.fahrzeugSuchen(id);

        if (gefundFarhzeug != null) {
            gefundFarhzeug.anzeigen();

            // Besitzerinformationen anzeigen, falls verfügbar
            Mitarbeiter besitzer = gefundFarhzeug.getBesitzer();
            if (besitzer != null) {
                System.out.println("Besitzer: " + besitzer.getVorname() + " " + besitzer.getNachname());
            } else {
                System.out.println("Kein Besitzer zugewiesen.");
            }

        } else {
            System.out.println("Gibt es kein Fahrzeug mit ID " + id);
        }
    }


    public static void fahrzeugHinzufuegen() {
        System.out.println("\n--- Neues Fahrzeug hinzufügen ---");

        System.out.println("Typ wählen: 1=PKW, 2=LKW, 3=Motorrad, 4=Fahrrad");
        String typ = myScanner.nextLine().trim();

        System.out.print("Hersteller: ");
        String hersteller = myScanner.nextLine();

        System.out.print("Modell: ");
        String modell = myScanner.nextLine();

        System.out.print("Baujahr: ");
        int baujahr = Integer.parseInt(myScanner.nextLine());

        FahrzeugService.fahrzeugHinzufuegen(typ, hersteller, modell, baujahr);

    }

    public static void fahrzeugLoeschen() {
        System.out.println("\n--- Fahrzeug löschen ---");

        System.out.print("Fahrzeug-ID: ");
        int id_Loschen = Integer.parseInt(myScanner.nextLine());

       Fahrzeug fahrzeug_Suchen = FahrzeugService.fahrzeugSuchen(id_Loschen);

        if (fahrzeug_Suchen != null) {
            System.out.println("\nGefundener Farzeug:");
            System.out.println("Hersteller : "+ fahrzeug_Suchen.getTyp_Fahrzeug() + "Model: "+ fahrzeug_Suchen.getHersteller()
                    + "\nType " + fahrzeug_Suchen.getModell() + " Baujahr: " + fahrzeug_Suchen.getBaujahr());
        }

        boolean loescht_Result =  FahrzeugService.fahrzeugLoeschen(id_Loschen);

        if (loescht_Result) {
            System.out.println("Gibt Es Fahrzeug mit ID: "+id_Loschen+ " nicht mehr");
        } else {
            System.out.println("Gibt Es keinr Fahrzeug mit ID: "+id_Loschen);
        }
    }

    public static void fahrzeugZuweisen() {
        System.out.println("\n--- Fahrzeug einem Mitarbeiter zuweisen ---");

        System.out.print("Fahrzeug-ID: ");
        int fahrzeugId = Integer.parseInt(myScanner.nextLine());

        System.out.print("Mitarbeiter-ID: ");
        int mitarbeiterId = Integer.parseInt(myScanner.nextLine());

        boolean erfolg_result = FahrzeugService.fahrzeugZuweisen(fahrzeugId, mitarbeiterId);

        if (erfolg_result) {
            System.out.println("Fahrzeug wurde erfolgreich zugewiesen ID:" + mitarbeiterId);
        } else {
            System.out.println("ID falsch oder DB-Fehler.");
        }
    }

    public static void fahrzeugFahren() {
        System.out.println("\n--- Fahrzeug fahren ---");

        System.out.print("Fahrzeug-ID: ");
        int id = Integer.parseInt(myScanner.nextLine());

        System.out.print("Gefahrene Kilometer: ");
        double km = Double.parseDouble(myScanner.nextLine());

        boolean erfolg = FahrzeugService.fahrzeugFahren(id, km);

        if (erfolg) {
            System.out.println("Kilometer hinzugefügt.");
        } else {
            System.out.println("Problem beim Hinzufügen von Kilometern.");
        }
    }

    public static void wartungEintragen() {
        System.out.println("\n--- Wartungstermin eintragen ---");

        System.out.print("Fahrzeug-ID: ");
        int id = Integer.parseInt(myScanner.nextLine());

        System.out.print("Datum der Wartung ( YYYY-MM-DD): ");
        String datum = myScanner.nextLine().trim();
        if (datum.isEmpty()) {
            System.out.println("Datum darf nicht leer sein.");
            return;
        }

        boolean wartun_result = FahrzeugService.wartungEintragen(id, datum);

        if (wartun_result) {
            System.out.println("Datum der Fahrzeuginspektion hinzugefügt.");
        } else {
            System.out.println("Problem beim Eingeben des Fahrzeuginspektionsdatums.");
        }

    }

    public static void fahrzeugBearbeiten() {
        System.out.println("\n--- Fahrzeug Bearbeiten ---");

        System.out.print("Fahrzeug-ID: ");
        int id_Fahrzeug = Integer.parseInt(myScanner.nextLine());

       Fahrzeug farzeuchuchen = FahrzeugService.fahrzeugSuchen(id_Fahrzeug);

       if(farzeuchuchen == null){
           System.out.println("nicht gefunden");
           return;
       }

       farzeuchuchen.anzeigen();

        System.out.println("\n--- Neues Fahrzeug Bearbeiten ---");

        System.out.print("Hersteller: ");
        String hersteller = myScanner.nextLine();

        System.out.print("Modell: ");
        String modell = myScanner.nextLine();

        System.out.print("Baujahr: ");
        int baujahr = Integer.parseInt(myScanner.nextLine());

       FahrzeugService.fahrzeugBearbeiten(id_Fahrzeug,farzeuchuchen.getTyp_Fahrzeug(), hersteller, modell, baujahr);

    }

    public static void fahrzeugTanken() {
        System.out.println("\n--- Fahrzeug tanken ---");

        System.out.print("Fahrzeug-ID: ");
        int id = Integer.parseInt(myScanner.nextLine());

        System.out.print("Getankte Liter: ");
        double liter = Double.parseDouble(myScanner.nextLine());

        boolean erfolg = FahrzeugService.fahrzeugTanken(id, liter);

        if (erfolg) {
            System.out.println("Farhzeug ist getankt.");
        } else {
            System.out.println("Fahrzeug konnte nicht tanken.");
        }
    }


}
