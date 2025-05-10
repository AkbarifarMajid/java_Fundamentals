package view;

import model.Mannschaft;
import service.MannschaftService;
import model.*;

import java.util.List;
import java.util.Scanner;

// Diese Klasse bietet die Konsoleninteraktion für Mannschaften.
public class MannschafrView {

    private static final Scanner myScanner = new Scanner(System.in);

    // Zeigt alle Mannschaften
    public static void mannschaftenAnzeigen() {
        try {
            List<Mannschaft> mannschaften = MannschaftService.alleMannschaftenAnzeigen();

            if (mannschaften.isEmpty()) {
                System.out.println("Keine Mannschaften gefunden.");
            } else {
                System.out.println("Alle Mannschaften:");
                for (Mannschaft m : mannschaften) {
                    System.out.println(m);
                }
            }
        } catch (Exception error) {
            System.out.println(" beim Anzeigen der Mannschaften gibt es Problem: " + error.getMessage());
        }

    }// End mannschaftenAnzeigen


    // Neue Mannschaft hinzufügen
    public static void mannschaftHinzufuegen() {
        System.out.print("Name der Mannschaft: ");
        String name = myScanner.nextLine().trim();

        System.out.println("Verfügbare Altersgruppen: U13, U15, U17, U19, U23, ERWACHSENE");
        System.out.print("Liga der Mannschaft: ");
        String liga = myScanner.nextLine().trim();

        Mannschaft neueMannschaft = new Mannschaft(name, liga);
        boolean result = MannschaftService.mannschaftHinzufuegen(neueMannschaft);

        if (result) {
            System.out.println("Neu Mannschaft ist hinzugefügt!");
        } else {
            System.out.println("Mannschaft konnte nicht hinzugefügen.");
        }
    }// -- End mannschaftHinzufuegen


    // Fragt den Benutzer nach IDs und fügt ein Mitglied zur Mannschaft hinzu
    public static void mitgliedZurMannschaft() {
        try {
            System.out.print("ID der Mannschaft: ");
            int mannschaftId = Integer.parseInt(myScanner.nextLine().trim());

            System.out.print("ID des Mitglieds: ");
            int mitgliedId = Integer.parseInt(myScanner.nextLine().trim());

            boolean resultMi_Zur_Ma = MannschaftService.hinzu_MitgliedZurManschaft(mannschaftId,mitgliedId);

            if (resultMi_Zur_Ma) System.out.printf("Mitglied (ID: %d) wurde erfolgreich zur Mannschaft (ID: %d) hinzugefügt.%n", mitgliedId, mannschaftId);
            else System.out.println("Das Hinzufügen des Mitglieds zur Mannschaft ist fehlgeschlagen.");

        }catch (NumberFormatException error) {
            System.out.println("geben Sie eine gültige ID ein.");
        } catch (Exception error) {
            System.out.println("Es gab ein unerwartetes Problem: " + error.getMessage());
        }

    }// End mitgliedZurMannschaft


    // Fragt den Trainer nach ID und fügt Traner zur Mannschaft hinzu
    public static void trainerZurMannschaft(){

        try {
            System.out.print("ID des Trainer: ");
            int trainerId = Integer.parseInt(myScanner.nextLine().trim());

            System.out.print("ID der Mannschaft: ");
            int mannschaftId = Integer.parseInt(myScanner.nextLine().trim());

            boolean resultTr_Zur_Ma = MannschaftService.hinzu_TrainerZurManschaft(trainerId,mannschaftId);

            if(resultTr_Zur_Ma) System.out.printf("Trainer (ID: %d) wurde erfolgreich zur Mannschaft (ID: %d) hinzugefügt.%n", trainerId, mannschaftId);
            else System.out.println("Das Hinzufügen des Trainer zur Mannschaft ist fehlgeschlagen.");

        }catch (NumberFormatException error){
            System.out.println("geben Sie eine gültige ID ein.");
        }catch (Exception error) {
            System.out.println("Es gab ein unerwartetes Problem: " + error.getMessage());
        }

    }// End trainerZurMannschaft


    // Fragt nach IDs und entfernt ein Mitglied aus einer Mannschaft
    public static void loschen_Mi_Aus_Manschaft() {
        try {
            System.out.print("ID der Mannschaft: ");
            int mannschaftId = Integer.parseInt(myScanner.nextLine().trim());

            System.out.print("ID des Mitglieds: ");
            int mitgliedId = Integer.parseInt(myScanner.nextLine().trim());

            boolean result_Loschen = MannschaftService.loschen_Mi_Aus_Manschaft(mitgliedId, mannschaftId);

            if (result_Loschen) {
                System.out.printf("Mitglied (ID: %d) wurde erfolgreich aus Mannschaft (ID: %d) entfernt.%n", mitgliedId, mannschaftId);
            } else {
                System.out.println("Mitglied konnte nicht entfernt werden.");
            }

        } catch (NumberFormatException error) {
            System.out.println("Geben Sie ein gültige ID ein.");
        } catch (Exception error) {
            System.out.println("Ein unerwarteter Fehler ist aufgetreten: " + error.getMessage());
        }
    }// End loschen_Mi_Aus_Manschaft


