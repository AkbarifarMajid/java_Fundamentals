package view;

import util.MenuLoader;
import util.MenuPrinter;

import java.util.HashMap;
import java.util.Scanner;

public class HauptmenueView {

    private final Scanner scanner = new Scanner(System.in);

    public void starten() {
        String hauptOption;
        HashMap<String, String> hauptmenue = MenuLoader.loadMenu("menu/hauptmenue.txt");

        do {
            MenuPrinter.printMenu(hauptmenue, "Vereins Hauptmenü");
            hauptOption = scanner.nextLine().trim();

            switch (hauptOption) {
                case "1":
                    zeigeSubMenu("menu/subMenu_Mitglieder.txt", "MITGLIEDER-Menü");
                    break;
                case "2":
                    zeigeSubMenu("menu/subMenu_Trainer.txt", "TRAINER-Menü");
                    break;
                case "3":
                    zeigeSubMenu("menu/subMenu_Mannschaften.txt", "MANNSCHAFTEN-Menü");
                    break;
                case "4":
                    System.out.println("📋 Vereinsinformationen anzeigen (noch nicht implementiert)");
                    break;
                case "x":
                    System.out.println("Programm beendet.");
                    break;
                default:
                    System.out.println("Ungültige Eingabe!");
            }

        } while (!hauptOption.equals("x"));
    }

    // Zeigt ein Untermenü basierend auf der gegebenen Datei und steuert die Auswahl
    private void zeigeSubMenu(String dateiName, String titel) {
        String auswahl;
        HashMap<String, String> submenu = MenuLoader.loadMenu(dateiName);

        do {
            MenuPrinter.printMenu(submenu, titel);
            auswahl = scanner.nextLine().trim();

            switch (dateiName) {
                case "menu/subMenu_Mitglieder.txt":
                    mitgliederAktionen(auswahl);
                    break;
                case "menu/subMenu_Trainer.txt":
                    trainerAktionen(auswahl);
                    break;
                case "menu/subMenu_Mannschaften.txt":
                    mannschaftenAktionen(auswahl);
                    break;
            }

        } while (!auswahl.equals("z"));
    }

    private void mitgliederAktionen(String auswahl) {
        switch (auswahl) {
            case "1":
                MitgliedView.mitgliederAnzeigen();
                break;
            case "2":
                MitgliedView.mitgliedSuchen();
                break;
            case "3":
                MitgliedView.mitgliedHinzufuegen();
                break;
            case "4":
                MitgliedView.mitgliedLoschen();
                break;
            case "5":
                MitgliedView.mitgliedBearbeiten();
                break;
            case "z":
                break;
            default:
                System.out.println("Ungültige Eingabe im Mitglieder-Menü.");
        }
    }

    private void trainerAktionen(String auswahl) {
        switch (auswahl) {
            case "1":
                TrainerView.trainerAnzeigen();
                break;
            case "2":
                TrainerView.trainerSuchen();
                break;
            case "3":
                TrainerView.trainerHinzufuegen();
                break;
            case "4":
                TrainerView.trainerLoschen();
                break;
            case "5":
                TrainerView.trainerBearbeiten();
                break;
            case "z":
                break;
            default:
                System.out.println("Ungültige Eingabe im Trainer-Menü.");
        }
    }

    private void mannschaftenAktionen(String auswahl) {
        switch (auswahl) {
            case "1":
                System.out.println("👉 Mannschaften anzeigen (noch nicht implementiert)");
                break;
            case "2":
                System.out.println("👉 Neue Mannschaft erstellen (noch nicht implementiert)");
                break;
            case "3":
                System.out.println("👉 Mitglied zur Mannschaft hinzufügen (noch nicht implementiert)");
                break;
            case "4":
                System.out.println("👉 Trainer zur Mannschaft hinzufügen (noch nicht implementiert)");
                break;
            case "5":
                System.out.println("👉 Mitglied aus Mannschaft entfernen (noch nicht implementiert)");
                break;
            case "6":
                System.out.println("👉 Mannschaft löschen (noch nicht implementiert)");
                break;
            case "z":
                break;
            default:
                System.out.println("Ungültige Eingabe im Mannschaften-Menü.");
        }
    }
}
