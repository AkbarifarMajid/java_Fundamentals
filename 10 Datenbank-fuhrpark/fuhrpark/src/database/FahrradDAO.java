package database;

import model.Fahrrad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FahrradDAO {

    public static void einfuegen(Fahrrad fahrrad) {
        String sql = "INSERT INTO fahrrad (fahrzeug_id, hat_korb) VALUES (?, ?)";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, fahrrad.getId());
            statement.setBoolean(2, fahrrad.isHatKorb());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("beim Einfügen in Fahrrad-Tabelle gibt es Problem: " + e.getMessage());
        }
    }


    public static boolean getHatKorb(int fahrzeugId) {
        String sql = "SELECT hat_korb FROM fahrrad WHERE fahrzeug_id = ?";
        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, fahrzeugId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) return rs.getBoolean("hat_korb");
        } catch (SQLException e) {
            System.out.println("beim Laden von HatKorb gibt es Problem: " + e.getMessage());
        }
        return false;
    }



    public static void berabeiten(Fahrrad fahrrad) {
        String sql =  "update fahrrad set hat_korb = ? where fahrzeug_id=?";

        try (Connection connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, fahrrad.isHatKorb());
            statement.setInt(2, fahrrad.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("beim Update in Fahrrad-Tabelle gibe es Problem: " + e.getMessage());
        }
    }


}
