import java.util.Scanner;
import java.util.ArrayList;     // Für dynamische Liste der eingegebenen Zahlen
import java.util.HashSet;       // Um doppelte Zahlen zu vermeiden
import java.util.Collections;   // Zum Sortieren der Liste
import java.util.List;          // Generischer Typ für die Liste
import java.util.Set;           // Generischer Typ für die Menge
import java.util.Arrays;        // Um Arrays schön als Text


public class Lotto {
    static Scanner myScanner = new Scanner(System.in);
    static Set<Integer> userNumbers = new HashSet<>();
    static List<Integer> myList = new ArrayList<>();

    public static int[] readLottoZahl() {

        while (userNumbers.size() < 6) {
            System.out.print("Geben Sie die " + (userNumbers.size() + 1) + ". Zahl ein: ");

            if (myScanner.hasNextInt()) {
                int number = myScanner.nextInt();

                if (number < 1 || number > 45) {
                    System.out.println("Die Zahl muss zwischen 1 und 45 liegen. Wiederholen!");
                    //Überprüfen, ob eine Nummer doppelt vorhanden ist
                } else if (userNumbers.contains(number)) {
                    System.out.println("Diese Zahl haben Sie schon. Bitte nochmals eingeben!");
                } else {
                    userNumbers.add(number);
                    myList.add(number);
                }
            } else {
                System.out.println("Das ist keine Zahl. Wiederholen!");
                myScanner.next(); // Buffer Löschen
            }
        }//End while

        //Sort klein---- gross
        Collections.sort(myList);

        //konvert list to Array als int
        int[] finalList = new int[6];
        for (int i = 0; i < 6; i++) {
            finalList[i] = myList.get(i);
        }

        return finalList;
    }

    public static void main(String[] args) {
        int[] lottozahlen = readLottoZahl();

       /* System.out.print("Meine Lottozahlen Liste: ");
        for(int f:lottozahlen){
            System.out.println(f);
        }
        */

        System.out.println(Arrays.toString(lottozahlen));
    }
}
