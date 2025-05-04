package model;

import database.MotorradDAO;

public class Motorrad extends Fahrzeug {

    private int hubraum;
    private boolean gangschaltung;

    public Motorrad(String hersteller, String modell, int baujahr, int hubraum, boolean gangschaltung) {
        super(hersteller, modell, baujahr);
        this.hubraum = hubraum;
        this.gangschaltung = gangschaltung;
    }
    public Motorrad(int id,String typ_Fahrzeug , String hersteller, String modell, int baujahr, int hubraum, boolean gangschaltung) {
        super(id, typ_Fahrzeug,hersteller, modell, baujahr);
        this.hubraum = hubraum;
        this.gangschaltung = gangschaltung;
    }
    public Motorrad() {

    }

    public int getHubraum() {
        return hubraum;
    }

    public static int readVonADOHubraum(int fahrzeugId) {
        return MotorradDAO.getHubraum(fahrzeugId);

    }

    public void setHubraum(int hubraum) {
        this.hubraum = hubraum;
    }

    public boolean isGangschaltung() {
        return gangschaltung;
    }
    public static boolean readVonADOGangschaltung(int fahrzeugId) {
        return MotorradDAO.getGangschaltung(fahrzeugId);

    }


    public void setGangschaltung(boolean gangschaltung) {
        this.gangschaltung = gangschaltung;
    }

    @Override
    public void anzeigen() {
        System.out.println("Motorrad: " + hersteller + " " + modell + " Baujahr " + baujahr + "\nHubraum: "
                + hubraum + " ccm, Gangschaltung: " + (gangschaltung ? "Ja" : "Nein")+ "\nKilometerstand:"+ getKilometerstand()
        + "Kraftstoff: " + getKraftstoff() + "\nParkPlatz: "+ getStandort()+ " Warungstermin: " + getWartungstermine());
    }
}
