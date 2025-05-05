import view.HauptmenueView;

import database.DatabaseManager;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        //new HauptmenueView().starten();
        Connection con = DatabaseManager.getConnection();

        if (con != null) {
            System.out.println("✅ Verbindung zur Datenbank ist erfolgreich!");
        } else {
            System.out.println("❌ Verbindung fehlgeschlagen!");
        }

        // Verbindung am Ende schließen (optional)
        DatabaseManager.verbindungSchliessen();
    }
}
