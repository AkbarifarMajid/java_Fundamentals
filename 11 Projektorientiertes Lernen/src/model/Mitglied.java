package model;

public class Mitglied extends Person {
    private Altersgruppe altersgruppe;

    public Mitglied(int id, String vorname, String nachname, Geschlecht geschlecht, int alter, Kontaktinfo kontaktinfo, Altersgruppe altersgruppe) {
        super(id, vorname, nachname, geschlecht, alter, kontaktinfo);
        this.altersgruppe = altersgruppe;
    }

    public Mitglied(String vorname, String nachname, Geschlecht geschlecht, int alter, Kontaktinfo kontaktinfo, Altersgruppe altersgruppe) {
        super(vorname, nachname, geschlecht, alter, kontaktinfo); // استفاده از سازنده بدون id در کلاس والد
        this.altersgruppe = altersgruppe;
    }

    //Getter und Setter
    public Altersgruppe getAltersgruppe() {return altersgruppe;}
    public void setAltersgruppe(Altersgruppe altersgruppe) {this.altersgruppe = altersgruppe;}

    @Override
    public String toString() {
        return super.toString() + "\nAltersgruppe: " + altersgruppe;
    }
}
