package dao;


import java.sql.ResultSet;
import java.sql.SQLException;


public class TankenDAO {

    // Neu Tanken Hinzufügen
    public static boolean tanken_Hinzufuegen(int fahrzeugId, double liter) {
        String insertSQL = "INSERT INTO tanken_log (fahrzeug_id, liter, datum) VALUES (?, ?, ?)";
        boolean result = DatabaseUtils.executeUpdate(insertSQL, fahrzeugId, liter,
                java.sql.Date.valueOf(java.time.LocalDate.now())
        );

        if (result) {
            // Akualiziren fahrzeug
            FahrzeugDAO.kraftstoff_Aktualisieren(fahrzeugId, liter);
        } else {
            System.out.println("beim Einfügen in tanken_log gibt es Problem");
        }

        return result;
    } // tanken_Hinzufuegen

    // Gesamte Tanken Berechnen
    public static double gesamt_Tanken(int fahrzeugId) {
        String sql_Gesamt = "SELECT SUM(liter) AS gesamt FROM tanken_log WHERE fahrzeug_id = ?";
        try (ResultSet resultSet = DatabaseUtils.executePreparedSelect(sql_Gesamt, fahrzeugId)) {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getDouble("gesamt");
            }
        } catch (SQLException error) {
            System.out.println("beim Berechnen der Gesamtmenge gibt es Problem: " + error.getMessage());
        }
        return 0.0;
    }// End gesamt_Tanken


}
