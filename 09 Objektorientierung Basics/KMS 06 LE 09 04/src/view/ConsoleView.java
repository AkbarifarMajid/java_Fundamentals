package view;

import model.Album;
import model.Track;
import java.util.List;

public class ConsoleView {

    // Albuminformationen und Tracks anzeigen
    public void albumInfoAnzeige(Album album) {

        if (album != null) {
            System.out.println("\nAlbumdetails:");
            System.out.println(album.toString());
        } else {
            System.out.println("Das gewünschte Album wurde nicht gefunden.");
        }
    }

    // Liste der Tracks eines Albums anzeigen
    public void trackListAnzeige(Album album) {
        if (album != null) {
            List<Track> tracks = album.getAllTracksInAlbum();
            System.out.println("\nAlle Track im Album " + album.getTitleAlbum() + ":");

            if (tracks.isEmpty()) {
                System.out.println("  (Keine Tracks gefunden)");
            } else {
                for (Track tr : tracks) {
                    int cunter = 1 ;
                    System.out.println(cunter + ": \n" + tr.toString());
                    cunter++;
                }
            }
        } else {
            System.out.println("Das gewünschte Album wurde nicht gefunden.");
        }
    }

    // Eine einfache Erfolgsmeldung anzeigen
    public void messegeAnzeige(String message) {
        if (message != null && !message.isEmpty()) {
            System.out.println(message);
        } else {
            System.out.println("Es gibt Keine Nachricht zum Anzeigen.");
        }
    }

    // Eine Fehlermeldung anzeigen
    public void errorAnzeige(String errorMessage) {
        if (errorMessage != null && !errorMessage.isEmpty()) {
            System.err.println("Achtung: " + errorMessage);
        } else {
            System.err.println("Unbekannter Fehler.");
        }
    }


    // Alle Alben anzeigen
    public void alleAlbumsAnzeigen(List<Album> albums) {
        if (albums.isEmpty()) {
            System.out.println("Es gibt Keine Alben vorhanden.");
        } else {
            System.out.println("\nListe aller Alben:");
            int cunter = 1;
            for (Album album : albums) {
                System.out.println(cunter + ":\n" + album.toString());
                cunter++;
                System.out.println("----------------------------------");
            }
        }
    }



}
