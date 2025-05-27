package gui.mitarbeiter;

import model.Mitarbeiter;
import service.MitarbeiterService;

import javax.swing.*;
import java.awt.*;

public class Bearbeiten_Mitarbeiter_Dialog extends JDialog {

    //Methode zum Anzeigen des Mitarbeiter-Bearbeitungsformulars.
    public static void bearbeiten_Form_Zeigen(Component parentComponent, Mitarbeiter mitarbeiter, Runnable nachBearbeitung) {
        JDialog dialog = new JDialog((Window) SwingUtilities.getWindowAncestor(parentComponent), "Mitarbeiter bearbeiten", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(500, 380);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(parentComponent);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font schrift = new Font("Segoe UI Emoji", Font.PLAIN, 14);
        Color textFarbe = new Color(147, 4, 30);
        Color feldHintergrund = new Color(230, 240, 255);

        JTextField vornameFeld = new JTextField(mitarbeiter.getVorname(), 20);
        JTextField nachnameFeld = new JTextField(mitarbeiter.getNachname(), 20);
        JComboBox<String> abteilungBox = new JComboBox<>(new String[]{"Fuhrpark", "Verwaltung", "Buchhaltung", "Marketing", "IT"});
        abteilungBox.setSelectedItem(mitarbeiter.getAbteilung());
        JTextField telefonFeld = new JTextField(mitarbeiter.getTelefon(), 20);
        JTextField emailFeld = new JTextField(mitarbeiter.getEmail(), 20);
        JTextField datumFeld = new JTextField(mitarbeiter.getEinstellungsdatum(), 20);

        JTextField[] felder = {vornameFeld, nachnameFeld, telefonFeld, emailFeld, datumFeld};
        for (JTextField feld : felder) {
            feld.setFont(schrift);
            feld.setForeground(textFarbe);
            feld.setBackground(feldHintergrund);
        }
        abteilungBox.setFont(schrift);
        abteilungBox.setForeground(textFarbe);

        String[] labels = {"Vorname:", "Nachname:", "Abteilung:", "Telefon:", "Email:", "Einstellungsdatum:"};
        Component[] components = {vornameFeld, nachnameFeld, abteilungBox, telefonFeld, emailFeld, datumFeld};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            dialog.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            dialog.add(components[i], gbc);
        }

        JButton button_Speichern = new JButton("\u2714");
        button_Speichern.setToolTipText("Änderungen speichern");
        button_Speichern.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 22));
        button_Speichern.setForeground(new Color(0, 128, 0));
        button_Speichern.setFocusPainted(false);
        button_Speichern.setContentAreaFilled(false);
        button_Speichern.setBorderPainted(false);

        gbc.gridx = 0; gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(button_Speichern, gbc);

        button_Speichern.addActionListener(e -> {
            mitarbeiter.setVorname(vornameFeld.getText().trim());
            mitarbeiter.setNachname(nachnameFeld.getText().trim());
            mitarbeiter.setAbteilung((String) abteilungBox.getSelectedItem());
            mitarbeiter.setTelefon(telefonFeld.getText().trim());
            mitarbeiter.setEmail(emailFeld.getText().trim());
            mitarbeiter.setEinstellungsdatum(datumFeld.getText().trim());

            boolean result_Bearbeiten = MitarbeiterService.mitarbeiter_Bearbeiten(mitarbeiter);
            if (result_Bearbeiten) {
                JOptionPane.showMessageDialog(dialog, "Bearbeitung abgeschlossen.");
                dialog.dispose(); // Form wird zu
                if (nachBearbeitung != null)

                    nachBearbeitung.run();
            } else {
                JOptionPane.showMessageDialog(dialog, "beim Speichern gibt es Problem!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }
    // End bearbeiten_FormZeigen



}
