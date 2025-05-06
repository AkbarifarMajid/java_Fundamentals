package dao;

import database.DatabaseManager;
import model.Altersgruppe;
import model.Geschlecht;
import model.Kontaktinfo;
import model.Mitglied;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Diese Klasse verwaltet die Datenbankoperationen für Mitglieder.
public class MitgliedDAO {

    //Fügt neu Mitglied in tabelle ein
    public static boolean hinzufuegenMitglied(Mitglied mitglied) {
        String sql_hinzufugen = "insert into mitglieder "
                + "(vorname, nachname, geschlecht, age, email, telefon, adresse, altersgruppe)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connect_hinzufugen = DatabaseManager.getConnection();
             PreparedStatement statement_hinzufugen = connect_hinzufugen.prepareStatement(sql_hinzufugen, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement_hinzufugen.setString(1, mitglied.getVorname());
            statement_hinzufugen.setString(2, mitglied.getNachname());
            statement_hinzufugen.setString(3, mitglied.getGeschlecht().name());
            statement_hinzufugen.setInt(4, mitglied.getAlter());
            statement_hinzufugen.setString(5, mitglied.getKontaktinfo().getEmail());
            statement_hinzufugen.setString(6, mitglied.getKontaktinfo().getTelefonnummer());
            statement_hinzufugen.setString(7, mitglied.getKontaktinfo().getAdresse());
            statement_hinzufugen.setString(8, mitglied.getAltersgruppe().name());

            statement_hinzufugen.executeUpdate();

            ResultSet result_hinzufugen = statement_hinzufugen.getGeneratedKeys();
            if (result_hinzufugen.next()) {
                mitglied.setId(result_hinzufugen.getInt(1));
            }

            System.out.println("Neu Mitglied mit (ID: " + mitglied.getId() + ")" + "gespeichert");
            return true;

        } catch (SQLException error) {
            System.out.println("Beim Hinzufugen eine Mitglied (SQL) gibt es Problem " + error.getMessage());
            return false;
        }
    }//End hinzufugen

    // Gibt eine Liste aller Mitglied zurück
    public static List<Mitglied> anzeigenAlleMitglied() {
        List<Mitglied> liste_Mitglied = new ArrayList<>();

        String sql_ListeMitglied = "select * from mitglieder";

        try (Connection connect_ListeMitglied = DatabaseManager.getConnection();
             PreparedStatement statement_ListeMitglied = connect_ListeMitglied.prepareStatement(sql_ListeMitglied)) {
            ResultSet resultListe = statement_ListeMitglied.executeQuery();

            while (resultListe.next()) {
                Mitglied mitglied = new Mitglied(

                        resultListe.getInt("id"),
                        resultListe.getString("vorname"),
                        resultListe.getString("nachname"),
                        Geschlecht.valueOf(resultListe.getString("geschlecht")),
                        resultListe.getInt("age"),
                        new Kontaktinfo(
                                resultListe.getString("email"),
                                resultListe.getString("telefon"),
                                resultListe.getString("adresse")

                        ),
                        Altersgruppe.valueOf(resultListe.getString("altersgruppe"))
                );

                liste_Mitglied.add(mitglied);

            }// End while

        } catch (SQLException error) {
            System.out.println("beim lesen Liste Alle Mitglied gibt es Problem" + error.getMessage());
        }

        return liste_Mitglied;
    }//End anzeigenAlleMitglied

