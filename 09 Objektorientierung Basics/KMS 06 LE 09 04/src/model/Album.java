package model;

import java.util.ArrayList;
import java.util.List;

public class Album {

    private String albumId;
    private String titleAlbum;
    private String singerName;
    private List<Track> allTracksInAlbum;

    // Constructor
    public Album(String albumId, String title, String singer) {
        this.albumId = albumId;
        this.titleAlbum = title;
        this.singerName = singer;
        this.allTracksInAlbum = new ArrayList<>();
    }

    // Getter und Setter
    public String getAlbumId() { return albumId; }
    public String getTitleAlbum() { return titleAlbum; }
    public String getSingerName() { return singerName; }
    public List<Track> getAllTracksInAlbum() { return allTracksInAlbum; }
    public void setAlbumId(String albumId) {

        if (albumId != null && !albumId.isEmpty()) {
            this.albumId = albumId;
        } else {
            System.out.println("Fehler beim Erstellen der neuen Album-ID. Unbekannt gespeichert.");
            this.albumId = "Unbekannt";
        }
    }


    public void setSingerName(String singerName) {
        if (singerName != null && !singerName.isEmpty()) {
            this.singerName = singerName;
        } else {
            System.out.println("Fehler beim Erstellen der neuen Singer Name. Unbekannt gespeichert.");
            this.singerName = "Unbekannt Singer";
        }
    }

    public void setTitleAlbum(String titleAlbum) {
        if (titleAlbum != null && !titleAlbum.isEmpty()) {
            this.titleAlbum = titleAlbum;
        } else {
            System.out.println("Fehler beim Erstellen der neuen Title Album. Unbekannt gespeichert.");
            this.titleAlbum = "Unbekannt title Album";
        }
    }


    public void setAllTracksInAlbum(List<Track> allTracksInAlbum) {
        if (allTracksInAlbum != null) {
            this.allTracksInAlbum = allTracksInAlbum;
        } else {
            System.out.println("Trackliste darf nicht null sein. Keine Änderung vorgenommen.");
        }
    }

    // Einen neuen Track hinzufügen
    public void neuTrackHinzufuegen(Track track) {
        if (track != null) {
            allTracksInAlbum.add(track);
        } else {
            System.out.println(" Fehler beim Hinzufügen des neuen Track.");
        }
    }

    // Track löschen
    public boolean trackLoeschen(String trackId) {
        return allTracksInAlbum.removeIf(mytrack -> mytrack.getTrackId().equals(trackId));
    }

    // Gesamtdauer des Albums berechnen
    public double totalTimeAlbum() {
        double summe = 0;
        for (Track tr : allTracksInAlbum) {
            summe += tr.GenaueZeitberechnungnMinute();
        }
        return summe;
    }


    // Albuminformationen anzeigen
    @Override
    public String toString() {

        String albumInfo = "Album: " + titleAlbum + " von " + singerName + "\n";
        albumInfo += "Album-ID: " + albumId + "\n";
        albumInfo += "Alle Tracks:\n";

        if (allTracksInAlbum.isEmpty()) {
            albumInfo += "  (Keine Tracks vorhanden)\n";
        } else {
            int cunter = 1;
            for (Track t : allTracksInAlbum) {
                albumInfo += cunter + ":\n" + t.toString() + "\n";
                cunter++;
            }
        }

       // System.out.println(" -----------  Gesamte Dauer ---------------");
        albumInfo += "\n -----------  Gesamte Dauer --------------- \nGesamtdauer: " + String.format("%.2f", totalTimeAlbum()) + " Minuten\n";
        return albumInfo;
    }

}
