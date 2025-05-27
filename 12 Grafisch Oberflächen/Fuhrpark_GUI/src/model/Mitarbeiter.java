
package model;

  // Datenmodell für Mitarbeiter - passend zur Tabelle 'mitarbeiter' in der Datenbank.

public class Mitarbeiter {

    private int id;
    private String vorname;
    private String nachname;
    private String abteilung;
    private String telefon;
    private String email;
    private String einstellungsdatum;

    // Konstruktor für neue Mitarbeiter ohne ID
    public Mitarbeiter(String vorname, String nachname, String abteilung, String telefon, String email, String einstellungsdatum) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.abteilung = abteilung;
        this.telefon = telefon;
        this.email = email;
        this.einstellungsdatum = einstellungsdatum;
    }

    // Konstruktor mit ID
    public Mitarbeiter(int id, String vorname, String nachname, String abteilung, String telefon, String email, String einstellungsdatum) {
        this(vorname, nachname, abteilung, telefon, email, einstellungsdatum);
        this.id = id;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getVorname() { return vorname; }
    public void setVorname(String vorname) { this.vorname = vorname; }

    public String getNachname() { return nachname; }
    public void setNachname(String nachname) { this.nachname = nachname; }

    public String getAbteilung() { return abteilung; }
    public void setAbteilung(String abteilung) { this.abteilung = abteilung; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEinstellungsdatum() { return einstellungsdatum; }
    public void setEinstellungsdatum(String einstellungsdatum) { this.einstellungsdatum = einstellungsdatum; }

}
