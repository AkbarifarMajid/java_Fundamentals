package dao;

import database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatistikDAO {


    //Anzahl Mitglied
    public static int mitgliedGesamtzahl() {
        String sql = "SELECT COUNT(*) FROM mitglieder";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultCount = statement.executeQuery()) {

            if (resultCount.next()) return resultCount.getInt(1);

        } catch (SQLException error) {
            System.out.println("beim Zählen der Mitglieder gibt es Problem: " + error.getMessage());
        }
        return 0;
    }// End mitgliedGesamtzahl

    //Anzah mitglide das ein Mannshaft haben
    public static int mitgliederInTeams() {
        String sql = "SELECT COUNT(*) FROM mannschaft_mitglied";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet reszltCount = statement.executeQuery()) {

            if (reszltCount.next()) return reszltCount.getInt(1);

        } catch (SQLException error) {
            System.out.println("beim Zählen der Mitglieder in Mannschaften gibt es Problem: " + error.getMessage());
        }
        return 0;
    }// End mitgliederInTeams


    //Anzahl Trainer
    public static int trainerGesamtzahl() {
        String sql = "SELECT COUNT(*) FROM trainer";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet reasultAnzahl = statement.executeQuery()) {

            if (reasultAnzahl.next()) return reasultAnzahl.getInt(1);

        } catch (SQLException error) {
            System.out.println("beim Zählen der Trainer gibt es Problem: " + error.getMessage());
        }
        return 0;
    }// End trainerGesamtzahl


    //Anzah Trainer das ein Mannshaft haben
    public static int trainerInTeams() {
        String sql = "SELECT COUNT(*) FROM trainer_mannschaft";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultCount = statement.executeQuery()) {

            if (resultCount.next()) return resultCount.getInt(1);

        } catch (SQLException e) {
            System.out.println("beim Zählen der Trainer in Mannschaften gibt es Problem: " + e.getMessage());
        }
        return 0;
    }//End trainerInTeams


    //Anzahl Manschaft
    public static int mannschaftGesamtzahl() {
        String sql = "SELECT COUNT(*) FROM mannschaften";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultAnzahl = statement.executeQuery()) {

            if (resultAnzahl.next()) return resultAnzahl.getInt(1);

        } catch (SQLException error) {
            System.out.println("beim Zählen der Mannschaften gibt es Problem: " + error.getMessage());
        }
        return 0;
    }// End mannschaftGesamtzahl



}
