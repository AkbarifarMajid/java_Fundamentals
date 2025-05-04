package model;

import database.FahrradDAO;


public class Fahrrad extends Fahrzeug {

    private boolean hatKorb;

    public Fahrrad(String hersteller, String modell, int baujahr, boolean hatKorb) {
        super(hersteller, modell, baujahr);
        this.hatKorb = hatKorb;
    }
    public Fahrrad(int id, String typ_Fahrzeug ,String hersteller, String modell, int baujahr, boolean hatKorb) {
        super(id, typ_Fahrzeug,hersteller, modell, baujahr);
        this.hatKorb = hatKorb;
    }



    public boolean isHatKorb() {
        return hatKorb;
    }


    public static boolean radVonADOHatKorb(int fahrzeugId ) {
        return FahrradDAO.getHatKorb(fahrzeugId);

    }

    public Fahrrad() {

    }
    public void setHatKorb(boolean hatKorb) {
        this.hatKorb = hatKorb;
    }

    @Override
    public void anzeigen() {
        System.out.println("Fahrrad: " + hersteller + " " + modell + ", Baujahr " + baujahr + "\n Hat Korb: " + (hatKorb ? "Ja" : "Nein")
                + "\nParkPlatz: "+ getStandort()+ " ID Besitzer: "+ getBesitzer()+ " Warungstermin: " + getWartungstermine()
        );
    }
}

