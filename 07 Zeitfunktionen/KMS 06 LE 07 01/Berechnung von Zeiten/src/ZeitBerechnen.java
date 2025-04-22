import java.util.Scanner;
//Um Zeit- und Datumsklassen wie LocalDate,LocalTime,LocalDateTime,Duration,Period
import java.time.*;
//für Zeitformat fest (HH:mm)
import java.time.format.DateTimeFormatter;
//Zur Behandlung von Fehlern bei der Konvertierung von Text (String) in Datum/Uhrzeit.
import java.time.format.DateTimeParseException;
//für berechnen Sie den Abstand zwischen zwei Datumsangaben
import java.time.temporal.ChronoUnit;

public class ZeitBerechnen {
    static Scanner myScanner = new Scanner(System.in);

    static DateTimeFormatter myTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    //Arbeitszeit berechnen
    public static void arbeitenZeitBerechnen() {
        System.out.print("Ihre Startzeit: ");
        String startArbeit = myScanner.nextLine();
        System.out.print("Ihre Endzeit: ");
        String endArbeit = myScanner.nextLine();

        String startStr = timeFormatStringControll(startArbeit);
        String endStr = timeFormatStringControll(endArbeit);

        LocalTime start = null, end = null;

        //LocalTime Methode nimmt einen String wie „24:00“  und konvertiert ihn in ein echtes LocalTime-Objekt.
        try {
            start = LocalTime.parse(startStr, myTimeFormatter);
            end = LocalTime.parse(endStr, myTimeFormatter);

        } catch (DateTimeParseException e) {
            fehler("Zeitformat Erwartet: HH:mm");
        }

        if (end.isBefore(start)) {
            fehler("Endzeit darf nicht vor Startzeit liegen.");
        }

        //Duration Differenz zwischen zwei Zeiten zu berechnen.
        Duration myDuration = Duration.between(start, end);
        long stunden = myDuration.toHours();
        long minuten = myDuration.toMinutes() % 60;

        System.out.printf("Arbeitszeit: %d Stunden und %d Minuten\n", stunden, minuten);
    }

    // Methode zum Ändern des Zeitformats und Überprüfen, ob die Eingabe numerisch ist oder nicht
    public static String timeFormatStringControll(String input) {
        String[] teile = input.split(":");
        if (teile.length != 2 ||
                !teile[0].matches("\\d{1,2}") ||
                !teile[1].matches("\\d{1,2}")) {

            fehler("Bitte im Format HH:mm und nur mit Zahlen eingeben.");
        }

        String stunde = teile[0];
        String minute = teile[1];

        // Fügen bei Bedarf Nullen hinzu
        if (stunde.length() == 1) {
            stunde = "0" + stunde;
        }
        if (minute.length() == 1) {
            minute = "0" + minute;
        }

        return stunde + ":" + minute;
    }

    //Liefertage berechnen
    public static void lieferungDutumBerechnen() {

        System.out.print("Bestelldatum in diese format(YYYY-MM-DD): ");
        String bestellungDatum = myScanner.nextLine();
        System.out.print("Lieferdatum in diese format(YYYY-MM-DD): ");
        String lieferungDatum = myScanner.nextLine();

        //(LocalDate.parse)konvertiert eingegebenen Text( „2025-04-17“) in ein tatsächliches LocalDate-Objekt.
        LocalDate startDatum, endDatum;
        try {
             startDatum = LocalDate.parse(bestellungDatum);
             endDatum = LocalDate.parse(lieferungDatum);

            if (endDatum.isBefore(startDatum)) {
                fehler("Lieferdatum darf nicht vor dem Bestelldatum liegen.");
            }

            long tage = ChronoUnit.DAYS.between(startDatum, endDatum);
            System.out.printf("Zeitraum: %d Tage zwischen Bestellung und Lieferung\n", tage);

        } catch (DateTimeParseException e) {
            fehler(" Datumsformat Erwartet: YYYY-MM-DD");
        }
    }


    // Fehlerausgabe
    public static void fehler(String text) {
        System.out.println(text);
        System.exit(1);
    }

    public static void main(String[] args) {

        System.out.println("1. Arbeitszeit berechnen");
        System.out.println("2. Lieferung berechnen");
        String menuWahl = myScanner.nextLine();
        System.out.println(".............................");

        if (menuWahl.equals("1")) {
            arbeitenZeitBerechnen();
        } else if (menuWahl.equals("2")) {
            lieferungDutumBerechnen();
        } else {
            fehler("Nur 1 oder 2 erlaubt.");
        }

        myScanner.close();
    }// End main

}//End ZeitBerechnen
