package model;

public class Trainer extends Person {

    private String rolle;

    public Trainer(String vorname, String nachname, String rolle, Kontaktinfo kontakt) {
        super(vorname, nachname, kontakt);
        this.rolle = rolle;
    }

    public String getRolle() {return rolle;}

    public void setRolle(String rolle) {this.rolle = rolle;}

    @Override
    public String toString() {
        return vorname + " " + nachname + " (" + rolle + ")";
    }
}
