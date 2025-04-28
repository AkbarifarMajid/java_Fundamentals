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


}
