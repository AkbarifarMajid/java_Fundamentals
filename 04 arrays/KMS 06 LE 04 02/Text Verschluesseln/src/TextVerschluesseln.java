import java.util.Scanner;

public class TextVerschluesseln {
    static Scanner myScanner = new Scanner(System.in);

    // Caesar-Verschlüsselung mit Rückgabe des verschlüsselten Arrays
    public static char[] verschluesseln(char[] originalText, int schluessel) {
        char[] verschluesseltText = new char[originalText.length];

        for (int i = 0; i < originalText.length; i++) {
            char charText = originalText[i];

            // Nur Buchstaben verschlüsseln, andere Zeichen bleiben gleich
            if ((charText >= 'A' && charText <= 'Z') || (charText >= 'a' && charText <= 'z')) {
                charText = (char) (charText + schluessel);

                // Bei Überschreitung zurück zum Anfang des Alphabets
                if ((originalText[i] >= 'A' && originalText[i] <= 'Z') && charText > 'Z') {
                    charText = (char) (charText - 26);
                } else if ((originalText[i] >= 'a' && originalText[i] <= 'z') && charText > 'z') {
                    charText = (char) (charText - 26);
                }
            }

            // Schreibe verschlüsselten Buchstaben ins Ergebnis-Array
            verschluesseltText[i] = charText;
        }

        return verschluesseltText;
    } // End verschluesseln Mathod

    // ---------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("Bitte geben Sie einen Text ein:");
        String userText = myScanner.nextLine();

        int schluessel = 1;

        // Originaltext in ein Array umwandeln
        char[] originalText = new char[userText.length()];
        for (int i = 0; i < userText.length(); i++) {
            originalText[i] = userText.charAt(i);
        }

        // verschlüsseln und zurückgeben
        char[] verschluesseltText = verschluesseln(originalText, schluessel);

        // Ausgabe des verschlüsselten Textes
        System.out.print("Verschlüsselter Text: ");
        for (int i = 0; i < verschluesseltText.length; i++) {
            System.out.print(verschluesseltText[i]);
        }

        myScanner.close();
    }// End main
}// End TextVerschluesseln
