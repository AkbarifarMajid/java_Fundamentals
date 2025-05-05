package model;

import database.LkwDAO;


public class LKW extends Fahrzeug {

    private double ladegewicht;

    public LKW(String hersteller, String modell, int baujahr, double ladegewicht) {
        super(hersteller, modell, baujahr);
        this.ladegewicht = ladegewicht;
    }

    public LKW(int id,String typ_Fahrzeug ,String hersteller, String modell, int baujahr, double ladegewicht) {
        super(id,typ_Fahrzeug ,hersteller, modell, baujahr);
        this.ladegewicht = ladegewicht;
    }
    public LKW() {
    }



    public double getLadegewicht() {return ladegewicht;}

    public static double ReadVonDAOLadegewicht(int fahrzeugId) {
        return LkwDAO.getLadegewicht(fahrzeugId);}

    public void setLadegewicht(double ladegewicht) {this.ladegewicht = ladegewicht;}

    @Override
    public void anzeigen() {
        System.out.println("LKW: " + hersteller + " " + modell + ", Baujahr " + baujahr + "\n Ladegewicht: " + ladegewicht + " Tonnen" +  " Kilometerstand : "+ getKilometerstand()
                + " Kraftstoff: " + getKraftstoff() + "\nParkPlatz: "+ getStandort()+ " Warungstermin: " + getWartungstermine()

        );
    }
}
