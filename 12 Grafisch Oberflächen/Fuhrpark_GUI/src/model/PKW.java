
package model;

import dao.PkwDAO;

public class PKW extends Fahrzeug {

    private int sitzanzahl;

    public PKW(String hersteller, String modell, int baujahr, String kennzeichen, int sitzanzahl) {
        super(hersteller, modell, baujahr, kennzeichen);
        this.sitzanzahl = sitzanzahl;
    }

    public PKW(int id, String typ, String hersteller, String modell, int baujahr, String kennzeichen, int sitzanzahl) {
        super(id, typ, hersteller, modell, baujahr, kennzeichen);
        this.sitzanzahl = sitzanzahl;
    }

    public int getSitzanzahl() { return sitzanzahl; }

    public void setSitzanzahl(int sitzanzahl) { this.sitzanzahl = sitzanzahl; }

    public static int readVonDAOSitzanzahl(int fahrzeugId) {
        return PkwDAO.getSitzanzahl(fahrzeugId);
    }
/*
    @Override
    public void anzeigen() {
        System.out.println("PKW: " + hersteller + " " + modell + " (" + baujahr + "), Sitzanzahl: " + sitzanzahl);
    }

 */

    @Override
    public String toString() {
        return "PKW{" + "sitzanzahl=" + sitzanzahl + '}';
    }
}
