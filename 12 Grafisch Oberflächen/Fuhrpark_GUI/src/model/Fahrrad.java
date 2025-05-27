
package model;

import dao.FahrradDAO;

public class Fahrrad extends Fahrzeug {

    private boolean hatKorb;

    public Fahrrad(String hersteller, String modell, int baujahr, String kennzeichen, boolean hatKorb) {
        super(hersteller, modell, baujahr, kennzeichen);
        this.hatKorb = hatKorb;
    }

    public Fahrrad(int id, String typ, String hersteller, String modell, int baujahr, String kennzeichen, boolean hatKorb) {
        super(id, typ, hersteller, modell, baujahr, kennzeichen);
        this.hatKorb = hatKorb;
    }

    public boolean isHatKorb() { return hatKorb; }
    public void setHatKorb(boolean hatKorb) { this.hatKorb = hatKorb; }

    public static boolean radVonADOHatKorb(int fahrzeugId) {
        return FahrradDAO.get_Korb(fahrzeugId);
    }
/*
    @Override
    public void anzeigen() {
        System.out.println("Fahrrad: " + hersteller + " " + modell + " (" + baujahr + "), Mit Korb: " + (hatKorb ? "Ja" : "Nein"));
    }

 */

    @Override
    public String toString() {
        return "Fahrrad{" + "hatKorb=" + hatKorb + '}';
    }
}
