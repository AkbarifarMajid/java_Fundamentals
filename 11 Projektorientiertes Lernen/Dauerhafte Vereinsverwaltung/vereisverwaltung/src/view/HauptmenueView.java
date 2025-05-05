package view;

import util.MenuLoader;
import util.MenuPrinter;

import java.util.HashMap;
import java.util.Scanner;

//teuert die Anzeige des Hauptmenüs und die Navigation zu den Untermenüs
public class HauptmenueView {

    private final Scanner scanner = new Scanner(System.in);


     // Startet das Hauptmenü der Vereinsverwaltung
     public void starten() {
         String eingabe;
         HashMap<String, String> hauptmenue = MenuLoader.loadMenu("hauptmenue.txt");

         do {
             MenuPrinter.printMenu(hauptmenue, "Vereins Hauptmenü");
             eingabe = scanner.nextLine().trim();

             switch (eingabe) {
                 case "1":
                     zeigeSubMenu("subMenu_Mitglieder.txt", "Mitglieder-Menü");
                     break;
                 case "2":
                     zeigeSubMenu("subMenu_Trainer.txt", "Trainer-Menü");
                     break;
                 case "3":
                     zeigeSubMenu("subMenu_Mannschaften.txt", "Mannschaften-Menü");
                     break;
                 case "x":
                     break;
                 default:
                     System.out.println("Ungültige Eingabe im Untermenü.");             }

         } while (!eingabe.equals("x"));
     }


     //Zeigt ein Untermenü basierend auf der gegebenen Datei und steuert die Auswahl
    private void zeigeSubMenu(String dateiName, String titel) {
        String auswahl;
        HashMap<String, String> submenu = MenuLoader.loadMenu(dateiName);

        do {
            MenuPrinter.printMenu(submenu, titel);
            auswahl = scanner.nextLine().trim();

            // Hier kommen später die spezifischen Methoden (z.B. Mitglied hinzufügen)
            switch (auswahl) {
                case "1":
                    System.out.println("[Option 1 ausgewählt]");
                    break;
                case "2":
                    System.out.println("[Option 2 ausgewählt]");
                    break;
                case "3":
                    System.out.println("[Option 3 ausgewählt]");
                    break;
                case "4":
                    System.out.println("[Option 4 ausgewählt]");
                    break;
                case "5":
                    System.out.println("[Option 5 ausgewählt]");
                    break;
                case "z":
                    break;
                default:
                    System.out.println("Ungültige Eingabe im Untermenü.");            }

        } while (!auswahl.equals("z"));
    }

}
