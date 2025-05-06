import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("---INTEGER------");
        List<Object> myList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            myList.add(i);
        }
        ListManupolation.listPlus(myList, 10);
        myList.clear();

        System.out.println("\n---STRING------");

        myList.add("Graz");
        myList.add("Wien");
        myList.add("Salzburg");
        ListManupolation.listPlus(myList, 5);
        myList.clear();

        System.out.println("\n---DOUBLE------");

        for (double i = 1; i < 4; i += 0.7) {
            myList.add(i);
        }
        ListManupolation.listPlus(myList, 10);

    }
}