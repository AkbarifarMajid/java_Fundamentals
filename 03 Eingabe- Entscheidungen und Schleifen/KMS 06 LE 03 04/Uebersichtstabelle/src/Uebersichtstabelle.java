import java.util.Scanner;
import java.text.DecimalFormat;

public class Uebersichtstabelle {
    static Scanner myScanner = new Scanner(System.in);

    // Methode zur sicheren Eingabe eines Preises (Kommazahl)
    public static double readPreis(String text){
        System.out.print(text);

        while(true){
            if (myScanner.hasNextDouble()) {
                return myScanner.nextDouble();
            } else {
                System.out.println("FALSCH Eingabe! Bitte erneut versuchen.");
                myScanner.next();
            }
        }
    }

    // Methode zur Berechnung des Schritts (step)
    public static double stepRechnen(double start, double end){
        return (end - start) /10;

    }

    //Methode zum Anzeigen  Preis- und Mengentabelle
    public static void tabeleAnzeige(double startPreis, double endPreis, double stepPreis){

        // Formatierung vorbereiten
        DecimalFormat df = new DecimalFormat("0.#");

        // Tabellenkopf
        System.out.println("------------------------------------------");
        System.out.println("                                  Einzelpreise\n");
        System.out.print("     ");
        for (double preis = startPreis; preis < endPreis; preis += stepPreis) {
            System.out.printf("%6s", df.format(preis));
        }
        System.out.println();

        // Tabelleninhalt für Stückzahlen 10 bis 100
        for (int i = 10; i <= 100; i += 10) {
            System.out.printf("%5s", i + " " );
            for (double j = startPreis; j < endPreis; j += stepPreis) {
                double summe = i * j;
                System.out.printf("%6d", (int)Math.round(summe));
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        System.out.println("willkommen in Übersichtstabelle Programm .");

        // Eingabe von Minimum und Maximum Preis
        double startPreis = readPreis("Bitte geben Sie Minimum Prise: ");
        double endPreis = readPreis("Bitte geben Sie Maximum Preis: ");

        // Falls der Startpreis größer ist, tauschen
        if(startPreis > endPreis){
            System.out.println("Startpreis ist größer als Endpreis. Werte werden getauscht.");
            double tempBox = startPreis;
            startPreis = endPreis;
            endPreis =tempBox;
        }

        // Schrittweite berechnen
        double stepPreis = stepRechnen(startPreis,endPreis);

        tabeleAnzeige(startPreis,endPreis,stepPreis);

        myScanner.close();

    }//End main
}// End Uebersichtstabelle