import java.util.Scanner;

public class Main {
    static Scanner myScanner = new Scanner(System.in);

    //Farbcode vom Benutzer abrufen / Entfernen Sie das #-Zeichen, falls vorhanden
    public static String getHexCode() {
        System.out.print("Bitte geben Sie einen Farbcode ein : ");
        String input = myScanner.nextLine().trim();

        if (input.startsWith("#")) {
            input = input.substring(1);
        }
        return input;
    }

    //// Prüfen Sie, ob der Farbcode gültig ist: genau 6 Zeichen und enthält nur 0-9, A-F
    public static boolean isValidHexCode(String code) {

        return code.length() == 6 && code.matches("[0-9a-fA-F]{6}");
    }

    //// Hexadezimalcode in numerische RGB-Werte umwandeln
    public static int[] convertHexToRGB(String code) {
        int rot = Integer.parseInt(code.substring(0, 2), 16);
        int gruen = Integer.parseInt(code.substring(2, 4), 16);
        int blau = Integer.parseInt(code.substring(4, 6), 16);
        return new int[]{rot, gruen, blau};
    }

    //// Den Endwert der Farben numerisch anzeigen
    public static void showRGB(int r, int g, int b) {
        System.out.println("\nUmgewandelte RGB-Werte:");
        System.out.printf("ROT   = %3d\n", r);
        System.out.printf("GRUEN = %3d\n", g);
        System.out.printf("BLAU  = %3d\n", b);
    }

    //// Die Hauptmethode, die die Reihenfolge der Programmausführung bestimm
    public static void main(String[] args) {
        String hexCode;

     // Wiederhole, bis eine gültige Eingabe erfolgt ist
    while (true){
        hexCode = getHexCode();
        if (!isValidHexCode(hexCode)) {
            System.out.println("Der Code muss genau 6 gültige Hex-Zeichen enthalten.");
        }else{
            break;
        }
    }
        // Konvertieren und anzeigen
        int[] rgb = convertHexToRGB(hexCode);
        showRGB(rgb[0], rgb[1], rgb[2]);

        myScanner.close();
    }
}