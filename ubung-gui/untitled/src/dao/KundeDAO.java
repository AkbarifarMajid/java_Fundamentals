package dao;

import model.Kunde;
import util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * این کلاس عملیات دیتابیسی مربوط به مشتری را انجام می‌دهد.
 */
public class KundeDAO {

    /**
     * افزودن مشتری جدید به پایگاه داده
     */
    public void hinzufuegenKunde(Kunde kunde) {
       // String sql = "INSERT INTO kunde (vorname, nachname, telefon, email) VALUES (?, ?, ?, ?)";
        String sql = "insert into kunde (vorname, nachname,telefon, email) values (?,?,?,?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, kunde.getVorname());
            stmt.setString(2, kunde.getNachname());
            stmt.setString(3, kunde.getTelefon());
            stmt.setString(4, kunde.getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ خطا در افزودن مشتری به پایگاه داده.");
        }
    }
}
