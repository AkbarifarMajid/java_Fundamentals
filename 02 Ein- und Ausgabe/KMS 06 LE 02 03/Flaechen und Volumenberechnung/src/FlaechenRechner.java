import java.util.Scanner;

public class FlaechenRechner {

    static Scanner scan = new Scanner(System.in);

    // ---------------------- Menüanzeigemethode -----------------
    public static void menuAnzeige(){
        System.out.println("R = Rechteck (Quader) Rechnen");
        System.out.println("D = Gleichseitiges Dreieck (Prisma) Rechnen");
        System.out.println("K = Kreis (Zylinder) Rechnen");
        System.out.print("Welche Grundfläche und Volumen möchten Sie berechnen? ");
    }

    //---------------------- Menüauswahlmethode -----------------
    public static String readAuswahlMenu() {
        return scan.next().toUpperCase();
    }

    // ----------------- Methode zum Abrufen des Werts ------------------
    public static double readWert(String text){
        System.out.print(text);
        if(scan.hasNextDouble()){
            return scan.nextDouble();
        }
        else{
            System.out.println("!!! Geben Sie eine Dezimalzahl mit Komma ein.");
            return 0;
        }
    }

    //----- Methode zur Berechnung der Fläche eines Rechtecks und des Volumens eines Quaders
    public static void rechneRechteck(String auswahl) {
        System.out.println("\n--------------- Ihre Auswahl ist Rechteck und Quader  ----------");
        double laengeRechteck = readWert("Bitte geben Sie die länge ein (Rechteck): ");
        double breiteRechteck = readWert("Bitte geben Sie die Breite ein (Rechteck): ");
        double hoeheRechteck = readWert("Bitte geben Sie die Höhe Quader ein : ");

        double grundflaecheRechteck = laengeRechteck * breiteRechteck;
        double volumenQuader = grundflaecheRechteck * hoeheRechteck;

        System.out.printf("Grundfläche Rechteck: %.2f m²\n", grundflaecheRechteck);
        System.out.printf("Volumen Quader: %.2f m³\n", volumenQuader);
    }


    //----- Methode zur Berechnung der Fläche eines Dreieck und des Volumens eines Prismas
    public static void rechneDreieck(String auswahl){
        System.out.println("\n--------------- Ihre Auswahl ist Gleichseitiges Dreieck und Prisma  ----------");

        double seitLanaengeDreieck = readWert("Bitte geben Sie die Seitenlänge gleichseitigen Dreiecks ein (Dreieck): ");
        double hoehePrisma = readWert("Bitte geben Sie die Höhe Prismas ein : ");

        double grundflaecheDreiecke = (seitLanaengeDreieck * seitLanaengeDreieck * Math.sqrt(3)) / 4;
        double volumenPrisma = grundflaecheDreiecke * hoehePrisma;

        System.out.printf("Grundfläche Rechteck: %.2f m²\n", grundflaecheDreiecke);
        System.out.printf("Volumen Prisma: %.2f m³\n", volumenPrisma);
    }


    //----- Methode zur Berechnung der Fläche eines Kreis und des Volumens eines Zylinders
    public static void rechneKreis(String auswahl){
        System.out.println("\n--------------- Ihre Auswahl ist Kreis und Zylinder ----------");
        double lengeRadius = readWert("Bitte geben Sie den Radius des Kreises ein (Kreis): ");
        double hoeheZylinder = readWert("Bitte geben Sie die Höhe Zylinder ein: ");

        double grundflaecheKreis = (lengeRadius * lengeRadius) * Math.PI;
        double volumenZylinder = grundflaecheKreis * hoeheZylinder;

        System.out.printf("Grundfläche Kreis: %.2f m²\n", grundflaecheKreis);
        System.out.printf("Volumen Zylinder: %.2f m³\n", volumenZylinder);
    }

    public static void main(String[] args) {
        menuAnzeige();
        String benutzrAuswahl = readAuswahlMenu();

        switch (benutzrAuswahl) {
            case "R":
                rechneRechteck(benutzrAuswahl);
               break;
            case "D":
                rechneDreieck(benutzrAuswahl);
               break;
            case "K":
                rechneKreis(benutzrAuswahl);
                break;
            default:
                System.out.println("Bitte geben Sie R, D oder K ein.");
        }

    scan.close();
    }// End main

}// End FlaechenRechner