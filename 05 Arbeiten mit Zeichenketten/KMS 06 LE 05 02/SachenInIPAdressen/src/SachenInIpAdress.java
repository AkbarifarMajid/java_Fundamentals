import java.util.Scanner;

public class SachenInIpAdress {
    static Scanner myScanner = new Scanner(System.in);

    // Liest die IP-Adresse vom Benutzer ein
    public static String readIpAdress() {
        System.out.print("Bitte geben Sie eine IPv4-Adresse ein: ");
        return myScanner.nextLine().trim();
    }

    // Prüft, ob die eingegebene IP-Adresse gültig ist
    public static boolean controlIP(String ipAdress) {
        String [] ipTeile = ipAdress.split("\\.");
        if(ipTeile.length !=4) return false;

        for(String teil: ipTeile){
            int wert = Integer.parseInt(teil);
            if (wert < 0 || wert > 255) return false;
        }
        return true;

    }

    // Wandelt die IP-Adresse in eine binäre Darstellung um
    public static String binarayMethod(String ipAdress){
        String[] teile = ipAdress.split("\\.");
        String result = "";

        for (int i = 0; i < teile.length; i++) {
            int zahl = Integer.parseInt(teile[i]);// Konvert jede Teite ti int
            String binnary = Integer.toBinaryString(zahl); //In Binärcode konvertieren
            binnary = String.format("%8s", binnary).replace(' ', '0'); // Fügt Nullen hinzu, um 8 Ziffern zu erhalten.

            result += binnary;

            if (i < teile.length - 1) {
                result += ".";
            }
        }

        return result;

    }

    // Wandelt die IP-Adresse in eine hexadezimale Darstellung um
    public static String hexMethod(String ipAdress){
        String[] teile = ipAdress.split("\\.");
        String result = "";

        for (int i = 0; i < teile.length; i++) {
            int zahl = Integer.parseInt(teile[i]);
            String hex = Integer.toHexString(zahl).toUpperCase(); // In Hexadezimal umwandeln

            // Eine führende Null hinzufügen, wenn nur eine Stelle vorhanden ist
            if (hex.length() == 1) {
                hex = "0" + hex;
            }

            result += hex;

            if (i < teile.length - 1) {
                result += ".";
            }
        }
        return result;
    }

    // Wandelt die IP-Adresse in eine oktale Darstellung um
    public static String octalMethod(String ipAdress){
        String[] teile = ipAdress.split("\\.");
        String result = "";
        for (int i = 0; i < teile.length; i++) {
            int zahl = Integer.parseInt(teile[i]);
            String octal = Integer.toOctalString(zahl);

            result += octal;

            if (i < teile.length - 1) {
                result += ".";
            }
        }
        return result;
    }

// Hauptmethode: führt die Eingabe, Prüfung und Ausgabe durch
    public static void main(String[] args) {
        String ipAdress;

        while (true){
            ipAdress = readIpAdress();
            if(!controlIP(ipAdress)){
                System.out.println("Ihre IP_Adress ist ungültig .");
            }
            else{
                break;
            }
        }

        System.out.println("IP in Binär: " + binarayMethod(ipAdress));
        System.out.println("IP in Hexadezimal: " + hexMethod(ipAdress));
        System.out.println("IP in Oktal: " + octalMethod(ipAdress));

        myScanner.close();
    }// End main
}// End Sachenn In IP-Adrress