package database;

import model.Mitarbeiter;

import java.sql.*;
import java.util.ArrayList;

// Datenbankzugriffe für Mitarbeiter
public class MitarbeiterDAO {

    // Fügt neuen Mitarbeiter ein
    public static void einfuegen(Mitarbeiter mitarbeiter) {
        String sql = "INSERT INTO mitarbeiter (vorname_Mitarbeiter, nachname_Mitarbeiter, position_Mitarbeiter, telefon_Mitarbeiter) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, mitarbeiter.getVorname());
            pstmt.setString(2, mitarbeiter.getNachname());
            pstmt.setString(3, mitarbeiter.getPosition());
            pstmt.setString(4, mitarbeiter.getTelefonnummer());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                mitarbeiter.setId(rs.getInt(1));
            }

            System.out.println("Neu Mitarbeiter mit (ID: " + mitarbeiter.getId() + ")"+ "gespeichert");

        } catch (SQLException e) {
            System.out.println("beim Einfügen des Mitarbeiters gibt es problem: " + e.getMessage());
        }
    }

    // Gibt eine Liste aller Mitarbeiter zurück
    public static ArrayList<Mitarbeiter> ladenAlle() {
        ArrayList<Mitarbeiter> liste = new ArrayList<>();

        String sql = "SELECT * FROM mitarbeiter";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                Mitarbeiter m = new Mitarbeiter(
                        result.getString("vorname_Mitarbeiter"),
                        result.getString("nachname_Mitarbeiter"),
                        result.getString("position_Mitarbeiter"),
                        result.getString("telefon_Mitarbeiter")
                );
                m.setId(result.getInt("id_Mitarbeiter"));
                liste.add(m);
            }

        } catch (SQLException e) {
            System.out.println(" beim Laden alle Mitarbeiten gibt es problem: " + e.getMessage());
        }

        return liste;
    }

    // Einzelner Mitarbeiter nach ID
    public static Mitarbeiter findeNachId(int id) {
        String sql = "SELECT * FROM mitarbeiter WHERE id_Mitarbeiter = ?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Mitarbeiter m = new Mitarbeiter(
                        result.getString("vorname_Mitarbeiter"),
                        result.getString("nachname_Mitarbeiter"),
                        result.getString("position_Mitarbeiter"),
                        result.getString("telefon_Mitarbeiter")
                );
                m.setId(id);
                return m;
            }

        } catch (SQLException e) {
            System.out.println("bei Mitarbeiten Suche gibt es problem: " + e.getMessage());
        }

        return null;
    }

    // Löscht einen Mitarbeiter aus der Datenbank
    public static boolean loeschen(int id) {
        String sql = "DELETE FROM mitarbeiter WHERE id_Mitarbeiter = ?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int betroffen = statement.executeUpdate();
            return betroffen > 0;

        } catch (SQLException e) {
            System.out.println(" beim Mitarbeiter Löschen gibt es Problem: " + e.getMessage());
            return false;
        }
    }


    public static boolean mitarbeiter_Update(Mitarbeiter m) {
        String sql = """
        UPDATE mitarbeiter
        SET vorname_Mitarbeiter = ?, 
            nachname_Mitarbeiter = ?, 
            position_Mitarbeiter = ?, 
            telefon_Mitarbeiter = ?
        WHERE id_Mitarbeiter = ?
        """;

        try (Connection conn = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.getVorname());
            pstmt.setString(2, m.getNachname());
            pstmt.setString(3, m.getPosition());
            pstmt.setString(4, m.getTelefonnummer());
            pstmt.setInt(5, m.getId());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("❌ Fehler beim Aktualisieren: " + e.getMessage());
            return false;
        }
    }



/*
    // Aktualisiert die Mitarbeiterdaten
    public static boolean mitarbeiter_Update(Mitarbeiter mitarbeiter){
        String sql_Update = "UPDATE mitarbeiter SET vorname_Mitarbeiter=?,nachname_Mitarbeiter = ?, position_Mitarbeiter = ?, telefon_Mitarbeiter = ? where id_Mitarbeiter= ?";

        try (Connection  connect = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statment = connect.prepareStatement(sql_Update)) {

            statment.setString(1,mitarbeiter.getVorname());
            statment.setString(2,mitarbeiter.getNachname());
            statment.setString(3,mitarbeiter.getPosition());
            statment.setString(4,mitarbeiter.getTelefonnummer());
            statment.setInt(5,mitarbeiter.getId());

            int rows = statment.executeUpdate();
            return rows > 0;

        }catch(SQLException error){
            System.out.println("Bei update Mitarbeiter gibt es Problem" + error.getMessage());
            return false;
        }

    }

 */

}