    //Suche nach Ein Mitglied mit ID.
    public static Mitglied suchen_Mitglied_ID(int id) {
        String sql_Suchen_ID = "select * from mitglieder where id = ?";

        try (Connection connect_Suchen_ID = DatabaseManager.getConnection();
             PreparedStatement statement_Suchen_ID = connect_Suchen_ID.prepareStatement(sql_Suchen_ID)) {

            statement_Suchen_ID.setInt(1, id);

            ResultSet result_suchen = statement_Suchen_ID.executeQuery();

            if (result_suchen.next()) {
                return new Mitglied(
                        result_suchen.getInt("id"),
                        result_suchen.getString("vorname"),
                        result_suchen.getString("nachname"),
                        Geschlecht.valueOf(result_suchen.getString("geschlecht")),
                        result_suchen.getInt("age"),
                        new Kontaktinfo(
                                result_suchen.getString("email"),
                                result_suchen.getString("telefon"),
                                result_suchen.getString("adresse")
                        ),
                        Altersgruppe.valueOf(result_suchen.getString("altersgruppe"))
                );
            }// End If


        } catch (SQLException error) {
            System.out.println("beim Suchen Mitglied mit ID gibt es Problem" + error);
        }
        return null;
    }// End suchen_Mitglied_ID

    //Löscht ein Mitglied anhand der ID aus der Datenbank
    public static boolean loschen_Mitglied(int id) {
        String sql_LoschenMitglied = "DELETE FROM mitglieder WHERE id = ?";

        try (Connection connection_Loschen = DatabaseManager.getConnection();
             PreparedStatement statement_Loschen = connection_Loschen.prepareStatement(sql_LoschenMitglied)) {

            statement_Loschen.setInt(1,id);

            //Schreibt das Ergebnis der Löschung als 0 oder 1 in eine Variable.
            int result_Loschen = statement_Loschen.executeUpdate();

            if(result_Loschen > 0){
                System.out.println("Mitglied mit ID " + id + " nicht mehr in Datenbank");
                return true;
            }else {
                System.out.println("Gibt es kein Mitglied mit ID " + id );
                return false;
            }

        } catch (SQLException error) {
            System.out.println("Beim Mitglied Löschen gibt es Problem" + error);
            return false;
        }

    }// End loschen_Mitglied


    //Bearbeitet die Daten eines bestehenden Mitglieds anhand seiner ID
    public static boolean bearbeiten_Mitglied(Mitglied mitglied){
        String sql_Bearbeiten_M = "UPDATE mitglieder SET " +
                "vorname = ?, nachname = ?, geschlecht = ?, age = ?, " +
                "email = ?, telefon = ?, adresse = ?, altersgruppe = ? " +
                "WHERE id = ?";

        try (Connection connection_bearbeiten_M = DatabaseManager.getConnection();
             PreparedStatement statement_bearbeit_m = connection_bearbeiten_M.prepareStatement(sql_Bearbeiten_M)) {

            statement_bearbeit_m.setString(1,mitglied.getVorname());
            statement_bearbeit_m.setString(2, mitglied.getNachname());
            statement_bearbeit_m.setString(3, mitglied.getGeschlecht().name());
            statement_bearbeit_m.setInt(4, mitglied.getAlter());
            statement_bearbeit_m.setString(5, mitglied.getKontaktinfo().getEmail());
            statement_bearbeit_m.setString(6, mitglied.getKontaktinfo().getTelefonnummer());
            statement_bearbeit_m.setString(7, mitglied.getKontaktinfo().getAdresse());
            statement_bearbeit_m.setString(8, mitglied.getAltersgruppe().name());
            statement_bearbeit_m.setInt(9, mitglied.getId());

            //Schreibt das Ergebnis der update als 0 oder 1 in eine Variable.
            int result_Berabeiten = statement_bearbeit_m.executeUpdate();
            if (result_Berabeiten > 0){
                System.out.println("Die Informationen des Mitglieds mit der ID "  + mitglied.getId()+ " wurden aktualisiert.");
                return true;
            }
            else {
                System.out.println("Die Informationen für das Mitglied mit der ID "  + mitglied.getId()+ " wurden nicht gefunden.");
                return false;
            }


        }catch (SQLException erroe){
            System.out.println("beim bearbeitem Mitglid mit ID "+ mitglied.getId() +  "gibt es Problem" + erroe.getMessage());
            return false;
        }


        }//End bearbeiten_Mitglied


}//End MitgliedDAO