    // Fragt nach IDs und entfernt einen Trainer aus einer Mannschaft
    public static void loschen_Tra_Aus_Manschaft() {
        try {
            System.out.print("ID der Mannschaft: ");
            int mannschaftId = Integer.parseInt(myScanner.nextLine().trim());

            System.out.print("ID des Trainers: ");
            int trainerId = Integer.parseInt(myScanner.nextLine().trim());

            boolean result_Loschen = MannschaftService.loschen_Tra_Aus_Manschaft(trainerId, mannschaftId);

            if (result_Loschen) {
                System.out.printf("Trainer (ID: %d) wurde erfolgreich aus Mannschaft (ID: %d) entfernt.%n", trainerId, mannschaftId);
            } else {
                System.out.println("Trainer konnte nicht entfernt werden.");
            }

        } catch (NumberFormatException e) {
            System.out.println("geben Sie ein gültige ID ein.");
        } catch (Exception e) {
            System.out.println("Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage());
        }
    }// End loschen_Tra_Aus_Manschaft


    // Fragt nach ID und löscht eine Mannschaft nach Bestätigung
    public static void Loeschen_mannschaft() {
        try {
            System.out.print("ID der Mannschaft, die gelöscht werden soll: ");
            int id_Mannschaft = Integer.parseInt(myScanner.nextLine().trim());

            // Mannschaft aus Datenbank laden
            Mannschaft mannschaft = MannschaftService.suchenMannschaft(id_Mannschaft);
            if (mannschaft == null) {
                System.out.println("Mannschaft mit dieser ID wurde nicht gefunden.");
                return;
            }

            // Mannschaftsdaten anzeigen
            System.out.println("\nannschaftsdaten:");
            System.out.println(mannschaft);


            System.out.print("Sind Sie sicher, dass Sie diese Mannschaft löschen möchten? (j/n): ");
            String bestaetigung = myScanner.nextLine().trim().toLowerCase();


            if (bestaetigung.equals("j")) {
                boolean result_Loschen = MannschaftService.loschen_Mannschaft(id_Mannschaft);
                if (result_Loschen) {
                    System.out.println("Mannschaft mit ID "+ id_Mannschaft+ " wurde gelöscht.");
                } else {
                    System.out.println("Mannschaft konnte nicht gelöscht werden.");
                }
            } else {
                System.out.println("Löschvorgang abgebrochen.");
            }

        } catch (NumberFormatException error) {
            System.out.println("geben Sie eine gültige ID ein.");
        } catch (Exception error) {
            System.out.println("Ein Fehler ist aufgetreten: " + error.getMessage());
        }
    } // End Loeschen_mannschaft


    // Zeigt alle Mitglieder einer bestimmten Mannschaft anhand ihrer ID
    public static void alleMitgliederInMannschaft() {
        try {
            System.out.print("ID der Mannschaft: ");
            int id = Integer.parseInt(myScanner.nextLine().trim());

            Mannschaft mannschaft = MannschaftService.suchenMannschaft(id);
            if (mannschaft == null) {
                System.out.println("Mannschaft mit dieser ID " + id + " existiert nicht.");
                return;
            }

            List<Mitglied> mitglieder = mannschaft.getMitglieder();

            if (mitglieder.isEmpty()) {
                System.out.println("Diese Mannschaft hat keine Mitglieder.");
            } else {
                System.out.println("Mitglieder der Mannschaft \"" + mannschaft.getName() + "\":");
                for (Mitglied mitgli : mitglieder) {
                    System.out.println(mitgli);
                }
            }

        } catch (NumberFormatException error) {
            System.out.println("geben Sie eine gültige ID ein.");
        }
    }// End alleMitgliederInMannschaft


    // Zeigt alle Trainer einer bestimmten Mannschaft anhand ihrer ID
    public static void alleTrainerInMannschaft() {
        try {
            System.out.print("ID der Mannschaft: ");
            int id = Integer.parseInt(myScanner.nextLine().trim());

            Mannschaft mannschaft = MannschaftService.suchenMannschaft(id);
            if (mannschaft == null) {
                System.out.println("Mannschaft mit dieser ID " + id + " existiert nicht.");
                return;
            }

            List<Trainer> trainer = mannschaft.getTrainerListe();

            if (trainer.isEmpty()) {
                System.out.println("Diese Mannschaft hat keine Trainer.");
            } else {
                System.out.println("Trainer der Mannschaft \"" + mannschaft.getName() + "\":");
                for (Trainer train : trainer) {
                    System.out.println(train);
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("geben Sie eine gültige ID ein.");
        }
    }// End alleTrainerInMannschaft

}