import util.DatabaseManager;
import java.sql.Connection;

import view.KundeForm;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseManager.getConnection();
            if (conn != null) {
                System.out.println("✅ اتصال موفق به پایگاه داده!");
            }
        } catch (Exception e) {
            System.err.println("❌ خطا در اتصال: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            new KundeForm().setVisible(true);
        });

    }


}
