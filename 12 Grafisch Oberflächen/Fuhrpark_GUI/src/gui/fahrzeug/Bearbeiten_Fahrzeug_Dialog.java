package gui.fahrzeug;

import model.*;
import service.FahrzeugService;

import javax.swing.*;
import java.awt.*;

public class Bearbeiten_Fahrzeug_Dialog extends JDialog {

    private JTextField txtHersteller, txtModell, txtBaujahr, txtKennzeichen;
    private JTextField txtSpezial;
    private JCheckBox chkOption;

    private JPanel spezialPanel;

    public Bearbeiten_Fahrzeug_Dialog(JFrame parent, Fahrzeug fahrzeug) {
        super(parent, "Fahrzeug bearbeiten", true);
        setSize(450, 450);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        Color bg = new Color(240, 240, 255);
        Font font = new Font("SansSerif", Font.PLAIN, 14);
        setBackground(bg);
        getContentPane().setBackground(bg);

        GridBagConstraints feld_Position = new GridBagConstraints();
        feld_Position.insets = new Insets(8, 10, 5, 10);
        feld_Position.anchor = GridBagConstraints.WEST;

        // Hersteller
        feld_Position.gridx = 0; feld_Position.gridy = 0;
        add(label("Hersteller:", font), feld_Position);
        feld_Position.gridx = 1;
        txtHersteller = textField(fahrzeug.getHersteller(), font);
        add(txtHersteller, feld_Position);

        // Modell
        feld_Position.gridx = 0; feld_Position.gridy++;
        add(label("Modell:", font), feld_Position);
        feld_Position.gridx = 1;
        txtModell = textField(fahrzeug.getModell(), font);
        add(txtModell, feld_Position);

        // Baujahr
        feld_Position.gridx = 0; feld_Position.gridy++;
        add(label("Baujahr:", font), feld_Position);
        feld_Position.gridx = 1;
        txtBaujahr = textField(String.valueOf(fahrzeug.getBaujahr()), font);
        add(txtBaujahr, feld_Position);

        // Kennzeichen
        feld_Position.gridx = 0; feld_Position.gridy++;
        add(label("Kennzeichen:", font), feld_Position);
        feld_Position.gridx = 1;
        txtKennzeichen = textField(fahrzeug.getKennzeichen(), font);
        add(txtKennzeichen, feld_Position);

        // Spezialfelder
        feld_Position.gridx = 0; feld_Position.gridy++; feld_Position.gridwidth = 2;
        spezialPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        spezialPanel.setBackground(bg);
        txtSpezial = textField("", font);
        chkOption = new JCheckBox();
        chkOption.setBackground(bg);
        spezialPanel.add(new JLabel()); // Platzhalter
        add(spezialPanel, feld_Position);

        // Button Speichern
        feld_Position.gridx = 1; feld_Position.gridy++; feld_Position.gridwidth = 1;
        JButton btnSave = new JButton("Speichern");
        btnSave.setFont(font);
        add(btnSave, feld_Position);

        zeige_Spezial_felder(fahrzeug);

        btnSave.addActionListener(e -> {
            try {
                fahrzeug.setHersteller(txtHersteller.getText());
                fahrzeug.setModell(txtModell.getText());
                fahrzeug.setBaujahr(Integer.parseInt(txtBaujahr.getText()));
                fahrzeug.setKennzeichen(txtKennzeichen.getText());

                if (fahrzeug instanceof PKW p) p.setSitzanzahl(Integer.parseInt(txtSpezial.getText()));
                else if (fahrzeug instanceof LKW l) l.setLadevolumen(Double.parseDouble(txtSpezial.getText()));
                else if (fahrzeug instanceof Motorrad m) {
                    m.setHubraum(Integer.parseInt(txtSpezial.getText()));
                    m.setGangschaltung(chkOption.isSelected());
                }
                else if (fahrzeug instanceof Fahrrad f) f.setHatKorb(chkOption.isSelected());

                boolean ok = FahrzeugService.fahrzeug_Bearbeiten(fahrzeug);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Erfolgreich gespeichert.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Fehler beim Speichern.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Fehler: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    private void zeige_Spezial_felder(Fahrzeug f) {
        spezialPanel.removeAll();
        spezialPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        if (f instanceof PKW p) {
            spezialPanel.add(new JLabel("Sitzanzahl:"));
            txtSpezial.setText(String.valueOf(p.getSitzanzahl()));
            spezialPanel.add(txtSpezial);
        } else if (f instanceof LKW l) {
            spezialPanel.add(new JLabel("Ladevolumen:"));
            txtSpezial.setText(String.valueOf(l.getLadevolumen()));
            spezialPanel.add(txtSpezial);
        } else if (f instanceof Motorrad m) {
            spezialPanel.add(new JLabel("Hubraum:"));
            txtSpezial.setText(String.valueOf(m.getHubraum()));
            spezialPanel.add(txtSpezial);

            spezialPanel.add(new JLabel("Gangschaltung:"));
            chkOption.setSelected(m.getGangschaltung());
            spezialPanel.add(chkOption);
        } else if (f instanceof Fahrrad f1) {
            spezialPanel.add(new JLabel("Hat Korb:"));
            chkOption.setSelected(f1.isHatKorb());
            spezialPanel.add(chkOption);
        }

        spezialPanel.revalidate();
        spezialPanel.repaint();
    }

    private JLabel label(String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        lbl.setForeground(Color.BLUE);
        return lbl;
    }

    private JTextField textField(String value, Font font) {
        JTextField txt = new JTextField(value, 15);
        txt.setFont(font);
        txt.setForeground(Color.BLUE);
        return txt;
    }
}