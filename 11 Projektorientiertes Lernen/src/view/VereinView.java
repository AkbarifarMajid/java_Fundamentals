package view;

import dao.StatistikDAO;
import dao.TrainerDAO;
import dao.MannschaftDAO;

public class VereinView {

    // Zeigt eine Übersicht über das gesamte Vereinssystem
    public static void zeigeVereinInformationen() {
        int mitglieder = StatistikDAO.mitgliedGesamtzahl();
        int trainer = StatistikDAO.trainerGesamtzahl();
        int mannschaften = StatistikDAO.mannschaftGesamtzahl();
        int mitglied_mannschaft = StatistikDAO.mitgliederInTeams();
        int trainer_mannschaft = StatistikDAO.trainerInTeams();

        System.out.println("\nVerein Übersicht:");
        System.out.println("Mitglieder insgesamt: " + mitglieder);
        System.out.println("Trainer insgesamt: " + trainer);
        System.out.println("Mannschaften insgesamt: " + mannschaften);
        System.out.println("Verknüpfte Mitglieder mit Mannschaften: " + mitglied_mannschaft);
        System.out.println("Verknüpfte Trainer mit Mannschaften: " + trainer_mannschaft);
        System.out.println();
    }
}
