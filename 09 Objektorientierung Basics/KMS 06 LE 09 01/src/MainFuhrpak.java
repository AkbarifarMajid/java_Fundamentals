import java.util.ArrayList;

public class MainFuhrpak {
    public static void main(String[] args) {
        ArrayList<Fahrzeug> myFahrzeugListe = new ArrayList<>();

        // Es entstehen verschiedene Fahrzeuge
        //--------------------------------------------------------------------------------------------
        PKW pkw1 = new PKW(1, "Tyota", "Supra", 2025, 2);

        // Verhalten auf Geräte anwenden
        pkw1.fahren(150.5);
        pkw1.tanken(42.0);
        pkw1.setStandort("Parkplatz A");
        pkw1.wartungHinzufuegen("2025-04-27");


        //Informationen anzeigen
        System.out.println("\n------ PKW anzeigen() ------");
        pkw1.anzeigen();

        //--------------------LKW-----------------------------

        LKW lkw1 = new LKW(2,"Volvo", "Actros", 2021, 18.0);
        lkw1.fahren(300.0);
        lkw1.tanken(250.0);
        lkw1.setStandort("Lagerhalle 3");
        lkw1.wartungHinzufuegen("2025-04-10");

        //Informationen anzeigen
        System.out.println("\n------ LKW anzeigen() ------");
        lkw1.anzeigen();

        //--------------------Motorrad-----------------------------

        Motorrad motorrad1 = new Motorrad(3, "Suzuki", "MT-07", 2022, 689, true);
        motorrad1.fahren(300.0);
        motorrad1.tanken(250.0);
        motorrad1.setStandort("Lagerhalle 3");
        motorrad1.wartungHinzufuegen("2028-10-10");

        //Informationen anzeigen
        System.out.println("\n------ Motorrad anzeigen() ------");
        motorrad1.anzeigen();

        //--------------------Fahrrad-----------------------------

        Fahrrad fahrrad1 = new Fahrrad(4, "TX", "MT-07", 2024, false);
        fahrrad1.fahren(300.0);

        fahrrad1.setStandort("Fahrradständer B");
        fahrrad1.wartungHinzufuegen("2029-11-23");

        //Informationen anzeigen
        System.out.println("\n------ Fahrrad anzeigen() ------");
        fahrrad1.anzeigen();



        //Gesamtfahrzeugzähler anzeigen
        System.out.println("------------------------ Anzahl ----------------------------");
        System.out.println("Anzahl aller Fahrzeuge: " + Fahrzeug.getFahrzeugZaehler());


        //----------------- Add Alle object in list-----------
        myFahrzeugListe.add(pkw1);
        myFahrzeugListe.add(lkw1);
        myFahrzeugListe.add(motorrad1);
        myFahrzeugListe.add(fahrrad1);

        System.out.println("\n------ Alle Fahrzeuge anzeigen (mit Schleife) ------");
        for (Fahrzeug f : myFahrzeugListe) {
            f.anzeigen();
        }

    }// End main

}
