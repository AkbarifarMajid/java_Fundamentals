package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;

// Datenbankoperationen für alle Fahrzeugtypen
public class FahrzeugDAO {

    // Fügt ein Fahrzeug in die Haupttabelle ein
    public static void einfuegen(Fahrzeug fahrzeug, String typ) {
        String sql = "INSERT INTO fahrzeug (typ_Fahrzeug, hersteller_Fahrzeug, modell_Fahrzeug, baujahr_Fahrzeug) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, typ);
            statement.setString(2, fahrzeug.getHersteller());
            statement.setString(3, fahrzeug.getModell());
            statement.setInt(4, fahrzeug.getBaujahr());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                fahrzeug.setId(result.getInt(1));
            }

            System.out.println("Fahrzeug gespeichert mit (ID: " + fahrzeug.getId() + ")");

            // spezifische Daten in die jeweilige Tabelle schreiben
            switch (typ) {
                case "PKW" -> PkwDAO.einfuegen((PKW) fahrzeug);
                case "LKW" -> LkwDAO.einfuegen((LKW) fahrzeug);
                case "Motorrad" -> MotorradDAO.einfuegen((Motorrad) fahrzeug);
                case "Fahrrad" -> FahrradDAO.einfuegen((Fahrrad) fahrzeug);
            }

        } catch (SQLException e) {
            System.out.println("beim Einfügen des Fahrzeugs gibt es Problem: " + e.getMessage());
        }
    }

    // Lädt alle Fahrzeuge aus der Haupttabelle
    public static ArrayList<Fahrzeug> ladenAlle() {
        ArrayList<Fahrzeug> liste = new ArrayList<>();
        String sql = "SELECT * FROM fahrzeug";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                String typ = result.getString("typ_Fahrzeug");
                Fahrzeug fahrzeug;

                String hersteller = result.getString("hersteller_Fahrzeug");
                String modell = result.getString("modell_Fahrzeug");
                int baujahr = result.getInt("baujahr_Fahrzeug");
                double kilometerstand = result.getDouble("kilometer_Fahrzeug");
                double kraftstoff = result.getDouble("kraftstoff_Fahrzeug");
                String standort = result.getString("standort_Fahrzeug");

                switch (typ) {
                    case "PKW" -> fahrzeug = new PKW(hersteller, modell, baujahr, 0); // Platzhalterwert
                    case "LKW" -> fahrzeug = new LKW(hersteller, modell, baujahr, 0.0);
                    case "Motorrad" -> fahrzeug = new Motorrad(hersteller, modell, baujahr, 0, false);
                    case "Fahrrad" -> fahrzeug = new Fahrrad(hersteller, modell, baujahr, false);
                    default -> {
                        System.out.println("Falsche Type haben Sie: " + typ);
                        continue;
                    }
                }

                fahrzeug.setId(result.getInt("id_Fahrzeug"));
                fahrzeug.setKilometerstand(kilometerstand);
                fahrzeug.setKraftstoff(kraftstoff);
                fahrzeug.setStandort(standort);

                liste.add(fahrzeug);
            }

        } catch (SQLException e) {
            System.out.println("beim Laden gibt es fehler: " + e.getMessage());
        }

        return liste;
    }

    // Fahrzeug nach ID suchen
    public static Fahrzeug findeNachId(int id) {
        String sql_selrct = "SELECT * FROM fahrzeug WHERE id_Fahrzeug = ?";

        try (Connection connect_Database = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connect_Database.prepareStatement(sql_selrct)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String typ = resultSet.getString("typ_Fahrzeug");
                String hersteller = resultSet.getString("hersteller_Fahrzeug");
                String modell = resultSet.getString("modell_Fahrzeug");
                int baujahr = resultSet.getInt("baujahr_Fahrzeug");
                double kilometerstand = resultSet.getDouble("kilometer_Fahrzeug");
                double krafstoff = resultSet.getDouble("kraftstoff_Fahrzeug");
                String standort = resultSet.getString("standort_Fahrzeug");
                int besitzerId = resultSet.getInt("besitzer_id");

                String wartungtermin = resultSet.getString("wartungstermine");

               // rs.close();

                Fahrzeug farzeug = null;
                switch (typ) {
                    case "PKW": {
                        String sql_pkw = "SELECT * FROM pkw WHERE fahrzeug_id = ?";
                        PreparedStatement pstmt1 = connect_Database.prepareStatement(sql_pkw);
                        pstmt1.setInt(1, id);
                        ResultSet result_pkw = pstmt1.executeQuery();
                        if (result_pkw.next()) {
                            int sitzanzahl = result_pkw.getInt("sitzanzahl");
                            farzeug = new PKW(id, typ ,hersteller, modell, baujahr, sitzanzahl);
                        }
                        break;
                    }
                    case "LKW": {
                        String sql_lkw = "SELECT * FROM lkw WHERE fahrzeug_id = ?";
                        PreparedStatement pstmt1 = connect_Database.prepareStatement(sql_lkw);
                        pstmt1.setInt(1, id);
                        ResultSet result_lkw = pstmt1.executeQuery();
                        if (result_lkw.next()) {
                            int ladegewicht = result_lkw.getInt("ladegewicht");
                            farzeug = new LKW(id, typ,hersteller, modell, baujahr, ladegewicht);
                        }
                        break;
                    }
                    case "Motorrad": {
                        String sql_Motorad = "SELECT * FROM motorrad WHERE fahrzeug_id = ?";
                        PreparedStatement pstmt1 = connect_Database.prepareStatement(sql_Motorad);
                        pstmt1.setInt(1, id);
                        ResultSet result_Motorad = pstmt1.executeQuery();
                        if (result_Motorad.next()) {
                            int hubraum = result_Motorad.getInt("hubraum");
                            boolean gangschaltung = result_Motorad.getBoolean("gangschaltung");
                            farzeug = new Motorrad(id, typ,hersteller, modell, baujahr, hubraum, gangschaltung);
                        }
                        break;
                    }
                    case "Fahrrad": {
                        String sql_Fahrrad = "SELECT * FROM fahrrad WHERE fahrzeug_id = ?";
                        PreparedStatement pstmt1 = connect_Database.prepareStatement(sql_Fahrrad);
                        pstmt1.setInt(1, id);
                        ResultSet result_Fahrrad = pstmt1.executeQuery();
                        if (result_Fahrrad.next()) {
                            boolean hat_korb = result_Fahrrad.getBoolean("hat_korb");
                            farzeug = new Fahrrad(id, typ,hersteller, modell, baujahr, hat_korb);
                        }
                        break;
                    }
                }

                if (farzeug != null) {
                    farzeug.setId(resultSet.getInt("id_Fahrzeug"));
                    farzeug.setKilometerstand(kilometerstand);
                    farzeug.setKraftstoff(krafstoff);
                    farzeug.setStandort(standort);
                    Mitarbeiter besitzer = MitarbeiterDAO.findeNachId(besitzerId);
                    farzeug.setBesitzer(besitzer);

                    farzeug.setwartungstermine(wartungtermin);

                }

                return farzeug;
            }


        } catch (SQLException e) {
            System.out.println("bei Suche gibt es Problem : " + e.getMessage());
        }

        return null;
    }

    // Fahrzeug löschen
    public static boolean loeschen(int id) {
        String sql = "DELETE FROM fahrzeug WHERE id_Fahrzeug = ?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int betroffen = statement.executeUpdate();
            return betroffen > 0;

        } catch (SQLException e) {
            System.out.println("beim Löschen des Fahrzeugs gibt es Problem: " + e.getMessage());
            return false;
        }
    }

    // Aktualisiert den Besitzer eines Fahrzeugs
    public static boolean besitzerAktualisieren(int fahrzeugId, int mitarbeiterId) {
        String sql = "UPDATE fahrzeug SET besitzer_id = ? WHERE id_Fahrzeug = ?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, mitarbeiterId);
            statement.setInt(2, fahrzeugId);

            int betroffen = statement.executeUpdate();
            return betroffen > 0;

        } catch (SQLException e) {
            System.out.println("beim Aktualisieren des Besitzers gibt es Problem: " + e.getMessage());
            return false;
        }
    }

    // Aktualisiert den Kilometerstand eines Fahrzeugs
    public static boolean kilometerAktualisieren(int id, double neuerKmStand) {
        String sql = "UPDATE fahrzeug SET kilometer_Fahrzeug = ? WHERE id_Fahrzeug = ?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, neuerKmStand);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("beim Aktualisieren des Kilometerstands gibt es problem: " + e.getMessage());
            return false;
        }
    }

    // Fügt einen Wartungstermin als String hinzu
    public static boolean wartungAktualisieren(int id, String datum) {
        String sql = "UPDATE fahrzeug SET wartungstermine = ? WHERE id_Fahrzeug = ?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, datum);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println(" beim Aktualisieren des wartungstermine gibt es Problem: " + e.getMessage());
            return false;
        }
    }

    // Fahrzeug in die Datenbank Bearbeiten
    public static void berabeiten(Fahrzeug farzeug) {
        String sql = "UPDATE fahrzeug set hersteller_Fahrzeug=?, modell_Fahrzeug=?, baujahr_Fahrzeug=? where id_Fahrzeug =?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, farzeug.getHersteller());
            statement.setString(2, farzeug.getModell());
            statement.setInt(3, farzeug.getBaujahr());
            statement.setInt(4, farzeug.getId());

            statement.executeUpdate();

            ResultSet result_bearbeiten = statement.getGeneratedKeys();
            if (result_bearbeiten.next()) {
                farzeug.setId(result_bearbeiten.getInt(1));
            }

            System.out.println("Fahrzeug gespeichert (ID: " + farzeug.getId() + ")");

            // spezifische Daten in die jeweilige Tabelle schreiben zum Update
            switch (farzeug.getTyp_Fahrzeug()) {
                case "PKW"->PkwDAO.berabeiten((PKW) farzeug);
                case "LKW" -> LkwDAO.berabeiten((LKW) farzeug);
                case "Motorrad" -> MotorradDAO.berabeiten((Motorrad) farzeug);
                case "Fahrrad" -> FahrradDAO.berabeiten((Fahrrad) farzeug);
            }

        } catch (SQLException e) {
            System.out.println(" beim Update des Fahrzeugs gibt es Problem: " + e.getMessage());
        }
    }

    // Aktualisiert den Kraftstoffstand
    public static boolean kraftstoffAktualisieren(int id, double kraftstoff) {
        String sql = "UPDATE fahrzeug SET kraftstoff_Fahrzeug = ? WHERE id_Fahrzeug = ?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, kraftstoff);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Aktualisieren des Kraftstoffs gibt es Problem: " + e.getMessage());
            return false;
        }
    }


}
