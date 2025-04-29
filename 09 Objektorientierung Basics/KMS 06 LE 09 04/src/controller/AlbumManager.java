package controller;

import model.Album;
import model.Track;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class AlbumManager {

    public AlbumManager() {

    }

    // Dateiverarbeitungsklassen (FileReader, BufferedReader, FileWriter, BufferedWriter, IOException)


    public void saveAlbumsToFile(List<Album> albums, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Album album : albums) {
                writer.write(String.join(";", "ALBUM", album.getAlbumId(), album.getTitleAlbum(), album.getSingerName()));
                writer.newLine();
                for (Track track : album.getAllTracksInAlbum()) {
                    writer.write(String.join(";", "TRACK", track.getTrackId(), track.getTitleTrack(), track.getTypeFile(),
                            String.valueOf(track.getMinutes()), String.valueOf(track.getSeconds())));
                    writer.newLine();
                }
            }
            System.out.println("Datei gespeichert: " + fileName);
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }






    public List<Album> loadAlbumsFromFile(String fileName) {
        List<Album> albums = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Album currentAlbum = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                switch (parts[0]) {
                    case "ALBUM":
                        currentAlbum = new Album(parts[1], parts[2], parts[3]);
                        albums.add(currentAlbum);
                        break;
                    case "TRACK":
                        if (currentAlbum != null) {
                            currentAlbum.neuTrackHinzufuegen(new Track(parts[1], parts[2], parts[3],
                                    Integer.parseInt(parts[4]),
                                    Integer.parseInt(parts[5])));
                        }
                        break;
                }
            }
            System.out.println("Datei geladen: " + fileName);
        } catch (Exception e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
        }
        return albums;
    }


}