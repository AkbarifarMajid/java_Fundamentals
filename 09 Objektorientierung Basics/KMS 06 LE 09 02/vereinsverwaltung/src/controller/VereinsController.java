//Controller
package controller;

import model.*;

import java.util.List;

public class VereinsController {
    private Verein verein;

    //Constructor
    public VereinsController(Verein verein) {
        this.verein = verein;
    }

    // Mannschaft hinzufügen
    public void neuMannschaftHinzufuegen(String name, Geschlecht geschlect, Altersgruppe alter) {
        // Erstelle ein neues Mannschaftsobjekt
        Mannschaft neueMannschaft = new Mannschaft(name, geschlect, alter);

        verein.addMannschaft(neueMannschaft);
    }

    // Mannschaft entfernen
    public void mannschaftEntfernen(String name) {
        boolean entfernt = verein.removeMannschaftByName(name);
        if (entfernt) {
            System.out.println("Mannschaft gelöscht: " + name);
        } else {
            System.out.println("Mannschaft nicht gefunden: " + name);
        }
    }


    // Spieler zu einer Mannschaft hinzufügen
    public void spielerHinzufuegen(String teamName, Spieler spieler) {
        for (Mannschaft team : verein.getMannschaften()) {
            // Wenn der Teamname mit dem eingegebenen Team übereinstimmt
            if (team.getName().trim().equalsIgnoreCase(teamName.trim())) {
                team.addSpieler(spieler);
                System.out.println("Neue Spieler wurden dem Team hinzugefügt." + teamName);
                return;
            }
        }
        System.out.println("Mannschaft nicht gefunden: " + teamName);
    }

    // Spieler aus einer Mannschaft entfernen (nach Nachname)
    public void spielerEntfernen(String teamName, String nachname) {
        for (Mannschaft team : verein.getMannschaften()) {
            // Wenn der Teamname mit dem eingegebenen Team übereinstimmt
            if (team.getName().trim().equalsIgnoreCase(teamName.trim())) {
                // Den Spieler aus dem Team entfernen
                boolean entfernt = team.removeSpielerByNachname(nachname);
                if (entfernt) {
                    System.out.println("Spieler gelöscht: " + nachname);
                } else {
                    System.out.println("Kein Spieler mit Nachname '" + nachname + "' gefunden.");
                }
                return;
            }
        }
        System.out.println("Mannschaft nicht gefunden: " + teamName);
    }

    // Trainer zu einer Mannschaft hinzufügen
    public void neuTrainerHinzufuegen(String teamName, Trainer trainer) {
        // Wenn der Teamname mit dem eingegebenen Team übereinstimmt
        for (Mannschaft team : verein.getMannschaften()) {
            if (team.getName().trim().equalsIgnoreCase(teamName.trim())) {
                team.addTrainer(trainer);
                return;
            }
        }
        System.out.println("Mannschaft nicht gefunden: " + teamName);
    }

    // Trainer aus einer Mannschaft entfernen (nach Nachname)
    public void trainerEntfernen(String teamName, String nachname) {
        for (Mannschaft team : verein.getMannschaften()) {
            // Wenn der Teamname mit dem eingegebenen Team übereinstimmt
            if (team.getName().trim().equalsIgnoreCase(teamName.trim())) {
                boolean entfernt = team.removeTrainerByNachname(nachname);
                if (entfernt) {
                    System.out.println("Trainer gelöscht: " + nachname);
                } else {
                    System.out.println("Kein Trainer mit Nachname '" + nachname + "' gefunden.");
                }
                return;
            }
        }
        System.out.println("Mannschaft nicht gefunden: " + teamName);
    }

    // Gesamten Verein anzeigen
    public void vereinAnzeigen() {
        System.out.println(verein);
    }

    // Alle Mannschaften auflisten
    public void alleMannschaftenAnzeigen() {
        for (Mannschaft m : verein.getMannschaften()) {
            System.out.println("- " + m.getName());
        }
    }

    // Alle Spieler im Verein anzeigen
    public void alleSpielerAnzeigen() {
        for (Mannschaft team : verein.getMannschaften()) {
            for (Spieler spieler : team.getSpieler()) {
                System.out.println(spieler);
            }
        }
    }

    // Alle Trainer im Verein anzeigen
    public void alleTrainerAnzeigen() {
        for (Mannschaft team : verein.getMannschaften()) {
            for (Trainer trainer : team.getTrainer()) {
                System.out.println(trainer);
            }
        }
    }

    // Spieler eines bestimmten Teams anzeigen
    public void spielerEinesTeamsAnzeigen(String teamName) {
        for (Mannschaft team : verein.getMannschaften()) {
            if (team.getName().trim().equalsIgnoreCase(teamName.trim())) {
                for (Spieler spieler : team.getSpieler()) {
                    System.out.println(spieler);
                }
                return;
            }
        }
        System.out.println("Mannschaft nicht gefunden: " + teamName);
    }

    // Suche nach Spieler im gesamten Verein (nach Nachname)
    public void spielerNachNachnameSuchen(String nachname) {
        for (Mannschaft team : verein.getMannschaften()) {
            for (Spieler spieler : team.getSpieler()) {
                if (spieler.getNachname().equalsIgnoreCase(nachname)) {
                    System.out.println("Gefunden in " + team.getName() + ": " + spieler);
                }
            }
        }
    }

    // Zugriff auf alle Mannschaften
    public List<Mannschaft> getAlleMannschaften() {
        return verein.getMannschaften();
    }
}
