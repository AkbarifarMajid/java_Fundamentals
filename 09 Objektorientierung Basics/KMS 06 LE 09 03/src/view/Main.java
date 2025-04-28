package view;

import controller.Kontoverwaltung;
import model.Konto;

import java.util.Scanner;

public class Main {

    static Scanner myScanner = new Scanner(System.in);

    public static int readInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.print("Bitte eine gültige ganze Zahl eingeben: ");
            scan.next();
        }
        return scan.nextInt();
    }

    public static double readDouble(Scanner scan) {
        while (!scan.hasNextDouble()) {
            System.out.print("Bitte eine gültige Kommazahl eingeben: ");
            scan.next();
        }
        return scan.nextDouble();
    }


    public static void main(String[] args) {

        //Scanner myScanner = new Scanner(System.in);
        Kontoverwaltung myVerwaltung = new Kontoverwaltung();
        boolean runProgram = true;


        while (runProgram){
            System.out.println("--------------------------------");
            System.out.println("1. Neues Konto erstellen");
            System.out.println("2. Geld einzahlen");
            System.out.println("3. Geld abheben");
            System.out.println("4. Konto löschen");
            System.out.println("5. Kontostand anzeigen");
            System.out.println("6. Einzelnes Konto anzeigen");
            System.out.println("7. Alle Konten anzeigen");
            System.out.println("0. Beenden");
            System.out.print("Bitte wählen Sie 1,2,3,4,5,6,7 oder 0: ");

            int menuWahl = readInt(myScanner);
            System.out.println("--------------------------------");

            switch (menuWahl){

                // Neues Konto erstellen"
                case  1 -> {
                    System.out.print("Kontonummer: ");

                    int nummer = readInt(myScanner);
                    myScanner.nextLine();

                    System.out.print("Name: ");
                    String name = myScanner.nextLine();

                    Konto neuesKonto = new Konto(nummer, name);
                    myVerwaltung.neukontoHinzufugen(neuesKonto);
                }

                //Geld einzahlen
                case  2 -> {
                    System.out.print("Kontonummer: ");
                    int kontoNummer = readInt(myScanner);

                    System.out.print("Betrag: ");
                    double betragEinzahlen = readDouble(myScanner);

                    Konto kontiEinzahl = myVerwaltung.kontoSuchen(kontoNummer);
                    if(kontiEinzahl != null){
                        kontiEinzahl.geldHinzufugen(betragEinzahlen);
                    }else{
                        System.out.println("Konto nicht gefunden.");

                    }

                }


                //Geld abheben
                case  3 -> {
                    System.out.print("Kontonummer: ");
                    int kontoNummerAbholen = readInt(myScanner);

                    System.out.print("Betrag: ");
                    double betragAbholen = readDouble(myScanner);

                    Konto kontoAbholen = myVerwaltung.kontoSuchen(kontoNummerAbholen);
                    if(kontoAbholen != null){
                        kontoAbholen.geldNenmen(betragAbholen);
                    }else{
                        System.out.println("Konto nicht gefunden.");

                    }

                }

                //Konto löschen
                case 4 -> {
                    System.out.print("Kontonummer: ");
                    int kontoNummerDelete = readInt(myScanner);
                    myVerwaltung.kontoEntfernen(kontoNummerDelete);
                }


                //Kontostand anzeigen
                case 5 -> {
                    System.out.print("Kontonummer: ");
                    int kontoNumerAnzeige = readInt(myScanner);
                    myVerwaltung.kontostandAnzeigen(kontoNumerAnzeige);
                }


                //Einzelnes Konto anzeigen
                case 6 ->{
                    System.out.print("Kontonummer: ");
                    int kontoNummerSuchen = readInt(myScanner);
                    Konto konto = myVerwaltung.kontoSuchen(kontoNummerSuchen);

                    if (konto != null) {
                        System.out.println("Kontoinformationen:");
                        System.out.println(konto); // verwendet toString()
                    } else {
                        System.out.println("Konto wurde nicht gefunden.");
                    }
                }



                //Alle Konten anzeigen
                case 7 -> {
                    myVerwaltung.alleKontenAnzeigen();
                }

                //Beenden
                case 0 -> {
                    runProgram = false;
                    System.out.println("Programm beendet.");
                }


                default ->{
                    System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
                }



            }// End switch


        }// End while runProgram

        myScanner.close();

    }// End main

}// End Main
