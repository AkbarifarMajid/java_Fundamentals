package dao;
import database.DatabaseManager;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Diese Klasse verwaltet die Datenbankoperationen für Mannschaft
public class MannschaftDAO {

    // Fügt eine neue Mannschaft hinzu
    public static boolean hinzufuegenMannschaft(Mannschaft mannschaft) {
        String sql_Manschaft_Hi = "INSERT INTO mannschaften (name, liga) VALUES (?, ?)";

        try (Connection connection_Hinzu = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection_Hinzu.prepareStatement(sql_Manschaft_Hi, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, mannschaft.getName());
            preparedStatement.setString(2, mannschaft.getLiga());
            preparedStatement.executeUpdate();

            ResultSet resultSet_Hinzu = preparedStatement.getGeneratedKeys();
            if (resultSet_Hinzu.next()) {
                mannschaft.setId(resultSet_Hinzu.getInt(1));
            }

            System.out.println("Neue Mannschaft (ID: " + mannschaft.getId() + ") gespeichert.");
            return true;

        } catch (SQLException error) {
            System.out.println("beim Hinzufügen der Mannschaft gibt es Problem: " + error.getMessage());
            return false;
        }
    }// End hinzufuegenMannschaft


    // Gibt alle Mannschaften zurück
    public static List<Mannschaft> anzeigenAlleMannschaften() {
        List<Mannschaft> mannschaftList = new ArrayList<>();
        String sql = "SELECT * FROM mannschaften";

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            ResultSet resultSet_Anzeige = preparedStatement.executeQuery();
            while (resultSet_Anzeige.next()) {
                Mannschaft m = new Mannschaft(
                        resultSet_Anzeige.getInt("id"),
                        resultSet_Anzeige.getString("name"),
                        resultSet_Anzeige.getString("liga")
                );
                mannschaftList.add(m);
            }

        } catch (SQLException error) {
            System.out.println("beim Laden der Mannschaften gibt es Problem: " + error.getMessage());
        }

