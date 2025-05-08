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
    } // End starten

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
    } // End zeigeSubMenu

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
    } // End mitgliederAktionen

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
    } // End trainerAktionen

    private void mannschaftenAktionen(String auswahl) {
        switch (auswahl) {
            case "1":
                MannschafrView.mannschaftenAnzeigen();
                break;
            case "2":
                MannschafrView.mannschaftHinzufuegen();
                break;
            case "3":
                MannschafrView.mitgliedZurMannschaft();
                break;
            case "4":
                MannschafrView.trainerZurMannschaft();
                break;
            case "5":
                MannschafrView.loschen_Mi_Aus_Manschaft();
                break;
            case "6":
                MannschafrView.loschen_Tra_Aus_Manschaft();
                break;
            case "7":
                MannschafrView.Loeschen_mannschaft();
                break;
            case "8":
                MannschafrView.alleMitgliederInMannschaft();
                break;
            case "9":
                MannschafrView.alleTrainerInMannschaft();
                break;
            case "z":
                break;
            default:
                System.out.println("Ungültige Eingabe im Mannschaften-Menü.");
        }
    } // End mannschaftenAktionen

}
