import java.util.Scanner;
import java.util.Random;

public class Lottoziehungen {
    static Scanner myScanner = new Scanner(System.in);
    static Random myRandom = new Random();

    // Fragt die Anzahl der Lottoziehungen vom Benutzer ab und überprüft die Eingabe
    public static int getAnzahlZiehungen() {
        int anzahl = 0;
        System.out.print("Wie viele Lottoziehungen sollen simuliert werden? ");

        while (true) {
            if (!myScanner.hasNextInt()) {
                System.out.println("Bitte geben Sie eine gueltige Zahl ein.");
                myScanner.next(); // ungültige Eingabe verwerfen
                continue;
            }

            anzahl = myScanner.nextInt();

            if (anzahl <= 0) {
                System.out.println("Die Anzahl muss größer als 0 sein.");
                continue;
            }

            break;
        }

        return anzahl;
    }

    // Führt die angegebene Anzahl an Ziehungen durch und zählt, wie oft jede Zahl gezogen wurde
    public static int[] fuehreZiehungenDurch(int anzahlZiehungen){
        int[] statik = new int[45]; // Index 0 = Zahl 1, Index 44 = Zahl 45

        for(int i = 0 ; i < anzahlZiehungen ; i++){
            int gezogezahl = myRandom.nextInt(45) + 1; // Zahl zwischen 1 und 45
            statik[gezogezahl -1]++; // Zähle die gezogene Zahl
        }
        return statik;
    }

    // Gibt alle gezogenen Zahlen aus, die mindestens einmal gezogen wurden, inkl. Prozentanteil
    public static void zeigeStatistik(int[] statistik, int anzahlZiehungen) {

    System.out.println("\nHäufigkeit der gezogenen Zahlen (nur Zahlen mit mindestens 1 Treffer):");
    for (int i = 0; i < statistik.length; i++) {
        int anzahl = statistik[i];
        if (anzahl > 0) {
            int zahl = i + 1;
            double prozent = (anzahl * 100.0) / anzahlZiehungen;
            //System.out.println(anzahlZiehungen);
            System.out.printf("Die Zahl %2d wurde %2d-mal gezogen (%.2f%%)\n", zahl, anzahl, prozent);
        }
    }
}


    public static void main(String[] args) {

        int anzahlZiehungen = getAnzahlZiehungen();                  // Benutzer gibt Anzahl der Ziehungen ein
        int[] statistik = fuehreZiehungenDurch(anzahlZiehungen);     // Zufällige Ziehungen durchführen
        zeigeStatistik(statistik, anzahlZiehungen);                  // Ergebnis anzeigen

        myScanner.close();
    }// End main
}// End Lottoziehungen