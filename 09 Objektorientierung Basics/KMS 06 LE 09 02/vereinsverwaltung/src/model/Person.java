package model;

public abstract class Person {
    protected String vorname;
    protected String nachname;
    protected Kontaktinfo kontakt;

    public Person(String vorname, String nachname, Kontaktinfo kontakt) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.kontakt = kontakt;
    }

    public String getVorname() {return vorname;}

    public void setVorname(String vorname) {this.vorname = vorname;}

    public String getNachname() {return nachname;}

    public void setNachname(String nachname) {this.nachname = nachname;}

    public Kontaktinfo getKontakt() {return kontakt;}

    public void setKontakt(Kontaktinfo kontakt) {this.kontakt = kontakt;}


    @Override
    public String toString() {
        return vorname + " " + nachname + "(Email: " + kontakt.email() +")";

    }
}
