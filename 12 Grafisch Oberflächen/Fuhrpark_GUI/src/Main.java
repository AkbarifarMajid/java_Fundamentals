import javax.swing.SwingUtilities;

import gui.auth.Login_Form;
import gui.Main_Form;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //new Main_Form().setVisible(true);


           new Login_Form().setVisible(true);
        });
    }
}