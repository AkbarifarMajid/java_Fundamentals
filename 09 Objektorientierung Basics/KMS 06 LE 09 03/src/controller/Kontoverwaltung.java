package controller;
import model.Konto;
import java.util.ArrayList;


public class Kontoverwaltung {
    private ArrayList<Konto> kontoList;

    // Dieser Konstruktor erstellt standardmäßig eine neue, leere Liste von Konten.
    public Kontoverwaltung() {
        kontoList = new ArrayList<>();
    }

    // Optional: Getter für die Liste
    public ArrayList<Konto> getKontolist() {
        return kontoList;
    }



    // Suche nach Kontonummer
    public Konto kontoSuchen(int nummer) {
        for (Konto myKonto : kontoList) {
            if (myKonto.getMyNumber() == nummer) {
                return myKonto;
            }
        }
        return null;
    }



    // Neues Konto hinzufügen
    public void neukontoHinzufugen(Konto konto) {
        if (konto != null && konto.isValid()) {
            kontoList.add(konto);
            System.out.println("Konto erfolgreich hinzugefügt.");
        } else {
            System.out.println("Ungültiges Konto. Es wurde nicht hinzugefügt.");
        }
    }


    // Konto mit Nummer löschen
    public void kontoEntfernen(int nummer) {
        Konto gefunden = kontoSuchen(nummer);
        if (gefunden != null) {
            kontoList.remove(gefunden);
            System.out.println("Konto wurde gelöscht.");
        } else {
            System.out.println("Kein Konto mit dieser Nummer gefunden.");
        }
    }




    // Konkreten Kontostand anzeigen
    public void kontostandAnzeigen(int nummer) {
        Konto gefunden = kontoSuchen(nummer);
        if (gefunden != null) {
            System.out.println("Kontostand: " + gefunden.getMyBalance());
        } else {
            System.out.println("Konto wurde nicht gefunden.");
        }
    }



    // Alle Konten anzeigen
    public void alleKontenAnzeigen() {
        if (kontoList.isEmpty()) {
            System.out.println("Keine Konten vorhanden.");
        } else {
            for (Konto konto : kontoList) {
                System.out.println(konto); // verwendet toString()
            }
        }
    }



}// End Kontoverwaltung
