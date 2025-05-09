package model;

public class Kontaktinfo {
    private String email;
    private String telefonnummer;
    private String adresse;

    public Kontaktinfo(String email, String telefonnummer, String adresse) {
        this.email = email;
        this.telefonnummer = telefonnummer;
        this.adresse = adresse;
    }

    // Getter und Setter
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getTelefonnummer() {return telefonnummer;}
    public void setTelefonnummer(String telefonnummer) {this.telefonnummer = telefonnummer;}
    public String getAdresse() {return adresse;}
    public void setAdresse(String adresse) {this.adresse = adresse;}

    @Override
    public String toString() {
        return email + ", " + telefonnummer + ", " + adresse;
    }
}
