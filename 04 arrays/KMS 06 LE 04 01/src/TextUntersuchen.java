import java.util.Scanner;

public class TextUntersuchen {
    static Scanner myScanner = new Scanner (System.in);

    // Liste der Vokale zur späteren Überprüfung
    static char [] vokalList = {'a','A','e','E','o','O','u','U','ä','Ä','ö','Ö','ü','Ü'};

    // Liste der Satzzeichen zur Erkennung in einem Text
    static char[] satzzeichenArray = {
            '.', ',', '!', '?', ';', ':', '-', '…',
            '"', '\'', '(', ')', '[', ']', '{', '}', '/', '\\'
    };

    // Zählt die Anzahl der Vokale im gegebenen Text
    public static int countVokale(String text){
        int conter = 0;
        for (int i = 0; i < text.length() ; i++){
            char character = text.charAt(i);
            for (char voka : vokalList){
                if(character == voka){
                    conter++;
                    break;
                }
            }
        }
        return conter;
    }

    // Zählt die Anzahl der Leerzeichen (Spaces) im Text
    public static int countLeerzeichen(String text) {
        int count = 0;
        for (char leerzeichen : text.toCharArray()) {
            if (leerzeichen == ' ') {
                count++;
            }
        }
        return count;
    }

    // Zählt die Anzahl der Wörter im Text basierend auf Leerzeichen
    public static int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    // Zählt die Anzahl der Satzzeichen im Text
    public static int countSatzzeichen(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            for (char satzzeichen : satzzeichenArray) {
                if (c == satzzeichen) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    // Zählt die Anzahl der Konsonanten im Text
    public static int countKonsonanten(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char zeichen = text.charAt(i);
            if (Character.isLetter(zeichen) && !isVokal(zeichen)) {
                count++;
            }
        }
        return count;
    }

    // Prüft, ob ein Zeichen ein Vokal ist (wird in anderen Methoden verwendet)
    public static boolean isVokal(char zeichen) {
        for (char v : vokalList) {
            if (zeichen == v) {
                return true;
            }
        }
        return false;
    }

    // Hauptmethode zur Eingabe, Verarbeitung und Ausgabe der Analyseergebnisse
    public static void main(String[] args) {
        System.out.println("Bitte geben Sie einen Text ein:");
        String text = myScanner.nextLine();

        int vokale = countVokale(text);
        int leerzeichen = countLeerzeichen(text);
        int satzzeichen = countSatzzeichen(text);
        int woerter = countWords(text);
        int konsonanten = countKonsonanten(text);
        int laenge = text.length();

        System.out.println("Länge des Textes: " + laenge);
        System.out.println("Anzahl der Wörter: " + woerter);
        System.out.println("Anzahl der Vokale: " + vokale);
        System.out.println("Anzahl der Leerzeichen: " + leerzeichen);
        System.out.println("Anzahl der Satzzeichen: " + satzzeichen);
        System.out.println("Anzahl der Konsonanten: " + konsonanten);

        myScanner.close();
    }//End main
}// End TextUntersuchen
