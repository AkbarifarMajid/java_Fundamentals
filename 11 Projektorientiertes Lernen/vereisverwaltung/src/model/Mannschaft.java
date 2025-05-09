package model;

import java.util.ArrayList;
import java.util.List;

public class Mannschaft {
    private int id;
    private String name;
    private String liga;
    private List<Mitglied> mitglieder;
    private List<Trainer> trainerListe;

    public Mannschaft(int id, String name, String liga) {
        this.id = id;
        this.name = name;
        this.liga = liga;
        this.mitglieder = new ArrayList<>();
        this.trainerListe = new ArrayList<>();
    }

    // Konstruktor ohne ID – für neue Einträge vor dem Speichern in der DB
    public Mannschaft(String name, String liga) {
        this.name = name;
        this.liga = liga;
        this.mitglieder = new ArrayList<>();
        this.trainerListe = new ArrayList<>();
    }




    // Getter & Setter
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getLiga() {return liga;}
    public void setLiga(String liga) {this.liga = liga;}
    public List<Mitglied> getMitglieder() {return mitglieder;}
    public void setMitglieder(List<Mitglied> mitglieder) {this.mitglieder = mitglieder;}
    public List<Trainer> getTrainerListe() {return trainerListe;}
    public void setTrainerListe(List<Trainer> trainerListe) {this.trainerListe = trainerListe;}

    // Fügt ein Mitglied zur Mannschaft hinzu.
    public void addMitglied(Mitglied mitglid) {mitglieder.add(mitglid);}

    // Fügt einen Trainer zur Mannschaft hinzu.
    public void addTrainer(Trainer trainer) {trainerListe.add(trainer);}

    @Override
    public String toString() {
        return "ID: " + id +
                "\nMannschaft: " + name + ", Liga: " + liga +
                ", Mitglieder: " + mitglieder.size() +
                ", Trainer: " + trainerListe.size();
    }
}
