import java.util.Scanner;

public class Taschenrechner {
    static Scanner scanner = new Scanner(System.in);

    // Rechnen Integer Numbers
    public static int rechne(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    if (num1 % num2 != 0) {
                        double genau = (double) num1 / num2;
                        System.out.printf("Achtung Genaues Ergebnis wäre: %.2f\n", genau);
                    }
                    return num1 / num2;
                } else {
                    System.out.println("Division durch Null ist nicht möglich!");
                    return 0;
                }
            default:
                System.out.println("Operator kann nicht akzeptiert werden!");
                return 0;
        }
    }


    // Rechnen Double Numbers
    public static double rechne(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    System.out.println("Division durch Null ist nicht möglich!");
                    return 0;
                }
            default:
                System.out.println("Operator kann nicht aceptieren!");
                return 0;
        }
    }


    public static void main(String[] args) {
        System.out.print("Schreiben Sie die erste Zahl ein: ");
        String nummer1 = scanner.nextLine().trim();

        System.out.print("Schreiben Sie die zweite Zahl ein: ");
        String nummer2 = scanner.nextLine().trim();

        System.out.print("Schreiben Sie einen Operator ein (+, -, *, /): ");
        String operator = scanner.nextLine().trim();


        nummer1 = nummer1.replace(",", ".");
        nummer2 = nummer2.replace(",", ".");

        // Typ der Zahlen erkennen (int oder double)
        boolean isDouble = nummer1.contains(".") || nummer2.contains(".");


        if (isDouble) {
            double zahl1 = Double.parseDouble(nummer1);
            double zahl2 = Double.parseDouble(nummer2);
            double ergebnis = rechne(zahl1, zahl2, operator);
            System.out.printf("Result: %.2f %s %.2f = %.2f\n", zahl1, operator, zahl2, ergebnis);
        } else {
            int zahl1 = Integer.parseInt(nummer1);
            int zahl2 = Integer.parseInt(nummer2);
            int ergebnis = rechne(zahl1, zahl2, operator);
            System.out.printf("Result : %d %s %d = %d\n", zahl1, operator, zahl2, ergebnis);
        }

    }//End main

}// End Taschenrechner
