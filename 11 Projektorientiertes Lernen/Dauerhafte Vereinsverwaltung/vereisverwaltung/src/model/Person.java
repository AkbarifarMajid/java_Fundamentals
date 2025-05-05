package model;

public abstract class Person {
    protected int id;
    protected String vorname;
    protected String nachname;
    protected Geschlecht geschlecht;
    protected int alter;
    protected Kontaktinfo kontaktinfo;

    public Person(int id, String vorname, String nachname, Geschlecht geschlecht, int alter, Kontaktinfo kontaktinfo) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
        this.alter = alter;
        this.kontaktinfo = kontaktinfo;
    }

    // Getter und Setter
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getVorname() {return vorname;}
    public void setVorname(String vorname) {this.vorname = vorname;}
    public String getNachname() {return nachname;}
    public void setNachname(String nachname) {this.nachname = nachname;}
    public Geschlecht getGeschlecht() {return geschlecht;}
    public void setGeschlecht(Geschlecht geschlecht) {this.geschlecht = geschlecht;}
    public int getAlter() {return alter;}
    public void setAlter(int alter) {this.alter = alter;}
    public Kontaktinfo getKontaktinfo() {return kontaktinfo;}
    public void setKontaktinfo(Kontaktinfo kontaktinfo) {this.kontaktinfo = kontaktinfo;}

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + vorname + " " + nachname +
                ", Geschlecht: " + geschlecht +
                ", Alter: " + alter +
                ", Kontakt: " + kontaktinfo;
    }
}
