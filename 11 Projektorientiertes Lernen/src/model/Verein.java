package model;
import java.util.ArrayList;
import java.util.List;

//Repräsentiert einen Sportverein mit Mitgliedern, Trainern und Mannschaften.
public class Verein {
    private int id;
    private String name;
    private String adresse;
    private List<Mitglied> mitglieder;
    private List<Trainer> trainer;
    private List<Mannschaft> mannschaften;

    //Konstruktor zur Erstellung eines neuen Vereins.
    public Verein(int id, String name, String adresse) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.mitglieder = new ArrayList<>();
        this.trainer = new ArrayList<>();
        this.mannschaften = new ArrayList<>();
    }


    // Getter & Setter


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAdresse() {return adresse;}
    public void setAdresse(String adresse) {this.adresse = adresse;}
    public List<Mitglied> getMitglieder() {return mitglieder;}
    public void setMitglieder(List<Mitglied> mitglieder) {this.mitglieder = mitglieder;}
    public List<Trainer> getTrainer() {return trainer;}
    public void setTrainer(List<Trainer> trainer) {this.trainer = trainer;}
    public List<Mannschaft> getMannschaften() {return mannschaften;}
    public void setMannschaften(List<Mannschaft> mannschaften) {this.mannschaften = mannschaften;}



     //Fügt ein neues Mitglied zum Verein hinzu.
    public void addMitglied(Mitglied m) {
        mitglieder.add(m);
    }


    //Fügt ein neues Trainer zum Verein hinzu.
    public void addTrainer(Trainer trainer_add) {
        trainer.add(trainer_add);
    }

    // Fügt eine neue Mannschaft zum Verein hinzu.
    public void addMannschaft (Mannschaft mannschaft_add){
        mannschaften.add(mannschaft_add);
    }

    //Gibt eine textuelle Zusammenfassung des Vereins zurück.

    @Override
    public String toString() {
        return "Verein{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                ", mitglieder=" + mitglieder +
                ", trainer=" + trainer +
                ", mannschaften=" + mannschaften +
                '}';
    }



}