        return mannschaftList;
    }//End anzeigenAlleMannschaften


    // Sucht einen Mannschaft anhand der ID
    public static Mannschaft suchenMannschaft_ID(int id) {
        String sqlSuchen_T = "SELECT * FROM mannschaften WHERE id = ?";

        try (Connection connection_Suchen_Ma = DatabaseManager.getConnection();
             PreparedStatement statementSuchen_Ma = connection_Suchen_Ma.prepareStatement(sqlSuchen_T)) {

            statementSuchen_Ma.setInt(1, id);
            ResultSet resultSuchen = statementSuchen_Ma.executeQuery();

            if (resultSuchen.next()) {
                Mannschaft manschaft = new Mannschaft(
                        resultSuchen.getInt("id"),
                        resultSuchen.getString("name"),
                        resultSuchen.getString("liga")
                );

                manschaft.setMitglieder(ladeMitgliederVonMannschaft(id));
                manschaft.setTrainerListe(ladeTrainerVonMannschaft(id));

                return manschaft;
            }



        } catch (SQLException error) {
            System.out.println("bei der Mannschaft mit ID gibt es Problem: " + error.getMessage());
        }

        return null;
    }// --- End suchenMannschaft_ID

    // Fügt ein Mitglied zu einer Mannschaft hinzu
    public static boolean hinzu_MitgliedZurManschaft(int manschaftID, int mitgliedID){

        String sql_Mit_ZU_Man = "INSERT INTO mannschaft_mitglied (mannschaft_id, mitglied_id) VALUES (?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql_Mit_ZU_Man)){

            preparedStatement.setInt(1,manschaftID);
            preparedStatement.setInt(2,mitgliedID);
            preparedStatement.executeUpdate();

            return true;

        }catch (SQLException error) {
            System.out.println("beim Hinzufügen des Mitglieds zur Mannschaft gibt es Problem: " + error.getMessage());
            return false;
        }
    }// End hinzu_MitgliedZurManschaft

    // Fügt ein Trainer zu einer Mannschaft hinzu
    public static boolean hinzu_TrainerZurManschaft(int trainerID, int mannschaftID){

        String sql_Trainer_ZU_Man = "INSERT INTO trainer_mannschaft (trainer_id, mannschaft_id) VALUES (?, ?)";

        try (Connection myCoonection = DatabaseManager.getConnection();
        PreparedStatement mypreparedStatement = myCoonection.prepareStatement(sql_Trainer_ZU_Man)){

            mypreparedStatement.setInt(1,trainerID);
            mypreparedStatement.setInt(2,mannschaftID);
            mypreparedStatement.executeUpdate();
            return true;

        }catch (SQLException error){
            System.out.println("beim Hinzufügen des Trainer zur Mannschaft gibt es Problem: " + error.getMessage());
            return false;
        }

    }// End hinzu_TrainerZurManschaft

    // Entfernt ein Mitglied aus einer Mannschaft
    public static boolean loschen_Mi_Aus_Manschaft(int mitgliedID, int manschaftID){
        String sqlMi_Loschen = "Delete from mannschaft_mitglied  where mitglied_id =? and mannschaft_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlMi_Loschen)){

            preparedStatement.setInt(1,mitgliedID);
            preparedStatement.setInt(2,manschaftID);

            //Schreibt das Ergebnis der Löschung als 0 oder 1 in eine Variable.
            int resultLoschen = preparedStatement.executeUpdate();

            if (resultLoschen > 0) return true;
            else {
                System.out.println("Mitglied war nicht in dieser Mannschaft.");
                return false;
            }

        }catch (SQLException error) {
            System.out.println("beim Entfernen des Mitglieds aus der Mannschaft gibt es Problem: " + error.getMessage());
            return false;
        }

    }// End loschen_Mi_Aus_Manschaft

    // Entfernt ein Trainer aus einer Mannschaft
    public static boolean loschen_Tra_Aus_Manschaft(int trainerID, int manschaftID){
        String sqlMi_Loschen = "Delete from trainer_mannschaft  where trainer_id =? and mannschaft_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlMi_Loschen)){

            preparedStatement.setInt(1,trainerID);
            preparedStatement.setInt(2,manschaftID);

            //Schreibt das Ergebnis der Löschung als 0 oder 1 in eine Variable.
            int resultLoschen = preparedStatement.executeUpdate();

            if (resultLoschen > 0) return true;
            else {
                System.out.println("Trainer war nicht in dieser Mannschaft.");
                return false;
            }

        }catch (SQLException error) {
            System.out.println("beim Entfernen des Trainer aus der Mannschaft gibt es Problem: " + error.getMessage());
            return false;
        }
    }// End loschen_Tra_Aus_Manschaft

    // Löscht eine Mannschaft anhand der ID
    public static boolean loeschen_Mannschaft(int mannschaftId) {
        String sql_loschen = "DELETE FROM mannschaften WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql_loschen)) {

            preparedStatement.setInt(1, mannschaftId);
            //Schreibt das Ergebnis der Löschung als 0 oder 1 in eine Variable.
            int result_Loschen = preparedStatement.executeUpdate();

            if (result_Loschen > 0) return true;
             else {
                System.out.println("Keine Mannschaft mit dieser ID "+ mannschaftId+ " gefunden.");
                return false;
            }

        } catch (SQLException error) {
            System.out.println("beim Löschen der Mannschaft gibt es Problem: " + error.getMessage());
            return false;
        }
    }

    // Lädt alle Mitglieder, die einer bestimmten Mannschaft zugeordnet sind,
    // indem sie aus der Join-Tabelle 'mannschaft_mitglied' gelesen werden.
    public static List<Mitglied> ladeMitgliederVonMannschaft(int mannschaftId) {
        List<Mitglied> liste = new ArrayList<>();

        String sql = "SELECT * FROM mitglieder mitglider " +
                "JOIN mannschaft_mitglied manschatMitglied ON mitglider.id = manschatMitglied.mitglied_id " +
                "WHERE manschatMitglied.mannschaft_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, mannschaftId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Mitglied mitglied = new Mitglied(
                        rs.getInt("id"),
                        rs.getString("vorname"),
                        rs.getString("nachname"),
                        Geschlecht.valueOf(rs.getString("geschlecht")),
                        rs.getInt("age"),
                        new Kontaktinfo(
                                rs.getString("email"),
                                rs.getString("telefon"),
                                rs.getString("adresse")
                        ),
                        Altersgruppe.valueOf(rs.getString("altersgruppe"))
                );
                liste.add(mitglied);
            }

        } catch (SQLException error) {
            System.out.println("beim Laden der Mitglieder gibt es Problem: " + error.getMessage());
        }

        return liste;
    }// End ladeMitgliederVonMannschaft


    // Lädt alle Trainer, die einer bestimmten Mannschaft zugeordnet sind,
    // basierend auf der Zuordnung in der Join-Tabelle 'trainer_mannschaft'.
    public static List<Trainer> ladeTrainerVonMannschaft(int mannschaftId) {
        List<Trainer> liste = new ArrayList<>();

        String sql = "SELECT * FROM trainer trainer " +
                "JOIN trainer_mannschaft tm ON trainer.id = tm.trainer_id " +
                "WHERE tm.mannschaft_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mannschaftId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Trainer trainer = new Trainer(
                        rs.getInt("id"),
                        rs.getString("vorname"),
                        rs.getString("nachname"),
                        Geschlecht.valueOf(rs.getString("geschlecht")),
                        rs.getInt("age"),
                        new Kontaktinfo(
                                rs.getString("email"),
                                rs.getString("telefon"),
                                rs.getString("adresse")
                        ),
                        Rolle.valueOf(rs.getString("rolle")),
                        rs.getString("lizenzstufe")
                );

                liste.add(trainer);
            }

        } catch (SQLException error) {
            System.out.println("beim Laden der Trainer gibt es Problem: " + error.getMessage());
        }

        return liste;
    } //End ladeTrainerVonMannschaft






}
