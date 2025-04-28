
import model.Album;
import controller.AlbumController;
import view.ConsoleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    //Scanner
    static Scanner myScanner = new Scanner(System.in);


    // Hilfsmethode zur sicheren Eingabe von Ganzzahlen
    private static int readInt() {
        while (!myScanner.hasNextInt()) {
            System.out.println("Bitte eine Zahl eingeben:");
            myScanner.next();
        }
        int zahl = myScanner.nextInt();
        myScanner.nextLine(); // Puffer leeren
        return zahl;
    }


    // Untermenü: Hinzufügen
    private static void submenuHinzufuegen(AlbumController controller) {
        boolean back = false;
        while (!back) {

            System.out.println("\n-------- Hinzufügen --------");
            System.out.println("1. Neues Album erstellen");
            System.out.println("2. Neuen Track zu einem Album hinzufügen");
            System.out.println("3. Zurück zum Hauptmenü");
            System.out.print("Bitte wählen Sie 1,2 oder 3: ");

            int choice = readInt();
            System.out.println("--------------------------------");

            switch (choice) {
               // Neues Album erstellen
                case 1:
                    System.out.print("Album-ID: ");
                    String albumId = myScanner.nextLine();

                    System.out.print("Titel des Albums: ");
                    String title = myScanner.nextLine();

                    System.out.print("Name des Sängers: ");
                    String singer = myScanner.nextLine();

                    controller.neuAlbumErstellen(albumId, title, singer);
                    break;
                // Neuen Track zu einem Album hinzufügen
                case 2:
                    System.out.print("Album-ID : ");
                    String albumIdForTrack = myScanner.nextLine();

                    System.out.print("Track-ID: ");
                    String trackId = myScanner.nextLine();

                    System.out.print("Titel des Tracks: ");
                    String trackTitle = myScanner.nextLine();

                    System.out.print("Date Type Track: ");
                    String fileName = myScanner.nextLine();

                    System.out.print("Minuten der Dauer: ");
                    int minutes = readInt();

                    System.out.print("Sekunden der Dauer: ");
                    int seconds = readInt();

                    controller.neuTrackInAlbumHinzugugen(albumIdForTrack, trackId, trackTitle, fileName, minutes, seconds);
                    break;

                //Zurück zum Hauptmenü
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Bitte versuchen Sie es erneut.");
            }
        }
    }

    // Untermenü: Löschen
    private static void submenuLoeschen(AlbumController controller) {
        boolean back = false;
        while (!back) {
            System.out.println("\n -----------  Löschen -----------");
            System.out.println("1. Track aus einem Album löschen");
            System.out.println("2. Ganzes Album löschen");
            System.out.println("3. Zurück zum Hauptmenü");
            System.out.print("Bitte wählen Sie: 1,2 oder 3 ");

            int choice = readInt();
            System.out.println("--------------------------------");

            switch (choice) {
                //Track aus einem Album löschen
                case 1:
                    System.out.print("Album-ID: ");
                    String albumId = myScanner.nextLine();

                    System.out.print("Track-ID: ");
                    String trackId = myScanner.nextLine();

                    controller.trackAusAlbumLoschen(albumId, trackId);
                    break;

                //Ganzes Album löschen
                case 2:
                    System.out.print("Album-ID : ");
                    String albumIdZuLoschen = myScanner.nextLine();

                    controller.ganzAlbumLoschen(albumIdZuLoschen);
                    break;

                //Zurück zum Hauptmenü
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Bitte versuchen Sie es erneut.");
            }
        }
    }

    // Untermenü: Anzeigen
    private static void submenuAnzeigen(AlbumController controller) {
        boolean back = false;
        while (!back) {
            System.out.println("\n ------------- Anzeigen ----------------");
            System.out.println("1. Informationen eines Albums anzeigen");
            System.out.println("2. Alle Alben anzeigen");
            System.out.println("3. Informationen eines bestimmten Tracks anzeigen");
            System.out.println("4. Zurück zum Hauptmenü");
            System.out.print("Bitte wählen Sie 1,2,3 oder 4: ");

            int choice = readInt();
            System.out.println("--------------------------------");

            switch (choice) {

                //Informationen eines Albums anzeigen
                case 1:

                    System.out.print("Album-ID : ");
                    String albumId = myScanner.nextLine();

                    controller.infoAlbumAnzeigen(albumId);
                    break;

                //Alle Alben anzeigen
                case 2:
                    controller.alleAlbumsAnzeigen();
                    break;

                //Informationen eines bestimmten Tracks anzeigen
                case 3:
                    System.out.print("Album-ID : ");
                    String albumIdTrack = myScanner.nextLine();

                    System.out.print("Track-ID : ");
                    String trackId = myScanner.nextLine();

                    controller.trackDetailsAnzeigen(albumIdTrack, trackId);
                    break;

                // Zurück zum Hauptmenü
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Bitte versuchen Sie es erneut.");
            }
        }
    }


    //Main Program
    public static void main(String[] args) {


        List<Album> allAlbums = new ArrayList<>();
        ConsoleView myView = new ConsoleView();
        AlbumController myController = new AlbumController(allAlbums, myView);

        boolean beendedProgram = false;

        while (!beendedProgram) {

            System.out.println("1. Hinzufügen");
            System.out.println("2. Löschen");
            System.out.println("3. Anzeigen");
            System.out.println("4. beenden");
            System.out.print("Bitte wählen Sie 1,2,3 oder 4: ");

            int menuWahl = readInt();
            System.out.println("----------------------------");

            switch (menuWahl) {
                //Hinzufügen
                case 1:
                    submenuHinzufuegen(myController);
                    break;
                 //Löschen
                case 2:
                    submenuLoeschen(myController);
                    break;
                //Anzeigen
                case 3:
                    submenuAnzeigen(myController);
                    break;
                //beenden
                case 4:
                    System.out.println("Vielen Dank für Ihre Wahl.");
                    beendedProgram = true;
                    break;
                default:
                    System.out.println("Bitte versuchen Sie es erneut.");
            }

        }

        myScanner.close();

    } // End main


}//Enad Main
