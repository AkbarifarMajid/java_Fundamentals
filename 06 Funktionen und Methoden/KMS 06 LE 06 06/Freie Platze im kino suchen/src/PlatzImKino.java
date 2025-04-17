import java.util.Random;

public class PlatzImKino {
    static Random myRandom = new Random();

    // Generiert zufällig belegte und freie Sitzplätze im Kino (L = frei, B = besetzt)
    public static String[][] creatSitzordnung(int reihen, int sitzePlatz) {
        String[][] kinoHalle = new String[reihen][sitzePlatz];
        //Random rand = new Random();

        String[] symbole = {"L", "B"};

        for (int reihe = 0; reihe < reihen; reihe++) {
            for (int platz = 0; platz < sitzePlatz; platz++) {
                kinoHalle[reihe][platz] = symbole[myRandom.nextInt(2)];
            }
        }
        return kinoHalle;
    }

    // Zeigt den Sitzplan mit Reihenbuchstaben und Spaltennummern an
    public static void showHalle(String[][] kinoHalle) {
        System.out.println("\t\tEin Blick in den Kinosaal");
        //Kopfzeile mit Spaltennummern
        System.out.print("      ");  // Platz für Buchstaben links
        for (int i = 0; i < kinoHalle[0].length; i++) {
            System.out.printf("%-4d", i + 1);  // Nummerierung der Spalten
        }
        System.out.println("\n");

        //Zeilen mit Buchstaben
        for (int reihe = 0; reihe < kinoHalle.length; reihe++) {
            char zeilenbuchstabe = (char) ('A' + reihe);  // A, B, C, ...
            System.out.printf("%-6c", zeilenbuchstabe);  // Linke Spalte

            for (int spalte = 0; spalte < kinoHalle[reihe].length; spalte++) {
                String platz = kinoHalle[reihe][spalte];

                // Farbe nach Wert
                if (platz.equals("B")) {
                    System.out.printf("\u001B[31m%-4s\u001B[0m", platz);
                } else {
                    System.out.printf("\u001B[32m%-4s\u001B[0m", platz);
                }
            }
            System.out.println();
        }
    }

    // Zählt und zeigt die Anzahl der freien Plätze pro Reihe an
    public static void leerSitzKontrolle(String[][] kino) {
        System.out.println("----------- Alle Frei plätze om Kino ------------------");
        for (int reihe = 0; reihe < kino.length; reihe++) {
            int frei = 0;
            for (int spalte = 0; spalte < kino[reihe].length; spalte++) {
                if (kino[reihe][spalte].equals("L")) {
                    frei++;
                }
            }
            char zeile = (char) ('A' + reihe);
            System.out.printf("%c --> %d Frei\n", zeile, frei);

        }
    }

    // Berechnet und zeigt die prozentuale Auslastung des gesamten Kinosaals
    public static void sitzBesetezBerechnen(String[][] kino) {
            int gesamt = 0;
            int besetzt = 0;

            for (String[] reihe : kino) {
                for (String platz : reihe) {
                    gesamt++;
                    if (platz.equals("B")) {
                        besetzt++;
                    }
                }
            }

        double prozent = (besetzt * 100.0) / gesamt;

        //Farbdisplay und visuelle Warnungen
        if (prozent < 50) {
            System.out.printf("\u001B[33m Geringe Auslastung: %.2f%%\u001B[0m\n", prozent);
        } else if (prozent >= 90) {
            System.out.printf("\u001B[31m Hohe Auslastung: %.2f%%\u001B[0m\n", prozent);
        } else {
            System.out.printf("Auslastung: %.2f%%\n", prozent);
        }

    }

    public static void main(String[] args) {

        //Kino-Halle definieren
        String[][] kinoHalle = creatSitzordnung(5, 8);

        // Kino-Halle anzeigen
        showHalle(kinoHalle);

        // Freie Plätze je Reihe anzeigen
        leerSitzKontrolle(kinoHalle);

        //  Prozentuale Auslastung berechnen und anzeigen
        sitzBesetezBerechnen(kinoHalle);

    }// End main
}// End Platz im Kino