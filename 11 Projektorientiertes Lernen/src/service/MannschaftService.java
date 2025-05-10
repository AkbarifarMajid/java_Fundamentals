package service;

import dao.MannschaftDAO;
import dao.MitgliedDAO;
import dao.TrainerDAO;
import model.Mannschaft;
import java.util.List;


// Diese Klasse stellt die Geschäftslogik für Mannschaften bereit.

public class MannschaftService {

    // Neue Mannschaft hinzufügen
    public static boolean mannschaftHinzufuegen(Mannschaft mannschaft) {
        if (mannschaft == null || mannschaft.getName().isEmpty() || mannschaft.getLiga().isEmpty()) {
            System.out.println("Pflichtfelder dürfen nicht leer sein!");
            return false;
        }
        return MannschaftDAO.hinzufuegenMannschaft(mannschaft);
    }// End mannschaftHinzufuegen

    // Liste aller Mannschaften anzeigen
    public static List<Mannschaft> alleMannschaftenAnzeigen() {
        return MannschaftDAO.anzeigenAlleMannschaften();
    }// End alleMannschaftenAnzeigen

    // Fügt ein Mitglied zu einer Mannschaft hinzu
    public static boolean hinzu_MitgliedZurManschaft(int mannshaftID, int mitgliedID){
        //Existenprüfung
        if(MannschaftDAO.suchenMannschaft_ID(mannshaftID) == null ||
                MitgliedDAO.suchen_Mitglied_ID(mitgliedID) == null){
            System.out.println("Mannschaft-ID oder Mitglied-ID ist nicht verfügbar!");
            return false;
        }
        return MannschaftDAO.hinzu_MitgliedZurManschaft(mannshaftID,mitgliedID);

    } // end hinzu_MitgliedZurManschaft

    // Fügt ein Trainer zu einer Mannschaft hinzu
    public static boolean hinzu_TrainerZurManschaft(int trainerID, int mannschaftID){
        //Existenprüfung
        if(TrainerDAO.suchenTrainerMitID(trainerID) == null ||
                MannschaftDAO.suchenMannschaft_ID(mannschaftID) == null){
            System.out.println("Trainer-ID oder Mannschaft-ID ist nicht verfügbar!");
            return false;
        }
        return MannschaftDAO.hinzu_TrainerZurManschaft(trainerID,mannschaftID);
    }// End hinzu_TrainerZurManschaft


    // Entfernt ein Mitglied aus einer Mannschaft
    public static boolean loschen_Mi_Aus_Manschaft(int mitgliedId, int mannschaftId) {
        if (MannschaftDAO.suchenMannschaft_ID(mannschaftId) == null ||
                MitgliedDAO.suchen_Mitglied_ID(mitgliedId) == null) {
            System.out.println("Mannschaft-ID oder Mitglied-ID existiert nicht.");
            return false;
        }

        return MannschaftDAO.loschen_Mi_Aus_Manschaft(mitgliedId, mannschaftId);
    }// End loschen_Mi_Aus_Manschaft

    // Entfernt einen Trainer aus einer Mannschaft
    public static boolean loschen_Tra_Aus_Manschaft(int trainerId, int mannschaftId) {
        if (MannschaftDAO.suchenMannschaft_ID(mannschaftId) == null ||
                TrainerDAO.suchenTrainerMitID(trainerId) == null) {
            System.out.println("Mannschaft-ID oder Trainer-ID existiert nicht.");
            return false;
        }

        return MannschaftDAO.loschen_Tra_Aus_Manschaft(trainerId, mannschaftId);
    } // End loschen_Tra_Aus_Manschaft

    // Entfernt einer Mannschaft aus Datenbanck
    public static boolean loschen_Mannschaft(int mannschaftID){
        if (MannschaftDAO.suchenMannschaft_ID(mannschaftID) == null){
            System.out.println("Mannschaft-ID oder Trainer-ID existiert nicht.");
            return false;
        } else return MannschaftDAO.loeschen_Mannschaft(mannschaftID);
    }// End loschen_Mannschaft


    public static Mannschaft suchenMannschaft(int id) {
        return MannschaftDAO.suchenMannschaft_ID(id);
    }


}
