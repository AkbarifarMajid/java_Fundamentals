package database;

import java.sql.*;

public class DatabaseUtils {

    // Führt INSERT-, UPDATE- oder DELETE-Befehle ohne Rückgabewert aus
    public static void executeUpdate(String sql, Object... params) {
        try (Connection connect_Update = DatabaseManager.myFuhrpark_DB_Connection();
             PreparedStatement statement_Update = connect_Update.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                statement_Update.setObject(i + 1, params[i]);
            }

            statement_Update.executeUpdate();

        } catch (SQLException error) {
            System.out.println("SQL-Update-Problem: " + error.getMessage());
        }
    }

    // Führt einen einfachen SELECT-Befehl ohne Parameter aus und gibt ein ResultSet zurück
    public static ResultSet executeSimpleSelect(String sql) {
        try {
            Connection connectSimple_Select = DatabaseManager.myFuhrpark_DB_Connection();
            Statement stmtSimple_Select = connectSimple_Select.createStatement();
            return stmtSimple_Select.executeQuery(sql);

        } catch (SQLException error) {
            System.out.println("beim SELECT gibe es Problem: " + error.getMessage());
            return null;
        }
    }

    // Führt einen SELECT mit PreparedStatement und Parametern aus und gibt ein ResultSet zurück
    public static ResultSet executePreparedSelect(String sql, Object... params) {
        try {
            Connection connParameterSelect = DatabaseManager.myFuhrpark_DB_Connection();
            PreparedStatement stmtParameterSelect = connParameterSelect.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmtParameterSelect.setObject(i + 1, params[i]);
            }

            return stmtParameterSelect.executeQuery();

        } catch (SQLException error) {
            System.out.println("beim SELECT (Prepared) gibt es Problem: " + error.getMessage());
            return null;
        }
    }

    // Führt einen INSERT aus und gibt den automatisch generierten Primärschlüssel zurück
    public static int executeInsertWithGeneratedKey(String sql, Object... params) {
        try (Connection connInsert_Key = DatabaseManager.myFuhrpark_DB_Connection();
             PreparedStatement stmtInsert_Key = connInsert_Key.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < params.length; i++) {
                stmtInsert_Key.setObject(i + 1, params[i]);
            }

            stmtInsert_Key.executeUpdate();

            try (ResultSet rs = stmtInsert_Key.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException error) {
            System.out.println("beim INSERT mit Rückgabe-ID gibt es problem: " + error.getMessage());
        }

        return -1;
    }
}
