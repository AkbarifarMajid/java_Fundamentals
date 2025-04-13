import java.util.Scanner;

public class TeilTextEntnehmen {
    static Scanner myScanner = new Scanner(System.in);

    // Liest den vollständigen Text vom Benutzer ein
    public static String readText() {
        System.out.print("Bitte geben Sie den Text ein: ");
        return myScanner.nextLine().trim();

    }

    // Extrahiert die Artikelbezeichnung aus dem Text
    public static void extrahiereDaten(String eingabe) {

            // Erstes Paar eckiger Klammern für Artikelname
            int startArtikel = eingabe.indexOf('[');
            int endArtikel = eingabe.indexOf(']');

            // Zweites Paar für Bestellername
            int startBesteller = eingabe.indexOf('[', endArtikel);
            int endBesteller = eingabe.indexOf(']', startBesteller);

            if (startArtikel == -1 || endArtikel == -1 || startBesteller == -1 || endBesteller == -1) {
                System.out.println("Beispiel text derArtikel[USB-Stick]wurdebestelltvon[Majid Akbarifar]");
            }
            else {
                String artikelName = eingabe.substring(startArtikel + 1, endArtikel);
                String bestellerName = eingabe.substring(startBesteller + 1, endBesteller);

                System.out.println("Artikelbezeichnung: " + artikelName);
                System.out.println("Besteller*in: " + bestellerName);
            }

        }

    // Hauptmethode: Steuert den Ablauf des Programms
    public static void main(String[] args) {
        while (true) {
            String text = readText();
            // prüfen und extrahieren
            if (text.contains("[") && text.contains("]")) {
                extrahiereDaten(text);
                break;
            } else {
                System.out.println("Bitte geben Sie einen gültigen Text ein!");
            }
        }// End while
    }// End main
}