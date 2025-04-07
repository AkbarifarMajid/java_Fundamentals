import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        //Vorname abrufen
        System.out.println("Biite geben Sie Ihre Name ein: ");
        String vorname = scan.nextLine();

        //Nachname Abrufen
        System.out.println("Biite geben Sie Ihre Nachname ein: ");
        String nachname = scan.nextLine();

        //Info Anzeigen
        System.out.printf("Meine Name ist %s %s und ich habe java lernen angefangen ! \n", vorname , nachname );

        //Scanner ausschlißen
        scan.close();
    }


}