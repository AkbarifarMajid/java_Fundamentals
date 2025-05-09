package service;

import dao.MitgliedDAO;
import model.Mitglied;

import java.util.List;


//Diese Klasse stellt die Geschäftslogik für Mitglieder bereit.
// Sie verbindet die View-Schicht mit der DAO-Schicht.
public class MitgliedService {

    // Liste aller Mitglieder abrufen
    public static List<Mitglied> alleMitgliederAnzeigen() {
        return MitgliedDAO.anzeigenAlleMitglied();
    }//------ End alleMitgliederAnzeigen

    // Neuen Mitglied hinzufügen
    public static boolean neuesMitgliedHinzufugen(Mitglied mitglied){
         if (mitglied == null){
             System.out.println("Das übergebene Mitgliedsobjekt ist null");
             return false;
         }
         if(mitglied.getVorname().isEmpty() || mitglied.getNachname().isEmpty()
            || mitglied.getAltersgruppe().name().isEmpty() || mitglied.getGeschlecht().name().isEmpty()){
             System.out.println("Vor-Nachname,Altersgruppe oder Geschlecht muss nicht leer sein !");
             return false;
         }
         return MitgliedDAO.hinzufuegenMitglied(mitglied);
    }//----- End neuesMitgliedHinzufugen

    // Mitglied anhand ID suchen
    public static Mitglied suchenMitglied(int id){
        if(id < 0 ){
            System.out.println("Die ID muss eine positive Zahl sein!");
            return null;

        }else return MitgliedDAO.suchen_Mitglied_ID(id);

    }//------- End suchenMitglied

    // Mitglied löschen
    public static boolean loschenMitglied(int id){
        if(id < 0 ){
            System.out.println("Die ID muss eine positive Zahl sein!");
            return false;

        }else return MitgliedDAO.loschen_Mitglied(id);


    }//------ End loschenMitglied

    // Mitglied bearbeiten
    public static boolean bearbeitenMitglied(Mitglied mitglied){
        if(mitglied == null || mitglied.getId() <=0){
            System.out.println("Die zur Bearbeitung eingegebenen Informationen sind nicht korrekt.");
            return false;
        }else return MitgliedDAO.bearbeiten_Mitglied(mitglied);

    }// ---EndbearbeitenMitglied


}// End MitgliedService
