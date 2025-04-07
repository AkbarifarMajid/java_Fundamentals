import java.util.Scanner;

public class TaschenRechnen {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Eingabe der ersten Zahl, des Operators und der zweiten Zahl durch den Benutzer
        double zahl1 = leseZahl("Bitte geben Sie erste Nummer ein.: ");
        char operator = leseOperator("Bitte wählen Sie Ihren Operator ein (+, -, *, /): ");
        double zahl2 = leseZahl("Bitte geben Sie die zweite Zahl ein: ");


        double result = berechne(zahl1, zahl2, operator);

        if (!Double.isNaN(result)) {
            System.out.printf("%.2f %c %.2f = %.2f ",zahl1,operator,zahl2,result);
        }

        sc.close();
    }//End main

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
                if(zahl2 !=0) {
                    return zahl1 / zahl2;
                }else {
                    System.out.println("Division durch Null ist nicht erlaubt. ");
                    return Double.NaN;
                }
            default:
                System.out.println("Sie dürfen einen dieser ' + - * / ' operator auswählen.");
                return Double.NaN;
        }
    }


}