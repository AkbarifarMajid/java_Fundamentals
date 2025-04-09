import java.util.Scanner;

public class Schaltjahr {
    static Scanner myScanner = new Scanner(System.in);

    // Methode zur sicheren Eingabe einer Ganzzahl
    public static int readJahrZahl(String text){
        System.out.print(text);;

        while(true){
            if (myScanner.hasNextInt()) {
                return myScanner.nextInt();
            } else {
                System.out.println("FALSCH Eingebe ! versuchen Sie wieder!!");
                myScanner.next();
            }
        }
    }

    //Methode zur Prüfung, ob ein Jahr ein Schaltjahr ist
    public static boolean schaltjahr(int schaltJahr){
        return ((schaltJahr % 4 == 0 && schaltJahr % 100 !=0) || schaltJahr % 400 == 0);
    }

    // ---------------------- Menüanzeigemethode -----------------
    public static void menuAnzeige(){
        System.out.println("1 = Prüfen, ob ein bestimmtes Jahr ein Schaltjahr ist");
        System.out.println("2 = Alle Schaltjahre in einem bestimmten Zeitraum anzeigen");
        System.out.println("3 = Schließen");
    }

    //-------------------------------------------------------
    public static void main(String[] args) {
        menuAnzeige();

        int menuItem =readJahrZahl("Wass ist Ihre Auswahl? ");
        switch (menuItem){
            case 1:
                System.out.println("--------------------------------------------");
                int inputJahr = readJahrZahl("Bitte geben Sie Ihre Zahl ein: ");

                if (schaltjahr(inputJahr)) {
                    System.out.printf("Jahr %d ist ein Schaltjahr\n", inputJahr);
                }else {
                    System.out.printf("Jahr %d ist kein Schaltjahr\n", inputJahr);
                }
                break;

            case 2:

                System.out.println("--------------------------------------------");
                int startJahr = readJahrZahl("Bitte geben Sie eine Startjahr Zahl ein: ");
                int endtJahr = readJahrZahl("Bitte geben Sie eine Endjahr Zahl ein: ");

                // Kontrolle: Wenn Startjahr > Endjahr ist
                if(startJahr > endtJahr){
                    System.out.println("!!!!!!!!!!!!!!!!!!");
                    System.out.println("Startjahr ist größer als Endjahr. Werte werden getauscht.");
                    int tempBox = startJahr;
                    startJahr = endtJahr;
                    endtJahr = tempBox;
                }

                System.out.printf("Liste der Schaltjahr zwischen %d und %d :\n",startJahr, endtJahr);

                for(int jahr = startJahr ; jahr <= endtJahr ; jahr++){
                    if (schaltjahr(jahr)){
                        System.out.println(jahr);
                    }
                }
                break;

            case 3:
                System.out.println("GOOD BYE");
                break;

            default:
                System.out.println("Bitte geben Sie 1 oder 2 ein.");
        }
        myScanner.close();
    }//End main

}//Schaltjahr