package view;

import service.MitgliedService;
//import model.Mitglied;

import java.util.List;
import java.util.Scanner;
import model.*;

//Diese Klasse bietet eine Konsolenansicht für Mitglieder.
public class MitgliedView {

    private static final Scanner myScanner = new Scanner(System.in);


    // Methode zum Anzeigen aller Mitglieder
    public static void mitgliederAnzeigen(){
        List<Mitglied> mitglieder = MitgliedService.alleMitgliederAnzeigen();

        if (mitglieder.isEmpty()){
            System.out.println("List Mitglieder ist Leer !");
        }else{
            System.out.println("#### Mitglieder #####");
            for(Mitglied m_g: mitglieder){
                System.out.println(m_g);
            }
        }
    } //---- End mitgliederAnzeigen


    // Fügt ein neues Mitglied über die Konsole hinzu
    public static void mitgliedHinzufuegen() {

        System.out.print("Vorname: ");
        String vorname = myScanner.nextLine().trim();

        System.out.print("Nachname: ");
        String nachname = myScanner.nextLine().trim();

        System.out.println("Verfügbare Geschlechter: MAENNLICH, WEIBLICH, DIVERS");
        System.out.print("Geschlecht: ");
        String geschlechtEingabe = myScanner.nextLine().trim().toUpperCase();

        System.out.print("Alter : ");
        int alter = Integer.parseInt(myScanner.nextLine().trim());

        System.out.print("E-Mail: ");
        String email = myScanner.nextLine().trim();

        System.out.print("Telefonnummer: ");
        String telefon = myScanner.nextLine().trim();

        System.out.print("Adresse: ");
        String adresse = myScanner.nextLine().trim();

        System.out.println("Verfügbare Altersgruppen: U13, U15, U17, U19, U23, ERWACHSENE");
        System.out.print("Altersgruppe: ");
        String altersgruppeEingabe = myScanner.nextLine().trim().toUpperCase();
        try{
            Mitglied neuMitglied = new Mitglied(
                    vorname,
                    nachname,
                    model.Geschlecht.valueOf(geschlechtEingabe),
                    alter,
                    new model.Kontaktinfo(email, telefon, adresse),
                    model.Altersgruppe.valueOf(altersgruppeEingabe)
            );

            MitgliedService.neuesMitgliedHinzufugen(neuMitglied);


        } catch (IllegalArgumentException error) {
            System.out.println(" Eingabe bei Geschlecht oder Altersgruppe ist falsch!");

        }catch (Exception error) {
            System.out.println("beim Erstellen des Mitglieds gibt es Problem: " + error.getMessage());
        }
    }//----End mitgliedHinzufuegen


    // Sucht ein Mitglied anhand der ID und zeigt die Daten an
    public static void mitgliedSuchen() {
        System.out.print("Bitte geben Sie die Mitglieds-ID ein: ");

        try{
            int id_Suchen = Integer.parseInt(myScanner.nextLine().trim());
            Mitglied mitglied = MitgliedService.suchenMitglied(id_Suchen);

            System.out.println(mitglied);

        }catch (NumberFormatException error){
            System.out.println(" Bitte geben Sie eine gültige Mitglied ID ein.");

        }catch (Exception error){
            System.out.println("bei der Suchen Mitglied gibt es Problem: ." + error.getMessage());
        }

    }// --- End mitgliedSuchen


    // Löscht ein Mitglied anhand der eingegebenen ID
    public static void mitgliedLoschen(){
        System.out.print("Bitte geben Sie die ID des Mitglieds ein, das gelöscht werden soll: ");

        try {
            int id_Loschen = Integer.parseInt(myScanner.nextLine().trim());

            // vorheriges Anzeigen der Daten zur Bestätigung
            Mitglied mitglied = MitgliedService.suchenMitglied(id_Loschen);
            if (mitglied == null) {
                System.out.println("Kein Mitglied mit dieser ID gefunden.");
                return;
            }

            System.out.println(mitglied);

            System.out.print("Sind Sie sicher, dass Sie dieses Mitglied löschen möchten? (j/n): ");
            String bestaetigung = myScanner.nextLine().trim().toLowerCase();

            if (bestaetigung.equals("j")) {
                boolean resultLoschen = MitgliedService.loschenMitglied(id_Loschen);
                if (resultLoschen) {
                    System.out.println("Mitglied wurde gelöscht.");
                } else {
                    System.out.println("Mitglied konnte nicht gelöscht werden.");
                }
            } else {
                System.out.println("Löschvorgang abgebrochen.");
            }

        }catch (NumberFormatException error) {
            System.out.println("Bitte geben Sie eine gültige ID ein.");
        } catch (Exception error) {
            System.out.println(" beim Löschen gibt es Problem: " + error.getMessage());
        }

    }// ---End mitgliedLoschen


