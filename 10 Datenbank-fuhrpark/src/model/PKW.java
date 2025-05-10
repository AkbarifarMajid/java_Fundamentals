package model;

import database.PkwDAO;


public class PKW extends Fahrzeug {

    private int sitzanzahl;

    public PKW(String hersteller, String modell, int baujahr, int sitzanzahl) {
        super(hersteller, modell, baujahr);
        this.sitzanzahl = sitzanzahl;
    }
    public PKW(int id, String typ_Fahrzeug,String hersteller, String modell, int baujahr, int sitzanzahl) {
        super(id, typ_Fahrzeug, hersteller, modell, baujahr);
        this.sitzanzahl = sitzanzahl;
    }

    public PKW() {
    }

    public int getSitzanzahl() {return sitzanzahl;}

    public static int readVonDAOSitzanzahl(int fahrzeugId) {
        return PkwDAO.getSitzanzahl(fahrzeugId);
    }


    public void setSitzanzahl(int sitzanzahl) {this.sitzanzahl = sitzanzahl;}

    @Override
    public void anzeigen() {
        System.out.println("PKW: " + hersteller + " " + modell + ", Baujahr " + baujahr + "\nSitzanzahl: " + sitzanzahl + " Kilometerstand :" + getKilometerstand()
                + " Kraftstoff: " + getKraftstoff() + "\nParkPlatz: "+ getStandort()+  " Warungstermin: " + getWartungstermine()

        );
    }

}
