package controller;

import model.Album;
import model.Track;
import view.ConsoleView;

import java.util.List;

public class AlbumController {

    //Eine Liste aller jemals erstellten Alben.
    private List<Album> mayAlbums;
    //kommunizieren mit ConsoleView, um Informationen anzuzeigen
    private ConsoleView myView;

    // Konstruktor
    public AlbumController(List<Album> allAlbums, ConsoleView view) {
        this.mayAlbums = allAlbums;
        this.myView = view;
    }

    // Neues Album erstellen
    public void neuAlbumErstellen(String albumId, String title, String singer) {
        Album meinNeuAlbum = new Album(albumId, title, singer);
        mayAlbums.add(meinNeuAlbum);
        myView.messegeAnzeige("Ein neues Album ist entstanden.");
    }

    // Neuen Track zu einem Album hinzufügen
    public void neuTrackInAlbumHinzugugen(String albumId, String trackId, String title, String fileName, int minutes, int seconds) {
        Album album = albumSuchenBeiID(albumId);
        if (album != null) {
            Track neuTrack = new Track(trackId, title, fileName, minutes, seconds);
            album.neuTrackHinzufuegen(neuTrack);
            myView.messegeAnzeige("Track " + neuTrack + " wurde zu Album " +albumId + " hinzugefügt.");
        } else {
            myView.errorAnzeige("Album mit ID " + albumId + " nicht gefunden.");
        }
    }

    // Track aus einem Album löschen
    public void trackAusAlbumLoschen(String albumId, String trackId) {
        Album album = albumSuchenBeiID(albumId);
        if (album != null) {
            boolean removed = album.trackLoeschen(trackId);
            if (removed) {
                myView.messegeAnzeige("Track " +trackId+ " wurde zu Album " +albumId + " gelöscht.");
            } else {
                myView.errorAnzeige("Track mit ID " + trackId + " nicht gefunden.");
            }
        } else {
            myView.errorAnzeige("Album mit ID " + albumId + " nicht gefunden.");
        }
    }

    // Ein ganzes Album löschen
    public void ganzAlbumLoschen(String albumId) {
        Album album = albumSuchenBeiID(albumId);
        if (album != null) {
            mayAlbums.remove(album);
            myView.messegeAnzeige("Album "+ albumId+ " ist gelöscht ");
        } else {
            myView.errorAnzeige("Album mit ID " + albumId + " nicht gefunden.");
        }
    }

    // Albuminformationen anzeigen
    public void infoAlbumAnzeigen(String albumId) {
        Album album = albumSuchenBeiID(albumId);
        if (album != null) {
            myView.albumInfoAnzeige(album);
        } else {
            myView.errorAnzeige("Album mit ID " + albumId + " nicht gefunden.");
        }
    }

    // Hilfsmethode: Album anhand der ID finden
    private Album albumSuchenBeiID(String albumId) {
        for (Album findAlbum : mayAlbums) {
            if (findAlbum.getAlbumId().equals(albumId)) {
                return findAlbum;
            }
        }
        return null;
    }



    public void alleAlbumsAnzeigen() {
        if (mayAlbums.isEmpty()) {
            myView.messegeAnzeige("Es gibt Keine Alben vorhanden.");
        } else {
            int cunter = 1;
            for (Album album : mayAlbums) {
                myView.messegeAnzeige(cunter +":\n"+album.toString());
                System.out.println("----------------------------------");
            }
        }
    }


    public void trackDetailsAnzeigen(String albumId, String trackId) {
        Album album = albumSuchenBeiID(albumId);
        if (album != null) {
            List<Track> tracks = album.getAllTracksInAlbum();
            for (Track tr : tracks) {
                if (tr.getTrackId().equals(trackId)) {
                    myView.messegeAnzeige("Trackdetails:");
                    myView.messegeAnzeige(tr.toString());
                    return;
                }
            }
            myView.messegeAnzeige("Track mit ID " + trackId + " nicht gefunden.");
        } else {
            myView.messegeAnzeige("Album mit ID " + albumId + " nicht gefunden.");
        }
    }


}