    // Bearbeitet ein bestehendes Mitglied anhand der ID
    public static void mitgliedBearbeiten(){
        System.out.print("Bitte geben Sie die ID des Mitglieds ein, das bearbeitet werden soll: ");

        try {
            int id_Mitglieg = Integer.parseInt(myScanner.nextLine());
            Mitglied altMitglied_info = MitgliedService.suchenMitglied(id_Mitglieg);

            if (altMitglied_info == null) {
                System.out.println("Mitglied mit dieser ID nicht gefunden.");
                return;
            }

            System.out.println("\nAktuelle Daten des Mitglieds:");
            System.out.println(altMitglied_info);
            System.out.println("\nNeue Daten eingeben (leer lassen, um bisherigen Wert zu behalten):");

            System.out.print("Vorname [" + altMitglied_info.getVorname() + "]: ");
            String vorname = myScanner.nextLine().trim();
            if (vorname.isEmpty()) vorname = altMitglied_info.getVorname();

            System.out.print("Nachname [" + altMitglied_info.getNachname() + "]: ");
            String nachname = myScanner.nextLine().trim();
            if (nachname.isEmpty()) nachname = altMitglied_info.getNachname();

            System.out.print("Geschlecht (" + altMitglied_info.getGeschlecht() + "): ");
            String geschlechtInput = myScanner.nextLine().trim().toUpperCase();
            Geschlecht geschlecht;
            if(geschlechtInput.isEmpty()){
                geschlecht = altMitglied_info.getGeschlecht();
            }else {
                geschlecht = Geschlecht.valueOf(geschlechtInput);
            }

            System.out.print("Alter [" + altMitglied_info.getAlter() + "]: ");
            String alterInput = myScanner.nextLine().trim();
            int alter = alterInput.isEmpty() ? altMitglied_info.getAlter() : Integer.parseInt(alterInput);

            System.out.print("E-Mail [" + altMitglied_info.getKontaktinfo().getEmail() + "]: ");
            String email = myScanner.nextLine().trim();
            if (email.isEmpty()) email = altMitglied_info.getKontaktinfo().getEmail();

            System.out.print("Telefon [" + altMitglied_info.getKontaktinfo().getTelefonnummer() + "]: ");
            String telefon = myScanner.nextLine().trim();
            if (telefon.isEmpty()) telefon = altMitglied_info.getKontaktinfo().getTelefonnummer();

            System.out.print("Adresse [" + altMitglied_info.getKontaktinfo().getAdresse() + "]: ");
            String adresse = myScanner.nextLine().trim();
            if (adresse.isEmpty()) adresse = altMitglied_info.getKontaktinfo().getAdresse();

            System.out.print("Altersgruppe (" + altMitglied_info.getAltersgruppe() + "): ");
            String altersgruppeInput = myScanner.nextLine().trim().toUpperCase();

            Altersgruppe altersgruppe;
            if (altersgruppeInput.isEmpty()) {
                altersgruppe = altMitglied_info.getAltersgruppe();
            } else {
                altersgruppe = Altersgruppe.valueOf(altersgruppeInput);
            }


            Mitglied bearbeitetesMitglied = new Mitglied(
                    altMitglied_info.getId(),
                    vorname,
                    nachname,
                    geschlecht,
                    alter,
                    new Kontaktinfo(email, telefon, adresse),
                    altersgruppe
            );

            boolean result_Bearbeiten = MitgliedService.bearbeitenMitglied(bearbeitetesMitglied);
            if (result_Bearbeiten) {
                System.out.println("Mitglied wurde aktualisiert.");
            } else {
                System.out.println("Mitglied konnte nicht aktualisiert werden.");
            }



        } catch (NumberFormatException error) {
            System.out.println("Ungültiges Zahlenformat bei der ID oder Alter.");
        } catch (IllegalArgumentException error) {
            System.out.println("Ungültige Eingabe bei Geschlecht oder Altersgruppe.");
        } catch (Exception error) {
            System.out.println("beim Bearbeiten gibt es Problem: " + error.getMessage());
        }


    }// ---mitgliedBearbeiten


}
