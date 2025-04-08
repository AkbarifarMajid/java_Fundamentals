import java.lang.foreign.SymbolLookup;
import java.util.Scanner;

public class EingabenUeberpruefen {
    static Scanner myScanner = new Scanner(System.in);

    // _____________   Methode zur Überprüfung und Rückgabe einer Bezeichnung -----------
    public static String readBezeichnung(){
        System.out.println("Bitte geben Sie die Produkt Name:");
        String inputBezeichnung;

        while(true){
            inputBezeichnung = myScanner.nextLine();

            if(!inputBezeichnung.matches("[A-Za-zÄÜÖüäö0-9]+([A-Za-zÄÜÖüäö0-9 ])*")){
                System.out.println("FALSCH BEZEICHNUNG !!");
                continue;
            }

            return inputBezeichnung;
        }

    }

    // _____________   Methode zur Überprüfung und Rückgabe einer Ganz Zahl -----------
    public static int readStuckZahl(){
        System.out.println("Bitte geben Sie  eine ganze Zahl ein:");;

        while(true){
            if (myScanner.hasNextInt()) {
                return myScanner.nextInt();
            } else {
                System.out.println("FALSCH GANZE ZAHL !!");
                myScanner.next();
            }
        }
    }

    // _____________   Methode zur Überprüfung und Rückgabe einer Kommazahl (Preis) -----------
    public static double readEinzelpreis(){
        System.out.println("Bitte geben Sie  eine ganze Zahl ein:");;

        while(true){
            if (myScanner.hasNextDouble()) {
                return myScanner.nextDouble();
            } else {
                System.out.println("FALSCH EINZAHL PREIS !!");
                myScanner.next();
            }
        }
    }

    //_------------------------MAIN -----------------------------_
    public static void main(String[] args) {
        String bezeichnung = readBezeichnung();
        int stuckZahl = readStuckZahl();
        double einzahlPreis = readEinzelpreis();

        System.out.printf("Bezeichnung: %s \nStuckZahl: %d \nEinzahlPreis : %.2f", bezeichnung, stuckZahl, einzahlPreis);

    myScanner.close();
    }// End main

}// End EingabenUeberpruefen