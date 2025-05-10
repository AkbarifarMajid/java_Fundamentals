package view;

import model.*;
import service.TrainerService;

import java.util.List;
import java.util.Scanner;

public class TrainerView {

    private static final Scanner myScanner = new Scanner(System.in);

    // Zeigt alle Trainer aus der Datenbank an
    public static void trainerAnzeigen(){
        try {
            List<Trainer> trainerListe = TrainerService.alleTrainerAnzeigen();

            if (trainerListe.isEmpty()) {
                System.out.println("List Trainer ist Leer !");
            } else {
                System.out.println("#### Trainer ####");
                for (Trainer trainer : trainerListe) {
                    System.out.println(trainer);
                }
            }

        } catch (Exception error) {
            System.out.println(" beim Anzeigen der Trainer gibt es Problem: " + error.getMessage());
        }
    } //---- End trainerAnzeigen


    // Fügt ein neues Trainer über die Konsole hinzu
    public static void trainerHinzufuegen() {

        System.out.print("Vorname: ");
        String vorname = myScanner.nextLine().trim();

        System.out.print("Nachname: ");
        String nachname = myScanner.nextLine().trim();

        System.out.println("Verfügbare MAENNLICH, WEIBLICH, DIVERS, ");
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

        System.out.println("Verfügbare CHEFTRAINER, TAKTIKTRAINER, ATHLETIKTRAINER, ANALYST, TORWARTTRAINER");
        System.out.print("Rolle: ");
        String rolleInput = myScanner.nextLine().trim().toUpperCase();

        System.out.print("Lizenzstufe: ");
        String lizenzstufe = myScanner.nextLine().trim();

        try{
            Trainer neuTrainer = new Trainer(
                    vorname,
                    nachname,
                    model.Geschlecht.valueOf(geschlechtEingabe),
                    alter,
                    new model.Kontaktinfo(email, telefon, adresse),
                    Rolle.valueOf(rolleInput),
                    lizenzstufe
            );

            TrainerService.trainerHinzufuegen(neuTrainer);


        } catch (IllegalArgumentException error) {
            System.out.println(" Eingabe bei Geschlecht oder Roll ist falsch!");

        }catch (Exception error) {
            System.out.println("beim Erstellen des Trainer gibt es problem: " + error.getMessage());
        }
    }//----End trainerHinzufuegen


    // Sucht ein Trainer anhand der ID und zeigt die Daten an
    public static void trainerSuchen() {
        System.out.print("Bitte geben Sie die Trainer-ID ein: ");

        try{
            int id_Suchen = Integer.parseInt(myScanner.nextLine().trim());
            Trainer trainer = TrainerService.suchenTrainer(id_Suchen);

            System.out.println(trainer);

        }catch (NumberFormatException error){
            System.out.println(" Bitte geben Sie eine gültige ID ein.");

        }catch (Exception error){
            System.out.println("bei der Suchen gibt es Problem: ." + error.getMessage());
        }

    }// --- End trainerSuchen


    // Löscht ein Trainer anhand der eingegebenen ID
    public static void trainerLoschen(){
        System.out.print("Bitte geben Sie die ID des Trainer ein, das gelöscht werden soll: ");

        try {
            int id_Loschen = Integer.parseInt(myScanner.nextLine().trim());

            // vorheriges Anzeigen der Daten zur Bestätigung
            Trainer trainer = new TrainerService().suchenTrainer(id_Loschen);

            if (trainer == null) {
                System.out.println("Kein Trainer mit dieser ID gefunden.");
                return;
            }

            System.out.println(trainer);

            System.out.print("Sind Sie sicher, dass Sie dieses Trainer löschen möchten? (j/n): ");
            String bestaetigung = myScanner.nextLine().trim().toLowerCase();

            if (bestaetigung.equals("j")) {
                boolean resultLoschen = TrainerService.loschenTrainer(id_Loschen);
                if (resultLoschen) {
                    System.out.println("Trainer wurde gelöscht.");
                } else {
                    System.out.println("Trainer konnte nicht gelöscht werden.");
                }
            } else {
                System.out.println("Löschvorgang abgebrochen.");
            }

        }catch (NumberFormatException error) {
            System.out.println("Bitte geben Sie eine gültige ID ein.");
        } catch (Exception error) {
            System.out.println(" beim Löschen Trainer gibt es Problem: " + error.getMessage());
        }

    }// ---End trainerLoschen


