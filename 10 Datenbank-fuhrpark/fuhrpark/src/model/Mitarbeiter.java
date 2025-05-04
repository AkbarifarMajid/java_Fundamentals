package model;


public class Mitarbeiter {

    private int id;
    private String vorname;
    private String nachname;
    private String position;
    private String telefonnummer;

    public Mitarbeiter(String vorname, String nachname, String position, String telefonnummer) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.position = position;
        this.telefonnummer = telefonnummer;
    }
    public  Mitarbeiter() {

    }



    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getVorname() { return vorname; }
    public String getNachname() { return nachname; }
    public String getPosition() { return position; }
    public String getTelefonnummer() { return telefonnummer; }




}
