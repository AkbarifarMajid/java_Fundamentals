package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

// Liest eine Menü-Datei ale .txt und gibt sie als LinkedHashMap zurück.

public class MenuLoader {

    public static LinkedHashMap<String, String> loadMenu(String filename) {
        LinkedHashMap<String, String> menuMap = new LinkedHashMap<>();

        try (InputStream is = MenuLoader.class.getClassLoader().getResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String zeile;
            while ((zeile = reader.readLine()) != null) {
                String[] teile = zeile.split(";");
                if (teile.length == 2) {
                    menuMap.put(teile[0].trim(), teile[1].trim());
                }
            }

        } catch (IOException | NullPointerException e) {
            System.out.println("beim Laden des Menüs gibt es Problem: " + filename);
        }

        return menuMap;
    }// End loadMenu
}