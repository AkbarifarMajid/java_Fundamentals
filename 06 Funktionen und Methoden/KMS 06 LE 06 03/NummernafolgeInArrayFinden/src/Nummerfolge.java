import java.util.Random;
import java.util.Scanner;

public class Nummerfolge {
    static Scanner myScanner = new Scanner(System.in);

    // Erstellt ein Array mit 30 Zufallszahlen zwischen 0 und 9
    public static int[] creatRandomdata(){
        int[] randomData = new int[15];
        Random myRandom = new Random();

        for (int i = 0; i < randomData.length; i++) {
            randomData[i] = myRandom.nextInt(10);
        }

        System.out.print("Datenarray: ");
        for (int zahl : randomData) {
            System.out.print(zahl + " ");
        }
        System.out.println();
        return randomData;
    }

    // Liest eine gültige vierstellige Prüfsequenz vom Benutzer ein
    public static int[] readMyZahl(){
        int[] mySequenz =new int[4];

        for(int i = 0; i < 4 ; i++){
            System.out.printf("Nummner %d: ",i+1);
            if(myScanner.hasNextInt()){
                mySequenz[i] = myScanner.nextInt();
            }
            else{
                System.out.println("Falsche Eingabe ");
                i--;
                myScanner.next();
            }
        }
        System.out.print("\nEingegebene Prüfsequenz: ");
        for (int zahl : mySequenz) {
            System.out.print(zahl + " ");
        }
        return mySequenz;
    }

    // Prüft, ob die eingegebene Sequenz im Datenarray vorhanden ist
    public static boolean sequenzFolge(int[] randomData, int[] folgenSequenz) {
        for (int i = 0; i < randomData.length - 4; i++) {
            if (randomData[i] == folgenSequenz[0] &&
                    randomData[i + 1] == folgenSequenz[1] &&
                    randomData[i + 2] == folgenSequenz[2] &&
                    randomData[i+ 3] == folgenSequenz[3]) {
                return true;
            }
        }
        return false;
    }

    // Hauptmethode
    public static void main(String[] args) {

        int [] randomData = creatRandomdata();
        int[] folgenSequenz = readMyZahl();
        boolean result = sequenzFolge(randomData, folgenSequenz);

        if (result) {
            System.out.println("\nEs gibt");
        } else {
            System.out.println("\nEs gibt nicht");
        }

    }// End main
}// End Nummerfolge