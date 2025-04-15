import java.util.Random;

public class Nummerfolge {

    public static void main(String[] args) {
        int[] daten = new int[30]; //
        Random random = new Random();

        for (int i = 0; i < daten.length; i++) {
            daten[i] = random.nextInt(10); //
        }

        //
        System.out.print("Datenarray: ");
        for (int zahl : daten) {
            System.out.print(zahl + " ");
        }


    }// End main
}// End Nummerfolge