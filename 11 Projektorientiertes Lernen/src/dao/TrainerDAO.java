package dao;

import database.DatabaseManager;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Diese Klasse verwaltet die Datenbankoperationen für Trainer
public class TrainerDAO {

    // Fügt einen neuen Trainer in die Datenbank ein
    public static boolean hinzufuegenTrainer(Trainer trainer) {
        String sql_Hinzufugen_T = "INSERT INTO trainer (vorname, nachname, geschlecht, age, email, telefon, adresse, rolle, lizenzstufe) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection_T = DatabaseManager.getConnection();
             PreparedStatement statement_T = connection_T.prepareStatement(sql_Hinzufugen_T, Statement.RETURN_GENERATED_KEYS)) {

            statement_T.setString(1, trainer.getVorname());
            statement_T.setString(2, trainer.getNachname());
            statement_T.setString(3, trainer.getGeschlecht().name());
            statement_T.setInt(4, trainer.getAlter());
            statement_T.setString(5, trainer.getKontaktinfo().getEmail());
            statement_T.setString(6, trainer.getKontaktinfo().getTelefonnummer());
            statement_T.setString(7, trainer.getKontaktinfo().getAdresse());
            statement_T.setString(8, trainer.getRolle().name());
            statement_T.setString(9, trainer.getLizenzstufe());

            statement_T.executeUpdate();

            ResultSet result_hinzufugen = statement_T.getGeneratedKeys();
            if (result_hinzufugen.next()) {
                trainer.setId(result_hinzufugen.getInt(1));
            }

            System.out.println("Neu Trainer mit (ID: " + trainer.getId() + ")" + "gespeichert");
            return true;

        } catch (SQLException error) {
            System.out.println("Beim Hinzufugen eine Trainer (SQL) gibt es Problem " + error.getMessage());
            return false;
        }


    }// ---End hinzufuegenTrainer


    // Gibt alle Trainer aus der Datenbank zurück
    public static List<Trainer> anzeigenAlleTrainer() {
        List<Trainer> trainerListe = new ArrayList<>();
        String sql_AnzeigeAll_T = "SELECT * FROM trainer";

        try (Connection connectionALL_T = DatabaseManager.getConnection();
             PreparedStatement statementALL_T = connectionALL_T.prepareStatement(sql_AnzeigeAll_T)) {

            ResultSet result_Liste = statementALL_T.executeQuery();

            while (result_Liste.next()) {
                Trainer trainer = new Trainer(
                        result_Liste.getInt("id"),
                        result_Liste.getString("vorname"),
                        result_Liste.getString("nachname"),
                        Geschlecht.valueOf(result_Liste.getString("geschlecht")),
                        result_Liste.getInt("age"),
                        new Kontaktinfo(
                                result_Liste.getString("email"),
                                result_Liste.getString("telefon"),
                                result_Liste.getString("adresse")
                        ),
                        Rolle.valueOf(result_Liste.getString("rolle")),
                        result_Liste.getString("lizenzstufe")
                );
                trainerListe.add(trainer);
            }

        } catch (SQLException error) {
            System.out.println("Fehler beim Lesen der Trainerliste: " + error.getMessage());
        }

        return trainerListe;
    }// --- End anzeigenAlleTrainer


    // Sucht einen Trainer anhand der ID
    public static Trainer suchenTrainerMitID(int id) {
        String sqlSuchen_T = "SELECT * FROM trainer WHERE id = ?";

        try (Connection connection_Suchen_T = DatabaseManager.getConnection();
             PreparedStatement statementSuchen_T = connection_Suchen_T.prepareStatement(sqlSuchen_T)) {

            statementSuchen_T.setInt(1, id);
            ResultSet resultSuchen = statementSuchen_T.executeQuery();

            if (resultSuchen.next()) {
                return new Trainer(
                        resultSuchen.getInt("id"),
                        resultSuchen.getString("vorname"),
                        resultSuchen.getString("nachname"),
                        Geschlecht.valueOf(resultSuchen.getString("geschlecht")),
                        resultSuchen.getInt("age"),
                        new Kontaktinfo(
                                resultSuchen.getString("email"),
                                resultSuchen.getString("telefon"),
                                resultSuchen.getString("adresse")
                        ),
                        Rolle.valueOf(resultSuchen.getString("rolle")),
                        resultSuchen.getString("lizenzstufe")
                );
            }

        } catch (SQLException error) {
            System.out.println("Fehler bei der Trainersuche mit ID: " + error.getMessage());
        }

        return null;
    }// --- End suchenTrainerMitID


    // Löscht einen Trainer anhand der ID
    public static boolean loeschenTrainer(int id) {
        String sql_Loeschen_T = "DELETE FROM trainer WHERE id = ?";

        try (Connection connection_Loeschen_T = DatabaseManager.getConnection();
             PreparedStatement statement_Loeschen_T = connection_Loeschen_T.prepareStatement(sql_Loeschen_T)) {

            statement_Loeschen_T.setInt(1, id);
            int loeschErgebnis = statement_Loeschen_T.executeUpdate();

            if (loeschErgebnis > 0) {
                System.out.println("Trainer mit ID " + id + " wurde gelöscht.");
                return true;
            } else {
                System.out.println("Kein Trainer mit ID " + id + " gefunden.");
                return false;
            }

        } catch (SQLException error) {
            System.out.println("Fehler beim Löschen des Trainers: " + error.getMessage());
            return false;
        }
    } // --- End loeschenTrainer


    // Aktualisiert die Daten eines bestehenden Trainers
    public static boolean bearbeitenTrainer(Trainer trainer) {
        String sql_Bearbeiten_T = "UPDATE trainer SET " +
                "vorname = ?, nachname = ?, geschlecht = ?, age = ?, " +
                "email = ?, telefon = ?, adresse = ?, rolle = ?, lizenzstufe = ? " +
                "WHERE id = ?";

        try (Connection connection_Bearbeiten_T = DatabaseManager.getConnection();
             PreparedStatement statement_Bearbeiten_T = connection_Bearbeiten_T.prepareStatement(sql_Bearbeiten_T)) {

            statement_Bearbeiten_T.setString(1, trainer.getVorname());
            statement_Bearbeiten_T.setString(2, trainer.getNachname());
            statement_Bearbeiten_T.setString(3, trainer.getGeschlecht().name());
            statement_Bearbeiten_T.setInt(4, trainer.getAlter());
            statement_Bearbeiten_T.setString(5, trainer.getKontaktinfo().getEmail());
            statement_Bearbeiten_T.setString(6, trainer.getKontaktinfo().getTelefonnummer());
            statement_Bearbeiten_T.setString(7, trainer.getKontaktinfo().getAdresse());
            statement_Bearbeiten_T.setString(8, trainer.getRolle().name());
            statement_Bearbeiten_T.setString(9, trainer.getLizenzstufe());
            statement_Bearbeiten_T.setInt(10, trainer.getId());

            int updateErgebnis = statement_Bearbeiten_T.executeUpdate();

            if (updateErgebnis > 0) {
                System.out.println("Trainer mit ID " + trainer.getId() + " wurde aktualisiert.");
                return true;
            } else {
                System.out.println("Trainer mit ID " + trainer.getId() + " wurde nicht gefunden.");
                return false;
            }

        } catch (SQLException error) {
            System.out.println("Fehler beim Bearbeiten des Trainers: " + error.getMessage());
            return false;
        }
    }// ---End bearbeitenTrainer


}
