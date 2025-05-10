package service;

import dao.TrainerDAO;
import model.Mitglied;
import model.Trainer;

import java.util.List;

// Diese Klasse stellt die Geschäftslogik für Trainer bereit.
// Sie verbindet die View-Schicht mit der DAO-Schicht.
public class TrainerService {

    // Alle Trainer abrufen
    public static List<Trainer> alleTrainerAnzeigen() {
        return TrainerDAO.anzeigenAlleTrainer();
    }//------ End alleTrainerAnzeigen


    // Neuen Trainer hinzufügen
    public static boolean trainerHinzufuegen(Trainer trainer){
        if (trainer == null){
            System.out.println("Das übergebene Trainer objekt ist null");
            return false;
        }
        if(trainer.getVorname().isEmpty() || trainer.getNachname().isEmpty()
                || trainer.getGeschlecht() == null || trainer.getRolle() == null){
            System.out.println("Pflichtfelder (Vorname, Nachname, Geschlecht, Rolle) dürfen nicht leer sein !");
            return false;
        }
        return TrainerDAO.hinzufuegenTrainer(trainer);
    }//----- End trainerHinzufuegen


    // Trainer anhand ID suchen
    public static Trainer suchenTrainer(int id){
        if(id < 0 ){
            System.out.println("Die Trainer ID muss eine positive Zahl sein!");
            return null;

        }else return TrainerDAO.suchenTrainerMitID(id);

    }//------- End suchenTrainer


    // Trainer löschen
    public static boolean loschenTrainer(int id) {
        if (id < 0) {
            System.out.println("Die Trainer ID muss eine positive Zahl sein!");
            return false;

        } else return TrainerDAO.loeschenTrainer(id);


    }//------ End loschenTrainer

    // Trainer bearbeiten
    public static boolean bearbeitenTrainer(Trainer trainer){
        if(trainer == null || trainer.getId() <=0){
            System.out.println("Die zur Bearbeitung eingegebenen Informationen sind nicht korrekt.");
            return false;
        }else return TrainerDAO.bearbeitenTrainer(trainer);

    }// ---EndbearbeitenTrainer



}
