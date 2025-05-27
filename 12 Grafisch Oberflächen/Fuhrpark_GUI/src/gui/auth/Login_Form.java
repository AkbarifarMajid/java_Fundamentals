package gui.auth;

import dao.BenutzerDAO;
import gui.Main_Form;

import javax.swing.*;
import java.awt.*;

public class Login_Form extends JFrame {

    private JTextField txtBenutzername;
    private JPasswordField txtPasswort;
    private JCheckBox chkShowPassword;
    private JButton buttonLogin, btnRegister;
    private JLabel lableStatus;

    public Login_Form() {
        setTitle("Fuhrpark-SZF - Login");
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Font font = new Font("SansSerif", Font.PLAIN, 15);
        Color backgroundColor = new Color(240, 240, 255);

        JPanel login_Panel = new JPanel(new GridBagLayout());
        login_Panel.setBackground(backgroundColor);
        GridBagConstraints feld_Position  = new GridBagConstraints();
        feld_Position .insets = new Insets(8, 10, 5, 10);
        feld_Position .fill = GridBagConstraints.HORIZONTAL;
        feld_Position .anchor = GridBagConstraints.WEST;

        // ---------- Labels ----------
        JLabel labelBenutzer = new JLabel("Benutzername:");
        JLabel labelPasswort = new JLabel("Passwort:");
        JLabel[] labels = {labelBenutzer, labelPasswort};
        for (JLabel lbl : labels) {
            lbl.setFont(font);
            lbl.setForeground(Color.BLUE);
        }

        // ---------- Eingabefelder ----------
        txtBenutzername = new JTextField(20);
        txtPasswort = new JPasswordField(20);
        txtPasswort.setEchoChar('•');

        JTextField[] fields = {txtBenutzername, txtPasswort};
        for (JTextField f : fields) {
            f.setFont(font);
            f.setForeground(Color.BLUE);
        }

        // ---------- Platzierung ----------
        feld_Position .gridx = 0; feld_Position .gridy = 0;
        login_Panel.add(labelBenutzer, feld_Position );
        feld_Position .gridx = 1;
        login_Panel.add(txtBenutzername, feld_Position );

        feld_Position .gridx = 0; feld_Position .gridy = 1;
        login_Panel.add(labelPasswort, feld_Position );
        feld_Position .gridx = 1;
        login_Panel.add(txtPasswort, feld_Position );

        // ---------- Passwort anzeigen ----------
        feld_Position .gridx = 1; feld_Position .gridy = 2;
        chkShowPassword = new JCheckBox("Passwort anzeigen");
        chkShowPassword.setFont(font);
        chkShowPassword.setBackground(backgroundColor);
        chkShowPassword.setForeground(Color.BLUE);
        chkShowPassword.addActionListener(e -> {
            txtPasswort.setEchoChar(chkShowPassword.isSelected() ? (char) 0 : '•');
        });
        login_Panel.add(chkShowPassword, feld_Position );

        // ---------- Buttons mit Unicode ----------
        feld_Position .gridx = 1; feld_Position .gridy = 3;
        feld_Position .anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(backgroundColor);

        buttonLogin = new JButton("\uD83D\uDD13 Einloggen");
        btnRegister = new JButton("\u2795 Registrieren");
        buttonLogin.setFont(font);
        btnRegister.setFont(font);
        buttonLogin.setForeground(Color.BLUE);
        btnRegister.setForeground(Color.BLUE);

        buttonPanel.add(buttonLogin);
        buttonPanel.add(btnRegister);
        login_Panel.add(buttonPanel, feld_Position );

        // ---------- Statusmeldung ----------
        feld_Position .gridx = 1; feld_Position .gridy = 4;
        lableStatus = new JLabel(" ");
        lableStatus.setFont(font);
        lableStatus.setForeground(Color.RED);
        login_Panel.add(lableStatus, feld_Position );

        add(login_Panel);

        // ---------- Login-Aktion ----------
        buttonLogin.addActionListener(e -> {
            String benutzer = txtBenutzername.getText().trim();
            String passwort = new String(txtPasswort.getPassword()).trim();

            if (benutzer.isEmpty() || passwort.isEmpty()) {
                lableStatus.setText("Bitte Benutzername und Passwort eingeben.");
                return;
            }

            boolean korrekt = BenutzerDAO.bnutzer_Password_Control(benutzer, passwort);

            if (korrekt) {
                dispose();
                new Main_Form().setVisible(true);
            } else {
                lableStatus.setText("Benutzername oder Passwort ist falsch!");
            }
        });

        // ---------- Registrierung öffnen ----------
        btnRegister.addActionListener(e -> {
            new Register_Form().setVisible(true);
        });
    }
} // Ende Login_Form
