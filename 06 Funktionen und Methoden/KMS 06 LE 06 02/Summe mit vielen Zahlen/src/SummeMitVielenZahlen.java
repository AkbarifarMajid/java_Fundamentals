//import java.util.Scanner;

public class SummeMitVielenZahlen {
    //static Scanner myScanner = new Scanner(System.in);

    // Diese Methode berechnet die Summe beliebig vieler Zahlen
    public static double summeRechnen(double... beliebig) {
        double summe = 0;
        for (double zahl : beliebig) {
            summe += zahl;
        }
        System.out.println("Anzahl: " + beliebig.length);
        System.out.printf("Summe %.2f: ", summe);
        return summe;
    }

//-------------------------------------
    public static void main(String[] args) {
        System.out.println("Berechnung der Summe:");
        summeRechnen(100.5, 200, 50.25, 25, 2.2);
        System.out.println("\n------------------------------");
        summeRechnen(100.5, 200, 50.25, 25);
        System.out.println("\n------------------------------");
        summeRechnen(100.5, 200, 50.25);

    }// End main

}//End Summe mit Vielen Zahlen
