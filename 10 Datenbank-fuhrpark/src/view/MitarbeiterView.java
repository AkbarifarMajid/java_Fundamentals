package view;

import model.Mitarbeiter;
import service.MitarbeiterService;

import java.util.ArrayList;
import java.util.Scanner;

// Benutzerschnittstelle für Mitarbeiter
public class MitarbeiterView {

    public static Scanner myScanner = new Scanner(System.in);

    // Neuen Mitarbeiter hinzufügen
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

    //Mitarbeiter nach ID suchen
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

    //Zeigt alle Mitarbeiter
    public static void mitarbeiterAnzeigen(ArrayList<Mitarbeiter> liste) {
        //var liste = MitarbeiterDAO.ladenAlle();
        if (liste.isEmpty()) {
            System.out.println("Die Mitarbeiter Liste ist leer.");
        } else {
            System.out.println("--- Mitarbeiterliste ---");
            for (var mitarbeiter : liste) {
                System.out.printf("ID: %d - %s %s\nPosition: (%s)\nTel: %s%n",
                        mitarbeiter.getId(), mitarbeiter.getVorname(), mitarbeiter.getNachname(),
                        mitarbeiter.getPosition(),
                        mitarbeiter.getTelefonnummer());
                System.out.println("--------------------");
            }
        }
    }

    //Löschen
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

    // Bearbeiten
    public static void mitarbeiterBearbeiten(){
        System.out.println("\n--- Mitarbeiter Bearbeiten ---");

        System.out.println(" Geben Sie den Mitarbeiter ID ein. ");
        int id_Mitarbeiter = Integer.parseInt(myScanner.nextLine());

        Mitarbeiter mitarbeiter_Suchen = MitarbeiterService.mitarbeiterSuchen(id_Mitarbeiter);


        if (mitarbeiter_Suchen != null) {
            //System.out.println("\nGefundener Mitarbeiter:");
            //System.out.println(mitarbeiter_Suchen.getId() + ": " + mitarbeiter_Suchen.getVorname() + " " + mitarbeiter_Suchen.getNachname() + ", " + mitarbeiter_Suchen.getPosition());
       // } else {
            System.out.println("Mit ID: "+ id_Mitarbeiter +" gibt es Kein Mitarbeiter ");
            return;
        }


        System.out.println("\nGefundener Mitarbeiter:");
        System.out.println(mitarbeiter_Suchen.getId() + ": " + mitarbeiter_Suchen.getVorname() + " " + mitarbeiter_Suchen.getNachname() + ", " + mitarbeiter_Suchen.getPosition());

        System.out.println("\nDrücken Sie Enter, um das aktuelle Feld zu behalten.");

        System.out.print("Neuer Vorname: ");
        String neuerVorname = myScanner.nextLine();

        System.out.print("Neuer Nachname: ");
        String neuerNachname = myScanner.nextLine();

        System.out.print("Neue Position: ");
        String neuePosition = myScanner.nextLine();

        System.out.print("Neue Telefonnummer: ");
        String neueTelefonnummer = myScanner.nextLine();

        boolean berabeiten_Result = MitarbeiterService.mitarbeiter_Bearbeiten(
                id_Mitarbeiter,
                neuerVorname.isBlank() ? null : neuerVorname,
                neuerNachname.isBlank() ? null : neuerNachname,
                neuePosition.isBlank() ? null : neuePosition,
                neueTelefonnummer.isBlank() ? null : neueTelefonnummer);
        //System.out.println("Was möchten Sie ändern?");
        //System.out.println("1 - Vorname");
        //System.out.println("2 - Nachname");
        //System.out.println("3 - Position");
        //System.out.println("4 - Telefonnummer");
        //System.out.print("Auswahl: ");
        //String bearbeiten_Auswahl = myScanner.nextLine();

       // System.out.println(" Neuer Wert für Ihre Auswahl : ");
        //String neu_Wert_Bearbeiten = myScanner.nextLine();

       // boolean berabeiten_Result = MitarbeiterService.mitarbeiter_Bearbeiten(id_Mitarbeiter,bearbeiten_Auswahl,neu_Wert_Bearbeiten);

        if(berabeiten_Result){
            System.out.println("Mitarbeiter mit ID: "+ id_Mitarbeiter + "ist hat neu Wert.");
        }else{
            System.out.println(" Bei Bearbeiten gibt es Problem !");
        }

    }


    /*
    // Bearbeiten
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

     */

}
