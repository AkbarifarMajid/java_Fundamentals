
package model;

import dao.MotorradDAO;

public class Motorrad extends Fahrzeug {

    private int hubraum;
    private boolean gangschaltung;
/*
    public Motorrad(String hersteller, String modell, int baujahr, String kennzeichen, int hubraum, boolean gangschaltung) {
        super(hersteller, modell, baujahr,kennzeichen);
        this.hubraum = hubraum;
        this.gangschaltung = gangschaltung;
    }

 */

    public Motorrad(int id, String typ, String hersteller, String modell, int baujahr, String kennzeichen, int hubraum, boolean gangschaltung) {
        super(id, typ, hersteller, modell, baujahr,kennzeichen);
        this.hubraum = hubraum;
        this.gangschaltung = gangschaltung;
    }


    public Motorrad(String hersteller, String modell, int baujahr, String kennzeichen, int hubraum, boolean gangschaltung) {
        super(hersteller, modell, baujahr, kennzeichen);
        this.hubraum = hubraum;
        this.gangschaltung = gangschaltung;
    }


    public int getHubraum() {
        return hubraum;
    }
    public boolean getGangschaltung() {return gangschaltung;}

    public void setHubraum(int hubraum) {this.hubraum = hubraum;}
    public void setGangschaltung(boolean gangschaltung) {this.gangschaltung = gangschaltung;}


    public static int readVonADOHubraum(int fahrzeugId) {
        return MotorradDAO.getHubraum(fahrzeugId);
    }

    public static boolean readVonADOGangschaltung(int fahrzeugId) {
        return MotorradDAO.getGangschaltung(fahrzeugId);
    }
/*
    @Override
    public void anzeigen() {
        System.out.println("Motorrad: " + hersteller + " " + modell + ", Baujahr " + baujahr +
                ", Hubraum: " + hubraum + "ccm, Gangschaltung: " + (gangschaltung ? "Ja" : "Nein"));
    }

 */

    @Override
    public String toString() {
        return "Motorrad{" + "hubraum=" + hubraum + ", gangschaltung=" + gangschaltung + '}';
    }
}
