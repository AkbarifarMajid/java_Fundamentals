import controller.AlbumController;
import controller.MenuController;
import controller.AlbumManager;
import model.Album;
import view.ConsoleView;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Scanner für Benutzereingaben
    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {

        //  Erstellen des Datei-Managers
        AlbumManager fileManager = new AlbumManager();

        //  Laden der vorhandenen Alben aus der Datei
        List<Album> allAlbums = fileManager.loadAlbumsFromFile("albums.txt");

        //  Erstellen der View und des AlbumControllers
        ConsoleView myView = new ConsoleView();
        AlbumController myController = new AlbumController(allAlbums, myView);

        //  Erstellen des MenuControllers und Starten des Menüs
        MenuController myMenuController = new MenuController();
        myMenuController.startMenu(myController);

        //  Speichern der aktuellen Albenliste in der Datei nach Programmende
        fileManager.saveAlbumsToFile(allAlbums, "albums.txt");

        //  Schließen des Scanners
        myScanner.close();
    }
}
