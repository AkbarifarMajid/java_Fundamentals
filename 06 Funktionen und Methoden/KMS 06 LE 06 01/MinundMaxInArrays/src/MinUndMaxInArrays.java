import java.util.Scanner;
import java.util.Random;

public class MinUndMaxInArrays {
    static Scanner myScanner = new Scanner(System.in);

    // Ermittelt das Minimum in einem Array
    public static double minimumZahl(double[] zahlen) {
        double min = zahlen[0];
        for (int i = 1; i < zahlen.length; i++) {
            if (zahlen[i] < min) {
                min = zahlen[i];
            }
        }
        return min;
    }

    // Ermittelt das Maximum in einem Array
    public static double maxzimumZahl(double[] zahlen) {
        double max = zahlen[0];
        for (int i = 1; i < zahlen.length; i++) {
            if (zahlen[i] > max) {
                max = zahlen[i];
            }
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println("Wellcome bei Min und Max in Arrays");

        double [] wertZhal = new double[100];
        Random myRandom = new Random();

        for (int i = 0; i < wertZhal.length; i++) {
            wertZhal[i] = 0.00 + myRandom.nextDouble(999.99); // Bereich: 100 - 999
        }


        for (int i = 0; i < wertZhal.length; i++) {
            System.out.printf("%8.2f", wertZhal[i]);
            if ((i + 1) % 10 == 0) {
                System.out.println(); // Neue Zeile nach 10 Werten
            }
        }


        double minimum = minimumZahl(wertZhal);
        System.out.printf("\nMinimum %.2f", minimum);
        double maximum = maxzimumZahl(wertZhal);
        System.out.printf("\nMinimum %.2f", maximum);



    }//End main
}// End MinUndMaxInArrays