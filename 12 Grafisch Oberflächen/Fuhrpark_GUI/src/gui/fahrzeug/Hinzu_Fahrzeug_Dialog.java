package gui.fahrzeug;

import javax.swing.*;
import java.awt.*;
import model.*;
import service.FahrzeugService;
import util.IconLoader;

public class Hinzu_Fahrzeug_Dialog extends JDialog {

    private JTextField txtHersteller, txtModell, txtBaujahr, txtKennzeichen;
    private JComboBox<String> comboTyp;

    private JPanel specialFieldPanel;
    private JTextField txtZusatz;
    private JCheckBox chkBoolean;

    public Hinzu_Fahrzeug_Dialog(JFrame parent) {
        super(parent, "Neues Fahrzeug hinzufügen", true);
        setSize(450, 450);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        Color hintergrund = new Color(240, 240, 255);
        Font schrift = new Font("SansSerif", Font.PLAIN, 14);
        Color textFarbe = Color.BLUE;

        getContentPane().setBackground(hintergrund);
        GridBagConstraints feld_Position = new GridBagConstraints();
        feld_Position.insets = new Insets(8, 10, 5, 10);

        int y = 0;

        // Hilfsfunktion zum Hinzufügen von Label und Textfeld
        JLabel[] labels = new JLabel[4];
        JTextField[] fields = new JTextField[]{
                txtHersteller = new JTextField(15),
                txtModell = new JTextField(15),
                txtBaujahr = new JTextField(15),
                txtKennzeichen = new JTextField(15)
        };
        String[] beschriftungen = {"Hersteller:", "Modell:", "Baujahr:", "Kennzeichen:"};

        for (int i = 0; i < fields.length; i++, y++) {
            feld_Position.gridx = 0; feld_Position.gridy = y; feld_Position.anchor = GridBagConstraints.EAST;
            labels[i] = new JLabel(beschriftungen[i]);
            labels[i].setFont(schrift); labels[i].setForeground(textFarbe);
            add(labels[i], feld_Position);

            feld_Position.gridx = 1; feld_Position.anchor = GridBagConstraints.WEST;
            fields[i].setFont(schrift); fields[i].setForeground(textFarbe);
            add(fields[i], feld_Position);
        }

        // Typ Auswahl
        feld_Position.gridx = 0; feld_Position.gridy = y; feld_Position.anchor = GridBagConstraints.EAST;
        JLabel lblTyp = new JLabel("Typ:");
        lblTyp.setFont(schrift); lblTyp.setForeground(textFarbe);
        add(lblTyp, feld_Position);

        feld_Position.gridx = 1; feld_Position.anchor = GridBagConstraints.WEST;
        comboTyp = new JComboBox<>(new String[]{"PKW", "LKW", "Motorrad", "Fahrrad"});
        comboTyp.setFont(schrift);
        add(comboTyp, feld_Position);
        y++;

        // Spezialfelder Panel
        feld_Position.gridx = 0; feld_Position.gridy = y; feld_Position.gridwidth = 2;
        specialFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        specialFieldPanel.setBackground(hintergrund);
        txtZusatz = new JTextField(15); txtZusatz.setFont(schrift); txtZusatz.setForeground(textFarbe);
        chkBoolean = new JCheckBox(); chkBoolean.setBackground(hintergrund);
        specialFieldPanel.add(txtZusatz); specialFieldPanel.add(chkBoolean);
        add(specialFieldPanel, feld_Position);
        feld_Position.gridwidth = 1;
        y++;

        // Speichern Button
        feld_Position.gridx = 1; feld_Position.gridy = y;
        JButton btnSpeichern = new JButton("\u2795");
        IconLoader.styl_UnicCode_Buttons(btnSpeichern, "\u2795");
        btnSpeichern.setToolTipText("Neu Fahrzeug Speichen");
        btnSpeichern.setFont(schrift);
        add(btnSpeichern, feld_Position);

        // Aktionen
        comboTyp.addActionListener(e -> spezial_Fild_zeige());
        btnSpeichern.addActionListener(e -> fahrzeug_Speichern());

        spezial_Fild_zeige();
        setVisible(true);
    }

    private void spezial_Fild_zeige() {
        specialFieldPanel.removeAll();
        String typ = (String) comboTyp.getSelectedItem();

        switch (typ) {
            case "PKW" -> {
                specialFieldPanel.add(new JLabel("Sitzanzahl:"));
                specialFieldPanel.add(txtZusatz);
            }
            case "LKW" -> {
                specialFieldPanel.add(new JLabel("Ladevolumen:"));
                specialFieldPanel.add(txtZusatz);
            }
            case "Motorrad" -> {
                specialFieldPanel.add(new JLabel("Hubraum:"));
                specialFieldPanel.add(txtZusatz);
                specialFieldPanel.add(new JLabel("Gangschaltung:"));
                specialFieldPanel.add(chkBoolean);
            }
            case "Fahrrad" -> {
                specialFieldPanel.add(new JLabel("Hat Korb:"));
                specialFieldPanel.add(chkBoolean);
            }
        }
        specialFieldPanel.revalidate();
        specialFieldPanel.repaint();
    }
    private void fahrzeug_Speichern() {
        try {
            String typ = (String) comboTyp.getSelectedItem();
            String hersteller = txtHersteller.getText().trim();
            String modell = txtModell.getText().trim();
            int baujahr = Integer.parseInt(txtBaujahr.getText().trim());
            String kennzeichen = txtKennzeichen.getText().trim();

            Fahrzeug fahrzeug = switch (typ) {
                case "PKW" -> new PKW(hersteller, modell, baujahr, kennzeichen, Integer.parseInt(txtZusatz.getText()));
                case "LKW" -> new LKW(hersteller, modell, baujahr, kennzeichen, Double.parseDouble(txtZusatz.getText()));
                case "Motorrad" -> new Motorrad(hersteller, modell, baujahr, kennzeichen, Integer.parseInt(txtZusatz.getText()), chkBoolean.isSelected());
                case "Fahrrad" -> new Fahrrad(hersteller, modell, baujahr, kennzeichen, chkBoolean.isSelected());
                default -> null;
            };

            String fehler = FahrzeugService.validate_Fahrzeug(fahrzeug);
            if (fehler != null) {
                JOptionPane.showMessageDialog(this, fehler, "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (FahrzeugService.fahrzeug_Hinzufuegen(fahrzeug)) {
                JOptionPane.showMessageDialog(this, "Fahrzeug gespeichert.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Speichern fehlgeschlagen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fehlerhafte Eingabe: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }


}
