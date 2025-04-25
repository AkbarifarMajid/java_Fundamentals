import java.util.ArrayList;

public abstract class Fahrzeug {
    private static int fahrzeugZaehler = 0;

    private int id;
    private String hersteller;
    private String modell;
    private int baujahr;
    private double kilometerstand;
    private double getankterKraftstoff;
    private String standort;
    private ArrayList<String> wartungstermine;
/*
    public Fahrzeug(int id, String hersteller, String modell, int baujahr, double kilometerstand, double getankterKraftstoff, String standort, ArrayList<String> wartungstermine) {
        this.id = id;
        this.hersteller = hersteller;
        this.modell = modell;
        this.baujahr = baujahr;
        this.kilometerstand = kilometerstand;
        this.getankterKraftstoff = getankterKraftstoff;
        this.standort = standort;
        this.wartungstermine = wartungstermine;
    }

 */


    public Fahrzeug(int id, String hersteller, String modell, int baujahr) {
        this.id = id;
        this.hersteller = hersteller;
        this.modell = modell;
        this.baujahr = baujahr;
        this.kilometerstand = 0.0;
        this.getankterKraftstoff = 0.0;
        this.standort = "Unbekannt Plaz";
        this.wartungstermine = new ArrayList<>();

        fahrzeugZaehler++;
    }



    //-----------------------------------------------------------------------------------
    // Getter und Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public double getKilometerstand() {
        return kilometerstand;
    }

    public void setKilometerstand(double kilometerstand) {
        this.kilometerstand = kilometerstand;
    }

    public double getGetankterKraftstoff() {
        return getankterKraftstoff;
    }

    public void setGetankterKraftstoff(double getankterKraftstoff) {
        this.getankterKraftstoff = getankterKraftstoff;
    }

    public String getStandort() {
        return standort;
    }

    public void setStandort(String neuerStandort) {
        this.standort = neuerStandort;
    }

    public ArrayList<String> getWartungstermine() {
        return wartungstermine;
    }

    public void setWartungstermine(ArrayList<String> wartungstermine) {
        this.wartungstermine = wartungstermine;
    }


    public static int getFahrzeugZaehler() {
        return fahrzeugZaehler;
    }
//----------------------------------------------------------------------------------------
    //Notwendige Methoden für Fahrzeug

    public void fahren(double km){
        this.kilometerstand +=km;
    }

    public void tanken(double liter) {
        this.getankterKraftstoff += liter;
    }

    public void wartungHinzufuegen(String datum) {
        this.wartungstermine.add(datum);
    }

//-------------------------------------------------------------------------------
    //toString-Methode zum Anzeigen zusammenfassender Objektinformationen


    @Override
    public String toString() {
        return String.format(
                "Fahrzeug[ID=%d, Hersteller=%s, Modell=%s, Baujahr=%d, km=%.1f, Kraftstoff=%.1f, Standort=%s, Wartungstermine=%s]",
                id, hersteller, modell, baujahr, kilometerstand, getankterKraftstoff, standort, wartungstermine);
    }

    // Abstrakte Methode zum Drucken detaillierter Informationen
    public abstract void anzeigen();



}
