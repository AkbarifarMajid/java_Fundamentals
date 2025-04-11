import java.util.Scanner;
import java.util.Random;

public class SimulationFragen {
    static Scanner myScanner = new Scanner(System.in);
    static Random myRandom = new Random();

    // Fragt die Anzahl der Testpersonen vom Benutzer ab und überprüft die Eingabe.
    public static int getAnzahlPersonen() {
        int anzahl = 0;
        System.out.print("Wie viele Testpersonen sollen simuliert werden? ");

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

    // Erzeugt ein Array mit zufälligen Bewertungen zwischen 1 und 3.
    public static int[] creatWertRandom(int anzahlPersonen ){
        int[] bewertung = new int[anzahlPersonen];

        for (int i = 0; i < anzahlPersonen; i++) {
            bewertung[i] = myRandom.nextInt(3) + 1;
        }
        return bewertung;

    }

    // Zählt die Stimmen für jede Bewertungsstufe und gibt Prozentwerte aus.
    public static void zaelenStimmen(int[] wertRandom){
        int wert1 =0 ,wert2 =0, wert3 =0;

        for (int i = 0; i < wertRandom.length; i++) {
            if (wertRandom[i] == 1) {
                wert1++;
            } else if (wertRandom[i] == 2) {
                wert2++;
            } else if (wertRandom[i] == 3) {
                wert3++;
            }
        }
       // int gesamt = wertRandom.length;

        // Prozent Rechnen
        double prozent1 = (wert3 * 100.0) / wertRandom.length;
        double prozent2 = (wert2 * 100.0) / wertRandom.length;
        double prozent3 = (wert1 * 100.0) / wertRandom.length;


        System.out.println("\n\nAuswertung der Stimmen:\n");
        System.out.printf("Hervorragend  = %d Stimmen (%.1f%%)\n", wert3, prozent1);
        System.out.printf("Akzeptabel = %d Stimmen (%.1f%%)\n", wert2, prozent2);
        System.out.printf("Nicht empfehlenwert = %d Stimmen (%.1f%%)\n", wert1, prozent3);

    }

    public static void main(String[] args) {
        int anzahlPersonen = getAnzahlPersonen();

        int [] wertRandom = creatWertRandom(anzahlPersonen);

        //Stimmen anzeigen
        System.out.print("Bewertungen: ");
        for (int i = 0; i < wertRandom.length; i++) {
            System.out.print(wertRandom[i] + " ");
        }
        zaelenStimmen(wertRandom);

        myScanner.close();
    }// End main
}// End SimulationFragen