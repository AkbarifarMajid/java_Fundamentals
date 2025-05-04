package view;

import database.MitarbeiterDAO;
import model.Fahrzeug;
import model.Mitarbeiter;
import service.FahrzeugService;
import service.MitarbeiterService;

import java.util.ArrayList;
import java.util.Scanner;

public class MitarbeiterView {

    public static Scanner myScanner = new Scanner(System.in);

    public static void mitarbeiterSuchen() {
        System.out.println("\n--- Mitarbeiter suchen ---");
        System.out.print("Mitarbeiter-ID: ");
        int id_Mitarbeiter = Integer.parseInt(myScanner.nextLine());

        Mitarbeiter mitarbeiter = MitarbeiterService.mitarbeiterSuchen(id_Mitarbeiter);

        if (mitarbeiter != null) {
            System.out.println("\nGefundener Mitarbeiter:");
            System.out.println(mitarbeiter.getId() + ": " + mitarbeiter.getVorname() + " " + mitarbeiter.getNachname() + ", " + mitarbeiter.getPosition());
        } else {
            System.out.println("Mit ID: "+ id_Mitarbeiter +" gibt es Kein Mitarbeiter ");
        }
    }

    public static void mitarbeiterAnzeigen(ArrayList<Mitarbeiter> liste) {
        //var liste = MitarbeiterDAO.ladenAlle();
        if (liste.isEmpty()) {
            System.out.println("Die Mitarbeiter Liste ist leer.");
        } else {
            System.out.println("--- Mitarbeiterliste ---");
            for (var mitarbeiter : liste) {
                System.out.printf("ID: %d - %s %s\n Position: (%s), Tel: %s%n",
                        mitarbeiter.getId(), mitarbeiter.getVorname(), mitarbeiter.getNachname(),
                        mitarbeiter.getPosition(),
                        mitarbeiter.getTelefonnummer());
                System.out.println("--------------------");
            }
        }
    }

    public static void mitarbeiterHinzufuegen() {
        System.out.println("\n--- Mitarbeiter hinzufügen ---");

        System.out.print("Vorname: ");
        String vorname = myScanner.nextLine();

        System.out.print("Nachname: ");
        String nachname = myScanner.nextLine();

        System.out.print("Position: ");
        String position = myScanner.nextLine();

        System.out.print("Telefonnummer: ");
        String telefon = myScanner.nextLine();

        MitarbeiterService.mitarbeiterHinzufuegen(vorname, nachname, position, telefon);


    }

    public static void mitarbeiterLoeschen() {
        System.out.println("\n--- Mitarbeiter löschen ---");
        System.out.print("Mitarbeiter-ID: ");
        int id_Mitarbeiter = Integer.parseInt(myScanner.nextLine());

        Mitarbeiter mitarbeiter_Suchen = MitarbeiterService.mitarbeiterSuchen(id_Mitarbeiter);

        if (mitarbeiter_Suchen != null) {
            System.out.println("\nGefundener Mitarbeiter:");
            System.out.println("VorName: " +  mitarbeiter_Suchen.getVorname() + " NachName: " + mitarbeiter_Suchen.getNachname() + "\nPosition: " + mitarbeiter_Suchen.getPosition());
        } else {
            System.out.println("Mit ID: "+ id_Mitarbeiter +" gibt es Kein Mitarbeiter ");
            return;
        }


        boolean geloescht = MitarbeiterService.mitarbeiterLoeschen(id_Mitarbeiter);

        if (geloescht) {
            System.out.println("Mitarbeiter mit ID: " + id_Mitarbeiter + " wurde gelöscht.");
        } else {
            System.out.println("konnte nicht gelöscht werden.");
        }
    }

    public static void mitarbeiterBearbeiten(){
        System.out.println("\n--- Mitarbeiter Bearbeiten ---");

        System.out.println(" Geben Sie den Mitarbeiter ID ein. ");
        int id_Mitarbeiter = Integer.parseInt(myScanner.nextLine());

        Mitarbeiter mitarbeiter_Suchen = MitarbeiterService.mitarbeiterSuchen(id_Mitarbeiter);


        if (mitarbeiter_Suchen != null) {
            System.out.println("\nGefundener Mitarbeiter:");
            System.out.println(mitarbeiter_Suchen.getId() + ": " + mitarbeiter_Suchen.getVorname() + " " + mitarbeiter_Suchen.getNachname() + ", " + mitarbeiter_Suchen.getPosition());
        } else {
            System.out.println("Mit ID: "+ id_Mitarbeiter +" gibt es Kein Mitarbeiter ");
            return;
        }


        System.out.println("Was möchten Sie ändern?");
        System.out.println("1 - Vorname");
        System.out.println("2 - Nachname");
        System.out.println("3 - Position");
        System.out.println("4 - Telefonnummer");
        System.out.print("Auswahl: ");
        String bearbeiten_Auswahl = myScanner.nextLine();

        System.out.println(" Neuer Wert für Ihre Auswahl : ");
        String neu_Wert_Bearbeiten = myScanner.nextLine();

        boolean berabeiten_Result = MitarbeiterService.mitarbeiter_Bearbeiten(id_Mitarbeiter,bearbeiten_Auswahl,neu_Wert_Bearbeiten);

        if(berabeiten_Result){
            System.out.println("Mitarbeiter mit ID: "+ id_Mitarbeiter + "ist hat neu Wert.");
        }else{
            System.out.println(" Bei Bearbeiten gibt es Problem !");
        }

    }


}
