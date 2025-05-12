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

    } // End mitarbeiterHinzufuegen

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
    }// End mitarbeiterSuchen

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
    }// End mitarbeiterAnzeigen

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
    } // End mitarbeiterLoeschen


    public static void mitarbeiterBearbeiten() {
        System.out.println("\n--- Mitarbeiter Bearbeiten ---");

        System.out.print("Mitarbeiter-ID: ");
        int id_Mitarbeiter = Integer.parseInt(myScanner.nextLine());

        Mitarbeiter alt_info = MitarbeiterService.mitarbeiterSuchen(id_Mitarbeiter);

        if (alt_info == null) {
            System.out.println("Mitarbeiter mit ID: " + id_Mitarbeiter + "verfügbar ist.");
            return;
        }

        System.out.println("\nGefundener Mitarbeiter:");
        System.out.println(alt_info.getId() + ": " + alt_info.getVorname() + " " + alt_info.getNachname() + ", " + alt_info.getPosition());

        System.out.println("\nDrücken Sie Enter, um den alten Wert zu behalten.");

        System.out.print("Vorname [" + alt_info.getVorname() + "]: ");
        String vornameInput = myScanner.nextLine();
        if(!vornameInput.isBlank()) alt_info.setVorname(vornameInput);

        System.out.print("Nachname [" + alt_info.getNachname() + "]: ");
        String nachnameInput = myScanner.nextLine();
        if(!nachnameInput.isBlank()) alt_info.setNachname(nachnameInput);

        System.out.print("Position [" + alt_info.getPosition() + "]: ");
        String positionInput = myScanner.nextLine();
        if(!positionInput.isBlank()) alt_info.setPosition(positionInput);

        System.out.print("Telefonnummer [" + alt_info.getTelefonnummer() + "]: ");
        String telefonInput = myScanner.nextLine();
        if(!telefonInput.isBlank()) alt_info.setTelefonnummer(telefonInput);


        boolean erfolg = MitarbeiterService.mitarbeiter_Bearbeiten(alt_info);
        if (erfolg) {
            System.out.println("Mitarbeiter mit ID: "+ id_Mitarbeiter + "ist hat neu Wert.");
        } else {
            System.out.println("Bei Bearbeiten gibt es Problem .");
        }
    } // End mitarbeiterBearbeiten


}
