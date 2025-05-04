package view;

import service.FahrzeugService;
import service.MitarbeiterService;

import java.util.HashMap;
import java.util.Scanner;

public class HauptmenueView {

    private final Scanner scanner = new Scanner(System.in);

    public void starten() {
        String haupt_Options;
        HashMap<String, String> hauptmenue = MenuLoader.loadMenu("menu_haupt.txt");


        do {
            MenuPrinter.printMenu(hauptmenue, "Fuhrpark Hauptmenü");
            haupt_Options = scanner.nextLine().trim();

            if (haupt_Options.equals("1")) {
                zeigeSubMenu("subMenu_Anzeigen.txt", "Anzeigen Menü");
            } else if (haupt_Options.equals("2")) {
                zeigeSubMenu("subMenu_Hinzufügen.txt", "Hinzufügen Menü");
            } else if (haupt_Options.equals("3")) {
                zeigeSubMenu("subMenu_Löschen.txt", "Löschen Menü");
            } else if (haupt_Options.equals("4")) {
                zeigeSubMenu("subMenu_Bearbeiten.txt", "Bearbeiten Menü");
            } else if (haupt_Options.equals("5")) {
                zeigeSubMenu("subMenu_Leistungen.txt", "Leistungen Menü");
            } else if (haupt_Options.equals("x")) {
                System.out.println("Programm beendet.");
            } else {
                System.out.println("Ungültige Eingabe!");
            }

        } while (!haupt_Options.equals("x"));
    }


    private void zeigeSubMenu(String dateiName, String titel) {
        String manu_Wealen;
        HashMap<String, String> submenu = MenuLoader.loadMenu(dateiName);

        do {
            MenuPrinter.printMenu(submenu, titel);
            manu_Wealen = scanner.nextLine().trim();


            if (dateiName.contains("Anzeigen")) {
                if (manu_Wealen.equals("1")) FahrzeugView.anzeigenAlle(FahrzeugService.serviceAlleFahrzeug());
                else if (manu_Wealen.equals("2")) MitarbeiterView.mitarbeiterAnzeigen(MitarbeiterService.serviceAlleMitarbeiter());
                else if (manu_Wealen.equals("3")) FahrzeugView.fahrzeugSuchen();
                else if (manu_Wealen.equals("4")) MitarbeiterView.mitarbeiterSuchen();
            }
            else if (dateiName.contains("Hinzufügen")) {
                if (manu_Wealen.equals("1")) FahrzeugView.fahrzeugHinzufuegen();
                else if (manu_Wealen.equals("2")) MitarbeiterView.mitarbeiterHinzufuegen();
            }
            else if (dateiName.contains("Löschen")) {
                if (manu_Wealen.equals("1")) FahrzeugView.fahrzeugLoeschen();
                else if (manu_Wealen.equals("2")) MitarbeiterView.mitarbeiterLoeschen();
            }
            else if (dateiName.contains("Bearbeiten")) {
                if (manu_Wealen.equals("1")) FahrzeugView.fahrzeugBearbeiten();
                else if (manu_Wealen.equals("2")) MitarbeiterView.mitarbeiterBearbeiten();
            }
            else if (dateiName.contains("Leistungen")) {
                if (manu_Wealen.equals("1")) FahrzeugView.fahrzeugFahren();
                else if (manu_Wealen.equals("2")) FahrzeugView.fahrzeugTanken();
                else if (manu_Wealen.equals("3")) FahrzeugView.wartungEintragen();
                else if (manu_Wealen.equals("4")) FahrzeugView.fahrzeugZuweisen();
            }

            if (!manu_Wealen.equals("z")) {
                System.out.println();
            }

        } while (!manu_Wealen.equals("z"));


    }


}
