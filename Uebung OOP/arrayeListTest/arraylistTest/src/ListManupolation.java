import java.util.List;

public class ListManupolation {

    public static void listPlus(List list, int faktor) {

        int counter = 0;
        for(Object element : list) {
            if (element instanceof Integer) {
                int resultInteger = (int)element + faktor;
                list.set(counter, resultInteger);
                System.out.println("INTEGER: " + resultInteger);
            }

            else if (element instanceof String) {
                String resultString =  element + String.valueOf(faktor);
                list.set(counter, resultString);
                System.out.println("STRING: " + resultString);

            }

            else if  (element instanceof Double) {
                double resultDouble = (double)element + (double) faktor;
                list.set(counter, resultDouble);
                System.out.println("DOUBLE: " + resultDouble );
            }

            else {
                System.out.println("Falsche Type Habe Sie " + element);
            }
            counter++;
        }

    }
}
