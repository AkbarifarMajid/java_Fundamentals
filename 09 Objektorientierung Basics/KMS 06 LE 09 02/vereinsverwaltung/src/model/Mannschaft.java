package model;

import java.util.ArrayList;
import java.util.List;

public class Mannschaft {
    private String name;
    private Geschlecht geschlecht;
    private Altersgruppe altersgruppe;
    private List<Spieler> spieler = new ArrayList<>();
    private List<Trainer> trainer = new ArrayList<>();

     public Mannschaft(String name, Geschlecht geschlecht, Altersgruppe altersgruppe) {
        this.name = name;
        this.geschlecht = geschlecht;
        this.altersgruppe = altersgruppe;
    }


    public void setName(String name) {this.name = name;}

    public Geschlecht getGeschlecht() {return geschlecht;}

    public void setGeschlecht(Geschlecht geschlecht) {this.geschlecht = geschlecht;}

    public Altersgruppe getAltersgruppe() {return altersgruppe;}

    public void setAltersgruppe(Altersgruppe altersgruppe) {this.altersgruppe = altersgruppe;}

    public void setSpieler(List<Spieler> spieler) {this.spieler = spieler;}

    public void setTrainer(List<Trainer> trainer) {this.trainer = trainer;}

    public String getName() { return name; }

    public List<Spieler> getSpieler() { return spieler; }
    public List<Trainer> getTrainer() { return trainer; }

    public void addSpieler(Spieler mySpieler) {spieler.add(mySpieler);}

    public void addTrainer(Trainer myTrainer) {trainer.add(myTrainer);}

    // delete Spieler
    public boolean removeSpielerByNachname(String nachname) {
        boolean entfernt = spieler.removeIf(s -> s.getNachname().equalsIgnoreCase(nachname));
        if (entfernt) {
            System.out.println("Spieler gelöscht: " + nachname);
        } else {
            System.out.println("Kein Spieler mit Nachname '" + nachname + "' gefunden.");
        }
        return entfernt;
    }


    //Delete Trainer
    public boolean removeTrainerByNachname(String nachname) {
        boolean entfernt = trainer.removeIf(t -> t.getNachname().equalsIgnoreCase(nachname));
        if (entfernt) {
            System.out.println("Trainer gelöscht: " + nachname);
        } else {
            System.out.println("Kein Trainer mit Nachname '" + nachname + "' gefunden.");
        }
        return entfernt;
    }


    @Override
    public String toString() {
        return name + " [" + geschlecht + ", " + altersgruppe + "]\n  Spieler: " + spieler + "\n  Trainer: " + trainer;
    }
}
