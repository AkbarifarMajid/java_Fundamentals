package dao;

import database.DatabaseManager;
import java.sql.*;

public class DatabaseUtils {

    // Führt ein SQL-Update (INSERT, UPDATE, DELETE) mit Parametern aus
    public static boolean executeUpdate(String sql, Object... params) {
        try (Connection fuhrpark_db_connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement connectionStatement = fuhrpark_db_connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                connectionStatement.setObject(i + 1, params[i]);
            }
            return connectionStatement.executeUpdate() > 0;
        } catch (SQLException error) {
            System.out.println("SQL-Update-Problem: " + error.getMessage());
            return false;
        }
    }


    // Führt eine vorbereitete SELECT-Abfrage mit Parametern aus (Suchen)
    public static ResultSet executePreparedSelect(String sql, Object... params) {
        try {
            Connection fuhrpark_db_connection = DatabaseManager.getMyFuhrpark_DB_Connection();
            PreparedStatement connectionStatement = fuhrpark_db_connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                connectionStatement.setObject(i + 1, params[i]);
            }
            return connectionStatement.executeQuery();
        } catch (SQLException error) {
            System.out.println("SELECT (Prepared) Fehler: " + error.getMessage());
            return null;
        }
    }

    // Führt ein INSERT mit Rückgabe des automatisch generierten Schlüssels aus
    public static int executeInsertWithGeneratedKey(String sql, Object... params) {
        try (Connection fuhrpark_db_connection = DatabaseManager.getMyFuhrpark_DB_Connection();
             PreparedStatement connectionStatement = fuhrpark_db_connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.length; i++) {
                connectionStatement.setObject(i + 1, params[i]);
            }
            connectionStatement.executeUpdate();
            try (ResultSet rs = connectionStatement.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("INSERT mit Key Fehler: " + e.getMessage());
        }
        return -1;
    }

/*
    // Führt eine einfache SELECT-Abfrage ohne Parameter aus
    public static ResultSet executeSimpleSelect(String sql) {
        try {
            Connection fuhrpark_db_connection = DatabaseManager.getMyFuhrpark_DB_Connection();
            Statement connectionStatement = fuhrpark_db_connection.createStatement();
            return connectionStatement.executeQuery(sql);
        } catch (SQLException error) {
            System.out.println("SELECT Fehler: " + error.getMessage());
            return null;
        }
    }

    // Zählt die Anzahl der Einträge aus einem SELECT COUNT(*)-Befehl
    public static int zaehleEintraege(String sql) {
        try (ResultSet rs = executeSimpleSelect(sql)) {
            if (rs != null && rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Fehler beim Zählen: " + e.getMessage());
        }
        return 0;
    }

    // Summiert Werte aus einem SELECT SUM(...) Befehl
    public static int summeEintraege(String sql) {
        try (ResultSet rs = executeSimpleSelect(sql)) {
            if (rs != null && rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Fehler beim Summieren: " + e.getMessage());
        }
        return 0;
    }

 */


}
