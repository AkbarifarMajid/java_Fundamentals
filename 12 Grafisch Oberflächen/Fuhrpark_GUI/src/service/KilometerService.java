package service;


import dao.KilometerDAO;


public class KilometerService {

    //Anzahl
    public static double getGesamtKilometer() {
        return KilometerDAO.berechneGesamtKilometer();
    }
}
