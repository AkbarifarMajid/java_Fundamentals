import java.util.Scanner;

public class FarbecodesUmrechnen {
    static Scanner myScanner = new Scanner(System.in);


    // RGB direkt vom Benutzer einlesen
    public static int[] readRGB() {
        System.out.println("Geben Sie die RGB-Werte im Format 'zahl1,zahl2,zahl3' ein: ");
        String input = myScanner.nextLine().trim();

        String[] teileRGB = input.split(",");
        if (teileRGB.length != 3) {
            fehler("Geben Sie bitte 3 Zahlen getrennt mit Komma.");
        }

        int[] rgb = new int[3];
        try {
            for (int i = 0; i < 3; i++) {
                rgb[i] = Integer.parseInt(teileRGB[i].trim());
            }
        } catch (NumberFormatException e) {
            fehler("Fehler beim Umwandeln: " + e.getMessage());
        }
        return rgb;
    }

    // CSS-Code lesen und in RGB umwandeln
    public static int[] readCSS() {
        System.out.print("Geben Sie den CSS-Farbcode ein : ");
        String inputSCCcode = myScanner.nextLine().trim();

        if (inputSCCcode.startsWith("#")) {
            inputSCCcode = inputSCCcode.substring(1);
        }

        if (inputSCCcode.length() != 6 || !inputSCCcode.matches("[0-9a-fA-F]{6}")) {
            fehler("Bitte geben Sie genau 6 gültige Hex-Zeichen  mit anfang '#' ein.");
        }

        int [] inputFinalCSS = convertHexToRgb(inputSCCcode);
        return inputFinalCSS;
    }

    // CSS-Code in RGB konvertieren
    public static int[] convertHexToRgb(String code) {
        try {
            int r = Integer.parseInt(code.substring(0, 2), 16);
            int g = Integer.parseInt(code.substring(2, 4), 16);
            int b = Integer.parseInt(code.substring(4, 6), 16);
            return new int[]{r, g, b};
        } catch (NumberFormatException e) {
            fehler("Problem beim Konvertieren des Farbwerts: " + e.getMessage());
            return null; // nie erreicht
        }
    }

    // Ausgabe RGB-Werte
    public static void showUserRGB(int[] rgb) {
        System.out.printf("Eingegebene RGB-Werte: (%d, %d, %d)%n", rgb[0], rgb[1], rgb[2]);
    }

    //RGB wert in min 0 und max 255 kontrolle
    public static boolean controlRGBwert(int[] rgb) {
        for (int wert : rgb) {
            if (wert < 0 || wert > 255) {
                return false;
            }
        }
        return true;
    }

    // RGB → CMY konvertieren
    public static double[] convertRGBtoCMY(int[] rgb) {
        double[] rgbTOcmy = new double[3];
        rgbTOcmy[0] = (255 - rgb[0]) / 255.0 * 100;
        rgbTOcmy[1] = (255 - rgb[1]) / 255.0 * 100;
        rgbTOcmy[2] = (255 - rgb[2]) / 255.0 * 100;
        return rgbTOcmy;
    }

    // Ausgabe CMY-Werte
    public static void showFinalCmy(double[] cmy) {
        System.out.println("\nUmgerechnete CMY-Werte:");
        System.out.printf("Cyan:    %.2f%%%n", cmy[0]);
        System.out.printf("Magenta: %.2f%%%n", cmy[1]);
        System.out.printf("Yellow:  %.2f%%%n", cmy[2]);
    }


    public static void fehler(String meldung) {
        System.err.println(meldung);
        System.exit(1);
    }


    public static void main(String[] args) {

        System.out.println("Bitte waelen Sie Die RGB oder CSS: ");
        String userModus = myScanner.nextLine();

        int[] userInput =null;

        if (userModus.equalsIgnoreCase("RGB")) {
            userInput = readRGB();

            if (!controlRGBwert(userInput)) {
                fehler("Geben Sit bitte eine Werte zwischen 0 und 255 liegen.");
            }

        } else if (userModus.equalsIgnoreCase("CSS")) {
            userInput = readCSS();

        } else {
            fehler("Falsche Modus eingegeben!");
        }

        showUserRGB(userInput);

        double[] cmy = convertRGBtoCMY(userInput);
        showFinalCmy(cmy);
    }

}