    // Bearbeitet ein bestehendes Trainer anhand der ID
    public static void trainerBearbeiten(){
        System.out.print("Bitte geben Sie die ID des Trainer ein, das bearbeitet werden soll: ");

        try {
            int id_Trainer = Integer.parseInt(myScanner.nextLine());
            Trainer altTrainer_Info = TrainerService.suchenTrainer(id_Trainer);


            if (altTrainer_Info == null) {
                System.out.println("Trainer mit dieser ID nicht gefunden.");
                return;
            }

            System.out.println("\nAktuelle Daten des Trainer:");
            System.out.println(altTrainer_Info);
            System.out.println("\nNeue Daten eingeben (leer lassen, um bisherigen Wert zu behalten):");

            System.out.print("Vorname [" + altTrainer_Info.getVorname() + "]: ");
            String vorname = myScanner.nextLine().trim();
            if (vorname.isEmpty()) vorname = altTrainer_Info.getVorname();

            System.out.print("Nachname [" + altTrainer_Info.getNachname() + "]: ");
            String nachname = myScanner.nextLine().trim();
            if (nachname.isEmpty()) nachname = altTrainer_Info.getNachname();

            System.out.print("Geschlecht (" + altTrainer_Info.getGeschlecht() + "): ");
            String geschlechtInput = myScanner.nextLine().trim().toUpperCase();
            Geschlecht geschlecht;
            if(geschlechtInput.isEmpty()){
                geschlecht = altTrainer_Info.getGeschlecht();
            }else {
                geschlecht = Geschlecht.valueOf(geschlechtInput);
            }

            System.out.print("Alter [" + altTrainer_Info.getAlter() + "]: ");
            String alterInput = myScanner.nextLine().trim();
            int alter = alterInput.isEmpty() ? altTrainer_Info.getAlter() : Integer.parseInt(alterInput);

            System.out.print("E-Mail [" + altTrainer_Info.getKontaktinfo().getEmail() + "]: ");
            String email = myScanner.nextLine().trim();
            if (email.isEmpty()) email = altTrainer_Info.getKontaktinfo().getEmail();

            System.out.print("Telefon [" + altTrainer_Info.getKontaktinfo().getTelefonnummer() + "]: ");
            String telefon = myScanner.nextLine().trim();
            if (telefon.isEmpty()) telefon = altTrainer_Info.getKontaktinfo().getTelefonnummer();

            System.out.print("Adresse [" + altTrainer_Info.getKontaktinfo().getAdresse() + "]: ");
            String adresse = myScanner.nextLine().trim();
            if (adresse.isEmpty()) adresse = altTrainer_Info.getKontaktinfo().getAdresse();

            System.out.print("Rolle (" + altTrainer_Info.getRolle() + "): ");
            String rolleInput = myScanner.nextLine().trim().toUpperCase();
            Rolle rolle = rolleInput.isEmpty() ? altTrainer_Info.getRolle() : Rolle.valueOf(rolleInput);

            System.out.print("Lizenzstufe [" + altTrainer_Info.getLizenzstufe() + "]: ");
            String lizenz = myScanner.nextLine().trim();
            if (lizenz.isEmpty()) lizenz = altTrainer_Info.getLizenzstufe();

            // Neuer Trainer mit ID
            Trainer bearbeitetesTrainer= new Trainer(
                    altTrainer_Info.getId(),
                    vorname,
                    nachname,
                    geschlecht,
                    alter,
                    new Kontaktinfo(email, telefon, adresse),
                    rolle,
                    lizenz
            );

            boolean result_Bearbeiten = TrainerService.bearbeitenTrainer(bearbeitetesTrainer);
            if (result_Bearbeiten) {
                System.out.println("Trainer wurde aktualisiert.");
            } else {
                System.out.println("Trainer konnte nicht aktualisiert werden.");
            }



        } catch (NumberFormatException error) {
            System.out.println("Ungültiges Zahlenformat bei der ID oder Alter.");
        } catch (IllegalArgumentException error) {
            System.out.println("Ungültige Eingabe bei Geschlecht oder Altersgruppe.");
        } catch (Exception error) {
            System.out.println("beim Bearbeiten gibt es Problem: " + error.getMessage());
        }


    }// ---trainerBearbeiten


}
