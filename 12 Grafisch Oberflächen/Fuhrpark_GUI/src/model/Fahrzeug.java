
package model;

import dao.FahrzeugDAO;

// Abstrakte Basisklasse für alle Fahrzeugtypen
public abstract class Fahrzeug {

    // Zugewiesener Besitzer (Mitarbeiter) des Fahrzeugs
   // protected Mitarbeiter besitzer;


    protected int id;
    protected String typ_Fahrzeug;
    protected String hersteller;
    protected String modell;
    protected int baujahr;
    protected String kennzeichen;
    protected double kilometerstand;

    public Fahrzeug(String hersteller, String modell, int baujahr, String kennzeichen) {
        this.hersteller = hersteller;
        this.modell = modell;
        this.baujahr = baujahr;
        this.kennzeichen = kennzeichen;

    }


    public Fahrzeug(int id, String typ_Fahrzeug, String hersteller, String modell, int baujahr, String kennzeichen) {
        this(hersteller, modell, baujahr, kennzeichen);
        this.id = id;
        this.typ_Fahrzeug = typ_Fahrzeug;
    }

    public Fahrzeug() {}

    //public abstract void anzeigen();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTyp_Fahrzeug() { return typ_Fahrzeug; }
    public void setTyp_Fahrzeug(String typ) { this.typ_Fahrzeug = typ; }

    public String getHersteller() { return hersteller; }
    public void setHersteller(String hersteller) { this.hersteller = hersteller; }

    public String getModell() { return modell; }
    public void setModell(String modell) { this.modell = modell; }

    public int getBaujahr() { return baujahr; }
    public void setBaujahr(int baujahr) { this.baujahr = baujahr; }

    public String getKennzeichen() { return kennzeichen; }
    public void setKennzeichen(String kennzeichen) { this.kennzeichen = kennzeichen; }

    public double getKilometerstand() { return kilometerstand; }
    public void setKilometerstand(double km) { this.kilometerstand = km; }

    public static Fahrzeug fahrzeugSuchen(int fahrzeugId) {
        return FahrzeugDAO.find_Fahrzeug_Id(fahrzeugId);
    }



    @Override
    public String toString() {
        return "ID: " + id + "\nTyp: " +hersteller  + "\nMarke: " + modell + "\nKM: " + kilometerstand;
    }



}
