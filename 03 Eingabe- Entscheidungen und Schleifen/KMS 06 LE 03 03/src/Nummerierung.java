import java.util.Scanner;

public class Nummerierung {
    static Scanner myScanner = new Scanner(System.in);

    // _____________   Methode zur Überprüfung und Rückgabe einer Kommazahl (Preis) -----------
    public static double readnummer(String text){
        System.out.print(text);

        while(true){
            if (myScanner.hasNextDouble()) {
                return myScanner.nextDouble();
            } else {
                System.out.println("FALSCH Eingabe!!");
                myScanner.next();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("willkommen in Nummerierung Programm .");
        double startNummer = readnummer("Bitte geben Sie Start Nummer: ");
        double endNummer = readnummer("Bitte geben Sie End Nummer: ");
        double stepNummer = readnummer("Bitte geben Sie die Schrittweite ein: ");

        // Kontrolle: Wenn StarNummber > EndNummber ist
        if(startNummer > endNummer){
            System.out.println("!!!!!!!!!!!!!!!!!!");
            System.out.println("Startnummer ist größer als Endnummer. Werte werden getauscht.");
            double tempBox = startNummer;
            startNummer = endNummer;
            endNummer =tempBox;
        }

        System.out.println("Ihre einfache Nummerliste:");

        double i = startNummer;
        while(i <= endNummer){
            System.out.printf("%.1f  ",i);
            i += stepNummer;
        }

    }//End main

}//End Nummerierung