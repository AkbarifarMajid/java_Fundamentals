package gui.auth;

import dao.BenutzerDAO;
import util.IconLoader;

import javax.swing.*;
import java.awt.*;

public class Register_Form extends JFrame {

    private JTextField textVorname, textNachname, textTelefon, textEmail, textBenutzername;
    private JPasswordField textPasswort;
    private JRadioButton radio_B_Maile, radio_B_Female, radio_B_Other;
    private JButton buttonRegister;
    private JLabel lableStatus;

    public Register_Form() {
        setTitle("Registrierung - Fuhrpark");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Font font = new Font("SansSerif", Font.PLAIN, 14);
        Color backgroundColor = new Color(240, 240, 255);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(backgroundColor);
        GridBagConstraints feld_Position = new GridBagConstraints();
        feld_Position.insets = new Insets(10, 10, 5, 10);
        feld_Position.fill = GridBagConstraints.HORIZONTAL;
        feld_Position.anchor = GridBagConstraints.WEST;

        // ---------- Labels ----------
        JLabel labelVorname = new JLabel("Vorname:");
        JLabel labelNachname = new JLabel("Nachname:");
        JLabel labelTelefon = new JLabel("Telefon:");
        JLabel labelEmail = new JLabel("Email:");
        JLabel labelBenutzername = new JLabel("Benutzername:");
        JLabel labelPasswort = new JLabel("Passwort:");
        JLabel labelGeschlecht = new JLabel("Geschlecht:");

        JLabel[] labels = {labelVorname, labelNachname, labelTelefon, labelEmail, labelBenutzername, labelPasswort, labelGeschlecht};
        for (JLabel lbl : labels) {
            lbl.setFont(font);
            lbl.setForeground(Color.BLUE);
        }

        // ---------- Eingabefelder ----------
        textVorname = new JTextField(20);
        textNachname = new JTextField(20);
        textTelefon = new JTextField(20);
        textEmail = new JTextField(20);
        textBenutzername = new JTextField(20);
        textPasswort = new JPasswordField(20);

        JComponent[] fields = {textVorname, textNachname, textTelefon, textEmail, textBenutzername, textPasswort};
        for (JComponent field : fields) {
            field.setFont(font);
            field.setForeground(Color.BLUE);
        }

        // ---------- Geschlecht Panel ----------
        radio_B_Maile = new JRadioButton("Männlich");
        radio_B_Female = new JRadioButton("Weiblich");
        radio_B_Other = new JRadioButton("Andere");
        ButtonGroup geschlechtGroup = new ButtonGroup(); // Sie sind alle in einer Gruppe, also können wir nur eine auswählen.
        geschlechtGroup.add(radio_B_Maile);
        geschlechtGroup.add(radio_B_Female);
        geschlechtGroup.add(radio_B_Other);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setBackground(backgroundColor);
        JRadioButton[] radios = {radio_B_Maile, radio_B_Female, radio_B_Other};
        for (JRadioButton radio : radios) {
            radio.setFont(font);
            radio.setBackground(backgroundColor);
            genderPanel.add(radio);
        }



        // ---------- Platzierung ----------
        Component[] komponenten = {textVorname, textNachname, textTelefon, textEmail, textBenutzername, textPasswort, genderPanel};
        for (int i = 0; i < labels.length; i++) {
            feld_Position.gridx = 0; feld_Position.gridy = i; panel.add(labels[i], feld_Position);
            feld_Position.gridx = 1; panel.add(komponenten[i], feld_Position);
        }

        // ---------- Button ----------
        feld_Position.gridx = 1; feld_Position.gridy = labels.length;
        feld_Position.anchor = GridBagConstraints.CENTER;
        buttonRegister = new JButton("\u2795");
        IconLoader.styl_UnicCode_Buttons(buttonRegister, "\u2795");
        buttonRegister.setToolTipText("Jetzt registrieren");

        panel.add(buttonRegister, feld_Position);


        // ---------- Status Label ----------
        feld_Position.gridx = 1; feld_Position.gridy = labels.length + 1;
        lableStatus = new JLabel(" ");
        lableStatus.setFont(font);
        lableStatus.setForeground(Color.RED);
        panel.add(lableStatus, feld_Position);

        add(panel);

        // ---------- Action ----------
        buttonRegister.addActionListener(e -> {
            String vorname = textVorname.getText().trim();
            String nachname = textNachname.getText().trim();
            String telefon = textTelefon.getText().trim();
            String email = textEmail.getText().trim();
            String benutzername = textBenutzername.getText().trim();
            String passwort = new String(textPasswort.getPassword()).trim();
            String geschlecht = radio_B_Maile.isSelected() ? "Männlich" : radio_B_Female.isSelected() ? "Weiblich" : radio_B_Other.isSelected() ? "Andere" : "";

            if (vorname.isEmpty() || nachname.isEmpty() || telefon.isEmpty()
                    || email.isEmpty() || passwort.isEmpty() || geschlecht.isEmpty()
                    || benutzername.isEmpty()) {
                lableStatus.setText("Bitte füllen Sie alle Felder aus.");
                return;
            }

            boolean success = BenutzerDAO.neu_Benutzer_Speichen(
                    vorname, nachname, telefon, email, geschlecht, benutzername, passwort
            );

            if (success) {
                String info = "Registrierung erfolgreich!\n\n" +
                        "Vorname: " + vorname + "\n" +
                        "Nachname: " + nachname + "\n" +
                        "Benutzername: " + benutzername + "\n" +
                        "Telefon: " + telefon + "\n" +
                        "Email: " + email + "\n" +
                        "Geschlecht: " + geschlecht;

                JOptionPane.showMessageDialog(this, info, "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                lableStatus.setText("Fehler beim Speichern der Daten!");
            }
        });
    }// Ende Register_Form

}