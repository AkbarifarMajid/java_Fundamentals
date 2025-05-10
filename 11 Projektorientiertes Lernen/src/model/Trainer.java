package model;

//Repräsentiert einen Trainer im Verein.

public class Trainer extends Person {
    private Rolle rolle;
    private String lizenzstufe;

    //Konstruktor für einen Trainer.
    public Trainer(int id, String vorname, String nachname, Geschlecht geschlecht, int alter, Kontaktinfo kontaktinfo, Rolle rolle, String lizenzstufe) {
        super(id, vorname, nachname, geschlecht, alter, kontaktinfo);
        this.rolle = rolle;
        this.lizenzstufe = lizenzstufe;
    }

    //Konstruktor für einen Trainer.
    public Trainer( String vorname, String nachname, Geschlecht geschlecht, int alter, Kontaktinfo kontaktinfo, Rolle rolle, String lizenzstufe) {
        super( vorname, nachname, geschlecht, alter, kontaktinfo);
        this.rolle = rolle;
        this.lizenzstufe = lizenzstufe;
    }
    //Getter und Setter

    public Rolle getRolle() {return rolle;}
    public void setRolle(Rolle rolle) {this.rolle = rolle;}
    public String getLizenzstufe() {return lizenzstufe;}
    public void setLizenzstufe(String lizenzstufe) {this.lizenzstufe = lizenzstufe;}

    @Override
    public String toString() {
        return super.toString() +
                "\nRolle: " + rolle.getBeschreibung() +"(" + rolle.name()+  ")"+
                "\nLizenzstufe: " + lizenzstufe;
    }
}
