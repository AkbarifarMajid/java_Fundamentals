import model.*;
import controller.VereinsController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //meine Objecte
        Verein myVerein = new Verein("Storm", "Graz");
        VereinsController controller = new VereinsController(myVerein);
        Scanner myScanner = new Scanner(System.in);

        int subMenu;

        while (true) {
            System.out.println("1. Hinzufügen ");
            System.out.println("2. Anzeigen ");
            System.out.println("3. Löschen ");
            System.out.println("0. Beenden");
            System.out.print("Bitte wählen Sie 1,2,3 oder 0: ");

            int benutzerWahl = Integer.parseInt(myScanner.nextLine());
            System.out.println("-----------------------------");
            switch (benutzerWahl) {

                //Hinzufügen
                case 1 -> {

                    System.out.println("\n1 (H) Mannschaft hinzufügen");
                    System.out.println("2 (H) Spieler zu Mannschaft hinzufügen");
                    System.out.println("3 (H) Trainer zu Mannschaft hinzufügen");
                    System.out.print("Whlen Sie 1,2 oder 3: ");
                    subMenu = Integer.parseInt(myScanner.nextLine());
                    System.out.println("-----------------------------");
                    switch (subMenu) {

                        //Mannschaft hinzufügen"
                        case 1 -> {
                            for (Mannschaft team : controller.getAlleMannschaften()) {
                                System.out.println(team.getName());
                            }

                            System.out.print("Name der Mannschaft: ");
                            String tname = myScanner.nextLine().trim();
                            System.out.print("Geschlecht (HERREN/DAMEN): ");

                            // Umwandlung der Benutzereingabe in einen gültigen Enum-Wert von Geschlecht
                            Geschlecht geschlecht = Geschlecht.valueOf(myScanner.nextLine().toUpperCase().trim());
                            System.out.print("Altersgruppe (U13/U15/U17/U19/ERWACHSENE): ");

                            // Umwandlung der Benutzereingabe in einen gültigen Enum-Wert von Altersgruppe
                            Altersgruppe alter = Altersgruppe.valueOf(myScanner.nextLine().toUpperCase().trim());
                            controller.neuMannschaftHinzufuegen(tname, geschlecht, alter);
                        }

                        //Spieler zu Mannschaft hinzufügen
                        case 2 -> {
                            for (Mannschaft team : controller.getAlleMannschaften()) {
                                System.out.println(team.getName());
                            }

                            System.out.print("Zu welcher Mannschaft? ");
                            String team = myScanner.nextLine();

                            System.out.print("Vorname: ");
                            String vorname = myScanner.nextLine();

                            System.out.print("Nachname: ");
                            String nachname = myScanner.nextLine();

                            System.out.print("Trikotnummer: ");
                            int number = Integer.parseInt(myScanner.nextLine());

                            System.out.print("Telefon: ");
                            String telefon = myScanner.nextLine();

                            System.out.print("Email: ");
                            String email = myScanner.nextLine();

                            //Erstellen eines Kontaktobjekts vom Typ record
                            Kontaktinfo myKontaktInfo = new Kontaktinfo(telefon, email);
                            //Erstellen einen neuen Spieler mit eingegebenen Informationen
                            Spieler mySpieler = new Spieler(vorname, nachname, number, myKontaktInfo);
                            //Spieler wird dem Team hinzugefügen.
                            controller.spielerHinzufuegen(team, mySpieler);
                        }

                        //Trainer zu Mannschaft hinzufügen
                        case 3 -> {
                            System.out.print("Zu welcher Mannschaft? ");
                            String team = myScanner.nextLine();

                            System.out.print("Vorname: ");
                            String vorname = myScanner.nextLine();

                            System.out.print("Nachname: ");
                            String nachname = myScanner.nextLine();

                            System.out.print("Rolle : ");
                            String rolle = myScanner.nextLine();

                            System.out.print("Telefon: ");
                            String telefon = myScanner.nextLine();

                            System.out.print("Email: ");
                            String email = myScanner.nextLine();

                            //Erstellen eines Kontaktobjekts vom Typ record
                            Kontaktinfo myKontaktInfo = new Kontaktinfo(telefon, email);
                            //Erstellen einen neuen Trainer mit eingegebenen Informationen
                            Trainer myTrainer = new Trainer(vorname, nachname, rolle, myKontaktInfo);
                            //Spieler wird dem Team hinzugefügen.
                            controller.neuTrainerHinzufuegen(team, myTrainer);

                        }
                        default -> System.out.println("Ungültige Auswahl.");
                    }
                }

                //Anzeigen
                case 2 -> {
                    System.out.println("1 (A) Verein anzeigen");
                    System.out.println("2 (A) Alle Mannschaften anzeigen");
                    System.out.println("3 (A) Alle Spieler anzeigen");
                    System.out.println("4 (A) Alle Trainer anzeigen");
                    System.out.println("5 (A) Spieler eines Teams anzeigen");
                    System.out.println("6 (A) Spieler nach Nachname suchen");
                    System.out.print("Bitte wählwn Sie 1,2,3,4,5 oder 6 :");

                    subMenu = Integer.parseInt(myScanner.nextLine());
                    System.out.println("-----------------------------");
                    switch (subMenu) {
                        case 1 -> controller.vereinAnzeigen();
                        case 2 -> controller.alleMannschaftenAnzeigen();
                        case 3 -> controller.alleSpielerAnzeigen();
                        case 4 -> controller.alleTrainerAnzeigen();
                        case 5 -> {
                            for (Mannschaft team : controller.getAlleMannschaften()) {
                                System.out.println(team.getName());
                            }

                            System.out.print("Name der Mannschaft: ");
                            String team = myScanner.nextLine();
                            controller.spielerEinesTeamsAnzeigen(team);
                        }
                        case 6 -> {
                            System.out.print("Nachname eingeben: ");
                            String nachname = myScanner.nextLine();
                            controller.spielerNachNachnameSuchen(nachname);
                        }
                        default -> System.out.println("Ungültige Auswahl.");
                    }
                }

                //Löschen
                case 3 -> { // Löschen
                    System.out.println("1 (L) Mannschaft löschen");
                    System.out.println("2 (L) Trainer aus Mannschaft löschen");
                    System.out.println("3 (L) Spieler löschen ");
                    System.out.print(" Wahl Sie 1 ,2 oder 3: ");
                    subMenu = Integer.parseInt(myScanner.nextLine());
                    System.out.println("-----------------------------");

                    switch (subMenu) {

                        //Mannschaft löschen
                        case 1 -> {
                            for (Mannschaft team : controller.getAlleMannschaften()) {
                                System.out.println(team.getName());
                            }
                            System.out.print("Name der Mannschaft: ");
                            String team = myScanner.nextLine();
                            controller.mannschaftEntfernen(team);
                        }

                        //Trainer aus Mannschaft löschen
                        case 2 -> {
                            for (Mannschaft team : controller.getAlleMannschaften()) {
                                System.out.println(team.getName());
                            }
                            System.out.print("Name der Mannschaft: ");
                            String team = myScanner.nextLine();

                            System.out.print("Nachname des Trainers: ");
                            String nachname = myScanner.nextLine();

                            controller.trainerEntfernen(team, nachname);
                        }

                        //Spieler löschen
                        case 3 -> {
                            for (Mannschaft team : controller.getAlleMannschaften()) {
                                System.out.println(team.getName());
                            }
                            System.out.print("Name der Mannschaft: ");
                            String team = myScanner.nextLine();

                            System.out.print("Nachname des Spieler: ");
                            String nachname = myScanner.nextLine();

                            controller.spielerEntfernen(team, nachname);
                        }

                        default -> System.out.println("Ungültige Auswahl.");
                    }
                }

                //Beenden
                case 0 -> {
                    System.out.println("Vielen Dank, dass Sie sich für uns entschieden haben.");
                    return;
                }
                default -> System.out.println("Wählen Sie bitte 1,2,3 oder 0");

            }//End Haupt switch
        }// End while(true)
    }//End main
}// End Main
