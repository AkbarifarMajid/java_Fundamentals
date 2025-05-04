package model;

import database.FahrzeugDAO;

import java.util.ArrayList;


public abstract class Fahrzeug {

    protected Mitarbeiter besitzer;

    protected int id;
    protected String typ_Fahrzeug;
    protected String hersteller;
    protected String modell;
    protected int baujahr;
    protected double kilometerstand;
    protected double kraftstoff;
    protected String standort;
    protected String wartungstermine;



    public Fahrzeug(String hersteller, String modell, int baujahr) {
        this.hersteller = hersteller;
        this.modell = modell;
        this.baujahr = baujahr;
        this.kilometerstand = 0.0;
        this.kraftstoff = 0.0;
        this.standort = "Unbekannt";
        this.wartungstermine = "Unbekannt Datum";
    }
    public Fahrzeug(int id, String typ_Fahrzeug, String hersteller, String modell, int baujahr) {
        this.id = id;
        this.typ_Fahrzeug = typ_Fahrzeug;
        this.hersteller = hersteller;
        this.modell = modell;
        this.baujahr = baujahr;
        this.kilometerstand = 0.0;
        this.kraftstoff = 0.0;
        this.standort = "Unbekannt";
        this.wartungstermine = "Unbekannt Datum";
    }

    public Fahrzeug() {

    }


    // Methoden
    public void fahren(double kilometer) {
        if (kilometer > 0) {
            this.kilometerstand += kilometer;
        }
    }

    public void tanken(double liter) {
        if (liter > 0) {
            this.kraftstoff += liter;
        }
    }

    public void setwartungstermine(String datum) {
        //Validate ????
        if (datum != null && !datum.isEmpty()) {

            this.wartungstermine = datum;
        }
    }

    public void setBesitzer(Mitarbeiter m)
    { this.besitzer = m;
    }
    public Mitarbeiter getBesitzer() { return besitzer; }


    public void setKilometerstand(double kilometerstand) {
        this.kilometerstand = kilometerstand;
    }

    public String getWartungstermine() {
        return wartungstermine;
    }

    public String getTyp_Fahrzeug() {
        return typ_Fahrzeug;
    }

    public void setTyp_Fahrzeug(String typ_Fahrzeug) {
        this.typ_Fahrzeug = typ_Fahrzeug;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getHersteller() { return hersteller; }
    public String getModell() { return modell; }
    public int getBaujahr() { return baujahr; }
    public double getKilometerstand() { return kilometerstand; }
    public double getKraftstoff() { return kraftstoff; }
    public String getStandort() { return standort; }
    public void setStandort(String standort) { this.standort = standort; }
    //public ArrayList<String> getWartungstermine() { return wartungstermine; }

    public void setKraftstoff(double kraftstoff) {
        this.kraftstoff = kraftstoff;
    }

    public static Fahrzeug fahrzeugSuchen(int farzeugId){
        return FahrzeugDAO.findeNachId(farzeugId);

    }

    public abstract void anzeigen();

}
