import java.util.Scanner;

public class TaschenRechnen {

    static Scanner sc = new Scanner(System.in);

    // Fragt den Benutzer nach einer Zahl und gibt diese als double zurück
    public static double leseZahl(String frage) {
        System.out.print(frage);
        return sc.nextDouble();
    }

    // Fragt den Benutzer nach einer Operator
    public static char leseOperator(String frage) {
        System.out.print(frage);
        return sc.next().charAt(0);
    }

    // Auswertung des eingegebenen Operators und Durchführung der entsprechenden Rechenoperation
    public static double berechne(double zahl1, double zahl2, char operator) {
        switch (operator){
            case '+':
                return zahl1 + zahl2;
            case '-':
                return zahl1 - zahl2;
            case '*':
                return zahl1 * zahl2;
            case '/':
                return zahl1 / zahl2;
            default:
                System.out.println("Bitte + - * oder / eingeben.");
                System.exit(0);
                return 0;
        }
    }

    public static void main(String[] args) {

        // Eingabe der ersten Zahl, des Operators und der zweiten Zahl durch den Benutzer
        double zahl1 = leseZahl("Bitte geben Sie erste Nummer ein.: ");
        char operator = leseOperator("Bitte wählen Sie Ihren Operator ein (+, -, *, /): ");
        double zahl2 = leseZahl("Bitte geben Sie die zweite Zahl ein: ");

        if (operator == '/' && zahl2 == 0) {
            System.out.println("Division durch Null ist nicht erlaubt.");
            sc.close();
            return;
        }

        double result = berechne(zahl1, zahl2, operator);

        System.out.printf("%.2f %c %.2f = %.2f ",zahl1,operator,zahl2,result);
        sc.close();
    }//End main

}
