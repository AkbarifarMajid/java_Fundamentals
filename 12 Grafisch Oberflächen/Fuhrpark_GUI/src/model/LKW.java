
package model;

import dao.LkwDAO;

public class LKW extends Fahrzeug {

    private double ladevolumen;

    public LKW(String hersteller, String modell, int baujahr, String kennzeichen, double ladevolumen) {
        super(hersteller, modell, baujahr, kennzeichen);
        this.ladevolumen = ladevolumen;
    }

    public LKW(int id, String typ, String hersteller, String modell, int baujahr, String kennzeichen, double ladevolumen) {
        super(id, typ, hersteller, modell, baujahr, kennzeichen);
        this.ladevolumen = ladevolumen;
    }

    public double getLadevolumen() { return ladevolumen; }
    public void setLadevolumen(double ladevolumen) { this.ladevolumen = ladevolumen; }

    public static double ReadVonDAOLadegewicht(int fahrzeugId) {
        return LkwDAO.getLadevolumen(fahrzeugId);
    }
/*
    @Override
    public void anzeigen() {
        System.out.println("LKW: " + hersteller + " " + modell + " (" + baujahr + "), Ladevolumen: " + ladevolumen);
    }

 */

    @Override
    public String toString() {
        return "LKW{" + "ladevolumen=" + ladevolumen + '}';
    }
}
