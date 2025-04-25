package model;

public class Spieler extends Person{

    private int trikotnummer;


    public Spieler(String vorname, String nachname, int trikotnummer, Kontaktinfo kontakt) {
        super(vorname, nachname, kontakt);
        this.trikotnummer = trikotnummer;
    }


    public int getTrikotnummer() {return trikotnummer;}

    public void setTrikotnummer(int trikotnummer) {this.trikotnummer = trikotnummer;}


    @Override
    public String toString() {
        return vorname + " " + nachname + " (Nr. " + trikotnummer + ", Email: " + kontakt.email() + ")";
    }
}
