package gui.zuweisung;

import model.Fahrzeug;
import util.IconLoader;

import javax.swing.*;
import java.awt.*;

import service.FahrzeugService;

public class Zuweisung_Haupt_Panel extends JPanel {

    public Zuweisung_Haupt_Panel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        mainPanel.setBackground(Color.WHITE);

        // -------------------- Kilometer Panel --------------------
        JPanel kmPanel = new JPanel(new GridBagLayout());
        kmPanel.setBackground(new Color(210, 228, 243));
        kmPanel.setBorder(BorderFactory.createTitledBorder("KILOMETER HINZUFÜGEN"));

        GridBagConstraints gbcKm = new GridBagConstraints();
        gbcKm.insets = new Insets(10, 10, 10, 10);
        gbcKm.fill = GridBagConstraints.HORIZONTAL;
        gbcKm.anchor = GridBagConstraints.WEST;

        JLabel lblIdKm = new JLabel("Fahrzeug-ID:");
        JTextField txtIdKm = new JTextField(10);
        JButton btnSucheKm = new JButton();
        IconLoader.styl_UnicCode_Buttons(btnSucheKm, "\uD83D\uDD0D");

        JLabel lblStartKm = new JLabel("Start-KM:");
        JTextField txtStartKm = new JTextField(10);

        JLabel lblEndKm = new JLabel("End-KM:");
        JTextField txtEndKm = new JTextField(10);

        JButton btnAddKm = new JButton("Hinzufügen");
        IconLoader.styl_UnicCode_Buttons(btnAddKm, "\u2795");

        JLabel lblStatusKm = new JLabel(" ");
        lblStatusKm.setForeground(Color.RED);

        Component[] kmLabels = {lblIdKm, lblStartKm, lblEndKm};
        Component[] kmFields = {wrapWithSearchPanel(txtIdKm, btnSucheKm), txtStartKm, txtEndKm};

        for (int i = 0; i < kmLabels.length; i++) {
            gbcKm.gridx = 0; gbcKm.gridy = i;
            kmPanel.add(kmLabels[i], gbcKm);
            gbcKm.gridx = 1;
            kmPanel.add(kmFields[i], gbcKm);
        }

        gbcKm.gridx = 1; gbcKm.gridy = 3;
        kmPanel.add(btnAddKm, gbcKm);

        gbcKm.gridx = 1; gbcKm.gridy = 4;
        kmPanel.add(lblStatusKm, gbcKm);

        // Suche Button (KM)
        btnSucheKm.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdKm.getText().trim());
                Fahrzeug f = FahrzeugService.fahrzeug_Suchen_ID(id);
                if (f != null) {
                    txtStartKm.setText(String.valueOf(f.getKilometerstand()));
                    lblStatusKm.setText("Letzter Stand: " + f.getKilometerstand() + " KM");
                } else {
                    lblStatusKm.setText("Fahrzeug nicht gefunden!");
                }
            } catch (NumberFormatException ex) {
                lblStatusKm.setText("Ungültige ID!");
            }
        });

        // Speichern Button (KM)
        btnAddKm.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdKm.getText().trim());
                double start = Double.parseDouble(txtStartKm.getText().trim());
                double end = Double.parseDouble(txtEndKm.getText().trim());

                if (FahrzeugService.kilometer_Hinzufuegen(id, start, end)) {
                    lblStatusKm.setForeground(Color.BLUE);
                    lblStatusKm.setText("Erfolgreich gespeichert!");
                    txtEndKm.setText("");
                } else {
                    lblStatusKm.setForeground(Color.RED);
                    lblStatusKm.setText("Der Endkilometer ist klein");
                }
            } catch (NumberFormatException ex) {
                lblStatusKm.setForeground(Color.RED);
                lblStatusKm.setText("Ungültige Eingabe!");
            }
        });


        // -------------------- Tanken Panel --------------------
        JPanel tankPanel = new JPanel(new GridBagLayout());
        tankPanel.setBackground(new Color(184, 208, 230));
        tankPanel.setBorder(BorderFactory.createTitledBorder("TANKEN"));

        GridBagConstraints gbcTank = new GridBagConstraints();
        gbcTank.insets = new Insets(10, 10, 10, 10);
        gbcTank.fill = GridBagConstraints.HORIZONTAL;
        gbcTank.anchor = GridBagConstraints.WEST;

        JLabel lblIdTank = new JLabel("Fahrzeug-ID:");
        JTextField txtIdTank = new JTextField(10);
        JButton btnSucheTank = new JButton();
        IconLoader.styl_UnicCode_Buttons(btnSucheTank, "\uD83D\uDD0D");

        JLabel lblBisher = new JLabel("Bisher (Liter):");
        JTextField txtBisher = new JTextField(10);
        txtBisher.setEditable(false);

        JLabel lblNeu = new JLabel("Neu (Liter):");
        JTextField txtNeu = new JTextField(10);

        JButton btnAddTank = new JButton("Hinzufügen");
        IconLoader.styl_UnicCode_Buttons(btnAddTank, "\u2795");

        JLabel lblStatusTank = new JLabel(" ");
        lblStatusTank.setForeground(Color.RED);

        Component[] tankLabels = {lblIdTank, lblBisher, lblNeu};
        Component[] tankFields = {
                wrapWithSearchPanel(txtIdTank, btnSucheTank),
                txtBisher,
                txtNeu
        };

        for (int i = 0; i < tankLabels.length; i++) {
            gbcTank.gridx = 0; gbcTank.gridy = i;
            tankPanel.add(tankLabels[i], gbcTank);
            gbcTank.gridx = 1;
            tankPanel.add(tankFields[i], gbcTank);
        }

        gbcTank.gridx = 1; gbcTank.gridy = 3;
        tankPanel.add(btnAddTank, gbcTank);

        gbcTank.gridx = 1; gbcTank.gridy = 4;
        tankPanel.add(lblStatusTank, gbcTank);

        // Suche Button (Tanken)
        btnSucheTank.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdTank.getText().trim());
                double liter = FahrzeugService.berechne_Tanken(id);
                txtBisher.setText(String.valueOf(liter));
                lblStatusTank.setText("Gesamt: " + liter + " Liter");
            } catch (NumberFormatException ex) {
                lblStatusTank.setText("Ungültige ID!");
            }
        });

        // Speichern Button (Tanken)
        btnAddTank.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdTank.getText().trim());
                double liter = Double.parseDouble(txtNeu.getText().trim());
                if (FahrzeugService.tanken_Hinzufuegen(id, liter)) {
                    double gesamt = FahrzeugService.berechne_Tanken(id);
                    txtBisher.setText(String.valueOf(gesamt));
                    lblStatusTank.setForeground(Color.BLUE);
                    lblStatusTank.setText("Erfolgreich gespeichert!");
                    txtNeu.setText("");
                } else {
                    lblStatusTank.setForeground(Color.RED);
                    lblStatusTank.setText("Fehler beim Speichern!");
                }
            } catch (NumberFormatException ex) {
                lblStatusTank.setForeground(Color.RED);
                lblStatusTank.setText("Ungültige Eingabe!");
            }
        });

        mainPanel.add(kmPanel);
        mainPanel.add(tankPanel);
        add(mainPanel, BorderLayout.CENTER);
    }

    // Hilfsmethode: ID-Feld + Suche-Button
    private JPanel wrapWithSearchPanel(JTextField field, JButton button) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBackground(null);
        panel.add(field);
        panel.add(button);
        return panel;
    }
}
