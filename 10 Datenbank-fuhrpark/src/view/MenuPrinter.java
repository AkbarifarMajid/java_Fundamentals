package view;

import java.util.Map;

// Druckt ein Menü basierend auf einer Map.

public class MenuPrinter {

    public static void printMenu(Map<String, String> menuMap, String titel) {
        System.out.println("\n*****************   " + titel.toUpperCase() + "   ****************");

        for (Map.Entry<String, String> eintrag : menuMap.entrySet()) {
            String key = eintrag.getKey().trim();
            String text = eintrag.getValue();

            System.out.printf("%2s - %s%n", key, text);

        }
    }// End printMenu
}
